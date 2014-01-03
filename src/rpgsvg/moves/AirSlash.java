package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class AirSlash extends Move {
	
	private static final long serialVersionUID = -4278866342181804532L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.FLYING;
	private int damage = 75;
	private int accuracy = 95;
	private int pp = 20;
	private int critstage = 0;
	
	public AirSlash(){
		
	}
	
	public void run(){
		if(Battle.random.nextDouble() < 0.3){
			// FLINCH
		}
	}
}