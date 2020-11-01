package model;

import processing.core.PApplet;
import processing.core.PImage;

public class PartidaView extends Pantalla {

	Logica logica;
	PImage fondo;
	
	

	public PartidaView(PApplet app) {
		super(app);

		logica = new Logica(app);
		fondo = app.loadImage("img/fondo.jpg");
		
	}


	public void pintarPartida() {
		app.imageMode(CORNER);
		app.image(fondo, 0, 0, 1200, 700);
		
	}
	

	public void arrastrarLogica() {
		//logica.arrastrar();
	}
	
	public void tiempo() {
		app.fill(255);
		app.text("Tiempo restante " + logica.tiempo(), 505, 50);
	}
	


	

}
