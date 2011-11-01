package view.controller;

import app.GUIController;
import config.AppConfig;
import data.DataFactory;
import view.*;
import model.dialogue.*;

/**
 * Kümmert sich um die Anzeige der verschiedenen Dialoge
 * 
 * @version $Id: DialogueController.java 251 2011-01-19 08:58:39Z mtack001 $
 * @author Markus Tacker <m@tacker.org>
 */
public class DialogueController extends SubController {
	private ErrorDialogueView error;
	private HelpDialogueView help;
	private LoadDialogueView load;
	private SaveDialogueView save;
	private NewDialogueView newWorld;

	/**
	 * Konstruktor
	 *  
	 * @todo Dialog-Views erzeugen
	 * @param dataFactory
	 * @param config
	 */
	public DialogueController(DataFactory dataFactory, AppConfig config) {
		super(dataFactory, config);
	}

	/**
	 * Zeigt den Dialog zum Laden einer Welt an
	 */
	public void showLoadDialogue() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Zeigt den Dialog zum Speichern einer Welt an
	 */
	public void showSaveDialogue() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Zeigt den Hilfe-Dialog an
	 */
	public void showHelpDialogue() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Zeigt den Dialog zum erzeugen einer neuen Welt an
	 */
	public void showNewDialogue() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Zeigt den Fehler-Dialog an.
	 * 
	 * @param model
	 */
	public void showErrorDialoge(ErrorDialogueModel model) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Schließt alle offenen Dialoge
	 */
	public void closeAll() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Gibt den {@link SaveDialogueView Save-Dialog} zurück
	 * 
	 * @return SaveDialogueView
	 */
	public SaveDialogueView getSaveDialogue() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @see SubController#load()
	 * @todo Texte für die Dialoge laden
	 */
	@Override
	protected void load() {
		DataFactory df = getDataFactory();
	}
}