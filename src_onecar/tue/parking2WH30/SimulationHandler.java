package tue.parking2WH30;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class SimulationHandler {
	private boolean[] parkingLot;
	
	private double[] drivingDistances;
	private double[] walkingDistances;;
	private final double parkingLotLength = 5;
	
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
		try {
			Scanner s = new Scanner(new File("drivingDistances.txt")).useLocale(Locale.US);
			drivingDistances = new double[100];
			for(int i = 0; i < drivingDistances.length; i++) {
				drivingDistances[i] = s.nextDouble();
			}
			
			s = new Scanner(new File("walkingDistances.txt")).useLocale(Locale.US);
			walkingDistances = new double[100];
			for(int i = 0; i < walkingDistances.length; i++) {
				walkingDistances[i] = s.nextDouble();
			}
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		parkingLot = new boolean[100];
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
		case 2:
			for(int i = parkingLot.length-1; i >= 0; i--) {
				if(!parkingLot[i]) {
					distance = (drivingDistances[i] + parkingLotLength)/drivespeed + walkingDistances[i]/walkspeed;
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
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				System.out.print(boolToInt(parkingLot[10*i+j]));
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	private int boolToInt(boolean b) {
		if(b) return 1;
		return 0;
	}
}