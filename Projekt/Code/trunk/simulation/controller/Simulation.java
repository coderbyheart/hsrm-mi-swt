package simulation.controller;

import model.planer.*;

/**
 * Basisklasse für alle Simulations-Controller
 */
public abstract class Simulation
{
	protected WorldModel world;
	
	public Simulation(WorldModel world)
	{
		this.world = world;
	}
	
	/**
	 * Die Simulation einen Schritt weiter laufen lassen
	 */
	public abstract void next();
}