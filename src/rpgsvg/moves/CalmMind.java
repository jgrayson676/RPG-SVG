package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class CalmMind extends Move {

	private static final long serialVersionUID = -4501533563286325086L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.PSYCHIC;
	private int damage = 0;
	private int accuracy = 100;
	private int pp = 20;
	private int critstage = 0;
	
	public CalmMind(){
		
	}
	
	public void run(){
			Modifier m = new Modifier();
			m.setStatModifier(Modifier.SPECIALDEFENSE, +1);
			m.setStatModifier(Modifier.SPECIAL,+1);
			Battle.getAttacker().applyModifier(m);
	}
}