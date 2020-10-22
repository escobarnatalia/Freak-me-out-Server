package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Inicio extends Pantalla {
	
	PImage menu;
	PImage start;
	PImage exit;
	
	public Inicio(PApplet app) {
		super(app);
		menu = app.loadImage("img/MenuBg.png");
		start = app.loadImage("img/StartOver.png");
		exit = app.loadImage("img/ExitOver.png");
	}
	
	public void pintarInicio() {
		app.imageMode(CORNER);
		app.image(menu, 0, 0, 1200, 700);
		
	}
	
	public void pintarStart() {
		app.imageMode(CORNER);
		app.image(start, 0, 0, 1200, 700);
	}
	
	public void pintarExit() {
		app.imageMode(CORNER);
		app.image(exit, 0, 0, 1200, 700);
	}

}
