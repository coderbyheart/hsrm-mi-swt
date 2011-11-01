package model.dialogue;

import view.ErrorDialogueView;

/**
 * Model für die {@link ErrorDialogueView}
 */
public class ErrorDialogueModel extends DialogueModel
{

	private String errorMessage;

	public String getErrorMessage()
	{
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}
}