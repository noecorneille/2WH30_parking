package tue.parking2WH30;

import java.util.Comparator;

public class ParkingSpot {
	private int index;
	private double travelTime;
	
	public ParkingSpot(int index, double travelTime) {
		this.index = index;
		this.travelTime = travelTime;
	}
	
	public int getIndex() { return index; }
	public double getTravelTime() { return travelTime; }
	
	public static Comparator<ParkingSpot> indexComparator = (p1, p2) -> Integer.compare(p1.getIndex(), p2.getIndex());
	public static Comparator<ParkingSpot> travelTimeComparator = (p1, p2) -> Double.compare(p1.getTravelTime(), p2.getTravelTime());	
}
