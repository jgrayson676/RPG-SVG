package rpgsvg.moves;

import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class XScissor extends Move {

	private static final long serialVersionUID = 8480590458555215815L;
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.BUG;
	private int damage = 80;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 15;
	private int critstage = 0;
	
	public XScissor(){
		
	}
	
	public void run(){
		
	}
}
