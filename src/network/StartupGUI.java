package network;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;

import rpgsvg.SelectGUI;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.Box;

public class StartupGUI {

	public JFrame frame;
	private JTextField txtServerIp;
	private JTextField txtPort;
	private JButton btnStop;
	private JButton btnStartServer;
	private RPGServer server;
	private Thread thread;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartupGUI window = new StartupGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartupGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JLabel lblRpgsvgNetworkingEdition = new JLabel("RPG-SVG BETA   Version 6.0");
		lblRpgsvgNetworkingEdition.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.add(lblRpgsvgNetworkingEdition);
		
		JSeparator separator = new JSeparator();
		panel_3.add(separator);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		
		txtServerIp = new JTextField();
		txtServerIp.setText("192.168.0.1:4444");
		txtServerIp.setColumns(10);
		
		JButton btnStartClient = new JButton("Start Client");
		btnStartClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new RPGClient(txtServerIp.getText());
				frame.dispose();
			}
		});
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel lblServerIp = new JLabel("Server IP");
		panel.add(lblServerIp);
		panel.add(txtServerIp);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);
		panel.add(btnStartClient);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel lblPort = new JLabel("Port");
		panel_1.add(lblPort);
		
		txtPort = new JTextField();
		txtPort.setText("4444");
		panel_1.add(txtPort);
		txtPort.setColumns(10);
		
		btnStartServer = new JButton("Start Server");
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStartServer.setEnabled(false);
				btnStop.setEnabled(true);
				thread = new Thread() {
					public void run() {
						server = new RPGServer(Integer.parseInt(txtPort.getText()), StartupGUI.this);
						server.listen();
					}
				};
				thread.start();
			}
		});
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue_1);
		panel_1.add(btnStartServer);
		
		btnStop = new JButton("Stop");
		btnStop.setEnabled(false);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					server.serverSocket.close();
					btnStop.setEnabled(false);
					btnStartServer.setEnabled(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel_1.add(btnStop);
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2);
		
		JButton btnStartSingleComputer = new JButton("Start Single Computer");
		panel_2.add(btnStartSingleComputer);
		btnStartSingleComputer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SelectGUI(0, new Random(), null);
				SelectGUI.frmSelectGUI.setVisible(true);
				frame.dispose();
			}
		});
		
		frame.pack();
	}

}
