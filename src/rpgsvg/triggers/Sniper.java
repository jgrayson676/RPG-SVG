package rpgsvg.triggers;

import rpgsvg.Battle;
import rpgsvg.Pokemon;

public class Sniper extends Trigger{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Sniper(Pokemon p){
		super(p);
		list = 2;
	}
	
	public void run(){
		if(Battle.crit == 2){
			Battle.crit = 3;
		}
	}

}
