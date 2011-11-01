package controller.simulation;

/**
 * Kümmert sich um die Simulation
 */
public class SimulationController
{

	private boolean running;
	private TrafficLightSimulationController trafficLightSim;
	private VehicleSimulationController vehicleSim;

	public boolean isRunning()
	{
		return this.running;
	}

	public void setRunning(boolean running)
	{
		this.running = running;
	}

	/**
	 * Startet die Simulation
	 * @return 
	 */
	public void start()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Stoppt die Simulation
	 * @return 
	 */
	public void stop()
	{
		throw new UnsupportedOperationException();
	}
}