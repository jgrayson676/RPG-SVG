package rpgsvg.moves;

import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class AquaJet extends Move{

	
	private static final long serialVersionUID = 13423701238471L;
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.WATER;
	private int damage = 40;
	private int accuracy = 100;
	private int priority = 1;
	private int pp = 20;
	private int critstage = 0;
	
	public AquaJet(){
		
	}
	
	public void run(){
		
	}
	
}
