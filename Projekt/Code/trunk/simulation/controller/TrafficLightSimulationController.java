package simulation.controller;

import model.planer.PlacedTileModel;
import model.planer.PlacedTrafficlightModel;
import model.planer.PlacedVehicleModel;
import model.planer.RouteModel;
import model.planer.TileModel;
import model.planer.TrafficlightModel;
import model.planer.TrafficlightPhase;
import model.planer.WorldModel;

/**
 * <p>
 * <b>Controller für die Ampelschaltung</b>
 * <p>
 * Ablauf einer Ampelsimulation:
 * <ul>
 * <li>
 * Aus dem WorldModel holt sich der Controller alle {@link WorldModel#getPlacedTiles() platzierten Kacheln}.
 * <li>
 * Wenn diese Kacheln über eine Ampel verfügt und diese auch {@link PlacedTileModel#isTrafficlightsEnabled() aktiv ist}, werden alle
 * {@link RouteModel Strecken} {@link PlacedTileModel#getRoutes() auf der Kachel} durchgegangen
 * und dort die {@link PlacedTrafficlightModel Ampeln } entsprechend des hier definierten
 * Ablaufes geschaltet.
 * <li>
 * Der Controller merkt sich dazu, ob der Ampelverbund einer
 * Kachel bereits initialisiert wurde und setzt dann die Ampeln
 * in die entsprechende {@link PlacedTrafficlightModel#setPhase(TrafficlightPhase) Phase} gesetzt.
 * </ul>
 * 
 * Die Simulation beschreibt eine Ampelsituation einer Kreuzung (T-Kreuzung oder Kreuzung)
 */
public class TrafficLightSimulationController extends Simulation
{
	public TrafficLightSimulationController(WorldModel world) {
		super(world);
	}

	private int phase1 = 5;
	private int phase2 = 1;
	private boolean go_switch = true;
	
	private int phaseAlert = 1;


	public void next() {
		//Ampelschaltung für Kreuzung
		for(PlacedTileModel tile : world.getPlacedTiles()){
			if(!tile.getTrafficlights().isEmpty()){	
				if(go_switch){
					if(phase1 > 0) setCrossPhase(1,tile);
					else if(phase2 > 0) setCrossPhase(2,tile);	
				}else{
					if(phase1 > 0) setCrossPhase(3,tile);
					else if(phase2 > 0) setCrossPhase(4,tile);	
				}
			}
		}
		if(phase1>0) phase1--;
		else if(phase2>0) phase2--;
		
		if(phase1==0&&phase2==0){
			phase1 = 5;
			phase2 = 2;
			go_switch = !go_switch;
		}
	}	

	private void setCrossPhase(int i, PlacedTileModel tile) {
		switch(i){
		case 1: tile.getTrafficlights().get(0).setPhase(TrafficlightPhase.GREEN);
				tile.getTrafficlights().get(1).setPhase(TrafficlightPhase.GREEN);
				tile.getTrafficlights().get(2).setPhase(TrafficlightPhase.RED);
				if(tile.getName().equals("Kreuzung")) {
					tile.getTrafficlights().get(3).setPhase(TrafficlightPhase.RED);
				}
				break;
		case 2: tile.getTrafficlights().get(0).setPhase(TrafficlightPhase.YELLOW);
				tile.getTrafficlights().get(1).setPhase(TrafficlightPhase.YELLOW);
				tile.getTrafficlights().get(2).setPhase(TrafficlightPhase.RED_YELLOW);
				if(tile.getName().equals("Kreuzung")) tile.getTrafficlights().get(3).setPhase(TrafficlightPhase.RED_YELLOW);
				break;
		case 3: tile.getTrafficlights().get(0).setPhase(TrafficlightPhase.RED);
				tile.getTrafficlights().get(1).setPhase(TrafficlightPhase.RED);
				tile.getTrafficlights().get(2).setPhase(TrafficlightPhase.GREEN);
				if(tile.getName().equals("Kreuzung"))tile.getTrafficlights().get(3).setPhase(TrafficlightPhase.GREEN);
				break;
		case 4: tile.getTrafficlights().get(0).setPhase(TrafficlightPhase.RED_YELLOW);
				tile.getTrafficlights().get(1).setPhase(TrafficlightPhase.RED_YELLOW);
				tile.getTrafficlights().get(2).setPhase(TrafficlightPhase.YELLOW);
				if(tile.getName().equals("Kreuzung"))tile.getTrafficlights().get(3).setPhase(TrafficlightPhase.YELLOW);
				break;

		}
		
		
	}

	public void alert() {
		switch(phaseAlert){
			case 0: for(PlacedTileModel tile : world.getPlacedTiles()){
						if(!tile.getTrafficlights().isEmpty()){	
							for(TrafficlightModel t : tile.getTrafficlights()){
							t.setPhase(TrafficlightPhase.ALERT);
							}
						}
					}
					phaseAlert=1;
					break;
			case 1: off();
					phaseAlert=0;
					break; 
		}
	}
	
	public void off(){
		for(PlacedTileModel tile : world.getPlacedTiles()){
			if(!tile.getTrafficlights().isEmpty()){	
				for(TrafficlightModel t : tile.getTrafficlights())	
					t.setPhase(TrafficlightPhase.OFF);
			}
		}
	}

	public void setPhase1(int phase1) {
		this.phase1 = phase1;
	}

	public int getPhase1() {
		return phase1;
	}

	public void setPhase2(int phase2) {
		this.phase2 = phase2;
	}

	public int getPhase2() {
		return phase2;
	}

	public void setPhaseAlert(int phaseAlert) {
		this.phaseAlert = phaseAlert;
	}

	public int getPhaseAlert() {
		return phaseAlert;
	}
}