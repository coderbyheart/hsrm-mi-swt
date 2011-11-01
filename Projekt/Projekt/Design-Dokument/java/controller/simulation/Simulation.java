package controller.simulation;

import model.planer.*;

/**
 * Basisklasse für alle Simulations-Controller
 */
public abstract class Simulation
{

	private WorldModel world;

	public WorldModel getWorld()
	{
		return this.world;
	}

	public void setWorld(WorldModel world)
	{
		this.world = world;
	}

	/**
	 * Aktualisiert die Daten in der Welt
	 * @return 
	 */
	public void update()
	{
		throw new UnsupportedOperationException();
	}
}