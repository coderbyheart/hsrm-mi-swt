package model.menu;

import model.*;

/**
 * Enthält die Einträge der Menüleiste
 */
public class MenuItemListModel extends Model
{

	/**
	 * Enthält die Einträge des Menüs
	 */
	private String[] items;

	public String[] getItems()
	{
		return this.items;
	}

	public void setItems(String[] items)
	{
		this.items = items;
	}
}