package model;

import java.util.LinkedList;

import processing.core.PApplet;

public class Logica {
	private PApplet app;
	private LinkedList<Orbe> orbeList;

	public Logica(PApplet app) {
		this.app = app;

		orbeList = new LinkedList<Orbe>();

		for (int i = 0; i < 20; i++) {

			float posX = app.random(404, 820);
			float posY = app.random(437, 550);


			orbeList.add(new Orbe(posX, posY, app));
			
			//System.out.println("aiuda");

		}
	}
	
	public void pintarLogica() {
		
		for (int i = 0; i < orbeList.size(); i++) {
			Orbe orbecito = orbeList.get(i);
			orbecito.pintarOrbe();
		}
		
	}

}
