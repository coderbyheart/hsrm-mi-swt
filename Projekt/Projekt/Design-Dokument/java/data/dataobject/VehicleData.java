package data.dataobject;

/**
 * Data-Klasse für ein Fahrzeug
 */
public class VehicleData extends DataObject
{

	private String id;
	private String name;
	private String description;
	private String source;
	private VehicleSpeedData speed;

	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getSource()
	{
		return this.source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	/**
	 * 
	 * @return 
	 */
	public VehicleSpeedData getSpeed()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param speed
	 * @return 
	 */
	public void setSpeed(VehicleSpeedData speed)
	{
		throw new UnsupportedOperationException();
	}
}