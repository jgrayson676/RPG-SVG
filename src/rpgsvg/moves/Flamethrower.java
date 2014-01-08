package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class Flamethrower extends Move {

	private static final long serialVersionUID = -978545222198675007L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.FIRE;
	private int damage = 90;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 15;
	private int critstage = 0;
	
	public Flamethrower(){
		
	}
	
	public void run(){
		if(Battle.random.nextDouble() < 0.1){
			Modifier m = new Modifier();
			m.setAddstatus(Pokemon.BURN);
			Battle.getDefender().applyModifier(m);
		}
	}
}
