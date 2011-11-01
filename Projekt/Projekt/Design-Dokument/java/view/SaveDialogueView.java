package view;

import model.dialogue.*;

/**
 * View für den Welt-Speichern-Dialog
 */
public class SaveDialogueView
{

	private SaveDialogueModel model;

	public void setModel(SaveDialogueModel model)
	{
		this.model = model;
	}

	/**
	 * Gibt den ausgewählten Dateinamen zurück
	 * @return 
	 */
	public String getFilename()
	{
		throw new UnsupportedOperationException();
	}
}