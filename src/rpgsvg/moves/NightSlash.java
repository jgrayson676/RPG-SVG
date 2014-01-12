package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;


public class NightSlash extends Move {
	
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.DARK;
	private int damage = 70;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 15;
	private int critstage = 1;
	
	private static final long serialVersionUID = -7797793695858438150L;

	public NightSlash(){
		
	}
	
	public void run(){

	}
}