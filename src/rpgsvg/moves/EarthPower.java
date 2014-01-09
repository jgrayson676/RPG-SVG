package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class EarthPower extends Move {

	private static final long serialVersionUID = -3296913817007290311L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.GROUND;
	private int damage = 90;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 10;
	private int critstage = 0;
	
	public EarthPower(){
		
	}
	
	public void run(){
		if(Battle.random.nextDouble() < 0.1){
			Modifier m = new Modifier();
			m.setStatModifier(Modifier.SPECIALDEFENSE, -1);
			Battle.getDefender().applyModifier(m);
		}
	}
}
