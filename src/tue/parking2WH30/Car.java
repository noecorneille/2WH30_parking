package tue.parking2WH30;

import java.util.ArrayList;

public abstract class Car {
	public boolean isParked;
	protected int position;
	protected int distanceDriven; // In index units
								  // TODO: Index unit --> real unit
	
	protected ArrayList<Integer> targets; // Indices of target parking spots
	
	public Car() {
		this.position = 0;
		this.isParked = false;
		this.targets =  new ArrayList<Integer>();
	}
	
	public abstract void moveFurther(ParkingLot parkingLot); // Classes are passed by reference in java
	
	public ArrayList<Integer> lookAhead(ParkingLot parkingLot) {
		ArrayList<Integer> visible = new ArrayList<Integer>(); // Indices of visible parking spots in parkingLot.parkingSpots
		
		/* TODO: position --> visible parking spots */
		
		return visible;
	}
}
