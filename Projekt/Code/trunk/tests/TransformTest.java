package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import simulation.behaviour.vehicle.PathFollowingBehaviourBase;
import util.Transform;

/**
 * Tests für {@link Transform}
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id$
 */
public class TransformTest {
	/**
	 * Testet {@link Transform#fixAngle(double)}
	 */
	@Test
	public void testFixAngle() {
		double a180 = Math.PI;
		double a90 = Math.PI / 2;
		double a45 = Math.PI / 4;
		
		assertTrue(Transform.fixAngle(10 * a180) == 0);
		
		assertTrue(Transform.fixAngle(-0) == 0);
		assertTrue(Transform.fixAngle(0) == 0);
		assertTrue(Transform.fixAngle(-a180) == a180);
		assertTrue(Transform.fixAngle(a180) == a180);
		assertTrue(Transform.fixAngle(a180 + a45) == -(a90 + a45));
		assertTrue(Transform.fixAngle(a180 + a90) == -(a90));
		assertTrue(Transform.fixAngle(a180 + a90 + a45) == -(a45));
		assertTrue(Transform.fixAngle(2 * a180) == 0);
		assertTrue(Transform.fixAngle(4 * a180) == 0);
		assertTrue(Transform.fixAngle(10 * a180) == 0);
		assertTrue(Transform.fixAngle(3 * a180) == a180);
		assertTrue(Transform.fixAngle(5 * a180) == a180);
		assertTrue(Transform.fixAngle(11 * a180) == a180);
		assertTrue(Transform.fixAngle(37 * a180) == a180);
	}

}
