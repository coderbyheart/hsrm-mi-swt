package view;

import model.menu.*;

/**
 * View für das Menü
 */
public class MenuView extends View
{

	private MenuItemListModel model;

	public void setModel(MenuItemListModel model)
	{
		this.model = model;
	}
}