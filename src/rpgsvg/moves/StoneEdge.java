package rpgsvg.moves;

import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class StoneEdge extends Move {

	private static final long serialVersionUID = 7299566463480322584L;
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.ROCK;
	private int damage = 100;
	private int accuracy = 80;
	private int priority = 0;
	private int pp = 5;
	private int critstage = 1;
	
	public StoneEdge(){
		
	}
	
	public void run(){
		
	}
}
