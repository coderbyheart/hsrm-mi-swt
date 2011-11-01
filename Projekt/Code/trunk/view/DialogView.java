package view;

/**
 * Basisklasse für Dialoge
 */
public abstract class DialogView implements View
{

	private boolean visible = false;
	/**
	 * Breite des Dialoges in Prozent
	 */
	private int width = 50;
	/**
	 * Höhe des Dialoges in Prozent
	 */
	private int height = 75;

	public boolean isVisible()
	{
		return this.visible;
	}

	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}

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

	/**
	 * Zeigt den Dialog an
	 */
	public void show()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Verbirgt den Dialog
	 */
	public void hide()
	{
		throw new UnsupportedOperationException();
	}
}