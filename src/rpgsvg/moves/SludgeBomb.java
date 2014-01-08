package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class SludgeBomb extends Move {

	private static final long serialVersionUID = 2224996344834359570L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.POISON;
	private int damage = 90;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 10;
	private int critstage = 0;
	
	public SludgeBomb(){
		if(Battle.random.nextDouble() < 0.3){
			Modifier m = new Modifier();
			m.setAddstatus(Pokemon.POISON);
			Battle.getDefender().applyModifier(m);
		}
	}
}
