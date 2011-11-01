package model;

import java.io.File;
import model.planer.PlanerModel;

/**
 * Basisklasse für alle Models, die eine Quelle besitzen
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: SourceModel.java 146 2010-12-30 20:40:36Z mtack001 $
 */
public abstract class SourceModel extends PlanerModel {

	private File source;

	/**
	 * Gibt die Quelldatei zurück
	 * 
	 * @return Quelldatei
	 */
	public File getSource() {
		return this.source;
	}

	/**
	 * Setzt die Quelldatei
	 * 
	 * @param source
	 */
	public void setSource(File source) {
		this.source = source;
	}
	
	/**
	 * Erzeugt die String-Repräsentation des Objektes
	 * 
	 * @return string
	 */
	public String toString()
	{
		return String.format("%s %s", this.getClass().getName(), getSource());
	}
}