package rpgsvg.moves;

import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class SeedBomb extends Move {

	private static final long serialVersionUID = -6806250782437849929L;
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.GRASS;
	private int damage = 80;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 15;
	private int critstage = 0;
	
	public SeedBomb(){
		
	}
}
