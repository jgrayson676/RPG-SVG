package rpgsvg.triggers;

import rpgsvg.Battle;
import rpgsvg.Pokemon;

public class Thickfat extends Trigger{


	private static final long serialVersionUID = 4;

	public Thickfat(Pokemon p){
		super(p);
		list = 3;
	}
	
	public void run(){
		
		if(Battle.m.type == 2 || Battle.m.type == 6){
			Battle.typemod /= 2;
		}
	}
}
