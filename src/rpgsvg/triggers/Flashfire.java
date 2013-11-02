package rpgsvg.triggers;

import rpgsvg.Battle;
import rpgsvg.Pokemon;

public class Flashfire extends Trigger{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Flashfire(Pokemon p)
	{
		super(p);
		list = 3;
	}
	
	public void run()
	{
		if(Battle.m.type == 2)
		{
			Battle.typemod = 0;
		}
	}
	

}
