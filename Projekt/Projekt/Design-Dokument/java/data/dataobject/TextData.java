package data.dataobject;

/**
 * Data-Klasse für ein Text
 */
public class TextData extends DataObject
{

	private String id;
	private String text;

	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getText()
	{
		return this.text;
	}

	public void setText(String text)
	{
		this.text = text;
	}
}