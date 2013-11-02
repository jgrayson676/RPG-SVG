package rpgsvg.triggers;

import rpgsvg.Battle;
import rpgsvg.Pokemon;

public class Wonderguard extends Trigger{
	/**
	 * 
	 */
	private static final long serialVersionUID = 11L;

	public Wonderguard(Pokemon p){
		super(p);
		list = 4;
	}
	
	public void run(){
		
		
		if(!(Battle.m.type == 2 || Battle.m.type == 10 || Battle.m.type == 13 || Battle.m.type == 14 || Battle.m.type == 16)){
			Battle.finaldamage = 0;
		}
	}

}
