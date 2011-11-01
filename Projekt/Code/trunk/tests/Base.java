package tests;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;

/**
 * Basisklasse für alle tests.
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: Base.java 149 2010-12-31 14:51:43Z mtack001 $
 */
public abstract class Base {

	/**
	 * Logging einrichten
	 */
	@Before
	public void setUp() {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.INFO);
	}
}
