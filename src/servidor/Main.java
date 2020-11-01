package servidor;

import com.google.gson.Gson;

import model.Connect;
import model.Coord;
import model.Generic;
import model.Inicio;
import model.Instrucciones;
import model.Logica;
import model.Orbe;
import model.PartidaView;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Main extends PApplet {

	private TCPServidor tcp;
	PartidaView partida;
	Inicio inicio;
	Instrucciones instru;
	Connect connect;
	Logica logic;
	private int pantallas;
	// Posiciones iniciales
	float x;
	float y;
	int g;
	int slotp1;
	int slotp2;
	int sc1;
	int sc2;
	boolean grab;
	boolean grab2;
	PImage P1;
	PImage P1G;
	PImage P2;
	PImage P2G;
	PFont font;

	public static void main(String[] args) {
		PApplet.main("servidor.Main");
	}

	public void settings() {
		size(1200, 700);
	}

	public void setup() {

		x = 250;
		y = 250;
		slotp1 = 0;
		slotp2 = 0;
		sc1=0;
		sc2=0;
		grab = false;
		grab2=false;

		logic = new Logica(this);

		P1 = loadImage("img/jugador1.png");
		P1G = loadImage("img/jugador1g.png");
		P2 = loadImage("img/jugador2.png");
		P2G = loadImage("img/jugador2g.png");
		font = createFont("res/Gilroy-Black.ttf", 20);

		tcp = TCPServidor.getInstance();
		tcp.setObserver(this);
		// tcp.start();

		partida = new PartidaView(this);
		inicio = new Inicio(this);
		instru = new Instrucciones(this);
		pantallas = 0;

	}

	public void draw() {
		background(0);
		textFont(font, 20);

		switch (pantallas) {
		case 0:
			inicio.pintarInicio();
			text("X:" + mouseX + "Y:" + mouseY, mouseX, mouseY);

			// boton start
			if (mouseX > 948 & mouseY > 590 & mouseX < 1115 & mouseY < 633) {
				inicio.pintarStart();
			}

			// boton exit
			if (mouseX > 82 & mouseY > 590 & mouseX < 200 & mouseY < 633) {
				inicio.pintarExit();
			}

			break;
		case 1:
			instru.pintarInstrucciones();
			text("X:" + mouseX + "Y:" + mouseY, mouseX, mouseY);

			// boton next
			if (mouseX > 521 & mouseY > 592 & mouseX < 674 & mouseY < 630) {
				instru.pintarNext();
			}

			break;

		/*
		 * case 2: connect.pintarConnect();
		 * 
		 * 
		 * break;
		 */

		case 2:
			// juego
			partida.pintarPartida();
			partida.tiempo();
			score();

			tcp.getSessions();

			for (int i = 0; i < tcp.getSessions().size(); i++) {

				if (i == 0) {
					if (grab == false) {
						imageMode(CENTER);
						image(P1, x, y, 105, 70);
						rect(x, y, 10, 10);
					} else {
						imageMode(CENTER);
						image(P1G, x, y, 105, 70);
					}

				} else if (i == 1) {
					if (grab2 == false) {
						imageMode(CENTER);
						image(P2, x, y, 105, 70);
					} else {
						imageMode(CENTER);
						image(P2G, x, y, 105, 70);
					}

				}

				for (int l = 0; l < logic.orbeArray.size(); l++) {

					Orbe orbecito = logic.orbeArray.get(l);

					orbecito.pintarOrbe();

					System.out.println("arregloo" + logic.orbeArray);

					if (slotp1 == 0 & i == 0) {
						if (dist(x, y, orbecito.getPosX(), orbecito.getPosY()) < 50 & orbecito.isBlue() == false) {

							System.out.println("averx" + orbecito.getPosX());
							System.out.println("posx" + x);
							System.out.println("avery" + orbecito.getPosY());
							System.out.println("psoy" + y);
							logic.getOrbeArray().remove(orbecito);
							slotp1 = 1;
							grab = !grab;
						}
					}

					if (slotp1 == 1 & i == 0) {
						if (dist(x, y, 980, 512) < 20) {
							slotp1 = 0;
							grab = !grab;
							sc1++;
							
						}
					}
					
					
					if (slotp2 == 0 & i == 1) {
						if (dist(x, y, orbecito.getPosX(), orbecito.getPosY()) < 50 & orbecito.isBlue() == false) {

							System.out.println("averx" + orbecito.getPosX());
							System.out.println("posx" + x);
							System.out.println("avery" + orbecito.getPosY());
							System.out.println("psoy" + y);
							logic.getOrbeArray().remove(orbecito);
							slotp2 = 1;
							grab2 = !grab2;
						}
					}

					if (slotp2 == 1 & i == 1) {
						if (dist(x, y, 980, 512) < 20) {
							slotp2 = 0;
							grab2 = !grab2;
							sc2++;
						}
					}
					
					
				}

			}
			fill(255);
			text("X:" + mouseX + "Y:" + mouseY, mouseX, mouseY);
			break;

		}

	}

	public void mouseClicked() {
		if (mouseX > 948 & mouseY > 590 & mouseX < 1115 & mouseY < 633) {
			pantallas = 1;
		}

		if (mouseX > 82 & mouseY > 590 & mouseX < 200 & mouseY < 633) {
			exit();
		}

		if (mouseX > 521 & mouseY > 592 & mouseX < 674 & mouseY < 630) {
			pantallas = 2;
		}
	}

	public void SetCoord(float posx, float posy) {
		x = posx;
		y = posy;
	}

	public void SetGrab(int gg) {
		g = gg;
	}

	public void keyPressed() {
		if (key == 'g') {
			grab = !grab;
		}
	}
	
	
	public void score() {
		fill(255);
		text("Puntos: " +sc2, 100, 50);
		
		fill(255);
		text("Puntos: " +sc1 , 800, 50);
	}

}
