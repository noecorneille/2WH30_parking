package tue.parking2WH30;

import java.util.ArrayList;
import java.util.Random;

public class SimulationHandler {
	private boolean[] parkingLot;
	
	private final double[] drivingDistances = {1,2,3,4};
	private final double[] walkingDistances = {5,6,7,8};
	
	private final int strategyNumber = 1;
	
	
	private Random random;
		
	public SimulationHandler() {
		parkingLot = new boolean[4];
		
		// TODO: init parkingLot randomly
	}
	
	private void emptyRandomParkingSpot() {
		ArrayList<Integer> occupiedSpotIndices = new ArrayList<Integer>();
		for(int i = 0; i < parkingLot.length; i++) {
			if(parkingLot[i]) {
				occupiedSpotIndices.add(i);
			}
		}
		
		parkingLot[occupiedSpotIndices.get(random.nextInt(occupiedSpotIndices.size()))] = false;
	}
	
	public double updateReturnDistance() {
		double distance = 0;
		
		switch(strategyNumber) {
		case 1: // First available
			for(int i = 0; i < parkingLot.length; i++) {
				if(!parkingLot[i]) {
					distance = drivingDistances[i] + walkingDistances[i];
					parkingLot[i] = true;
				}
			}
		break;
		default:
		break;		
		}
		emptyRandomParkingSpot();
		return distance;
	}
}
