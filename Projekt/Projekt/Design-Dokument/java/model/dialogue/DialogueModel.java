package model.dialogue;

import model.*;

/**
 * Basisklasse für alle Dialoge
 */
public abstract class DialogueModel extends Model
{

	private String title;
	private String cancelCloseButton;

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getCancelCloseButton()
	{
		return this.cancelCloseButton;
	}

	public void setCancelCloseButton(String cancelCloseButton)
	{
		this.cancelCloseButton = cancelCloseButton;
	}
}