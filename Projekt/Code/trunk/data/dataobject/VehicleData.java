package data.dataobject;

/**
 * Data-Object für ein Fahrzeug
 * 
 * @version $Id: VehicleData.java 238 2011-01-17 19:27:51Z mtack001 $
 * @author Markus Tacker <m@tacker.org>
 */
public class VehicleData extends DataObject {

	private String id;
	private String name;
	private String description;
	private String source;
	private float speed;

	/**
	 * Gibt die ID zurück
	 * 
	 * @return ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Setzt die ID
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gibt den Namen zurück
	 * 
	 * @return Name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Setzt den Namen
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gibt die Beschreibung zurück
	 * 
	 * @return Beschreibung
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Setzt die Beschreibung
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gibt die Quelldatei für die Grafik des Fahrzeuges zurück
	 * 
	 * @return Quelldatei
	 */
	public String getSource() {
		return this.source;
	}

	/**
	 * Setzt die Quelldatei für die Grafik des Fahrzeuges
	 * 
	 * @param source
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * Gibt die Geschwindigkeit zurück
	 * 
	 * @return Geschwindigkeit
	 */
	
	
	/**
	 * Erzeugt die String-Repräsentation des Objektes
	 * 
	 * @return string
	 */
	public String toString()
	{
		return String.format("%s #%s (%s) %s %.1f", VehicleData.class.getName(), getId(), getName(), getSource(), getSpeed());
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getSpeed() {
		return speed;
	}
}