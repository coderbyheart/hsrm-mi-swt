package controller;

import data.*;
import view.*;

/**
 * Basisklasse für alle Untercontroller
 */
public abstract class SubController
{

	private DataFactory dataFactory;
	private View view;
	private GUIController master;

	public DataFactory getDataFactory()
	{
		return this.dataFactory;
	}

	public void setDataFactory(DataFactory dataFactory)
	{
		this.dataFactory = dataFactory;
	}

	public View getView()
	{
		return this.view;
	}

	public void setView(View view)
	{
		this.view = view;
	}

	public GUIController getMaster()
	{
		return this.master;
	}

	public void setMaster(GUIController master)
	{
		this.master = master;
	}
}