package view;

import model.planer.*;

/**
 * View für die Werkzeugleiste
 */
public class ToolbarView extends View
{

	private TrashModel trash;
	private TileModel[] tiles;
	private VehicleModel[] vehicles;

	public void setTrash(TrashModel trash)
	{
		this.trash = trash;
	}

	public void setTiles(TileModel[] tiles)
	{
		this.tiles = tiles;
	}

	public void setVehicles(VehicleModel[] vehicles)
	{
		this.vehicles = vehicles;
	}
}