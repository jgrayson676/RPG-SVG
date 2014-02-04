package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class ShadowBall extends Move {

	private static final long serialVersionUID = -1868066135348951952L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.GHOST;
	private int damage = 80;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 15;
	private int critstage = 0;
	
	public ShadowBall(){
		
	}
	
	public void run(){
		if(Battle.random.nextDouble() < 0.2){
			Modifier m = new Modifier();
			m.setStatModifier(Modifier.SPECIALDEFENSE, -1);
			Battle.getDefender().applyModifier(m);
		}
	}
}