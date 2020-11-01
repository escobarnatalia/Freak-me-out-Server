package servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.UUID;

import com.google.gson.Gson;

import model.Coord;
import model.Generic;
import model.Grab;


public class Session extends Thread {

	private Coord coord;
	private Grab grab;
	private String id;
	private BufferedWriter writer;
	private Socket socket;
	private Main observer;
	
	public Session(Socket socket) {
		this.socket = socket;
		this.id = UUID.randomUUID().toString();
	
		
		
		

	}

	@Override
	public void run() {
		try {
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			writer = new BufferedWriter(new OutputStreamWriter(os));
			BufferedReader reader= new BufferedReader(new InputStreamReader(is));
			

			while (true) {
				System.out.println("Esperando...");
				String line = reader.readLine();
				//System.out.println("Recibido:" + line);
				
	
				Gson gson = new Gson();
				Generic generic = gson.fromJson(line, Generic.class);
				
		
				switch(generic.getType()) {
				
				case "Coord": 
				Coord coord = gson.fromJson(line, Coord.class);
				float posx = coord.getX();
				float posy = coord.getY();				
				observer.SetCoord(posx,posy);
				
				break;
				
				case "Grab":
				Grab grab = gson.fromJson(line, Grab.class);
				int gg= grab.getG();
				observer.SetGrab(gg);
				
				
				}
				
				
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
	
	}

	public void setObserver(Main observer) {
		this.observer = observer;
	}

	


	
}
