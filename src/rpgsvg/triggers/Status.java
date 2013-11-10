package rpgsvg.triggers;

import rpgsvg.Battle;
import rpgsvg.MainGUI;
import rpgsvg.Pokemon;

public class Status extends Trigger {
	

	private static final long serialVersionUID = 1L;

	public Status(Pokemon p){
        super(p);
        list = 2;
    }
    
    public void run(){
    	
    	
        if(parent.status == Pokemon.NO_STATUS)
          return;
        switch (parent.status)
        {
        case(Pokemon.BURN):
            if(Battle.m.getContact())
            {
            	Battle.tempAttackMod *= .5;
            }
        break;
              
        case (Pokemon.FREEZE):
            if(Battle.random.nextDouble() < 0.2)
            {
            	parent.status = Pokemon.NO_STATUS;
            }   
            else
            {
            	MainGUI.txtInfo.append("\n" + parent.name + " is frozen solid!\n");
            	Battle.pokemonAttacks = false;
            }
        break;
                
        case (Pokemon.SLEEP):
        {
        	System.out.println("someone is asleep");
        	
        	
            if(parent.statusTimer == 0)
            {
            	parent.status = Pokemon.NO_STATUS;
            }
            else
            {
            	System.out.println("bool set as false");
            	parent.statusTimer--;
            	MainGUI.txtInfo.append("\n" + parent.name + " is asleep!\n");
            	Battle.pokemonAttacks = false;
            } 
        }
        break;
           
        case (Pokemon.PARALYSIS):
            if(Battle.random.nextDouble() < 0.25)
            {
            	MainGUI.txtInfo.append("\n" + parent.name + " is fully paralyzed!\n");
            	Battle.pokemonAttacks = false;
            }
        } 
        
        parent.setStatusText();
        
    }
}
