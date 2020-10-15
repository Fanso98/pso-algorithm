package org.inz.pso;



// klasa odpowiedzialna za predkosc poruszania sie czastek

public class Velocity {
	// przechowywanie szybkosci w wielowymiarowej tablicy dzieki temu mozna rozwiazywac zadania z wieloma zmiennymi
	private double[] vel;
	//konstruktor
	public Velocity(double[] vel) {
		this.vel = vel;
	}
	//getter
	public double[] getPos() {
		return vel;
	}
	//setter
	public void setPos(double[] vel) {
		this.vel = vel;
	}
	
}
