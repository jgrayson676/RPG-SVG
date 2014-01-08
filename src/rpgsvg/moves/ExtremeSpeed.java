package rpgsvg.moves;

import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class ExtremeSpeed extends Move {

	private static final long serialVersionUID = 8343076041844726829L;
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.NORMAL;
	private int damage = 80;
	private int accuracy = 100;
	private int priority = 2;
	private int pp = 5;
	private int critstage = 0;
	
	public ExtremeSpeed(){
		
	}
	
	public void run(){
		
	}
}
