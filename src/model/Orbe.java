package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Orbe extends Pantalla {

	private float posX;
	private float posY;
	private String orbe;
	private boolean isBlue;


	PImage orbee;

	public Orbe(float posX, float posY, PApplet app, boolean isBlue) {
		super(app);
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		this.isBlue = isBlue;

		if (isBlue) {
			orbee = app.loadImage("img/orbe2.png");
		} else {
			orbee = app.loadImage("img/orbe1.png");
		}

		

	}

	public void pintarOrbe() {
		app.imageMode(CENTER);
		app.image(orbee, posX, posY);

	}

	public void mover(float x, float y) {
		this.posX = x;
		this.posY = y;
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

}
