package org.inz.pso;
//Klasa odpowiedzialna za czastke i jej informacje
//fitness-funkcja do optymalizacji
public class Particle {
	private double fitnessValue; //wynik funkcji
	private Velocity velocity;
	private Location location;
	
	public Particle() { }
	//konstruktor
	public Particle(double fitnessValue, Velocity velocity, Location location) {
		this.fitnessValue = fitnessValue;
		this.velocity = velocity;
		this.location = location;
	}
	//Gettery i Settery
	public Velocity getVelocity() {
		return velocity;
	}

	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	//Getter podaje wartość funkcji na podstawie aktualnej lokacji.
	public double getFitnessValue() {
		fitnessValue = Problem.evaluate(location);
		return fitnessValue;
	}
}
