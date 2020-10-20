package model;

import processing.core.PApplet;
import processing.core.PImage;

public class PartidaView extends Pantalla{
	
	PImage fondo;
	Logica logica;

	public PartidaView(PApplet app) {
		super (app);
		
		logica = new Logica(app);
		
		
	}
	
	public void cargar() {
		fondo = app.loadImage("img/fondo.jpg");
		
	}
	
	public void pintar() {
		app.imageMode(CORNER);
		app.image(fondo, 0, 0, 1200, 700);
		logica.pintarLogica();
		
	}

}
