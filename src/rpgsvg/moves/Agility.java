package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class Agility extends Move {

	private static final long serialVersionUID = -5820980256292939989L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.PSYCHIC;
	private int damage = 0;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 30;
	private int critstage = 0;
	
	public Agility(){
		
	}
	
	public void run(){
			Modifier m = new Modifier();
			m.setStatModifier(Modifier.SPEED, +2);
			Battle.getAttacker().applyModifier(m);
	}
}