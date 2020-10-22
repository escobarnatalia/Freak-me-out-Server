package servidor;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
	 private ServerSocket server;
	 private ArrayList <Session> sessions;
	 
	 
	 
	 
	 
   
	    public void setObserver(Main observer) {
	    	this.observer = observer;
	    }
	    
	    public Main getObserver() {
	    	return observer;
	    }
	
	
	
	@Override
	public void run() {
		try {
			
			sessions = new ArrayList<Session>();
			
			
			// 1.esperando conexion, saludar
			server = new ServerSocket(5000);
			
			
			while(true) {
			System.out.println("Esperando conexion");
			Socket socket = server.accept();
			Session session = new Session (socket);
			session.setObserver(observer);
			session.start();
			sessions.add(session);
			// 3.cliente y servidor conectados
			System.out.println("Cliente conectado");
			}
			

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Session> getSessions() {
		// TODO Auto-generated method stub
		return this.sessions;
		
	}

	
}
