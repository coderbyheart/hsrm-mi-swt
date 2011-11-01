package simulation.behaviour.vehicle;

import java.awt.geom.Point2D;

import model.planer.PlacedVehicleModel;

/**
 * Einfaches Bewegungsverhalten: In der aktuellen Richtung weiter fahren
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id$
 */
public class SimpleMovingBehaviour implements IMovingBehaviour {

	private PlacedVehicleModel vehicle;

	public SimpleMovingBehaviour(PlacedVehicleModel vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public Point2D getTarget() {
		double xDist = vehicle.getPixelSpeed() * Math.sin(vehicle.getRotation());
		double yDist = vehicle.getPixelSpeed() * -Math.cos(vehicle.getRotation());
		return new Point2D.Double(xDist, yDist);
	}

	@Override
	public double getSpeed() {
		return vehicle.getPixelSpeed();
	}

}
