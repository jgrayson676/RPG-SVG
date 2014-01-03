package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class BodySlam extends Move {

	private static final long serialVersionUID = -4374448566319186612L;
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.NORMAL;
	private int damage = 85;
	private int accuracy = 100;
	private int priority = 0;
	private int critstage = 0;
	private int pp = 15;
	
	public BodySlam(){
		
	}
	
	public void run(){
		if(Battle.random.nextDouble() < 0.3){
			Modifier m = new Modifier();
			m.setAddstatus(Pokemon.PARALYSIS);
			Battle.getDefender().applyModifier(m);
		}
	}
}