package ampel;

public class Ampel {
	
	private boolean rot;
	private boolean gelb;
	private boolean gruen;
	private zustand aktZustand;
	
	private enum zustand {
		ROT,
		ROTGELB,
		GRUEN,
		GELB
	};

	public Ampel()
	{
		this.rot = true;
		this.gelb = false;
		this.gruen = false;
		this.aktZustand = zustand.ROT;
	}

	public boolean isRot() {
		return rot;
	}

	public boolean isGelb() {
		return gelb;
	}

	public boolean isGruen() {
		return gruen;
	}
	
	public void tick()
	{
		switch(aktZustand) {
		case ROT:
			aktZustand = zustand.ROTGELB;
			this.rot = true;
			this.gelb = true;
			this.gruen = false;
			break;
		case ROTGELB:
			aktZustand = zustand.GRUEN;
			this.rot = false;
			this.gelb = false;
			this.gruen = true;
			break;
		case GRUEN:
			aktZustand = zustand.GELB;
			this.rot = false;
			this.gelb = true;
			this.gruen = false;
			break;
		case GELB:
			this.reset();
			break;
		}
	}
	
	public void reset()
	{
		aktZustand = zustand.ROT;
		this.rot = true;
		this.gelb = false;
		this.gruen = false;
	}
	
	public boolean isDefault(){
		return isRot() && !isGelb() && !isGruen();
	}
	
	public String getStatus()
	{
		switch(aktZustand) {
		case ROTGELB:
			return "Rot/Gelb";
		case GRUEN:
			return "Grün";
		case GELB:
			return "Gelb";
		default:
			return "Rot";
		}
	}
}
