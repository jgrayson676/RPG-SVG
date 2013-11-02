package rpgsvg.triggers;

import rpgsvg.Modifier;

import rpgsvg.Pokemon;

public class Speedboost extends Trigger{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Speedboost(Pokemon p)
	{
		super(p);
		list = 5;
	}
	
	public void run()
	{
		Modifier mod = new Modifier();
		mod.statModifiers[4] = 1;
		
		parent.applyModifier(mod);
		
	}

}
