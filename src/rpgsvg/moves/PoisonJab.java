package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class PoisonJab extends Move {

	private static final long serialVersionUID = 7911314052048166879L;
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.POISON;
	private int damage = 80;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 20;
	private int critstqge = 0;
	
	public PoisonJab(){
		
	}
	
	public void run(){
		if(Battle.random.nextDouble() < 0.3){
			Modifier m = new Modifier();
			m.setAddstatus(Pokemon.POISON);
			Battle.getDefender().applyModifier(m);
		}
	}
}