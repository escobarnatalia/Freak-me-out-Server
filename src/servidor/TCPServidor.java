package servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

import model.Coord;
import model.Generic;


class TCPServidor extends Thread {

	// Objeto unico que se va a usar en todas las demás sesiones
	protected static TCPServidor instanciaUnica;

	// Constructor inaccesible por medios normales
	private TCPServidor() {}

	protected static TCPServidor getInstance() {
		if (instanciaUnica == null) {
			instanciaUnica = new TCPServidor();
			instanciaUnica.start();
		}
		return instanciaUnica;
	}
	
	 private Main observer;
	    
	    public void setObserver(Main observer) {
	    	this.observer = observer;
	    }
	    
	    public Main getObserver() {
	    	return observer;
	    }
	
	
	private Socket socket;
	private ServerSocket server;
	private BufferedReader reader;
	private BufferedWriter writer;

	@Override
	public void run() {
		try {
			// 1.esperando conexion, saludar
			server = new ServerSocket(5000);
			System.out.println("Esperando conexion");
			socket = server.accept();
			// 3.cliente y servidor conectados
			System.out.println("Cliente conectado");

			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			reader = new BufferedReader(isr);

			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			writer = new BufferedWriter(osw);

			while (true) {
				System.out.println("Esperando...");
				String line = reader.readLine();
				System.out.println("Recibido: " + line);

				Gson gson = new Gson();
				Generic generic = gson.fromJson(line,Generic.class);
				
				
				switch(generic.getType()) {
				case "Coord":
					Coord coordenada = gson.fromJson(line, Coord.class);
					float posx=coordenada.getX();
					float posy=coordenada.getY();
					observer.SetCoord(posx,posy);
					System.out.println(posx);
					System.out.println(posy);
					
					break;
				}
				
				
				
				

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void SendMessage(String msg) {
		new Thread(() -> {
			try {
				writer.write(msg + "\n");
				writer.flush();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}).start();
		{

		}
	}
}
