package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class FirePunch extends Move {

	private static final long serialVersionUID = -3592598980821125938L;
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.FIRE;
	private int damage = 75;
	private int accuracy = 100;
	private int pp = 15;
	private int critstage = 0;
	
	public FirePunch(){
		
	}
	
	public void run(){
		if(Battle.random.nextDouble() < 0.1){
			Modifier m = new Modifier();
			m.setAddstatus(Pokemon.BURN);
			Battle.getDefender().applyModifier(m);
		}
	}
}