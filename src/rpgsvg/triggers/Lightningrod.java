package rpgsvg.triggers;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;

public class Lightningrod extends Trigger{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Lightningrod(Pokemon p) {
		super(p);
		list = 3;
	}

	public void run(){
	
		Modifier modifier = new Modifier();
		if(Battle.m.getAttackType() == 4){
			modifier.setAttack(0);
			modifier.statModifiers[2] = 1;
		}
		
		parent.applyModifier(modifier);
	}
}
