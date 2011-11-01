package simulation.behaviour.vehicle;

import java.awt.geom.Point2D;

public interface IMovingBehaviour {

	Point2D getTarget();

	double getSpeed();
	
}
