package rpgsvg.triggers;


import rpgsvg.Pokemon;
import rpgsvg.Modifier;

public class Guts extends Trigger{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Guts(Pokemon p){
		super(p);
		list = 2;
	}
	
	public void run(){
		Modifier mod = new Modifier();
		if(parent.status != 0){
			mod.attack *=1.5;
		}
		parent.applyModifier(mod);
	}

}
