package tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import org.junit.Before;
import org.junit.Test;
import data.DataFactory;
import data.dataobject.WorldData;
import data.datasource.DataSource;
import data.datasource.xml.XMLDataSource;

/**
 * Tests für den {@link DataFactory}
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: ToolBarController.java 140 2010-12-30 16:57:22Z mtack001 $
 */
public class DataFactoryTest extends Base {

	DataFactory df;

	/**
	 * setup
	 */
	@Before
	@Override
	public void setUp() {
		super.setUp();
		df = new DataFactory();
		DataSource ds = new XMLDataSource(new File("resource/xml"));
		df.setDataSource(ds);
	}

	/**
	 * Testet {@link DataFactory#getNewWorld(int, int)}
	 */
	@Test
	public void testNewWorld() {
		WorldData world = df.getNewWorld(5, 6);
		assertTrue(world.getWidth() == 5);
		assertTrue(world.getHeight() == 6);
		assertTrue(world.getTiles().size() == 5);
		assertTrue(world.getVehicles().size() == 3);
		assertTrue(world.getPlacedTiles().size() == 0);
		assertTrue(world.getBaseTile().getSource().equals("Basiskachel.svg"));
	}
}
