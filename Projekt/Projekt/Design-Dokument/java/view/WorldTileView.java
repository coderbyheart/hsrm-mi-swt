package view;

import model.planer.*;

/**
 * <p>
 * <b>Verwaltet die Ansicht der einzelnen Kacheln</b>
 * <p>
 * Wenn die Ampel-Version der Kachel aktiv ist {@link PlacedTileModel#isTrafficlightEnabled()} werden
 * zu allen Strecken {@link PlacedTileModel#getRoutes()}, die über eine Ampel verfügen
 * {@link LocationModel#hasTrafficLight()} Ampeln gezeichnet.
 * <p>
 * Dies geschieht, in dem in Fahrtrichtung Rechts neben den Endpunkt einer Strecke {@link RouteModel}
 * die Ampel in der jeweils aktuellen Phase {@link PlacedTrafficlightModel#getPhase()} eine Grafik
 * gezeichnet wird {@link PlacedTrafficlightModel#getSourceForCurrentPhase()}.
 */
public class WorldTileView extends View
{

	private WorldView world;
	private PlacedTileModel model;
	private PlacedTrafficlightModel[] trafficLights;

	public WorldView getWorld()
	{
		return this.world;
	}

	public void setWorld(WorldView world)
	{
		this.world = world;
	}

	public PlacedTileModel getModel()
	{
		return this.model;
	}

	public void setModel(PlacedTileModel model)
	{
		this.model = model;
	}
}