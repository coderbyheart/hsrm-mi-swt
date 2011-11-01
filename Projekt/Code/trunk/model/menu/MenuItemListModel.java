package model.menu;

import java.util.HashMap;

import data.TextIds;

/**
 * Enthält die Texte für die Einträge der Menüleiste
 * 
 * Das Model ist eine Hashmap mit den IDs der Strings aus key und den
 * übersetzten Texten als value.
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: MenuItemListModel.java 237 2011-01-17 19:00:22Z mtack001 $
 */
@SuppressWarnings("serial")
public class MenuItemListModel extends HashMap<String, String> {
	public MenuItemListModel() {
		super();
	}
}