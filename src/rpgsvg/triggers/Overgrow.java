package rpgsvg.triggers;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;

public class Overgrow extends Trigger {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3;

	public Overgrow(Pokemon p) {
		super(p);
		list =2;
	}

	public void run()
	{
		Modifier modifier = new Modifier();
		if(parent.currenthealth < (parent.stats[0] / 3) && Battle.m.type == 5)
			modifier.attack = 1.5;
		Battle.attacker.applyModifier(modifier);
	}
	
}
