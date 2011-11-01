package view.controller;

import app.GUIController;
import config.AppConfig;
import data.*;
import view.*;

/**
 * Basisklasse für alle Untercontroller
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: SubController.java 251 2011-01-19 08:58:39Z mtack001 $
 */
public abstract class SubController
{
	private DataFactory dataFactory;
	protected AppConfig config;

	public SubController(DataFactory dataFactory,
			AppConfig config) {
		this.config = config;
		setDataFactory(dataFactory);
	}

	/**
	 * Gibt die {@link DataFactory} zurück
	 * @return DataFactory
	 */
	public DataFactory getDataFactory()
	{
		return this.dataFactory;
	}

	/**
	 * Setzt die {@link DataFactory}
	 * @param dataFactory
	 */
	public void setDataFactory(DataFactory dataFactory)
	{
		this.dataFactory = dataFactory;
		load();
	}

	/**
	 * Wird aufgerufen, wenn eine {@link DataFactory} verfügbar ist
	 */
	protected abstract void load();
}