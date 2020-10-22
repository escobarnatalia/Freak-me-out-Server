package model;

import processing.core.PApplet;
import processing.core.PImage;

public class PartidaView extends Pantalla {

	Logica logica;
	PImage fondo;
	//PImage P1;
	

	public PartidaView(PApplet app) {
		super(app);

		logica = new Logica(app);

	}

	public void cargar() {
		fondo = app.loadImage("img/fondo.jpg");
		//P1 = app.loadImage("img/jugador1.png");
		

	}

	public void pintarPartida() {
		app.imageMode(CORNER);
		app.image(fondo, 0, 0, 1200, 700);
		//app.image(P1,0,0,105,70);
		logica.pintarLogica();
	}
	

	public void arrastrarLogica() {
		logica.arrastrar();
	}

	

}
