package tests;

import static org.junit.Assert.assertTrue;
import model.planer.WorldModel;
import org.junit.Test;

import config.AppConfig;

/**
 * Tests für das {@link WorldModel}
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id$
 */
public class WorldModelTest {

	/**
	 * Testet {@link WorldModel#WorldModel(AppConfig, int, int)}
	 */
	@Test
	public void testEmptyWorld()
	{
		WorldModel worldModel = new WorldModel(new AppConfig(), 5, 6);
		assertTrue(worldModel.getWidth() == 5);
		assertTrue(worldModel.getHeight() == 6);
		assertTrue(worldModel.getPlacedTiles().size() == 0);
		assertTrue(worldModel.getTiles().size() == 0);
		assertTrue(worldModel.getVehicles().size() == 0);
	}
}
