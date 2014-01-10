package rpgsvg.moves;

import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class DarkPulse extends Move {

	private static final long serialVersionUID = -366880737011873747L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.DARK;
	private int damage = 80;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 15;
	private int critstage = 0;
	
	public DarkPulse(){
		
	}
	
	public void run(){
		//Need flinch pls
	}
}
