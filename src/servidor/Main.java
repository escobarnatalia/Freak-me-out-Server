package servidor;

import model.PartidaView;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {

	private TCPServidor tcp;
	PartidaView partida;
	private int pantallas;
	//Posiciones iniciales
	float x;
	float y;
	PImage P1;

	public static void main(String[] args) {
		PApplet.main("servidor.Main");
	}

	public void settings() {
		size(1200, 700);
	}

	public void setup() {
		
		x=250;
		y=250;
		
		
		P1 = loadImage("img/jugador1.png");

		tcp = TCPServidor.getInstance();
		tcp.setObserver(this);

		partida = new PartidaView(this);
		partida.cargar();
		pantallas = 0;

	}

	public void draw() {
		background(0);

		switch (pantallas) {
		case 0:
			partida.pintarPartida();
			
			image(P1,x,y,105,70);
			fill(255);
			ellipse(x,y,105,70);
			text("X:" + mouseX + "Y:" + mouseY, mouseX, mouseY);
			
			
			
			
			
			break;
		}

	}

	public void mouseDragged() {
		partida.arrastrarLogica();

	}
	
	
	public void SetCoord(float posx,float posy) {
		x=posx;
		y=posy;
	}

}
