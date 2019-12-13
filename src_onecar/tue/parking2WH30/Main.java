package tue.parking2WH30;

public class Main {
	public SimulationHandler sim;
	public final int N = 100;
	
	public void simulation() {
		sim = new SimulationHandler();
		
		// TODO: data vis
		double time = 0;
		for(int i = 0; i < N; i++) {
			sim.printParkingLot();
			double t = sim.updateReturnTime();
			time += t;
			//System.out.println(d);
		}
		sim.printParkingLot();
		time /= (double)N;
		System.out.println(time);
	}
	
	public static void main(String... args) {
		(new Main()).simulation();		
	}
}