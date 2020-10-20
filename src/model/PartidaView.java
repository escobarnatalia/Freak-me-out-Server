package model;

import processing.core.PApplet;
import processing.core.PImage;

public class PartidaView extends Pantalla{
	
	PImage fondo;

	public PartidaView(PApplet app) {
		super (app);
		
	}
	
	public void cargar() {
		fondo = app.loadImage("img/fondo.jpg");
		
	}
	
	public void pintar() {
		app.image(fondo, 0, 0, 1200, 700);
		
	}

}
