package edu.parking2WH30.parking;
/*
 * @author Noé Corneille
 * @id 1223165
 * 
 */

import java.util.Random;

public class ParkingSpot {
	
	public boolean occupied;
	
	protected double initialProb;
	
	public ParkingSpot(double initialProb) {
		this.initialProb = initialProb;		
	}
	
	public void init(Random rand) {
		double r = rand.nextDouble();
		occupied = r > initialProb;
	}
	
	public void occupy() {
		this.occupied = true;
	}
	
	public void unoccupy() {
		this.occupied = false;
	}
}
