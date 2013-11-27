package rpgsvg.moves;

import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;


public class Earthquake extends Move {
	
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.GROUND;
	private int damage = 100;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 10;
	private int critstage = 0;
	
	private static final long serialVersionUID = -779779369788338150L;

	public Earthquake(){
		
	}
	
	public void run(){
		
	}
}