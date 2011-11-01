package exception;

/**
 * Wird geworfen, wenn versucht wird, eine Welt zu laden, die zu Groß ist
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id$
 */
@SuppressWarnings("serial")
public class WorldTooLargeException extends WorldControllerException {

	private String name;
	
	/**
	 * @param name Name der Welt, die nicht geladen werden konnte
	 */
	public WorldTooLargeException(String name) {
		setName(name);
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
