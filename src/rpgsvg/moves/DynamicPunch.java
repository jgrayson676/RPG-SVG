package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class DynamicPunch extends Move {

	private static final long serialVersionUID = -525219295339965892L;
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.FIGHTING;
	private int damage = 100;
	private int accuracy = 50;
	private int pp = 5;
	private int critstage = 0;
	
	public DynamicPunch(){
		
	}
	
	public void run(){
		//confuse the defender
	}

}
