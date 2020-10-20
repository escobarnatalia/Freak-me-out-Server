package servidor;

import model.PartidaView;
import processing.core.PApplet;

public class Main extends PApplet{
	
	PartidaView partida;
	private int pantallas;

	public static void main(String[] args) {
		PApplet.main("servidor.Main");
	}
	
	public void settings() {
		size(1200, 700);		
	}
	public void setup() {
		partida = new PartidaView(this);
		partida.cargar();
		pantallas = 0; 
	
		
	}
	public void draw() {
		background(0);
		
		
		switch(pantallas) {
		case 0:
			partida.pintar();
			fill(255);
			text("X:" + mouseX + "Y:" + mouseY, mouseX, mouseY);
			break;
		}
		
	}

}
