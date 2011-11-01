package model.planer;

import java.io.File;

/**
 * Model einer platzierten Ampel
 */
public class PlacedTrafficlightModel extends TrafficlightModel
{

	private TrafficlightPhase phase = model.planer.TrafficlightPhase.OFF;
	public static final int PHASE_CHANGED = 5;


	public TrafficlightPhase getPhase()
	{
		return this.phase;
	}

	public void setPhase(TrafficlightPhase phase)
	{
		this.phase = phase;
		setChanged();
		notifyObservers(PHASE_CHANGED);
		
	}

	/**
	 * Gibt die Quelldatei für die aktuelle Phase zurück
	 */
	public File getSourceForCurrentPhase()
	{
		return getSourceForPhase(this.phase);
	}

	/**
	 * Gibt die Quelldate für eine bestimmte Phase zurück
	 * @param phase
	 */
	public File getSourceForPhase(TrafficlightPhase phase)
	{
		switch(phase){
			case OFF: return getOffSource();
			case RED: return getRedSource();
			case RED_YELLOW: return getRedYellowSource();
			case GREEN: return getGreenSource();
			case YELLOW: return getYellowSource();
		}
		
		//Sollte hier nicht ankommen
		return null;
	}
	
	public PlacedTrafficlightModel clone(){
		
		PlacedTrafficlightModel clone = new PlacedTrafficlightModel();	
		
		clone.setOffSource(getOffSource());
		clone.setRedSource(getRedSource());
		clone.setRedYellowSource(getRedYellowSource());
		clone.setGreenSource(getGreenSource());
		clone.setYellowSource(getYellowSource());
		
		return clone;
	}
	
}