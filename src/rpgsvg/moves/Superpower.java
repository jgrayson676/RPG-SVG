package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;


public class Superpower extends Move {
	
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.FIGHTING;
	private int damage = 120;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 5;
	private int critstage = 0;
	
	private static final long serialVersionUID = -7797793695858338150L;

	public Superpower(){
		
	}
	
	public void run(){
		Modifier m = new Modifier();
		m.setStatModifier(Modifier.ATTACK, -1);
		m.setStatModifier(Modifier.DEFENSE, -1);
		Battle.getAttacker().applyModifier(m);
	}
}