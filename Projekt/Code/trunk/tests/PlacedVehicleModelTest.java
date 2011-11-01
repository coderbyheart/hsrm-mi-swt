package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import util.Transform;

import config.AppConfig;

import model.planer.PlacedVehicleModel;

/**
 * Tests für {@link PlacedVehicleModel}
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: PlacedVehicleModelTest.java 192 2011-01-12 21:01:19Z mtack001 $
 */
public class PlacedVehicleModelTest {

	/**
	 * Test für {@link PlacedVehicleModel#setRotation(double)}
	 */
	@Test
	public void testRotation()
	{
		PlacedVehicleModel vehicle = new PlacedVehicleModel(new AppConfig(), null);
		
		for(int deg = 1; deg < 360; deg++) {
			vehicle.setRotation(Math.toRadians(deg));
			assertTrue(Transform.fixAngle2Degree(vehicle.getRotation()) == Transform.fixAngle2Degree(Math.toRadians(deg)));
		}
		
		for(int deg = 0; deg < 4 * 360; deg++) {
			int n = deg / 360;
			vehicle.setRotation(Math.toRadians(deg));
			if (deg > n * 360 + 180) {
				assertTrue(Transform.fixAngle2Degree(vehicle.getRotation()) == Transform.fixAngle2Degree(Math.toRadians(-(360 - deg))));
			} else {
				assertTrue(Transform.fixAngle2Degree(vehicle.getRotation()) == Transform.fixAngle2Degree(Math.toRadians(deg)));
			}
		}
	}
}
