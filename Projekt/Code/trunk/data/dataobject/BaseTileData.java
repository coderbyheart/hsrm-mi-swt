package data.dataobject;

import java.io.File;

/**
 * Data-Klasse für Basis-Kachel
 */
public class BaseTileData extends DataObject
{

	private String source;

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}
}