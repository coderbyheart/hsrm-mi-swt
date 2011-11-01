package model.planer;

import model.SourceModel;

/**
 * Model für ein Fahrzeug
 */
public class VehicleModel extends SourceModel
{
	private String id;
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

	

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getSpeed() {
		return speed;
	}

	
}