package tue.parking2WH30;

public class Main {
	public SimulationHandler sim;
	public ParkingLot p;
	
	public final int COLWIDTH = 5;
	public final int COLHEIGHT = 5;
	public final int COLNUMS = 5;
	
	public final int N = 1000;
	
	public static void main(String... args) {
		Main m = new Main();
		
		double[] initProb = new double[m.COLWIDTH * m.COLNUMS];
		
		for(int i = 0; i < m.COLWIDTH * m.COLNUMS; i++) {
			// TODO: initProb[i] = ...
		}
		
		m.p = new ParkingLot(m.COLWIDTH, m.COLHEIGHT, m.COLNUMS, initProb);
		m.sim = new SimulationHandler(m.p);
		
		for(int i = 0; i < m.N; i++) {
			m.sim.update();
		}
	}
}