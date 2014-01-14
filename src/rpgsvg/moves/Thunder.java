package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class Thunder extends Move {

	private static final long serialVersionUID = -1452056307314279048L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.ELECTRIC;
	private int damage = 110;
	private int accuracy = 70;
	private int priority = 0;
	private int pp = 10;
	private int critstage = 0;
	
	public Thunder(){
		
	}
	
	public void run(){
		if(Battle.random.nextDouble() < 0.3){
			Modifier m = new Modifier();
			m.setAddstatus(Pokemon.PARALYSIS);
			Battle.getDefender().applyModifier(m);
		}
	}
}
