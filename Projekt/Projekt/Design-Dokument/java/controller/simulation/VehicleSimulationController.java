package controller.simulation;

import model.planer.*;

/**
 * <p>
 * <b>Controller für das Fahren der Fahrzeuge</b>
 * <p>
 * Ablauf einer Fahrsimulation:
 * <ul>
 * <li>
 * Aus dem WorldModel holt sich der Controller alle platzierten Kacheln ({@link WorldModel#getPlacedTiles()})
 * und arbeitet sie nacheinander ab.
 * <li>
 * Wenn eine Kachel über platzierte Fahrzeuge verfügt werden alle
 * Fahrzeuge ({@link PlacedVehicleModel}) der Kachel ({@link PlacedTileModel#getPlacedVehicles()}) durchgegangen
 * und das Fahren wird Simuliert. ({@link VehicleSimulationController#driveVehicle(PlacedVehicleModel)} )
 * </ul>
 */
public class VehicleSimulationController extends Simulation
{

	/**
	 * Lässt alle Fahrzeuge auf der Welt fahren
	 * @return 
	 */
	public void vehiclesGo()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Lässt Fahrzeuge auf Welt fahren
	 * @param vehicle
	 * @param top
	 * @param left
	 * @param rotation
	 * @return 
	 */
	public void driveVehicle(PlacedVehicleModel vehicle)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Überprüft, ob das Fahrzeug seine Kachel wechselt
	 * @param vehicle
	 * @return 
	 */
	public void checkTile(PlacedVehicleModel vehicle)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Überprüft Weg für das Fahrzeug. Kachel kann verschiednene Abbiegungen haben,
	 * oder ein Ende, and dem das Fahrzeug wieder umkehrt.
	 * @param vehicle
	 * @return 
	 */
	public void checkWay(PlacedVehicleModel vehicle)
	{
		throw new UnsupportedOperationException();
	}
}