package tue.parking2WH30;

import java.util.ArrayList;

public class CarStrategyFirst extends Car {

	@Override
	public void moveFurther(ParkingLot parkingLot) {
		if(this.isParked) return;
		
		ArrayList<Integer> visibleSpots = this.lookAhead(parkingLot);
		
		if(targets.size() == 0) {
			for(Integer parkingSpotIndex : visibleSpots) {
				if(!parkingLot.parkingSpots.get(parkingSpotIndex).isOccupied) {
					targets.add(parkingSpotIndex);
				}
			}
		} else {
			for(Integer targetIndex : targets) {
				if(position - targetIndex == 0) {
					this.isParked = true;
					parkingLot.parkingSpots.get(targetIndex).isOccupied = true;
					return;
				}
			}
		}
		
		this.position++;
		this.distanceDriven++;
		
		if(position == parkingLot.maxPosition) this.position = 0;
	}

}
