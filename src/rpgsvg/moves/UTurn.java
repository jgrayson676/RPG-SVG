package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;


public class UTurn extends Move {
	
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.BUG;
	private int damage = 70;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 20;
	private int critstage = 0;
	
	private static final long serialVersionUID = -7797793695858368150L;

	public UTurn(){
		
	}
	
	public void run(){

	}
}