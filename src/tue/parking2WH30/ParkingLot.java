package tue.parking2WH30;

import java.util.ArrayList;

public class ParkingLot {
	public int rowLength, colLength, colNums;
	public int maxPosition; // = f(rowLength, colLength, colNums)
							// TODO: find this f
	
	public ArrayList<ParkingSpot> parkingSpots;
	
	public ParkingLot(int rowLength, int colLength, int colNums, double[] initProb) {
		if(initProb.length != rowLength * colNums) System.exit(0);
		
		this.rowLength = rowLength;
		this.colLength = colLength;
		this.colNums = colNums;
		
		this.maxPosition = rowLength * colNums + 2 * colLength * colNums; // ?
		
		this.parkingSpots = new ArrayList<ParkingSpot>();
		for(double d : initProb) {
			parkingSpots.add(0, new ParkingSpot(d));
		}
	}
}
