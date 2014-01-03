package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class Psychic extends Move {

	private static final long serialVersionUID = -4501533563286325086L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.PSYCHIC;
	private int damage = 90;
	private int accuracy = 100;
	private int pp = 10;
	private int critstage = 0;
	
	public Psychic(){
		
	}
	
	public void run(){
		if(Battle.random.nextDouble() < 0.1){
			Modifier m = new Modifier();
			m.setStatModifier(Modifier.SPECIALDEFENSE, -1);
			Battle.getDefender().applyModifier(m);
		}
	}
}