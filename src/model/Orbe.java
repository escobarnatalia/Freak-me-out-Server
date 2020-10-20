package model;

import processing.core.PApplet;

public class Orbe {
	
	private PApplet app; 
	private float posX;
	private float posY;
	private String orbe;
	private int tam; 
	
	
	public Orbe(float posX, float posY, int tam, PApplet app) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		this.tam = tam; 
		
	}
	
	public void pintarOrbe() {
		app.ellipseMode(app.CENTER);
		app.fill(255);
		app.ellipse(posX, posY, tam, tam);
	}

}
