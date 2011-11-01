package simulation.controller;

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
	 */
	public void start()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Stoppt die Simulation
	 */
	public void stop()
	{
		throw new UnsupportedOperationException();
	}
}