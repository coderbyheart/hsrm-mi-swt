package model.planer;

/**
 * Model für ein Auto
 */
public class VehicleModel extends SourceModel
{

	private String name;
	private String description;
	private float speed;

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

	public float getSpeed()
	{
		return this.speed;
	}

	public void setSpeed(float speed)
	{
		this.speed = speed;
	}
}