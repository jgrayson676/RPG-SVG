package rpgsvg.moves;

import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class Blizzard extends Move{


	private static final long serialVersionUID = 7666623245107610080L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.ICE;
	private int damage = 110;
	private int accuracy = 70;
	private int priority = 0;
	private int pp = 5;
	private int critstage = 0;
	
	public Blizzard(){
		
	}
	
	public void run(){
		
	}
	
	
}
