package controller;

import controller.simulation.*;
import data.DataFactory;
import view.*;

/**
 * Basis-Klasse der Anwendung
 * 
 * Der GUIController erzeugt die {@link SubController} und übergibt ihnen die {@link DataFactory} sowie die {@link View} mit der sie arbeiten
 */
public class GUIController
{

	private ToolBarController toolbar;
	private MenuController menu;
	private DialogueController dialogue;
	private WorldController world;
	private SimulationController simulation;
	private AppView view;
	private int maxVerTiles;
	private int maxHorTiles;
	private String language;

	public int getMaxVerTiles()
	{
		return this.maxVerTiles;
	}

	public int getMaxHorTiles()
	{
		return this.maxHorTiles;
	}

	public String getLanguage()
	{
		return this.language;
	}

	public void setLanguage(String language)
	{
		this.language = language;
	}

	/**
	 * Erzeugt den Controller und die dazugehörigen Untercontroller
	 * @return 
	 */
	public GUIController()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Gibt den Dialog-Controller zurück
	 * @return 
	 */
	protected DialogueController getDialogueController()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Öffnet den Dialiog zum Laden einer Welt und lädt dann anschliepend die Welt
	 * @return 
	 */
	public void loadWorldAction()
	{
		throw new UnsupportedOperationException();
	}
}