package tests;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import ampel.Ampel;
import ampel.AmpelKreuzung;

public class AmpelKreuzungTest {

	/**
	 * Standard-Zustand der Ampelkreuzung nach Erzeugen:
	 * Alle Ampeln sind rot
	 */
	@Test
	public void startTest() {
		AmpelKreuzung myAmpelKreuzung = new AmpelKreuzung();

		Ampel nord = myAmpelKreuzung.getNord();
		Ampel sued = myAmpelKreuzung.getSued();
		Ampel west = myAmpelKreuzung.getWest();
		Ampel ost = myAmpelKreuzung.getOst();

		assertTrue(nord.isRot());
		assertTrue(sued.isRot());
		assertTrue(west.isRot());
		assertTrue(ost.isRot());
		assertFalse(nord.isGelb());
		assertFalse(sued.isGelb());
		assertFalse(west.isGelb());
		assertFalse(ost.isGelb());
		assertFalse(nord.isGruen());
		assertFalse(sued.isGruen());
		assertFalse(west.isGruen());
		assertFalse(ost.isGruen());
		resetAmpelKreuzung(myAmpelKreuzung);
	}

	@Test
	public void tick1Test() {
		AmpelKreuzung myAmpelKreuzung = new AmpelKreuzung();
		myAmpelKreuzung.tick();

		Ampel nord = myAmpelKreuzung.getNord();
		Ampel sued = myAmpelKreuzung.getSued();
		Ampel west = myAmpelKreuzung.getWest();
		Ampel ost = myAmpelKreuzung.getOst();

		assertTrue(nord.isRot());
		assertTrue(sued.isRot());
		assertTrue(west.isRot());
		assertTrue(ost.isRot());
		assertTrue(nord.isGelb());
		assertTrue(sued.isGelb());
		assertFalse(west.isGelb());
		assertFalse(ost.isGelb());
		assertFalse(nord.isGruen());
		assertFalse(sued.isGruen());
		assertFalse(west.isGruen());
		assertFalse(ost.isGruen());
		resetAmpelKreuzung(myAmpelKreuzung);
	}

	@Test
	public void tick2Test() {
		AmpelKreuzung myAmpelKreuzung = new AmpelKreuzung();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();

		Ampel nord = myAmpelKreuzung.getNord();
		Ampel sued = myAmpelKreuzung.getSued();
		Ampel west = myAmpelKreuzung.getWest();
		Ampel ost = myAmpelKreuzung.getOst();

		assertFalse(nord.isRot());
		assertFalse(sued.isRot());
		assertTrue(west.isRot());
		assertTrue(ost.isRot());
		assertFalse(nord.isGelb());
		assertFalse(sued.isGelb());
		assertFalse(west.isGelb());
		assertFalse(ost.isGelb());
		assertTrue(nord.isGruen());
		assertTrue(sued.isGruen());
		assertFalse(west.isGruen());
		assertFalse(ost.isGruen());
		resetAmpelKreuzung(myAmpelKreuzung);
	}

	@Test
	public void tick3Test() {
		AmpelKreuzung myAmpelKreuzung = new AmpelKreuzung();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();

		Ampel nord = myAmpelKreuzung.getNord();
		Ampel sued = myAmpelKreuzung.getSued();
		Ampel west = myAmpelKreuzung.getWest();
		Ampel ost = myAmpelKreuzung.getOst();

		assertFalse(nord.isRot());
		assertFalse(sued.isRot());
		assertTrue(west.isRot());
		assertTrue(ost.isRot());
		assertTrue(nord.isGelb());
		assertTrue(sued.isGelb());
		assertFalse(west.isGelb());
		assertFalse(ost.isGelb());
		assertFalse(nord.isGruen());
		assertFalse(sued.isGruen());
		assertFalse(west.isGruen());
		assertFalse(ost.isGruen());
		resetAmpelKreuzung(myAmpelKreuzung);
	}

	@Test
	public void tick4Test() {
		AmpelKreuzung myAmpelKreuzung = new AmpelKreuzung();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();

		Ampel nord = myAmpelKreuzung.getNord();
		Ampel sued = myAmpelKreuzung.getSued();
		Ampel west = myAmpelKreuzung.getWest();
		Ampel ost = myAmpelKreuzung.getOst();

		assertTrue(nord.isRot());
		assertTrue(sued.isRot());
		assertTrue(west.isRot());
		assertTrue(ost.isRot());
		assertFalse(nord.isGelb());
		assertFalse(sued.isGelb());
		assertFalse(west.isGelb());
		assertFalse(ost.isGelb());
		assertFalse(nord.isGruen());
		assertFalse(sued.isGruen());
		assertFalse(west.isGruen());
		assertFalse(ost.isGruen());
		resetAmpelKreuzung(myAmpelKreuzung);
	}

	@Test
	public void tick5Test() {
		AmpelKreuzung myAmpelKreuzung = new AmpelKreuzung();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();

		Ampel nord = myAmpelKreuzung.getNord();
		Ampel sued = myAmpelKreuzung.getSued();
		Ampel west = myAmpelKreuzung.getWest();
		Ampel ost = myAmpelKreuzung.getOst();

		assertTrue(nord.isRot());
		assertTrue(sued.isRot());
		assertTrue(west.isRot());
		assertTrue(ost.isRot());
		assertFalse(nord.isGelb());
		assertFalse(sued.isGelb());
		assertTrue(west.isGelb());
		assertTrue(ost.isGelb());
		assertFalse(nord.isGruen());
		assertFalse(sued.isGruen());
		assertFalse(west.isGruen());
		assertFalse(ost.isGruen());
		resetAmpelKreuzung(myAmpelKreuzung);
	}

	@Test
	public void tick6Test() {
		AmpelKreuzung myAmpelKreuzung = new AmpelKreuzung();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();

		Ampel nord = myAmpelKreuzung.getNord();
		Ampel sued = myAmpelKreuzung.getSued();
		Ampel west = myAmpelKreuzung.getWest();
		Ampel ost = myAmpelKreuzung.getOst();

		assertTrue(nord.isRot());
		assertTrue(sued.isRot());
		assertFalse(west.isRot());
		assertFalse(ost.isRot());
		assertFalse(nord.isGelb());
		assertFalse(sued.isGelb());
		assertFalse(west.isGelb());
		assertFalse(ost.isGelb());
		assertFalse(nord.isGruen());
		assertFalse(sued.isGruen());
		assertTrue(west.isGruen());
		assertTrue(ost.isGruen());
		resetAmpelKreuzung(myAmpelKreuzung);
	}

	@Test
	public void tick7Test() {
		AmpelKreuzung myAmpelKreuzung = new AmpelKreuzung();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();

		Ampel nord = myAmpelKreuzung.getNord();
		Ampel sued = myAmpelKreuzung.getSued();
		Ampel west = myAmpelKreuzung.getWest();
		Ampel ost = myAmpelKreuzung.getOst();

		assertTrue(nord.isRot());
		assertTrue(sued.isRot());
		assertFalse(west.isRot());
		assertFalse(ost.isRot());
		assertFalse(nord.isGelb());
		assertFalse(sued.isGelb());
		assertTrue(west.isGelb());
		assertTrue(ost.isGelb());
		assertFalse(nord.isGruen());
		assertFalse(sued.isGruen());
		assertFalse(west.isGruen());
		assertFalse(ost.isGruen());
		resetAmpelKreuzung(myAmpelKreuzung);
	}

	@Test
	public void tick8Test() {
		AmpelKreuzung myAmpelKreuzung = new AmpelKreuzung();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();
		myAmpelKreuzung.tick();

		Ampel nord = myAmpelKreuzung.getNord();
		Ampel sued = myAmpelKreuzung.getSued();
		Ampel west = myAmpelKreuzung.getWest();
		Ampel ost = myAmpelKreuzung.getOst();

		assertTrue(nord.isRot());
		assertTrue(sued.isRot());
		assertTrue(west.isRot());
		assertTrue(ost.isRot());
		assertFalse(nord.isGelb());
		assertFalse(sued.isGelb());
		assertFalse(west.isGelb());
		assertFalse(ost.isGelb());
		assertFalse(nord.isGruen());
		assertFalse(sued.isGruen());
		assertFalse(west.isGruen());
		assertFalse(ost.isGruen());
		resetAmpelKreuzung(myAmpelKreuzung);
	}
	

	
	
	private void resetAmpelKreuzung(AmpelKreuzung myAmpelKreuzung){
		myAmpelKreuzung.reset();
		
		Ampel nord = myAmpelKreuzung.getNord();
		Ampel sued = myAmpelKreuzung.getSued();
		Ampel west = myAmpelKreuzung.getWest();
		Ampel ost = myAmpelKreuzung.getOst();

		assertTrue(nord.isRot());
		assertTrue(sued.isRot());
		assertTrue(west.isRot());
		assertTrue(ost.isRot());
		assertFalse(nord.isGelb());
		assertFalse(sued.isGelb());
		assertFalse(west.isGelb());
		assertFalse(ost.isGelb());
		assertFalse(nord.isGruen());
		assertFalse(sued.isGruen());
		assertFalse(west.isGruen());
		assertFalse(ost.isGruen());
	}
	
	@Test
	public void resetTest(){
		AmpelKreuzung myAmpelKreuzung = new AmpelKreuzung();
		for(int i = 0; i<(Math.random()*1000);i++){
			myAmpelKreuzung.tick();
		}
		resetAmpelKreuzung(myAmpelKreuzung);
	}

}