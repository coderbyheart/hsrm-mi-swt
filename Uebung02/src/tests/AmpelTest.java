package tests;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import ampel.Ampel;

public class AmpelTest {

	@Test
	public void startTest() {
		Ampel myAmpel = new Ampel();
		assertTrue(myAmpel.isRot());
		assertFalse(myAmpel.isGelb());
		assertFalse(myAmpel.isGruen());
	}
	
	@Test
	public void tick1Test(){
		Ampel myAmpel = new Ampel();
		myAmpel.tick();
		assertTrue(myAmpel.isRot());
		assertTrue(myAmpel.isGelb());
		assertFalse(myAmpel.isGruen());
		resetAmpel(myAmpel);
	}
	
	@Test
	public void tick2Test(){
		Ampel myAmpel = new Ampel();
		myAmpel.tick();
		myAmpel.tick();
		assertFalse(myAmpel.isRot());
		assertFalse(myAmpel.isGelb());
		assertTrue(myAmpel.isGruen());
		resetAmpel(myAmpel);
	}
	
	@Test
	public void tick3Test(){
		Ampel myAmpel = new Ampel();
		myAmpel.tick();
		myAmpel.tick();
		myAmpel.tick();
		assertFalse(myAmpel.isRot());
		assertTrue(myAmpel.isGelb());
		assertFalse(myAmpel.isGruen());
		resetAmpel(myAmpel);
	}
	
	@Test
	public void tick4Test(){
		Ampel myAmpel = new Ampel();
		myAmpel.tick();
		myAmpel.tick();
		myAmpel.tick();
		myAmpel.tick();
		assertTrue(myAmpel.isRot());
		assertFalse(myAmpel.isGelb());
		assertFalse(myAmpel.isGruen());
		resetAmpel(myAmpel);
	}
	
	@Test
	public void resetTest(){
		Ampel myAmpel = new Ampel();
		for(int i = 0; i<(Math.random()*1000);i++){
			myAmpel.tick();
		}
		resetAmpel(myAmpel);
	}
	
	
	private void resetAmpel(Ampel myAmpel){
		myAmpel.reset();
		assertTrue(myAmpel.isRot());
		assertFalse(myAmpel.isGelb());
		assertFalse(myAmpel.isGruen());	
	}
}