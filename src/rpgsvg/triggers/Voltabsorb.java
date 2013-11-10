package rpgsvg.triggers;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;

public class Voltabsorb extends Trigger{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;

	public Voltabsorb(Pokemon p){
		super(p);
		list = 4;
	}
	
	public void run(){
		if(Battle.m.getAttackType() == 4){
			Battle.finaldamage = 0;
			Modifier mod = new Modifier();
			mod.hp = 0.25;
			parent.applyModifier(mod);
		}
	}

}