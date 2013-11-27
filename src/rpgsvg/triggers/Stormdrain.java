package rpgsvg.triggers;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;

public class Stormdrain extends Trigger{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9L;

	public Stormdrain(Pokemon p) {
		super(p);
		list = 3;
	}

	public void run(){
	
		Modifier modifier = new Modifier();
		if(Battle.m.getAttackType() == 3){
			modifier.setAttack(0);
			modifier.statModifiers[2] = 1;
		}
		
		parent.applyModifier(modifier);
	}
}