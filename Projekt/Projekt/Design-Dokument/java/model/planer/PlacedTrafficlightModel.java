package model.planer;

/**
 * Model einer platzierten Ampel
 */
public class PlacedTrafficlightModel extends TrafficlightModel
{

	private TrafficlightPhase phase = model.planer.TrafficlightPhase.OFF;

	public TrafficlightPhase getPhase()
	{
		return this.phase;
	}

	public void setPhase(TrafficlightPhase phase)
	{
		this.phase = phase;
	}

	/**
	 * Gibt die Quelldatei für die aktuelle Phase zurück
	 * @return 
	 */
	public String getSourceForCurrentPhase()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Gibt die Quelldate für eine bestimmte Phase zurück
	 * @param phase
	 * @return 
	 */
	public String getSourceForPhase(TrafficlightPhase phase)
	{
		throw new UnsupportedOperationException();
	}
}