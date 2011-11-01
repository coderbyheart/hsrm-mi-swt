package app;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class KurvenMap extends TestMap {
	private static final long serialVersionUID = 7491479717199292110L;

	public KurvenMap(String map) {
		super(map);
	}

	public static void main(String[] args) {
		// Set up logging
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);
		new TestMap("world-example-kurve"); 
	}

}
