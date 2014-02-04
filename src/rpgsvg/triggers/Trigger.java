package rpgsvg.triggers;

import java.io.Serializable;

import rpgsvg.Pokemon;

public class Trigger implements Serializable{
	private static final long serialVersionUID = 7L;
	public Pokemon parent;
	int list = 0;
	int counter;
	boolean doesDisappear;
	
	
	public Trigger(Pokemon p)
	{
		parent = p;
	}
	
	public void run(){
	  
	}
	
	public int checkSlot()
	{
		return list;
	}
	
	public String getPokemon(){
		return parent.name;
	}
	
	public int getCounter(){
		return counter;
	}
	
	public String getList(){
		switch(list){
		case 1: return "Beginning of turn";
		case 2: return "Before attack";
		case 3: return "Before defence";
		case 4: return "After defence";
		case 5: return "End of turn";
		}
		return "No list found";
	}
}
