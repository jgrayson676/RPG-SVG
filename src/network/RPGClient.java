package network;
import java.io.*;
import java.net.*;
import java.util.Random;

import rpgsvg.MainGUI;
import rpgsvg.SelectGUI;

public class RPGClient extends NetworkObject {
	
	private Socket connectSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	private long seed;
	private SelectGUI selectGUI;
	

	public RPGClient(final String hostname) {
		
		String[] url = hostname.split(":");
		
		try {
			connectSocket = new Socket(url[0], Integer.parseInt(url[1]));
			out = new PrintWriter(connectSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                                        connectSocket.getInputStream()));
			
		} catch (UnknownHostException u) {
			System.err.println("Host not found: " + hostname);
			System.exit(1);
		} catch (IOException i) {
			System.err.println("IO didn't work!");
			System.exit(1);
		}
		
		// The very first thing the server sends over will be the seed.
		// Get the seed.
		
		try {
			seed = Long.parseLong(in.readLine());
		} catch (NumberFormatException e) {
			System.err.println("Seed failed!");
		} catch (IOException e1) {
			System.err.println("Seed thingy!");
		}

		
		// The client continuously receives input from the server in a separate thread.
		Thread serverIn = new Thread() {
			
			public void run() {
				String input;
				boolean done = false;
				while(!done) {
					try {
						if(in.ready())
							input = in.readLine();
						else
							input = "";
						processInput(input);		//Processes server input.
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				try {
					out.close();
					in.close();
					connectSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		Thread guiStuff = new Thread() {
			
			@SuppressWarnings("static-access")
			public void run() {
				selectGUI = new SelectGUI(2, new Random(seed), RPGClient.this);
				selectGUI.frmSelectGUI.setVisible(true);
			}
			
		};
		
		guiStuff.start();
		serverIn.start();

		

	}
	
	/***
	 * Handles server messages.
	 * Server arguments:
	 * TEAM <x>			index of list in adding pkmn
	 * MOVE <x>			do move <x>
	 * @param input
	 */

	public void processInput(String input) {
		String[] args = input.split(" ");
		
		if(input.length() > 0)
			System.out.println("CLIENT Recieved message: "+input);
		
		if(args[0].equals("TEAM")) {
			int pkmn = Integer.parseInt(args[1]);
			selectGUI.addPokemon(false, pkmn, 1);
		}
		
		if(args[0].equals("DROP")) {
			int pkmn = Integer.parseInt(args[1]);
			selectGUI.removePokemon(pkmn, 1);
		}
		
		if(args[0].equals("MOVE")) {
			int move = Integer.parseInt(args[1]);
			MainGUI.selectMove(move, 1);		//Server move
		}
		
		if(args[0].equals("READY")) {
			selectGUI.setReady(1);
		}
		
		if(args[0].equals("SWITCH")) {
			int move = Integer.parseInt(args[1]);
			MainGUI.switchIn(move, 1);
		}
	}
	
	public void sendMessage(String message) {
		System.out.println("CLIENT Sent message: "+message);
		out.println(message);
	}

}
