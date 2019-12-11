package tue.parking2WH30;

import java.util.ArrayList;
import java.util.Random;

public class SimulationHandler {
	private boolean[] parkingLot = {false, false, true, true};
	
	private final double[] drivingDistances = {1,2,3,4};
	private final double[] walkingDistances = {2,2.5,1.5,1};
	
	private final int strategyNumber = 1;
	
	private ArrayList<Integer> occupiedSpotIndices;
	
	
	private Random random;
		
	public SimulationHandler() {
		//parkingLot = new boolean[4];
		occupiedSpotIndices = new ArrayList<Integer>();
		random = new Random();
		
		for(int i = 0; i < parkingLot.length; i++) {
			if(parkingLot[i]) {
				occupiedSpotIndices.add(i);
			}
		}
		
		// TODO: init parkingLot randomly
	}
	
	private void emptyRandomParkingSpot() {
		int index = random.nextInt(occupiedSpotIndices.size());
		parkingLot[occupiedSpotIndices.get(index)] = false;
		occupiedSpotIndices.remove(index);
	}
	
	public double updateReturnDistance() {
		double distance = 0;
		
		switch(strategyNumber) {
		case 1: // First available
			for(int i = 0; i < parkingLot.length; i++) {
				if(!parkingLot[i]) {
					distance = drivingDistances[i] + walkingDistances[i];
					parkingLot[i] = true;
					occupiedSpotIndices.add(i);
					break;
				}
			}
		break;
		default:
		break;		
		}
		emptyRandomParkingSpot();
		return distance;
	}
	
	//temporary bruh	
	public void printParkingLot() {
		System.out.println(boolToInt(parkingLot[0]) + "" + boolToInt(parkingLot[1]));
		System.out.println(boolToInt(parkingLot[2]) + "" + boolToInt(parkingLot[3]));
		System.out.println();
	}
	
	private int boolToInt(boolean b) {
		return b ? 1 : 0;
	}
}
