package rpgsvg.triggers;


import rpgsvg.Pokemon;
import rpgsvg.Modifier;

public class Marvelscale extends Trigger{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Marvelscale(Pokemon p){
		super(p);
		list = 3;
	}
	
	public void run(){
		Modifier mod = new Modifier();
		if (parent.status !=0){
			mod.setDefense(mod.getDefense() * 1.5);
		}
		parent.applyModifier(mod);
	}

}
