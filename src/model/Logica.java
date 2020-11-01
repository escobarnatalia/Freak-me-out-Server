package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import processing.core.PApplet;
import servidor.Main;

public class Logica {
	private PApplet app;
	public ArrayList<Orbe> orbeArray;
	

	Date startDate;
	int tiempoLimite;
	int p1score;
	int p2score;
	
	
	private Coord coordp1;
	private Coord coordp2;

	public Logica(PApplet app) {
		this.app = app;

		orbeArray = new ArrayList<Orbe>();
		startDate = new Date();

		tiempoLimite = 120;
		
		p1score=0;
		p2score=0;
		
	
		

		Thread hilo = new Thread(() -> {
			int cont = 0;
			while (cont < 20) {

				float posX = app.random(404, 820);
				float posY = app.random(437, 550);
				boolean isBlue = true;

				if (cont % 2 == 0) {
					isBlue = false;
				}

				orbeArray.add(new Orbe(posX, posY, app, isBlue));
				cont++;
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		hilo.start();

	}


	public int tiempo() {

		Date endDate = new Date();

		int numSeconds = (int) ((endDate.getTime() - startDate.getTime()) / 1000);
		int tiempoRestante = tiempoLimite - numSeconds;
		//System.out.println("time: " + tiempoRestante);
		return tiempoRestante;

	}
	
	
	public ArrayList<Orbe> getOrbeArray() {
		return orbeArray;
	}

	public void setOrbeArray(ArrayList<Orbe> orbeArray) {
		this.orbeArray = orbeArray;
	}

}
