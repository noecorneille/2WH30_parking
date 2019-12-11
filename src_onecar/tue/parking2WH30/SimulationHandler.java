package tue.parking2WH30;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SimulationHandler {
	private boolean[] parkingLot = {false, false, true, true};
	
	private final double[] drivingDistances = {1,2,3,4};
	private final double[] walkingDistances = {2,2.5,1.5,1};
	
	private final double drivespeed = 4;
	private final double walkspeed = 1;
	
	private final double occupancyRate = 0.5;
	
	private final int strategyNumber = 1;
	
	private ArrayList<Integer> occupiedSpotIndices;
	
	private Random random;
		
	public SimulationHandler() {
		occupiedSpotIndices = new ArrayList<Integer>();
		random = new Random();
		
		initParkingSpace(occupancyRate);
	}
	
	private void initParkingSpace(double probability) {
		parkingLot = new boolean[4];
		for(int i = 0; i < parkingLot.length; i++) {
			parkingLot[i] = false;
		}
		
		int numOccupied = (int)(probability * parkingLot.length);
		
		ArrayList<Integer> indexList = new ArrayList<Integer>();
		for (int i = 0; i < parkingLot.length; i++){ 
			indexList.add(i);
		}
		Collections.shuffle(indexList);
		for(int k : indexList.subList(0,numOccupied)) {
			parkingLot[k] = true;
			occupiedSpotIndices.add(k);
		}
	}
	
	private void emptyRandomParkingSpot() {
		int index = random.nextInt(occupiedSpotIndices.size());
		parkingLot[occupiedSpotIndices.get(index)] = false;
		occupiedSpotIndices.remove(index);
	}

	public double updateReturnTime() {
		double distance = 0;
		
		switch(strategyNumber) {
		case 1: // First available
			for(int i = 0; i < parkingLot.length; i++) {
				if(!parkingLot[i]) {
					distance = drivingDistances[i]/drivespeed + walkingDistances[i]/walkspeed;
					parkingLot[i] = true;
					occupiedSpotIndices.add(i);
					break;
				}
			}
		break;
		// TODO: Rest of strategies
		default:
		break;		
		}
		emptyRandomParkingSpot();
		return distance;
	}
	
	// TODO: data vis
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
