package controller.simulation;

import model.planer.PlacedTileModel;
import model.planer.PlacedTrafficlightModel;
import model.planer.RouteModel;
import model.planer.TrafficlightPhase;
import model.planer.WorldModel;

/**
 * <p>
 * <b>Controller für die Ampelschaltung</b>
 * <p>
 * Ablauf einer Ampelsimulation:
 * <ul>
 * <li>
 * Aus dem WorldModel holt sich der Controller alle
 * platzierten Kacheln ({@link WorldModel#getPlacedTiles()})
 * <li>
 * Wenn diese Kacheln über eine Ampel verfügt und diese auch aktiv ist
 * ({@link PlacedTileModel#isTrafficlightEnabled()}) werden alle
 * Strecken ({@link RouteModel}) der Kachel ({@link PlacedTileModel#getRoutes()}) durchgegangen
 * und dort die Ampeln ({@link PlacedTrafficlightModel}) entsprechend des hier definieret
 * Ablaufes geschaltet.
 * <li>
 * Der Controller merkt sich dazu, ob der Ampelverbund einer
 * Kachel bereits initialisiert wurde und setzt dann die Ampeln
 * in die entsprechende Phase gesetzt {@link PlacedTrafficlightModel#setPhase(TrafficlightPhase)}
 * </ul>
 */
public class TrafficLightSimulationController extends Simulation
{

	private int redPhaseDuration = 5;
	private int redYellowPhaseDuration = 1;
	private int greenPhaseDuration = 5;
	private int yellowDuration = 1;

	public int getRedPhaseDuration()
	{
		return this.redPhaseDuration;
	}

	public void setRedPhaseDuration(int redPhaseDuration)
	{
		this.redPhaseDuration = redPhaseDuration;
	}

	public int getRedYellowPhaseDuration()
	{
		return this.redYellowPhaseDuration;
	}

	public void setRedYellowPhaseDuration(int redYellowPhaseDuration)
	{
		this.redYellowPhaseDuration = redYellowPhaseDuration;
	}

	public int getGreenPhaseDuration()
	{
		return this.greenPhaseDuration;
	}

	public void setGreenPhaseDuration(int greenPhaseDuration)
	{
		this.greenPhaseDuration = greenPhaseDuration;
	}

	public int getYellowDuration()
	{
		return this.yellowDuration;
	}

	public void setYellowDuration(int yellowDuration)
	{
		this.yellowDuration = yellowDuration;
	}
}