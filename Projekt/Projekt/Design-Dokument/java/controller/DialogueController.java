package controller;

import view.*;
import model.dialogue.*;

/**
 * Kümmert sich um die Anzeige der verschiedenen Dialoge
 */
public class DialogueController extends SubController
{

	private ErrorDialogueView error;
	private HelpDialogueView help;
	private LoadDialogueView load;
	private SaveDialogueView save;
	private NewDialogueView newWorld;

	/**
	 * Zeigt den Dialog zum Laden einer Welt an
	 * @return 
	 */
	public void showLoadDialogue()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Zeigt den Dialog zum Speichern einer Welt an
	 * @return 
	 */
	public void showSaveDialogue()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Zeigt den Hilfe-Dialog an
	 * @return 
	 */
	public void showHelpDialogue()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Zeigt den Dialog zum erzeugen einer neuen Welt an
	 * @return 
	 */
	public void showNewDialogue()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Zeigt den Fehler-Dialog an.
	 * @param model
	 * @return 
	 */
	public void showErrorDialoge(ErrorDialogueModel model)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Konstruktor
	 * @return 
	 */
	public DialogueController()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Schließt alle offenen Dialoge
	 * @return 
	 */
	public void closeAll()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Gibt den Save-Dialog zurück
	 * @return 
	 */
	public SaveDialogueView getSaveDialogue()
	{
		throw new UnsupportedOperationException();
	}
}