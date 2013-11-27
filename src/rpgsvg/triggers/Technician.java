package rpgsvg.triggers;

import rpgsvg.Battle;
import rpgsvg.Pokemon;
import rpgsvg.Modifier;

public class Technician extends Trigger{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	public Technician(Pokemon p){
		super(p);
		list = 2;
	}
	
	public void run(){
		Modifier modifier = new Modifier();
		if(Battle.m.getDamage() <= 60){
			modifier.setAttack(modifier.getAttack() * 1.5);
		}
		
		parent.applyModifier(modifier);
	}
}
