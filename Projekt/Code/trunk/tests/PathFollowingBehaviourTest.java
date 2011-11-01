package tests;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import model.planer.PlacedVehicleModel;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import config.AppConfig;

import simulation.behaviour.vehicle.PathFollowingBehaviour;
import simulation.behaviour.vehicle.PathFollowingBehaviourBase;
import util.PointDistance;
import util.Transform;

/**
 * Tests für {@link PathFollowingBehaviour}
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: AppViewTest.java 150 2010-12-31 17:12:04Z mtack001 $
 */
public class PathFollowingBehaviourTest extends Base {

	/**
	 * Testet {@link PathFollowingBehaviourBase#getAngle(PointDistance)}
	 */
	@Test
	public void testCheckPoint() {
		ArrayList<PointDistance> points = new ArrayList<PointDistance>();

		double a360 = Math.PI * 2;
		double a90 = Math.PI / 2;
		double a180 = Math.PI;
		double a45 = Math.PI / 4;

		// Fzg ist bei 0x0
		// Links von mir
		PointDistance left = new PointDistance(new Point2D.Double(-100, 0), 100);
		// Vor mir
		PointDistance front = new PointDistance(new Point2D.Double(0, -100),
				100);
		// Rechts von mir
		PointDistance right = new PointDistance(new Point2D.Double(100, 0), 100);
		// Hinter mir
		PointDistance behind = new PointDistance(new Point2D.Double(0, 100),
				100);

		AppConfig config = new AppConfig();
		Logger.getRootLogger().setLevel(Level.INFO);
		config.setDebugLogVehicles(true);
		PlacedVehicleModel vehicle = new PlacedVehicleModel(config, null);
		PathFollowingBehaviourBase behaviour = new PathFollowingBehaviourBase(
				config, vehicle);

		Double frontAngle = behaviour.getAngle(front);
		Double rightAngle = behaviour.getAngle(right);
		Double behindAngle = behaviour.getAngle(behind);
		Double leftAngle = behaviour.getAngle(left);

		// Alle Punkte stehe im 90°-Winkel zueinenander
		assertTrue((rightAngle - frontAngle) % a90 == 0);
		assertTrue((behindAngle - rightAngle) % a90 == 0);
		assertTrue((leftAngle - behindAngle) % a90 == 0);
		assertTrue((frontAngle - leftAngle) % a90 == 0);

		assertTrue(behaviour.getAngle(front) == 0);
		assertTrue(behaviour.getAngle(right) == a90);
		assertTrue(behaviour.getAngle(behind) == a180);
		assertTrue(behaviour.getAngle(left) == -(a90));

		// Um 45 Grad nach rechts drehen
		int offsetDeg = 45;
		while (offsetDeg <= 180) {
			double offset = Math.toRadians(offsetDeg);
			vehicle.setRotation(offset);

			frontAngle = behaviour.getAngle(front);
			rightAngle = behaviour.getAngle(right);
			behindAngle = behaviour.getAngle(behind);
			leftAngle = behaviour.getAngle(left);

			assertTrue(Transform.toRoundedDegrees(frontAngle) == Transform
					.fixAngle2Degree(0 - offset));
			assertTrue(Transform.toRoundedDegrees(rightAngle) == Transform
					.fixAngle2Degree(a90 - offset));
			assertTrue(Transform.toRoundedDegrees(behindAngle) == Transform
					.fixAngle2Degree(a180 - offset));
			assertTrue(Transform.toRoundedDegrees(leftAngle) == Transform
					.fixAngle2Degree(-a90 - offset));

			offsetDeg += 45;
		}
	}
}
