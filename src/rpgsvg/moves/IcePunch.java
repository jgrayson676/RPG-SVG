package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class IcePunch extends Move {

	private static final long serialVersionUID = -7118592877695792401L;
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.ICE;
	private int damage = 75;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 15;
	private int critstage = 0;
	
	public IcePunch(){
		
	}
	
	public void run(){
		if(Battle.random.nextDouble() < 0.1){
			Modifier m = new Modifier();
			m.setAddstatus(Pokemon.FREEZE);
			Battle.getDefender().applyModifier(m);
		}
	}
}
