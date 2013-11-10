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
		
		
		if(!(Battle.m.getAttackType() == 2 || Battle.m.getAttackType() == 10 || Battle.m.getAttackType() == 13 || Battle.m.getAttackType() == 14 || Battle.m.getAttackType() == 16)){
			Battle.finaldamage = 0;
		}
	}

}
