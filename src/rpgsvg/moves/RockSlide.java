package rpgsvg.moves;

import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class RockSlide extends Move {

	private static final long serialVersionUID = -4037289975914636584L;
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.ROCK;
	private int damage = 75;
	private int accuracy = 90;
	private int priority = 0;
	private int pp = 10;
	private int critstage = 0;
	
	public RockSlide(){
		
	}
	
	public void run(){
		//need flinch (30%)
	}
}
