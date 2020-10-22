package model;

import java.util.ArrayList;
import java.util.LinkedList;

import processing.core.PApplet;

public class Logica {
	private PApplet app;
	private ArrayList<Orbe> orbeArray;

	// private LinkedList<Orbe> orbeList;

	public Logica(PApplet app) {
		this.app = app;

		orbeArray = new ArrayList<Orbe>();

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
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		hilo.start();

	}

	public void pintarLogica() {

		for (int i = 0; i < orbeArray.size(); i++) {
			Orbe orbecito = orbeArray.get(i);
			orbecito.pintarOrbe();
		}

	}

	public void arrastrar() {

		for (int i = 0; i < orbeArray.size(); i++) {
			Orbe orbecito = orbeArray.get(i);
			
			
			if (PApplet.dist(app.mouseX, app.mouseY, orbecito.getPosX(), orbecito.getPosY()) < 25) {
				orbecito.mover(app.mouseX, app.mouseY);
				eliminarOrbe(orbecito);
				return;
			}

		}

	}

	public void eliminarOrbe(Orbe orbecito) {

	
		//amarillo
		if (app.mouseX > 893 & app.mouseY > 477 & app.mouseX < 1000 & app.mouseY < 550 & orbecito.isBlue() == false) {
			orbeArray.remove(orbecito);
		}
		//azul
		if (app.mouseX > 220 & app.mouseY > 477 & app.mouseX < 319 & app.mouseY <550 & orbecito.isBlue() == true) { 
			orbeArray.remove(orbecito);
		}

	}

}
