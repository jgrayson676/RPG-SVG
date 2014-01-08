package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class ThunderPunch extends Move {

	private static final long serialVersionUID = 9203283131317336704L;
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.ELECTRIC;
	private int damage = 75;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 15;
	private int critstage = 0;
	
	public ThunderPunch(){
		
	}
	
	public void run(){
		if(Battle.random.nextDouble() < 0.1){
			Modifier m = new Modifier();
			m.setAddstatus(Pokemon.PARALYSIS);
			Battle.getDefender().applyModifier(m);
		}
	}
}
