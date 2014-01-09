package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class Scald extends Move {

	private static final long serialVersionUID = 7810616113787083032L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.WATER;
	private int damage = 80;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 15;
	private int critstage = 0;

	public Scald(){
		
	}
	
	public void run(){
		if(Battle.random.nextDouble() < 0.3){
			Modifier m = new Modifier();
			m.setAddstatus(Pokemon.BURN);
			Battle.getDefender().applyModifier(m);
		}
	}
}
