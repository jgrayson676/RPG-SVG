package rpgsvg.moves;
import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class FireBlast extends Move {

	private static final long serialVersionUID = -6494721345646481789L;
	private boolean isPhysical = false;
	private Type type = Type.FIRE;
	private int damage = 110;
	private int accuracy = 85;
	private int priority = 0;
	
	public FireBlast() {
		
	}
	
	public void run() {
		if (! Battle.getDefender().ability.getClass().getName().equals("Flashfire")){
			if(Battle.random.nextDouble() < 0.1){
				Modifier m = new Modifier();
				m.setAddstatus(Pokemon.BURN);
				Battle.getDefender().applyModifier(m);
			}
		}
	}
}
