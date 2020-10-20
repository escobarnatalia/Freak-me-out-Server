package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Orbe extends Pantalla{
	
	private float posX;
	private float posY;
	private String orbe;
	PImage orbe1;
	PImage orbe2;
	
	
	public Orbe(float posX, float posY, PApplet app) {
		super(app);
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		
		orbe1 = app.loadImage("img/orbe1.png");
		orbe2 = app.loadImage("img/orbe2.png");
		
	}
	
	
	public void pintarOrbe() {
		app.imageMode(CENTER);
		app.image(orbe1, posX, posY);
		app.image(orbe2, posX, posY);
		
	}

}
