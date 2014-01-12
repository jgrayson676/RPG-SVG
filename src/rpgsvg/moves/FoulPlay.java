package rpgsvg.moves;

import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class FoulPlay extends Move {

	private static final long serialVersionUID = 7898657184398078774L;
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.DARK;
	private int damage = 95;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 15;
	private int critstage = 0;
	
	public FoulPlay(){
		this.setCalculateInBattle(false);
	}
	
	public void run(){
		
	}
}
