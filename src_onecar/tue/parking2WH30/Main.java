package tue.parking2WH30;

public class Main {
	public SimulationHandler sim;
	public final int N = 10;
	
	public static void main(String... args) {
		Main m = new Main();
		
		m.sim = new SimulationHandler();
		
		double distance = 0;
		for(int i = 0; i < m.N; i++) {
			double d = m.sim.updateReturnDistance();
			distance += d;
			//System.out.println(d);
			m.sim.printParkingLot();
		}
		distance /= (double)m.N;
		System.out.println(distance);
	}
}