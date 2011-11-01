package model.planer;

/**
 * Basisklasse für alle Models, die eine Quelle besitzen
 */
public abstract class SourceModel extends PlanerModel
{

	private String source;

	public String getSource()
	{
		return this.source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}
}