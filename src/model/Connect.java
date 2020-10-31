package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Connect extends Pantalla{
	
	PImage connectbg;
	PImage connect;

	public Connect(PApplet app) {
		super(app);
		
		connectbg= app.loadImage("img/ConnectBg.png");
		connect= app.loadImage("img/Connecting.png");
		
		
		
		
		// TODO Auto-generated constructor stub
	}
	
	
	public void pintarConnect() {
		app.imageMode(CORNER);
		app.image(connect, 0, 0, 1200, 700);
	}

}
