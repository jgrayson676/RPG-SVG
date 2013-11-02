package rpgsvg.triggers;

import rpgsvg.Battle;
import rpgsvg.Pokemon;

public class Noguard extends Trigger{
	
	public static final long serialVersionUID = 1;
	
	
	public Noguard(Pokemon p)
	{
		super(p);
		list = 3;
	}
	public void run()
	{
		Battle.accuracy = 100;
	}

}
