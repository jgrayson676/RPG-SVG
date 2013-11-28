package rpgsvg.moves;

import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class Flail extends Move {


	private static final long serialVersionUID = 3513174681261955728L;
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.NORMAL;
	private int damage = 0;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 15;
	private int critstage = 0;
	
	public Flail(){
		
	}
	
	public void run(){
		
	}
	
}
