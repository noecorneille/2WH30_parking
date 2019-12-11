package tue.parking2WH30;

public class Main {
	public SimulationHandler sim;
	public final int N = 1000;
	
	public static void main(String... args) {
		Main m = new Main();
		
		m.sim = new SimulationHandler();
		
		double distance = 0;
		for(int i = 0; i < m.N; i++) {
			distance += m.sim.updateReturnDistance();
		}
		distance /= (double)m.N;
		System.out.println(distance);
	}
}