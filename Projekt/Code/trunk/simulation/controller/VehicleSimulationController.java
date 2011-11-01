package simulation.controller;

import model.planer.PlacedVehicleModel;
import model.planer.WorldModel;

/**
 * <p>
 * <b>Controller für das Fahren der Fahrzeuge</b>
 * <p>
 * Ablauf einer Fahrsimulation:
 * <ul>
 * <li>
 * Aus dem WorldModel holt sich der Controller alle ({@link WorldModel#getPlacedVehicles() platzierten Fahrzeuge})
 * und ruft deren {@link PlacedVehicleModel#move() move()-Methode} auf.
 * </ul>
 */
public class VehicleSimulationController extends Simulation
{
	public VehicleSimulationController(WorldModel world) {
		super(world);
	}

	/**
	 * @see Simulation#next()
	 */
	@Override
	public void next() {
		for(PlacedVehicleModel vehicle: world.getPlacedVehicles()) {
			vehicle.move();
		}
	}
}