package servidor;

import model.Inicio;
import model.PartidaView;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {

	private TCPServidor tcp;
	PartidaView partida;
	Inicio inicio;
	private int pantallas;
	// Posiciones iniciales
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

		x = 250;
		y = 250;

		P1 = loadImage("img/jugador1.png");

		tcp = TCPServidor.getInstance();
		tcp.setObserver(this);
		// tcp.start();

		partida = new PartidaView(this);
		inicio = new Inicio(this);
		pantallas = 1;

	}

	public void draw() {
		background(0);

		switch (pantallas) {
		case 0:
			inicio.pintarInicio();
			text("X:" + mouseX + "Y:" + mouseY, mouseX, mouseY);
			
			//boton start
			if (mouseX > 948 & mouseY > 590 & mouseX < 1115 & mouseY < 633 ) {
				inicio.pintarStart();
			}
			
			//boton exit
			if (mouseX > 82 & mouseY > 590 & mouseX < 200 & mouseY < 633 ) {
				inicio.pintarExit();
			}
			

			break;
		case 1:
			partida.pintarPartida();
			//partida.tiempo();

			image(P1, x, y, 105, 70);
			fill(255);
			ellipse(x, y, 105, 70);
			text("X:" + mouseX + "Y:" + mouseY, mouseX, mouseY);
			
			
			
			break;
		}

	}

	public void mouseDragged() {
		partida.arrastrarLogica();

	}
	
	public void mouseClicked() {
		if (mouseX > 948 & mouseY > 590 & mouseX < 1115 & mouseY < 633 ) {
			pantallas = 1;
		}
	}
	
	
	public void SetCoord(float posx, float posy) {
		x = posx;
		y = posy;
	}

	public void ReceivedMessage(Session s, String line) {
		// TODO Auto-generated method stub
		System.out.println("message" + s.getID() + ":" + line);

	}

}
