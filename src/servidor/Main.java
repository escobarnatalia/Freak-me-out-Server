package servidor;

import com.google.gson.Gson;

import model.Connect;
import model.Coord;
import model.Generic;
import model.Inicio;
import model.Instrucciones;
import model.Logica;
import model.Orbe;
import model.PartidaView;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.sound.SoundFile;

public class Main extends PApplet {

	private TCPServidor tcp;
	PartidaView partida;
	Inicio inicio;
	Instrucciones instru;
	Connect conn;
	Logica logic;
	private int pantallas;
	// Posiciones iniciales
	float x;
	float y;
	int g;
	int slotp1;
	int slotp2;
	int sc1;
	int sc2;
	boolean grab;
	boolean grab2;
	PImage P1;
	PImage P1G;
	PImage P2;
	PImage P2G;
	PFont font;
	
	SoundFile gamemusic;
	SoundFile menumusic;

	public static void main(String[] args) {
		PApplet.main("servidor.Main");
	}

	public void settings() {
		size(1200, 700);
	}

	public void setup() {

		x = 250;
		y = 250;
		slotp1 = 0;
		slotp2 = 0;
		sc1 = 0;
		sc2 = 0;
		grab = false;
		grab2 = false;

		logic = new Logica(this);
		
		
		menumusic = new SoundFile(this,"music/menumusic.mp3");
		gamemusic = new SoundFile(this,"music/gamemusic.mp3");
		menumusic.amp((float) 0.1);
		menumusic.play();
		menumusic.loop();

		P1 = loadImage("img/jugador1.png");
		P1G = loadImage("img/jugador1g.png");
		P2 = loadImage("img/jugador2.png");
		P2G = loadImage("img/jugador2g.png");
		font = createFont("res/Gilroy-Black.ttf", 20);

		tcp = TCPServidor.getInstance();
		tcp.setObserver(this);
		// tcp.start();

		partida = new PartidaView(this);
		inicio = new Inicio(this);
		instru = new Instrucciones(this);
		conn = new Connect(this);
		pantallas = 0;

	}

	public void draw() {
		background(0);
		textFont(font, 20);

		switch (pantallas) {
		case 0:
			inicio.pintarInicio();
			text("X:" + mouseX + "Y:" + mouseY, mouseX, mouseY);

			// boton start
			if (mouseX > 948 & mouseY > 590 & mouseX < 1115 & mouseY < 633) {
				inicio.pintarStart();
			}

			// boton exit
			if (mouseX > 82 & mouseY > 590 & mouseX < 200 & mouseY < 633) {
				inicio.pintarExit();
			}

			break;
		case 1:
			instru.pintarInstrucciones();
			text("X:" + mouseX + "Y:" + mouseY, mouseX, mouseY);

			// boton next
			if (mouseX > 823 & mouseY > 560 & mouseX < 1000 & mouseY < 630) {
				instru.pintarNext();
			}

			break;

		case 2:

			conn.pintarConnect();

			tcp.getSessions();

			for (int i = 0; i < tcp.getSessions().size(); i++) {
				conn.pintarlink();
				if (i == 1) {
					pantallas = 3;
					menumusic.stop();
					gamemusic.amp((float) 0.1);
					gamemusic.play();
					gamemusic.loop();
				}

			}

			break;

		case 3:
			// juego
			partida.pintarPartida();
			partida.tiempo();
			score();

			tcp.getSessions();

			for (int i = 0; i < tcp.getSessions().size(); i++) {

				Session session = tcp.getSessions().get(i);

				if (i == 0) {
					if (grab == false) {
						imageMode(CENTER);
						image(P1, session.getCoord().getX(), session.getCoord().getY(), 105, 70);
						rect(session.getCoord().getX(), session.getCoord().getY(), 10, 10);
					} else {
						imageMode(CENTER);
						image(P1G, session.getCoord().getX(), session.getCoord().getY(), 105, 70);
					}

				}

				if (i == 1) {
					if (grab2 == false) {
						imageMode(CENTER);
						image(P2, session.getCoord().getX(), session.getCoord().getY(), 105, 70);
					} else {
						imageMode(CENTER);
						image(P2G, session.getCoord().getX(), session.getCoord().getY(), 105, 70);
					}

				}

				for (int l = 0; l < logic.orbeArray.size(); l++) {

					Orbe orbecito = logic.orbeArray.get(l);

					orbecito.pintarOrbe();

					

					if (slotp1 == 0 & i == 0) {
						if (dist(session.getCoord().getX(), session.getCoord().getY(), orbecito.getPosX(),
								orbecito.getPosY()) < 50 & orbecito.isBlue() == false) {

							logic.getOrbeArray().remove(orbecito);
							slotp1 = 1;
							grab = !grab;
						}
					}

					if (slotp1 == 1 & i == 0) {
						if (dist(session.getCoord().getX(), session.getCoord().getY(), 980, 512) < 20) {
							slotp1 = 0;
							grab = !grab;
							sc1++;

						}
					}

					if (slotp2 == 0 & i == 1) {
						if (dist(session.getCoord().getX(), session.getCoord().getY(), orbecito.getPosX(),
								orbecito.getPosY()) < 50 & orbecito.isBlue() == true) {

						
							logic.getOrbeArray().remove(orbecito);
							slotp2 = 1;
							grab2 = !grab2;
						}
					}

					if (slotp2 == 1 & i == 1) {
						if (dist(session.getCoord().getX(), session.getCoord().getY(), 259, 513) < 20) {
							slotp2 = 0;
							grab2 = !grab2;
							sc2++;
						}
					}

				}

			}
			fill(255);
			text("X:" + mouseX + "Y:" + mouseY, mouseX, mouseY);
			break;

		}

	}

	public void mouseClicked() {

		switch (pantallas) {
		case 0:
			if (mouseX > 948 & mouseY > 590 & mouseX < 1115 & mouseY < 633) {
				pantallas = 1;
			}

			if (mouseX > 82 & mouseY > 590 & mouseX < 200 & mouseY < 633) {
				exit();
			}

			break;

		case 1:
			if (mouseX > 823 & mouseY > 560 & mouseX < 1000 & mouseY < 630) {
				pantallas = 2;
			}

			break;

		case 2:
			if (mouseX > 948 & mouseY > 590 & mouseX < 1115 & mouseY < 633) {
				pantallas = 3;

				break;
			}

		}
	}

	public void keyPressed() {
		if (key == 'g') {
			grab = !grab;
		}
	}

	public void score() {
		fill(255);
		text("Puntos: " + sc2, 100, 50);

		fill(255);
		text("Puntos: " + sc1, 800, 50);
	}

	public void ReceivedMessage(Session s, String line) {
		// TODO Auto-generated method stub

		Gson gson = new Gson();
		Coord coordReceived = gson.fromJson(line, Coord.class);
		s.setCoord(coordReceived);

		Generic generic = gson.fromJson(line, Generic.class);

	

	}

}
