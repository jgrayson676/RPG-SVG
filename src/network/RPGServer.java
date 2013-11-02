package network;

import java.io.*;
import java.net.*;
import java.util.Random;

import rpgsvg.MainGUI;
import rpgsvg.SelectGUI;

public class RPGServer extends NetworkObject {
	
	public ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private long seed;
	private SelectGUI selectGUI;
	
	public StartupGUI startupGUI;

	public RPGServer(int port, StartupGUI s) {
		startupGUI = s;
		
		//Generate a seed.
		Random rand = new Random();
		seed = rand.nextLong();
		
		try {
			serverSocket = new ServerSocket(port);
		} catch(IOException e) {
			System.err.println("Could not open socket on port " + port);
			System.err.println(e);
			System.exit(1);
		}
		
		
	}
	
	public void listen() {
		try {
			clientSocket = serverSocket.accept();
			startupGUI.frame.dispose();
		} catch (IOException e) {
			System.err.println("Accept failed!");
		}
		
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                                        clientSocket.getInputStream()));
		} catch (IOException e) {
			System.err.println("Something went horribly wrong.");
		}
		
		//Send over the seed to the client.
		sendMessage(Long.toString(seed));
		
		
		Thread clientIn = new Thread() {
			
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
					clientSocket.close();
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		Thread guiStuff = new Thread() {
			@SuppressWarnings("static-access")
			public void run() {
				selectGUI = new SelectGUI(1, new Random(seed), RPGServer.this);
				selectGUI.frmSelectGUI.setVisible(true);
			}
		};

		guiStuff.start();
		clientIn.start();

	}
	
	public void processInput(String input) {
		String[] args = input.split(" ");
		
		if(args[0].equals("TEAM")) {
			int pkmn = Integer.parseInt(args[1]);
			selectGUI.addPokemon(false, pkmn, 2);
		}
		
		if(args[0].equals("DROP")) {
			int pkmn = Integer.parseInt(args[1]);
			selectGUI.removePokemon(pkmn, 2);
		}
		
		if(args[0].equals("MOVE")) {
			int move = Integer.parseInt(args[1]);
			MainGUI.selectMove(move, 2);		//Client move
		}
		
		if(args[0].equals("READY")) {
			selectGUI.setReady(2);
		}
		
		if(args[0].equals("SWITCH")) {
			int move = Integer.parseInt(args[1]);
			MainGUI.switchIn(move, 2);
		}
	}
	
	public void sendMessage(String message) {
		System.out.println("SERVER Sent message: "+message);
		out.println(message);
	}

}
