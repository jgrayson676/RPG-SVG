package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class CrossPoison extends Move {

	private static final long serialVersionUID = -7278201251647286411L;
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.POISON;
	private int damage = 70;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 20;
	private int critstage = 1;
	
	public CrossPoison(){
		
	}
	
	public void run(){
		if(Battle.random.nextDouble() < 0.1){
			Modifier m = new Modifier();
			m.setAddstatus(Pokemon.POISON);
			Battle.getDefender().applyModifier(m);
		}
	}
}
