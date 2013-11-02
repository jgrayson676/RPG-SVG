package rpgsvg.triggers;

import rpgsvg.Battle;
import rpgsvg.Pokemon;

public class Adaptability extends Trigger{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Adaptability(Pokemon p){
		super(p);
		list = 2;
	}
	
	public void run(){
		if(Battle.stab == 1.5){
			Battle.stab = 2;
		}
	}

}
