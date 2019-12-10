package tue.parking2WH30;

import java.util.Random;

public class ParkingSpot {
	public boolean isOccupied;
	
	private double initialProb;
		
	public ParkingSpot(double initialProb) {
		this.initialProb = initialProb;		
	}
	
	public void init(Random rand) {
		double r = rand.nextDouble();
		isOccupied = r > initialProb;
	}
}
