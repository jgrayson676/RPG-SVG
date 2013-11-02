package rpgsvg.triggers;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;

public class Waterabsorb extends Trigger{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;

	public Waterabsorb(Pokemon p){
		super(p);
		list = 4;
	}
	
	public void run(){
		if(Battle.m.type == 3){
			Battle.finaldamage = 0;
			Modifier mod = new Modifier();
			mod.hp = 0.25;
			parent.applyModifier(mod);
		}
	}

}
