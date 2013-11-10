package rpgsvg.triggers;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;

public class Static extends Trigger{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Static(Pokemon p)
	{
		super(p);
		list = 4;
	}
	
	public void run()
	{
		if(Battle.m.getContact() && Battle.random.nextDouble() < 0.3)
		{
			Battle.attacker.applyModifier(new Modifier(1, 1, Pokemon.PARALYSIS, 0,0,0,0,0,0,0,0,0,null,true));
			Battle.attacker.setStatusText();
		}
	}
}
