package rpgsvg.triggers;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;

public class Reckless extends Trigger{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Reckless(Pokemon p){
		super(p);
		list = 2;
	}
	
	public void run(){
		Modifier mod = new Modifier();
		if(Battle.m.self.recoil > 0){
			mod.attack *= 1.2;
		}
		parent.applyModifier(mod);
	}
}
