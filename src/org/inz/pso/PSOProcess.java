package org.inz.pso;

//najwazniejsza czesc kodu przygotowana na 2 wymiarowy problem

import java.util.Random;
import java.util.Vector;

public class PSOProcess implements PSOConstants {
	private Vector<Particle> swarm = new Vector<Particle>();
	private double[] pBest = new double[SWARM_SIZE];
	private Vector<Location> pBestLocation = new Vector<Location>();
	private double gBest;
	private Location gBestLocation;
	private double[] fitnessValueList = new double[SWARM_SIZE];
	
	Random generator = new Random();
	
	public void execute() {
		initializeSwarm();
		updateFitnessList();
		
		for(int i=0; i<SWARM_SIZE; i++) {
			pBest[i] = fitnessValueList[i];
			pBestLocation.add(swarm.get(i).getLocation());
		}
		int t = 0; //iteracje
		double w;  // waga
		double err = 9999; //przypisanie wartosci mozliwego bledu
		
		while(t < MAX_ITER && err > Problem.ERR_TOL) {
			// początek to update najlepszego przypadku personalnego
			for(int i=0; i<SWARM_SIZE; i++) {
				if(fitnessValueList[i] < pBest[i]) {
					pBest[i] = fitnessValueList[i];
					pBestLocation.set(i, swarm.get(i).getLocation());
				}
			}
			// update najlepszego przypadku globalnego
			int bestParticleIndex = PSOminfinder.getMinPos(fitnessValueList);
			if(t == 0 || fitnessValueList[bestParticleIndex] < gBest) {
				gBest = fitnessValueList[bestParticleIndex];
				gBestLocation = swarm.get(bestParticleIndex).getLocation();
			}
			//wyliczenie wagi bezwladnosci
			w = W_MAX - (((double) t) / MAX_ITER) * (W_MAX - W_MIN);
			
			for(int i=0; i<SWARM_SIZE; i++) {
				double r1 = generator.nextDouble();
				double r2 = generator.nextDouble();
				
				Particle p = swarm.get(i);
				
				// aktualizacja predkosci dla obu wymiarow na posdstawie wzoru uzywanego w pso
				double[] newVel = new double[DIMENSION];
				newVel[0] = (w * p.getVelocity().getPos()[0]) + 
							(r1 * C1) * (pBestLocation.get(i).getLoc()[0] - p.getLocation().getLoc()[0]) +
							(r2 * C2) * (gBestLocation.getLoc()[0] - p.getLocation().getLoc()[0]);
				newVel[1] = (w * p.getVelocity().getPos()[1]) + 
							(r1 * C1) * (pBestLocation.get(i).getLoc()[1] - p.getLocation().getLoc()[1]) +
							(r2 * C2) * (gBestLocation.getLoc()[1] - p.getLocation().getLoc()[1]);
				Velocity vel = new Velocity(newVel);
				p.setVelocity(vel);
				
				// aktualizacja lokacji po przejsciu z nowa predkoscia
				double[] newLoc = new double[DIMENSION];
				newLoc[0] = p.getLocation().getLoc()[0] + newVel[0];
				newLoc[1] = p.getLocation().getLoc()[1] + newVel[1];
				Location loc = new Location(newLoc);
				p.setLocation(loc);
			}
			err = Problem.evaluate(gBestLocation) ; // aktualizacja przypadku w celu dokladnosci
			
			
			System.out.println("Iteracja " + t + ": ");
			System.out.println(" Najlepszy X: " + gBestLocation.getLoc()[0]);
			System.out.println(" Najlepszy Y: " + gBestLocation.getLoc()[1]);
			System.out.println(" Wartosc dla tych przypadkow: " + Problem.evaluate(gBestLocation));
			t++;
			updateFitnessList();
		}
		
		System.out.println("\n Znaleziono wynik na interacj:" + (t - 1) + ", Wynik:");
		System.out.println(" Najlepszy X: " + gBestLocation.getLoc()[0]);
		System.out.println(" Najlepszy Y: " + gBestLocation.getLoc()[1]);
	}
	//inicjalizacja roju (przypisanie losowych lokacji startowych czasteczkom i predkosci)
	public void initializeSwarm() {
		Particle p;
		for(int i=0; i<SWARM_SIZE; i++) {
			p = new Particle();
			
			// losowanie miejsca czastki w dwu wymiarowej przestrzeni o okreslonych warunkach
			double[] loc = new double[DIMENSION];
			loc[0] = Problem.LOC_X_MIN + generator.nextDouble() * (Problem.LOC_X_MAX - Problem.LOC_X_MIN);
			loc[1] = Problem.LOC_Y_MIN + generator.nextDouble() * (Problem.LOC_Y_MAX - Problem.LOC_Y_MIN);
			Location location = new Location(loc);
			
			// losowanie predkosci czastki z jaka wykona swoj "ruch"
			double[] vel = new double[DIMENSION];
			vel[0] = Problem.VEL_MIN + generator.nextDouble() * (Problem.VEL_MAX - Problem.VEL_MIN);
			vel[1] = Problem.VEL_MIN + generator.nextDouble() * (Problem.VEL_MAX - Problem.VEL_MIN);
			Velocity velocity = new Velocity(vel);
			
			p.setLocation(location);
			p.setVelocity(velocity);
			swarm.add(p);
		}
	}
	//metoda uzupelniajaca liste wynikow funkcji ktorej minima szukamy
	public void updateFitnessList() {
		for(int i=0; i<SWARM_SIZE; i++) {
			fitnessValueList[i] = swarm.get(i).getFitnessValue();
		}
	}
}
