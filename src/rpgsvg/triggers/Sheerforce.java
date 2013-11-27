package rpgsvg.triggers;

import rpgsvg.Battle;
import rpgsvg.Pokemon;
import rpgsvg.Modifier;

public class Sheerforce extends Trigger{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Sheerforce(Pokemon p){
		super(p);
		list = 2;
	}
	
	public void run(){
		Modifier modifier = new Modifier();
		if(Battle.m.effectChance > 0 && Battle.m.getDamage() > 0){
			Battle.m.effectChance = 0;
			modifier.setAttack(modifier.getAttack() * 1.3);
		}
		
		parent.applyModifier(modifier);
	}
}
