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
	
	
}
