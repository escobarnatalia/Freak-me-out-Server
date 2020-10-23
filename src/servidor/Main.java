package servidor;

import com.google.gson.Gson;

import model.Coord;
import model.Generic;
import model.Inicio;
import model.PartidaView;
import processing.core.PApplet;
import processing.core.PFont;
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
	PFont font;

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
		font = createFont("res/Gilroy-Black.ttf", 20);

		tcp = TCPServidor.getInstance();
		tcp.setObserver(this);
		// tcp.start();
		
		

		partida = new PartidaView(this);
		inicio = new Inicio(this);
		pantallas = 0;

	}

	public void draw() {
		background(0);
		textFont(font, 20);

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
			//juego
			partida.pintarPartida();
			partida.tiempo();
			
			

			
			tcp.getSessions();
			for(int i = 0 ; i < tcp.getSessions().size(); i++) {
				Session session =  tcp.getSessions().get(i);
				image(P1, session.getCoord().getX(), session.getCoord().getY(), 105, 70);
				
			}
			
			
			
			fill(255);
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
		
		Gson gson = new Gson();
		Coord coordReceived = gson.fromJson(line, Coord.class);
		s.setCoord(coordReceived);
		
		
		
		
		
		
		Generic generic = gson.fromJson(line,Generic.class);
		
		
		
		/*switch(generic.getType()) {
		case "Coord":
			
			float posx=coordenada.getX();
			float posy=coordenada.getY();
			observer.SetCoord(posx,posy);
			System.out.println(posx);
			System.out.println(posy);
			
			break;
		}*/
		
		
		
		
		System.out.println("message" + s.getID() + ":" + line);

	}

}
