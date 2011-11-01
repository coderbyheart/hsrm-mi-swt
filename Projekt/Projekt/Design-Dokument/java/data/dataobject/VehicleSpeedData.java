package data.dataobject;

/**
 * Data-Klasse für die Geschwindigkeit eines Fahrzeuges
 */
public class VehicleSpeedData extends DataObject
{

	private String id;
	private float seconds;

	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public float getSeconds()
	{
		return this.seconds;
	}

	public void setSeconds(float seconds)
	{
		this.seconds = seconds;
	}
}