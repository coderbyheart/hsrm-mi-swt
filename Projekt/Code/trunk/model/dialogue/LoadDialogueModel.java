package model.dialogue;

import view.LoadDialogueView;

/**
 * Model für die {@link LoadDialogueView}
 */
public class LoadDialogueModel extends DialogueModel
{

	private String okButton;
	private String filename;

	public String getOkButton()
	{
		return this.okButton;
	}

	public void setOkButton(String okButton)
	{
		this.okButton = okButton;
	}

	public String getFilename()
	{
		return this.filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}
}