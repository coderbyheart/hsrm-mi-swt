package view;

/**
 * Verwaltet das Hauptfenster der Anwendung und erzeugt drei Unterviews für die Menüleiste, die Werkzeugleiste und die Welt
 */
public class AppView extends View
{

	private int width;
	private int height;
	private ToolbarView toolbarView;
	private MenuView menuView;
	private WorldView worldView;

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

	public ToolbarView getToolbarView()
	{
		return this.toolbarView;
	}

	public MenuView getMenuView()
	{
		return this.menuView;
	}

	public WorldView getWorldView()
	{
		return this.worldView;
	}
}