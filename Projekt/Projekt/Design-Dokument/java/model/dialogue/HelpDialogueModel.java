package model.dialogue;

import view.HelpDialogueView;

/**
 * Model für die {@link HelpDialogueView}
 */
public class HelpDialogueModel extends DialogueModel
{

	private String helpInfo;
	private String authors;
	private String version;

	public String getHelpInfo()
	{
		return this.helpInfo;
	}

	public void setHelpInfo(String helpInfo)
	{
		this.helpInfo = helpInfo;
	}

	public String getAuthors()
	{
		return this.authors;
	}

	public void setAuthors(String authors)
	{
		this.authors = authors;
	}

	public String getVersion()
	{
		return this.version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}
}