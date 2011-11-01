package model.dialogue;

import view.NewDialogueView;

/**
 * Model für die {@link NewDialogueView}
 */
public class NewDialogueModel extends DialogueModel
{

	private String okButton;
	private int maxHorTiles;
	private int maxVerTiles;

	public String getOkButton()
	{
		return this.okButton;
	}

	public void setOkButton(String okButton)
	{
		this.okButton = okButton;
	}

	public int getMaxHorTiles()
	{
		return this.maxHorTiles;
	}

	public void setMaxHorTiles(int maxHorTiles)
	{
		this.maxHorTiles = maxHorTiles;
	}

	public int getMaxVerTiles()
	{
		return this.maxVerTiles;
	}

	public void setMaxVerTiles(int maxVerTiles)
	{
		this.maxVerTiles = maxVerTiles;
	}
}