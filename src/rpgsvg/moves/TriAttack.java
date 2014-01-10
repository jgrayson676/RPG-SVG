package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class TriAttack extends Move {

	private static final long serialVersionUID = 43420733683862530L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.NORMAL;
	private int damage = 80;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 20;
	private int critstage = 0;
	
	public TriAttack(){
		
	}
	
	public void run(){
		if(Battle.random.nextDouble() < 0.2){
			Modifier m = new Modifier();
			double rand = Battle.random.nextDouble();
			if(rand < 1.0/3.0)
				m.setAddstatus(Pokemon.BURN);
			else if(rand < 2.0/3.0)
				m.setAddstatus(Pokemon.FREEZE);
			else
				m.setAddstatus(Pokemon.PARALYSIS);
			Battle.getDefender().applyModifier(m);
		}
	}
}
