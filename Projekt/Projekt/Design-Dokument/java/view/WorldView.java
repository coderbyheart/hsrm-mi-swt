package view;

import model.planer.*;

/**
 * Verwaltet die Kartenansicht
 */
public class WorldView extends View
{

	private int width;
	private int height;
	private WorldModel model;
	private WorldTileView[] tiles;

	public int getWidth()
	{
		return this.width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return this.height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public WorldModel getModel()
	{
		return this.model;
	}

	public void setModel(WorldModel model)
	{
		this.model = model;
	}
}