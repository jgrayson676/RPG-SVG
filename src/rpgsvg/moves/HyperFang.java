package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class HyperFang extends Move{

	
	private static final long serialVersionUID = -4634315359922447839L;
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.NORMAL;
	private int damage = 80;
	private int accuracy = 90;
	private int priority = 0;
	private int pp = 15;
	private int critstage = 0;
	
	public HyperFang(){
		
	}
	
	public void run(){
		if(Battle.random.nextDouble() < 0.1){
			//flinch
		}
	}
	

}
