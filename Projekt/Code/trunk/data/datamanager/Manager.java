package data.datamanager;

import config.AppConfig;

/**
 * Basisklasse aller Manager für Daten
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: Manager.java 148 2010-12-31 14:43:15Z mtack001 $
 */
public abstract class Manager
{
	protected AppConfig config;
	
	/**
	 * Konstruktor
	 * 
	 * @param config
	 */
	public Manager(AppConfig config)
	{
		this.config = config;
	}
}