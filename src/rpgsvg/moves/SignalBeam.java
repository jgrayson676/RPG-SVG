package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class SignalBeam extends Move {
	
	private static final long serialVersionUID = 6507984284269550073L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.BUG;
	private int damage = 75;
	private int accuracy = 100;
	private int pp = 15;
	private int critstage = 0;
	
	public SignalBeam(){
		
	}
	
	public void run(){
		if(Battle.random.nextDouble() < 0.1){
			//Confuse the defender
		}
	}
}