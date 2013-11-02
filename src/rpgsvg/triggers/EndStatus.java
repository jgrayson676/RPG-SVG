package rpgsvg.triggers;

import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.triggers.Trigger;

public class EndStatus extends Trigger {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public EndStatus(Pokemon p) {
      super(p);
      list = 5;
      parent.statusTimer = 0;
  }
  
  public void run() {
      if(parent.status == Pokemon.NO_STATUS)
        return;
      Modifier damage = new Modifier(1, 1, Pokemon.NO_STATUS, 0, 0, 0, 0, 0, 0, 0, 0, -0.125, null, true);
      switch (parent.status){
      case (Pokemon.BURN):
          parent.applyModifier(damage);	break;
      case (Pokemon.POISON):
          parent.applyModifier(damage);	break;
      case (Pokemon.BAD_POISON):
          parent.applyModifier(new Modifier(1, 1, Pokemon.NO_STATUS, 0, 0, 0, 0, 0, 0, 0, 0, -0.0625 * Math.pow(2, parent.statusTimer), null, true));
          parent.statusTimer++;
          break;
      }
  }

}
