package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class Growth extends Move {

	private static final long serialVersionUID = -4135002186182516266L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.NORMAL;
	private int damage = 0;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 40;
	private int critstage = 0;
	
	public Growth(){
		
	}
	
	public void run(){
			Modifier m = new Modifier();
			m.setStatModifier(Modifier.ATTACK, +1);
			m.setStatModifier(Modifier.SPECIAL, +1);
			Battle.getAttacker().applyModifier(m);
	}
}