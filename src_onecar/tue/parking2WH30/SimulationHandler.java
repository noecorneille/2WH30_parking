package tue.parking2WH30;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class SimulationHandler {
	private boolean[] parkingLot;
	private final double parkingLotLength = 298;
	
	private final double driveSpeed = 4;
	private final double walkSpeed = 1;
	
	//private final double occupancyRate = 0.99;
	
	private final int strategyNumber = 1;
	
	private ArrayList<ParkingSpot> occupiedSpots;
	private ArrayList<ParkingSpot> unoccupiedSpots;
	private Comparator<ParkingSpot> compare;
		
	private Random random;
		
	public SimulationHandler(double occupancyRate) {
		occupiedSpots = new ArrayList<ParkingSpot>();
		unoccupiedSpots = new ArrayList<ParkingSpot>();
		random = new Random();
		initParkingSpace(occupancyRate);
	}
	
	private void initParkingSpace(double probability) {
		double[] drivingDistances = new double[100];
		double[] walkingDistances = new double[100];
		
		// File i/o
		try {
			//Scanner s = new Scanner(new File("C:/Users/20171983/OneDrive - TU Eindhoven/Universiteit/2019-2020/Q2 2WH30 Mathematical modelling/source code/Java/2WH30_parking/drivingDistances.txt")).useLocale(Locale.US);
			Scanner s = new Scanner(new File("drivingDistances.txt")).useLocale(Locale.US);
			for(int i = 0; i < drivingDistances.length; i++) {
				drivingDistances[i] = s.nextDouble();
			}
			
			//s = new Scanner(new File("C:/Users/20171983/OneDrive - TU Eindhoven/Universiteit/2019-2020/Q2 2WH30 Mathematical modelling/source code/Java/2WH30_parking/walkingDistances.txt")).useLocale(Locale.US);
			s = new Scanner(new File("walkingDistances.txt")).useLocale(Locale.US);
			for(int i = 0; i < walkingDistances.length; i++) {
				walkingDistances[i] = s.nextDouble();
			}
			s.close();				
		} catch(FileNotFoundException e) {
			e.printStackTrace();	
		}
		
		// Generate empty parking lot
		parkingLot = new boolean[100];
		for(int i = 0; i < parkingLot.length; i++) {
			parkingLot[i] = false;
			unoccupiedSpots.add(new ParkingSpot(i, drivingDistances[i]/driveSpeed + walkingDistances[i]/walkSpeed));
		}
		
		// Occupy a set number of parking spots randomly
		int numOccupied = (int)(probability * parkingLot.length);
		
		ArrayList<Integer> indexList = new ArrayList<Integer>();
		for (int i = 0; i < parkingLot.length; i++){ 
			indexList.add(i);
		}
		Collections.shuffle(indexList);
		List<Integer> indices = indexList.subList(0, numOccupied);
		Collections.sort(indices, (a,b) -> Integer.compare(b, a));
		
		for(int k : indices) {
			parkingLot[k] = true;
			occupiedSpots.add(unoccupiedSpots.get(k));
			unoccupiedSpots.remove(k);
		}
		
		
		// Sort parkingSpot lists based on strategy
		switch(strategyNumber) {
			case 1: // Sort by index
				this.compare = ParkingSpot.indexComparator;
				break;
			case 2: // Sort by travel time
				this.compare = ParkingSpot.travelTimeComparator;
				break;
			// TODO: Rest of strategies
			default:
				break;	
		}
		
		Collections.sort(unoccupiedSpots, this.compare);
		Collections.sort(occupiedSpots, this.compare);
	}
	
	// Unoccupies a parking spot randomly
	private void emptyRandomParkingSpot() {
		int index = random.nextInt(occupiedSpots.size()); // Get random index from occupied spots
		ParkingSpot p = occupiedSpots.get(index); // Save this parkingspot to memory
		
		parkingLot[p.getIndex()] = false; // Ass parkingspot to lists of unoccupied spots
		insert(unoccupiedSpots, p);
		occupiedSpots.remove(index);
	}

	public double updateReturnTime() {
		double distance = 0;
		
		switch(strategyNumber) {
		case 1: // First available
			ParkingSpot p1 = unoccupiedSpots.get(0); // First available is first in list
			insert(occupiedSpots, p1);	// Occupy spot
			parkingLot[p1.getIndex()] = true;
			
			distance += p1.getTravelTime();
			unoccupiedSpots.remove(0);
			
		break;
		case 2: // Best available
			ParkingSpot p2 = unoccupiedSpots.get(0); // Best available is first in list
			insert(occupiedSpots, p2); // Occupy spot
			parkingLot[p2.getIndex()] = true;
			
			distance += p2.getTravelTime() + parkingLotLength/driveSpeed;
			unoccupiedSpots.remove(0);
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
	
	private void insert(ArrayList<ParkingSpot> list, ParkingSpot element) {
		int index = Collections.binarySearch(list, element, this.compare); // Inserts element in O(log n) time
		if(index < 0) { 
			index = -index - 1;
		}
		list.add(index, element);
	}

}