package edu.parking2WH30.parking;

import java.util.ArrayList;
import java.util.Random;

public class ParkingLot {

	public ArrayList<ParkingSpot> parkingSpots;
	
	protected int width, height;
	protected double distParkingSpots, distParkingLanes;
	
	public ParkingLot(int width, int height, double distParkingSpots, double distParkingLanes, double[] initProb) {
		this.width = width;
		this.height = height;
		this.distParkingSpots = distParkingSpots;
		this.distParkingLanes = distParkingLanes;
		
		Random r = new Random();
		parkingSpots = new ArrayList<ParkingSpot>();
		
		for(double p : initProb) {
			ParkingSpot park = new ParkingSpot(p);
			park.init(r);
			
			parkingSpots.add(park);
		}
	}
}
