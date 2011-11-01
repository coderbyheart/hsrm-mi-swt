package data.dataobject;

/**
 * Data-Klasse für ein Text
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: TextData.java 142 2010-12-30 18:00:41Z mtack001 $
 */
public class TextData extends DataObject {

	private String id;
	private String text;
	private String group;
	private String language;
	private TextData original;

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
	 * Gibt den übersetzten Text zurück
	 * 
	 * @return Text
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Setzt den übersetzten Text
	 * 
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Setzt den Original-Text
	 * 
	 * @param original
	 */
	public void setOriginal(TextData original) {
		this.original = original;
	}

	/**
	 * Gibt den Original-Text zurück
	 * 
	 * @return original
	 */
	public TextData getOriginal() {
		return original;
	}

	/**
	 * Setzt die Sprache der Übersetzung
	 * 
	 * @param language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Gibt die Sprache der Übersetzung zurück
	 * 
	 * @return Sprache
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Setzt die Gruppe der Übersetzung
	 * 
	 * @param group
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * Gibt die Gruppe der Übersetzung zurück
	 * 
	 * @return group
	 */
	public String getGroup() {
		return group;
	}
	
	/**
	 * Erzeugt die String-Repräsentation des Objektes
	 * 
	 * @return string
	 */
	public String toString()
	{
		return String.format("%s #%s (%s: %s)", TextData.class.getName(), getId(), getLanguage(), getText());
	}
}