package rpgsvg.triggers;

import rpgsvg.Battle;
import rpgsvg.Pokemon;


public class Sturdy extends Trigger{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8L;

	public Sturdy(Pokemon p){
		super(p);
		list = 4;
	}
	
	public void run(){
		
		System.out.println("Sturdy is being run.");
		
		if(parent.currenthealth == parent.stats[0] && Battle.finaldamage == parent.currenthealth)
		{
			System.out.println("finaldamage is lowered by 1");
			Battle.finaldamage--;
		}
	}
}
