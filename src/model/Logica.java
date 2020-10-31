package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import processing.core.PApplet;

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
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		hilo.start();

	}

	public int getP1score() {
		return p1score;
	}

	public void setP1score(int p1score) {
		this.p1score = p1score;
	}

	public int getP2score() {
		return p2score;
	}

	public void setP2score(int p2score) {
		this.p2score = p2score;
	}

	public void pintarLogica() {

		for (int i = 0; i < orbeArray.size(); i++) {
			Orbe orbecito = orbeArray.get(i);
			orbecito.pintarOrbe();
		}

	}

	public void arrastrar() {

		for (int i = 0; i < orbeArray.size(); i++) {
			System.out.println("iiiiiiiiiiiiiiii"+i);
			Orbe orbecito = orbeArray.get(i);

			if (PApplet.dist(coordp1.getX(), coordp2.getY(), orbecito.getPosX(), orbecito.getPosY())< 25 & orbecito.isBlue() == false) {
				//orbecito.mover(app.mouseX, app.mouseY);
				orbeArray.remove(orbecito);
				p2score++;
				
				System.out.println(coordp1.getX());
				System.out.println(coordp1.getY());
			}
			
			

		}

	}

	/*public void eliminarOrbe(Orbe orbecito) {

		// amarillo
		if (coord.getX() > orbecito.getPosX() & coord.getY() > orbecito.getPosY() & coord.getX() < 1000 & coord.getY() < 550 & orbecito.isBlue() == false) {
			orbeArray.remove(orbecito);
			p2score++;
		}
		// azul
		if (app.mouseX > 220 & app.mouseY > 477 & app.mouseX < 319 & app.mouseY < 550 & orbecito.isBlue() == true) {
			orbeArray.remove(orbecito);
			p1score++;
		}

	}*/

	public int tiempo() {

		Date endDate = new Date();

		int numSeconds = (int) ((endDate.getTime() - startDate.getTime()) / 1000);
		int tiempoRestante = tiempoLimite - numSeconds;
		//System.out.println("time: " + tiempoRestante);
		return tiempoRestante;

	}

	public Coord getCoordp1() {
		return coordp1;
	}

	public void setCoordp1(Coord coordp1) {
		this.coordp1 = coordp1;
	}

	public Coord getCoordp2() {
		return coordp2;
	}

	public void setCoordp2(Coord coordp2) {
		this.coordp2 = coordp2;
	}

}
