package rpgsvg.triggers;

import rpgsvg.Battle;
import rpgsvg.Pokemon;

public class Levitate extends Trigger{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Levitate(Pokemon p)
	{
		super(p);
		list = 3;
	}
	
	public void run()
	{
		if(Battle.m.getAttackType() == 9)
		{
			Battle.typemod = 0;
		}
	}
}
