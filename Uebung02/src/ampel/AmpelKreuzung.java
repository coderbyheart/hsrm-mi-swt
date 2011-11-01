package ampel;

/**
 * Ampelkreuzung
 * 
 * Diese besteht aus vier Ampeln, zwei in Nord-Süd-Richtung 
 * und zwei in West-Ost-Richtung.
 * 
 * Nach der Initialisierung sind alle Ampeln der Kreuzung
 * rot.
 * 
 * Beim Durchschalten werden erst die Nord-Süd-Ampeln
 * durchgeschaltet. Wenn diese Rot sind, wird die Richtung
 * auf West-Ost gewechselt usw.
 */
public class AmpelKreuzung {
	
	private Ampel nord, sued, west, ost;
	
	private enum richtung {
		NORD_SUED,
		WEST_OST
	};
	
	private richtung aktRichtung = null;
	
	public AmpelKreuzung(){
		nord = new Ampel();
		sued = new Ampel();
		west = new Ampel();
		ost = new Ampel();
		this.aktRichtung = richtung.NORD_SUED;
	}
	
	public void tick(){
		if(aktRichtung == richtung.NORD_SUED){
			nord.tick();
			sued.tick();
			if(nord.isDefault()){
				aktRichtung = richtung.WEST_OST;
			}
		} else {
			west.tick();
			ost.tick();
			if(west.isDefault()){
				aktRichtung = richtung.NORD_SUED;
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void printStatus()
	{
		System.out.println("Nord: " + nord.getStatus());
		System.out.println("Süd:  " + sued.getStatus());
		System.out.println("West: " + west.getStatus());
		System.out.println("Ost:  " + ost.getStatus());
	}
	
	public void reset() {
		nord.reset();
		sued.reset();
		west.reset();
		ost.reset();
		aktRichtung = richtung.NORD_SUED;
	}
	

	public Ampel getNord() {
		return nord;
	}

	public Ampel getSued() {
		return sued;
	}

	public Ampel getWest() {
		return west;
	}

	public Ampel getOst() {
		return ost;
	}
}
