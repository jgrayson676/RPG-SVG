package rpgsvg;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TeamGUI { // The team status window for RPG-SVG. Used to obtain
						// information on the team and switch Pokemon.

	/* FIELDS */
	private JFrame frmTeamGUI;
	public int team;
	public int teamsize;

	/* CONSTRUCTOR */
	public TeamGUI(int i) { // i = 1 displays Team 1, i == 2 displays Team 2
		team = i;
		if (team == 1)
			teamsize = MainGUI.team1.size();
		else if (team == 2)
			teamsize = MainGUI.team2.size();
		initialize();
		frmTeamGUI.setVisible(true);
	}

	/* METHODS */
	private void initialize() {

		frmTeamGUI = new JFrame(); // The Window
		frmTeamGUI.setAlwaysOnTop(true);
		
		MainGUI.frmMainGUI.setFocusable(false);
		MainGUI.frmMainGUI.setEnabled(false);
		
		frmTeamGUI.setTitle("Pokemon Team Information");
		if (team == 1)
			frmTeamGUI.setTitle("Pokemon Team Information- PLAYER 1");
		if (team == 2)
			frmTeamGUI.setTitle("Pokemon Team Information- PLAYER 2");
		frmTeamGUI.setResizable(false);
		frmTeamGUI.setBounds(260, 375, 600, 390);
		frmTeamGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTeamGUI.getContentPane().setLayout(null);
		
		class WindowEventHandler extends WindowAdapter {
			  public void windowClosing(WindowEvent evt) {
			    MainGUI.frmMainGUI.setEnabled(true);
			  }
			}
		
		frmTeamGUI.addWindowListener(new WindowEventHandler());

		

		JLabel lblInBattle = new JLabel("In Battle");
		lblInBattle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInBattle.setBounds(6, 6, 119, 16);
		frmTeamGUI.getContentPane().add(lblInBattle);

		JLabel lblP1 = new JLabel("------------"); // P1 Name
		if (team == 1)
			lblP1.setText(MainGUI.team1.get(0).name);
		if (team == 2)
			lblP1.setText(MainGUI.team2.get(0).name);
		lblP1.setFont(new Font("Helvetica", Font.BOLD, 18));
		lblP1.setBounds(6, 34, 130, 20);
		frmTeamGUI.getContentPane().add(lblP1);

		JLabel lblInParty = new JLabel("In Party");
		lblInParty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInParty.setBounds(6, 90, 119, 16);
		frmTeamGUI.getContentPane().add(lblInParty);

		JLabel lblP2 = new JLabel("------------"); // P2 Name
		if (teamsize > 1 && team == 1)
			lblP2.setText(MainGUI.team1.get(1).name);
		if (teamsize > 1 && team == 2)
			lblP2.setText(MainGUI.team2.get(1).name);
		lblP2.setFont(new Font("Helvetica", Font.BOLD, 18));
		lblP2.setBounds(6, 130, 130, 20);
		frmTeamGUI.getContentPane().add(lblP2);

		JLabel lblP3 = new JLabel("------------"); // P3 Name
		if (teamsize > 2 && team == 1)
			lblP3.setText(MainGUI.team1.get(2).name);
		if (teamsize > 2 && team == 2)
			lblP3.setText(MainGUI.team2.get(2).name);
		lblP3.setFont(new Font("Helvetica", Font.BOLD, 18));
		lblP3.setBounds(6, 180, 130, 20);
		frmTeamGUI.getContentPane().add(lblP3);

		JLabel lblP4 = new JLabel("------------"); // P4 Name
		if (teamsize > 3 && team == 1)
			lblP4.setText(MainGUI.team1.get(3).name);
		if (teamsize > 3 && team == 2)
			lblP4.setText(MainGUI.team2.get(3).name);
		lblP4.setFont(new Font("Helvetica", Font.BOLD, 18));
		lblP4.setBounds(6, 230, 130, 20);
		frmTeamGUI.getContentPane().add(lblP4);

		JLabel lblP5 = new JLabel("------------"); // P5 Name
		if (teamsize > 4 && team == 1)
			lblP5.setText(MainGUI.team1.get(4).name);
		if (teamsize > 4 && team == 2)
			lblP5.setText(MainGUI.team2.get(4).name);
		lblP5.setFont(new Font("Helvetica", Font.BOLD, 18));
		lblP5.setBounds(6, 280, 130, 20);
		frmTeamGUI.getContentPane().add(lblP5);

		JLabel lblP6 = new JLabel("------------"); // P6 Name
		if (teamsize > 5 && team == 1)
			lblP6.setText(MainGUI.team1.get(5).name);
		if (teamsize > 5 && team == 2)
			lblP6.setText(MainGUI.team2.get(5).name);
		lblP6.setFont(new Font("Helvetica", Font.BOLD, 18));
		lblP6.setBounds(6, 330, 130, 20);
		frmTeamGUI.getContentPane().add(lblP6);

		JProgressBar healthBarP1 = new JProgressBar(); // P1 Health
		if (team == 1)
			healthBarP1.setValue(MainGUI.team1.get(0).currenthealth * 100
					/ MainGUI.team1.get(0).stats[0]);
		if (team == 2)
			healthBarP1.setValue(MainGUI.team2.get(0).currenthealth * 100
					/ MainGUI.team2.get(0).stats[0]);
		healthBarP1.setBounds(140, 34, 146, 20);
		frmTeamGUI.getContentPane().add(healthBarP1);

		JProgressBar healthBarP2 = new JProgressBar(); // P2 Health
		healthBarP2.setBounds(140, 130, 146, 20);
		if (teamsize < 2)
			healthBarP2.setVisible(false);
		else if (team == 1)
			healthBarP2.setValue(MainGUI.team1.get(1).currenthealth * 100
					/ MainGUI.team1.get(1).stats[0]);
		else if (team == 2)
			healthBarP2.setValue(MainGUI.team2.get(1).currenthealth * 100
					/ MainGUI.team2.get(1).stats[0]);
		frmTeamGUI.getContentPane().add(healthBarP2);

		JProgressBar healthBarP3 = new JProgressBar(); // P3 Health
		healthBarP3.setBounds(140, 180, 146, 20);
		if (teamsize < 3)
			healthBarP3.setVisible(false);
		else if (team == 1)
			healthBarP3.setValue(MainGUI.team1.get(2).currenthealth * 100
					/ MainGUI.team1.get(2).stats[0]);
		else if (team == 2)
			healthBarP3.setValue(MainGUI.team2.get(2).currenthealth * 100
					/ MainGUI.team2.get(2).stats[0]);
		frmTeamGUI.getContentPane().add(healthBarP3);

		JProgressBar healthBarP4 = new JProgressBar(); // P4 Health
		healthBarP4.setBounds(140, 230, 146, 20);
		if (teamsize < 4)
			healthBarP4.setVisible(false);
		else if (team == 1)
			healthBarP4.setValue(MainGUI.team1.get(3).currenthealth * 100
					/ MainGUI.team1.get(3).stats[0]);
		else if (team == 2)
			healthBarP4.setValue(MainGUI.team2.get(3).currenthealth * 100
					/ MainGUI.team2.get(3).stats[0]);
		frmTeamGUI.getContentPane().add(healthBarP4);

		JProgressBar healthBarP5 = new JProgressBar(); // P5 Health
		healthBarP5.setBounds(140, 280, 146, 20);
		if (teamsize < 5)
			healthBarP5.setVisible(false);
		else if (team == 1)
			healthBarP5.setValue(MainGUI.team1.get(4).currenthealth * 100
					/ MainGUI.team1.get(4).stats[0]);
		else if (team == 2)
			healthBarP5.setValue(MainGUI.team2.get(4).currenthealth * 100
					/ MainGUI.team2.get(4).stats[0]);
		frmTeamGUI.getContentPane().add(healthBarP5);

		JProgressBar healthBarP6 = new JProgressBar(); // P6 Health
		healthBarP6.setBounds(140, 330, 146, 20);
		if (teamsize < 6)
			healthBarP6.setVisible(false);
		else if (team == 1)
			healthBarP6.setValue(MainGUI.team1.get(5).currenthealth * 100
					/ MainGUI.team1.get(5).stats[0]);
		else if (team == 2)
			healthBarP6.setValue(MainGUI.team2.get(5).currenthealth * 100
					/ MainGUI.team2.get(5).stats[0]);
		frmTeamGUI.getContentPane().add(healthBarP6);

		JButton btnSwitchP2 = new JButton("SWITCH"); // P2 Switch Button
		btnSwitchP2.setFont(new Font("Futura", Font.PLAIN, 12));
		try {
			if (team == 1 && MainGUI.team1.get(1).currenthealth == 0
					|| team == 2 && MainGUI.team2.get(1).currenthealth == 0) {
				btnSwitchP2.setEnabled(false);
			}
		} catch (Exception e) {
		}

		btnSwitchP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI.frmMainGUI.setEnabled(true);
				if (team == 1) {
					if (MainGUI.team1.get(0).currenthealth == 0) {
						frmTeamGUI.dispose();
						MainGUI.switchIn(1, team);
						if (MainGUI.team2.get(0).currenthealth == 0) {
							new TeamGUI(2);
						}
					} else {
						frmTeamGUI.dispose();
						MainGUI.selectMove(-2, 1);
					}

				}
				if (team == 2) {
					if (MainGUI.team2.get(0).currenthealth == 0) {
						frmTeamGUI.dispose();
						MainGUI.switchIn(1, team);
					} else {
						frmTeamGUI.dispose();
						MainGUI.selectMove(-2, 2);
					}

				}
			}
		});
		btnSwitchP2.setBounds(502, 129, 92, 29);
		if (teamsize < 2)
			btnSwitchP2.setVisible(false);
		frmTeamGUI.getContentPane().add(btnSwitchP2);

		JButton btnSwitchP3 = new JButton("SWITCH"); // P3 Switch Button
		btnSwitchP3.setFont(new Font("Futura", Font.PLAIN, 12));
		try {
			if (team == 1 && MainGUI.team1.get(2).currenthealth == 0
					|| team == 2 && MainGUI.team2.get(2).currenthealth == 0) {
				btnSwitchP3.setEnabled(false);
			}
		} catch (Exception e) {
		}

		btnSwitchP3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI.frmMainGUI.setEnabled(true);
				if (team == 1) {
					if (MainGUI.team1.get(0).currenthealth == 0) {
						frmTeamGUI.dispose();
						MainGUI.switchIn(2, team);
					} else {
						frmTeamGUI.dispose();
						MainGUI.selectMove(-3, 1);
					}

				}
				if (team == 2) {
					if (MainGUI.team2.get(0).currenthealth == 0) {
						frmTeamGUI.dispose();
						MainGUI.switchIn(2, team);
					} else {
						frmTeamGUI.dispose();
						MainGUI.selectMove(-3, 2);
					}

				}

			}
		});
		btnSwitchP3.setBounds(502, 179, 92, 29);
		if (teamsize < 3)
			btnSwitchP3.setVisible(false);
		frmTeamGUI.getContentPane().add(btnSwitchP3);

		JButton btnSwitchP4 = new JButton("SWITCH"); // P4 Switch Button
		btnSwitchP4.setFont(new Font("Futura", Font.PLAIN, 12));
		try {
			if (team == 1 && MainGUI.team1.get(3).currenthealth == 0
					|| team == 2 && MainGUI.team2.get(3).currenthealth == 0) {
				btnSwitchP4.setEnabled(false);
			}
		} catch (Exception e) {
		}

		btnSwitchP4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI.frmMainGUI.setEnabled(true);
				if (team == 1) {
					if (MainGUI.team1.get(0).currenthealth == 0) {
						frmTeamGUI.dispose();
						MainGUI.switchIn(3, team);
					} else {
						frmTeamGUI.dispose();
						MainGUI.selectMove(-4, 1);
					}

				}
				if (team == 2) {
					if (MainGUI.team2.get(0).currenthealth == 0) {
						frmTeamGUI.dispose();
						MainGUI.switchIn(3, team);
					} else {
						frmTeamGUI.dispose();
						MainGUI.selectMove(-4, 2);
					}

				}

			}
		});
		btnSwitchP4.setBounds(502, 229, 92, 29);
		if (teamsize < 4)
			btnSwitchP4.setVisible(false);
		frmTeamGUI.getContentPane().add(btnSwitchP4);

		JButton btnSwitchP5 = new JButton("SWITCH"); // P5 Switch Button
		btnSwitchP5.setFont(new Font("Futura", Font.PLAIN, 12));
		try {
			if (team == 1 && MainGUI.team1.get(4).currenthealth == 0
					|| team == 2 && MainGUI.team2.get(4).currenthealth == 0) {
				btnSwitchP5.setEnabled(false);
			}
		} catch (Exception e) {
		}

		btnSwitchP5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI.frmMainGUI.setEnabled(true);
				if (team == 1) {
					if (MainGUI.team1.get(0).currenthealth == 0) {
						frmTeamGUI.dispose();
						MainGUI.switchIn(4, team);
					} else {
						frmTeamGUI.dispose();
						MainGUI.selectMove(-5, 1);
					}

				}
				if (team == 2) {
					if (MainGUI.team2.get(0).currenthealth == 0) {
						frmTeamGUI.dispose();
						MainGUI.switchIn(4, team);
					} else {
						frmTeamGUI.dispose();
						MainGUI.selectMove(-5, 2);
					}

				}

			}
		});
		btnSwitchP5.setBounds(502, 279, 92, 29);
		if (teamsize < 5)
			btnSwitchP5.setVisible(false);
		frmTeamGUI.getContentPane().add(btnSwitchP5);

		JButton btnSwitchP6 = new JButton("SWITCH"); // P6 Switch Button
		btnSwitchP6.setFont(new Font("Futura", Font.PLAIN, 12));
		try {
			if (team == 1 && MainGUI.team1.get(5).currenthealth == 0
					|| team == 2 && MainGUI.team2.get(5).currenthealth == 0) {
				btnSwitchP6.setEnabled(false);
			}
		} catch (Exception e) {
		}

		btnSwitchP6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI.frmMainGUI.setEnabled(true);
				if (team == 1) {
					if (MainGUI.team1.get(0).currenthealth == 0) {
						frmTeamGUI.dispose();
						MainGUI.switchIn(5, team);
					} else {
						frmTeamGUI.dispose();
						MainGUI.selectMove(-6, 1);
					}

				}
				if (team == 2) {
					if (MainGUI.team2.get(0).currenthealth == 0) {
						frmTeamGUI.dispose();
						MainGUI.switchIn(5, team);
					} else {
						frmTeamGUI.dispose();
						MainGUI.actionSelectedP2 = -6;
						MainGUI.selectMove(-6, 2);
					}
				}

			}
		});
		btnSwitchP6.setBounds(502, 329, 92, 29);
		if (teamsize < 6)
			btnSwitchP6.setVisible(false);
		frmTeamGUI.getContentPane().add(btnSwitchP6);

		JButton btnInfoP1 = new JButton("INFO"); // P1 Info Button
		btnInfoP1.setFont(new Font("Futura", Font.PLAIN, 12));
		btnInfoP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (team == 1)
					JOptionPane.showMessageDialog(frmTeamGUI, MainGUI.team1
							.get(0).getFormattedStats(),
							MainGUI.team1.get(0).name, 2,
							MainGUI.team1.get(0).sprite1);
				if (team == 2)
					JOptionPane.showMessageDialog(frmTeamGUI, MainGUI.team2
							.get(0).getFormattedStats(),
							MainGUI.team2.get(0).name, 2,
							MainGUI.team2.get(0).sprite2);
			}
		});
		btnInfoP1.setBounds(408, 34, 92, 29);
		frmTeamGUI.getContentPane().add(btnInfoP1);

		JButton btnInfoP2 = new JButton("INFO"); // P2 Info Button
		btnInfoP2.setFont(new Font("Futura", Font.PLAIN, 12));
		btnInfoP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (team == 1)
					JOptionPane.showMessageDialog(frmTeamGUI, MainGUI.team1
							.get(1).getFormattedStats(),
							MainGUI.team1.get(1).name, 2,
							MainGUI.team1.get(1).sprite1);
				if (team == 2)
					JOptionPane.showMessageDialog(frmTeamGUI, MainGUI.team2
							.get(1).getFormattedStats(),
							MainGUI.team2.get(1).name, 2,
							MainGUI.team2.get(1).sprite2);
			}
		});
		btnInfoP2.setBounds(408, 128, 92, 29);
		if (teamsize < 2)
			btnInfoP2.setVisible(false);
		frmTeamGUI.getContentPane().add(btnInfoP2);

		JButton btnInfoP3 = new JButton("INFO"); // P3 Info Button
		btnInfoP3.setFont(new Font("Futura", Font.PLAIN, 12));
		btnInfoP3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (team == 1)
					JOptionPane.showMessageDialog(frmTeamGUI, MainGUI.team1
							.get(2).getFormattedStats(),
							MainGUI.team1.get(2).name, 2,
							MainGUI.team1.get(2).sprite1);
				if (team == 2)
					JOptionPane.showMessageDialog(frmTeamGUI, MainGUI.team2
							.get(2).getFormattedStats(),
							MainGUI.team2.get(2).name, 2,
							MainGUI.team2.get(2).sprite2);
			}
		});
		btnInfoP3.setBounds(408, 179, 92, 29);
		if (teamsize < 3)
			btnInfoP3.setVisible(false);
		frmTeamGUI.getContentPane().add(btnInfoP3);

		JButton btnInfoP4 = new JButton("INFO"); // P4 Info Button
		btnInfoP4.setFont(new Font("Futura", Font.PLAIN, 12));
		btnInfoP4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (team == 1)
					JOptionPane.showMessageDialog(frmTeamGUI, MainGUI.team1
							.get(3).getFormattedStats(),
							MainGUI.team1.get(3).name, 2,
							MainGUI.team1.get(3).sprite1);
				if (team == 2)
					JOptionPane.showMessageDialog(frmTeamGUI, MainGUI.team2
							.get(3).getFormattedStats(),
							MainGUI.team2.get(3).name, 2,
							MainGUI.team2.get(3).sprite2);
			}
		});
		btnInfoP4.setBounds(408, 229, 92, 29);
		if (teamsize < 4)
			btnInfoP4.setVisible(false);
		frmTeamGUI.getContentPane().add(btnInfoP4);

		JButton btnInfoP5 = new JButton("INFO"); // P5 Info Button
		btnInfoP5.setFont(new Font("Futura", Font.PLAIN, 12));
		btnInfoP5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (team == 1)
					JOptionPane.showMessageDialog(frmTeamGUI, MainGUI.team1
							.get(4).getFormattedStats(),
							MainGUI.team1.get(4).name, 2,
							MainGUI.team1.get(4).sprite1);
				if (team == 2)
					JOptionPane.showMessageDialog(frmTeamGUI, MainGUI.team2
							.get(4).getFormattedStats(),
							MainGUI.team2.get(4).name, 2,
							MainGUI.team2.get(4).sprite2);
			}
		});
		btnInfoP5.setBounds(408, 279, 92, 29);
		if (teamsize < 5)
			btnInfoP5.setVisible(false);
		frmTeamGUI.getContentPane().add(btnInfoP5);

		JButton btnInfoP6 = new JButton("INFO"); // P6 Info Button
		btnInfoP6.setFont(new Font("Futura", Font.PLAIN, 12));
		if (teamsize < 6)
			btnInfoP6.setVisible(false);
		btnInfoP6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (team == 1)
					JOptionPane.showMessageDialog(frmTeamGUI, MainGUI.team1
							.get(5).getFormattedStats(),
							MainGUI.team1.get(5).name, 2,
							MainGUI.team1.get(5).sprite1);
				if (team == 2)
					JOptionPane.showMessageDialog(frmTeamGUI, MainGUI.team2
							.get(5).getFormattedStats(),
							MainGUI.team2.get(5).name, 2,
							MainGUI.team2.get(5).sprite2);
			}
		});
		btnInfoP6.setBounds(408, 329, 92, 29);
		frmTeamGUI.getContentPane().add(btnInfoP6);

		JLabel lblTypeP1 = new JLabel("Type/Type"); // P1 Type
		if (team == 1)
			lblTypeP1.setText(MainGUI.team1.get(0).getType1() + "/"
					+ MainGUI.team1.get(0).getType2());
		if (team == 2)
			lblTypeP1.setText(MainGUI.team2.get(0).getType1() + "/"
					+ MainGUI.team2.get(0).getType2());
		lblTypeP1.setFont(new Font("Helvetica", Font.PLAIN, 15));
		lblTypeP1.setBounds(291, 34, 119, 20);
		frmTeamGUI.getContentPane().add(lblTypeP1);

		JLabel lblTypeP2 = new JLabel("Type/Type"); // P2 Type
		if (team == 1 && teamsize > 1)
			lblTypeP2.setText(MainGUI.team1.get(1).getType1() + "/"
					+ MainGUI.team1.get(1).getType2());
		else if (team == 2 && teamsize > 1)
			lblTypeP2.setText(MainGUI.team2.get(1).getType1() + "/"
					+ MainGUI.team2.get(1).getType2());
		else
			lblTypeP2.setVisible(false);
		lblTypeP2.setFont(new Font("Helvetica", Font.PLAIN, 15));
		lblTypeP2.setBounds(291, 130, 119, 20);
		frmTeamGUI.getContentPane().add(lblTypeP2);

		JLabel lblTypeP3 = new JLabel("Type/Type"); // P3 Type
		if (team == 1 && teamsize > 2)
			lblTypeP3.setText(MainGUI.team1.get(2).getType1() + "/"
					+ MainGUI.team1.get(2).getType2());
		else if (team == 2 && teamsize > 2)
			lblTypeP3.setText(MainGUI.team2.get(2).getType1() + "/"
					+ MainGUI.team2.get(2).getType2());
		else
			lblTypeP3.setVisible(false);
		lblTypeP3.setFont(new Font("Helvetica", Font.PLAIN, 15));
		lblTypeP3.setBounds(291, 180, 119, 20);
		frmTeamGUI.getContentPane().add(lblTypeP3);

		JLabel lblTypeP4 = new JLabel("Type/Type"); // P4 Type
		if (team == 1 && teamsize > 3)
			lblTypeP4.setText(MainGUI.team1.get(3).getType1() + "/"
					+ MainGUI.team1.get(3).getType2());
		else if (team == 2 && teamsize > 3)
			lblTypeP4.setText(MainGUI.team2.get(3).getType1() + "/"
					+ MainGUI.team2.get(3).getType2());
		else
			lblTypeP4.setVisible(false);
		lblTypeP4.setFont(new Font("Helvetica", Font.PLAIN, 15));
		lblTypeP4.setBounds(291, 230, 119, 20);
		frmTeamGUI.getContentPane().add(lblTypeP4);

		JLabel lblTypeP5 = new JLabel("Type/Type"); // P5 Type
		if (team == 1 && teamsize > 4)
			lblTypeP5.setText(MainGUI.team1.get(4).getType1() + "/"
					+ MainGUI.team1.get(4).getType2());
		else if (team == 2 && teamsize > 4)
			lblTypeP5.setText(MainGUI.team2.get(4).getType1() + "/"
					+ MainGUI.team2.get(4).getType2());
		else
			lblTypeP5.setVisible(false);
		lblTypeP5.setFont(new Font("Helvetica", Font.PLAIN, 15));
		lblTypeP5.setBounds(291, 280, 119, 20);
		frmTeamGUI.getContentPane().add(lblTypeP5);

		JLabel lblTypeP6 = new JLabel("Type/Type"); // P6 Type
		if (team == 1 && teamsize > 5)
			lblTypeP6.setText(MainGUI.team1.get(5).getType1() + "/"
					+ MainGUI.team1.get(5).getType2());
		else if (team == 2 && teamsize > 5)
			lblTypeP6.setText(MainGUI.team2.get(5).getType1() + "/"
					+ MainGUI.team2.get(5).getType2());
		else
			lblTypeP6.setVisible(false);
		lblTypeP6.setFont(new Font("Helvetica", Font.PLAIN, 15));
		lblTypeP6.setBounds(291, 330, 119, 18);
		frmTeamGUI.getContentPane().add(lblTypeP6);

		JLabel lblP1Sprite = new JLabel("-"); // P1 Sprite
		if (team == 1)
			lblP1Sprite = new JLabel(MainGUI.team1.get(0).minisprite);
		if (team == 2)
			lblP1Sprite = new JLabel(MainGUI.team2.get(0).minisprite);
		lblP1Sprite.setBounds(104, 22, 32, 32);
		frmTeamGUI.getContentPane().add(lblP1Sprite);

		JLabel lblP2Sprite = new JLabel("-"); // P2 Sprite
		if (team == 1 && teamsize > 1)
			lblP2Sprite = new JLabel(MainGUI.team1.get(1).minisprite);
		else if (team == 2 && teamsize > 1)
			lblP2Sprite = new JLabel(MainGUI.team2.get(1).minisprite);
		else
			lblP2Sprite.setVisible(false);
		lblP2Sprite.setBounds(104, 118, 32, 32);
		frmTeamGUI.getContentPane().add(lblP2Sprite);

		JLabel lblP3Sprite = new JLabel("-"); // P3 Sprite
		if (team == 1 && teamsize > 2)
			lblP3Sprite = new JLabel(MainGUI.team1.get(2).minisprite);
		else if (team == 2 && teamsize > 2)
			lblP3Sprite = new JLabel(MainGUI.team2.get(2).minisprite);
		else
			lblP3Sprite.setVisible(false);
		lblP3Sprite.setBounds(104, 168, 32, 32);
		frmTeamGUI.getContentPane().add(lblP3Sprite);

		JLabel lblP4Sprite = new JLabel("-"); // P4 Sprite
		if (team == 1 && teamsize > 3)
			lblP4Sprite = new JLabel(MainGUI.team1.get(3).minisprite);
		else if (team == 2 && teamsize > 3)
			lblP4Sprite = new JLabel(MainGUI.team2.get(3).minisprite);
		else
			lblP4Sprite.setVisible(false);
		lblP4Sprite.setBounds(104, 218, 32, 32);
		frmTeamGUI.getContentPane().add(lblP4Sprite);

		JLabel lblP5Sprite = new JLabel("-"); // P5 Sprite
		if (team == 1 && teamsize > 4)
			lblP5Sprite = new JLabel(MainGUI.team1.get(4).minisprite);
		else if (team == 2 && teamsize > 4)
			lblP5Sprite = new JLabel(MainGUI.team2.get(4).minisprite);
		else
			lblP5Sprite.setVisible(false);
		lblP5Sprite.setBounds(104, 268, 32, 32);
		frmTeamGUI.getContentPane().add(lblP5Sprite);

		JLabel lblP6Sprite = new JLabel("-"); // P6 Sprite
		if (team == 1 && teamsize > 5)
			lblP6Sprite = new JLabel(MainGUI.team1.get(5).minisprite);
		else if (team == 2 && teamsize > 5)
			lblP6Sprite = new JLabel(MainGUI.team2.get(5).minisprite);
		else
			lblP6Sprite.setVisible(false);
		lblP6Sprite.setBounds(104, 318, 32, 32);
		frmTeamGUI.getContentPane().add(lblP6Sprite);
	}
}
