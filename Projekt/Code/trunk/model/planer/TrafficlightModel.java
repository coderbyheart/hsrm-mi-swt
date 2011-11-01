package model.planer;

import java.io.File;

/**
 * Model einer Ampel
 */
public class TrafficlightModel extends PlanerModel
{

	private int top;
	private int left;
	private int rotation;
	
	private File offSource;
	private File redSource;
	private File redYellowSource;
	private File greenSource;
	private File yellowSource;
	
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
			case ALERT: return getYellowSource();

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
	
	public void setOffSource(File offSource) {
		this.offSource = offSource;
	}
	public File getOffSource() {
		return offSource;
	}
	public void setRedSource(File redSource) {
		this.redSource = redSource;
	}
	public File getRedSource() {
		return redSource;
	}
	public void setRedYellowSource(File redYellowSource) {
		this.redYellowSource = redYellowSource;
	}
	public File getRedYellowSource() {
		return redYellowSource;
	}
	public void setGreenSource(File greenSource) {
		this.greenSource = greenSource;
	}
	public File getGreenSource() {
		return greenSource;
	}
	public void setYellowSource(File yellowSource) {
		this.yellowSource = yellowSource;
	}
	public File getYellowSource() {
		return yellowSource;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getTop() {
		return top;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getLeft() {
		return left;
	}
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	public int getRotation() {
		return rotation;
	}

	
}