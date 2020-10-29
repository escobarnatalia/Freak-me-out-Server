package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Instrucciones extends Pantalla {
	
	PImage instrucciones;
	PImage next;

	public Instrucciones(PApplet app) {
		super(app);
		instrucciones = app.loadImage("img/InstruccionesBG.png");
		next = app.loadImage("img/InstruccionesBG2.png");
	}
	
	public void pintarInstrucciones() {
		app.imageMode(CORNER);
		app.image(instrucciones, 0, 0, 1200, 700);
	}
	
	public void pintarNext() {
		app.imageMode(CORNER);
		app.image(next, 0, 0, 1200, 700);
	}

}
