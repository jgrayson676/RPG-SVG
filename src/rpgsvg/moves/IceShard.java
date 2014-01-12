package rpgsvg.moves;

import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class IceShard extends Move {

	private static final long serialVersionUID = 7274794644837148633L;
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.ICE;
	private int damage = 40;
	private int accuracy = 100;
	private int priority = 1;
	private int pp = 30;
	private int critstage = 0;
	
	public IceShard(){
		
	}
}
