package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;


public class DrainPunch extends Move {
	
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.FIGHTING;
	private int damage = 75;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 10;
	private int critstage = 0;
	
	private static final long serialVersionUID = -7797793695854544150L;

	public DrainPunch(){
		
	}
	
	public void run(){
			//needs health draining
	}
}
