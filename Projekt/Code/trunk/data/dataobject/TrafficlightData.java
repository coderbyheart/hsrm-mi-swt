package data.dataobject;

/**
 * Data-Klasse für eine Ampel
 */
public class TrafficlightData extends DataObject
{

	private String offSource;
	private String redSource;
	private String redYellowSource;
	private String greenSource;
	private String yellowSource;

	public String getOffSource()
	{
		return this.offSource;
	}

	public void setOffSource(String offSource)
	{
		this.offSource = offSource;
	}

	public String getRedSource()
	{
		return this.redSource;
	}

	public void setRedSource(String redSource)
	{
		this.redSource = redSource;
	}

	public String getRedYellowSource()
	{
		return this.redYellowSource;
	}

	public void setRedYellowSource(String redYellowSource)
	{
		this.redYellowSource = redYellowSource;
	}

	public String getGreenSource()
	{
		return this.greenSource;
	}

	public void setGreenSource(String greenSource)
	{
		this.greenSource = greenSource;
	}

	public String getYellowSource()
	{
		return this.yellowSource;
	}

	public void setYellowSource(String yellowSource)
	{
		this.yellowSource = yellowSource;
	}
}