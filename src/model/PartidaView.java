package model;

import processing.core.PApplet;
import processing.core.PImage;

public class PartidaView extends Pantalla {

	Logica logica;
	PImage fondo;
	PImage menu;

	public PartidaView(PApplet app) {
		super(app);

		logica = new Logica(app);

	}

	public void cargar() {
		fondo = app.loadImage("img/fondo.jpg");
		menu = app.loadImage("img/MenuBg.png");

	}

	public void pintarPartida() {
		app.imageMode(CORNER);
		app.image(fondo, 0, 0, 1200, 700);
		logica.pintarLogica();
	}
	
	public void pintarMenu() {
		app.imageMode(CORNER);
		app.image(menu, 0, 0, 1200, 700);
	}

	public void arratrarLogica() {
		logica.arrastrar();
	}

	public void eliminar() {
		logica.eliminarOrbe();
	}

}
