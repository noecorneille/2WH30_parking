package tue.parking2WH30;

public class Main {
	public SimulationHandler sim;
	public final int N = 10;
	
	public static void main(String... args) {
		Main m = new Main();
		
		m.sim = new SimulationHandler();
		
		double time = 0;
		for(int i = 0; i < m.N; i++) {
			double t = m.sim.updateReturnTime();
			time += t;
			//System.out.println(d);
			m.sim.printParkingLot();
		}
		time /= (double)m.N;
		System.out.println(time);
	}
}