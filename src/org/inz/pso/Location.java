package org.inz.pso;

// Klasa odpowiadajaca za lokacje

public class Location {
	// lokacja w tablicy z racji wielowymiarowych mozliwosci 1D 2D
	private double[] loc;
	//konstruktor
	public Location(double[] loc) {
		this.loc = loc;
	}
	//Getter
	public double[] getLoc() {
		return loc;
	}
 	//Setter
	public void setLoc(double[] loc) {
		this.loc = loc;
	}
	
}
