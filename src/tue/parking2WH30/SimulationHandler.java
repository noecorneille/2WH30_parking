package tue.parking2WH30;

import java.util.ArrayList;
import java.util.Random;

public class SimulationHandler {
	private ParkingLot parkingLot;
	private ArrayList<Car> activeCars;
	private ArrayList<Integer> removeIndex;
	
	private final int SPAWN_TIME = 100; // TODO: determine SPAWN_TIME
	private int time;
	
	public SimulationHandler(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
		this.activeCars = new ArrayList<Car>();
		this.removeIndex = new ArrayList<Integer>();
		
		this.time = 0;
	}
	
	public void update() {
		for(int i = 0; i < activeCars.size(); i++) {
			if(activeCars.get(i).isParked) {
				removeIndex.add(0,i);
			}
			activeCars.get(i).moveFurther(parkingLot);
		}
		
		if(removeIndex.size() != 0) {
			int k = 0;
			for(Integer i : removeIndex) {
				activeCars.remove(i-k); // removeIndex zou sorted moeten zijn
				removeIndex.remove(0);
				k++;
			}
		}
		
		if(time == SPAWN_TIME) {
			Car car = new CarStrategyFirst();
			activeCars.add(0, car);
			
			// TODO: make random parking spot empty
			
			time = 0;
		}
		
		time++;
	}
}
