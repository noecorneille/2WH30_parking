package edu.parking2WH30.parking;

enum State {
	SEARCHING, PARKING, PARKED, EXITING
}

public class Car {
	private State state;
	private int position;
	
	private ParkingSpot park; // actually ParkingSpot* (pointer)
	
	public Car() {
		this.position = 0;
		this.state = State.SEARCHING;
		park = null;
	}
	
	public void update(Car front, ParkingSpot... parkingSpots) {
		if(state == State.EXITING) {
			position++;
			return;
		}
		
		if(state == State.PARKED) {
			return;
		}
		
		if(state == State.PARKING) {
			state = State.PARKED;
			return;
		}
		
		if(front.getState() == State.PARKING) {
			return;
		}
		
		/*
		 * Parking algorithm goes here 
		 */
		for(ParkingSpot p /* also pointer */ : parkingSpots) {
			if(!p.occupied) {
				p.occupy();	// occupies p in memory
				this.park = p; // park is assigned memory address of p
				this.state = State.PARKING;
				return;
			}
		}
		
		if(state == State.SEARCHING) {
			this.position++;
		}
	}
	
	public void unpark() {
		park.unoccupy();
		park = null;
		
		state = State.EXITING;
	}
	
	public State getState() { return state; }
	public int getPosition() { return position; }
	public ParkingSpot getPark() { return park; }
}
