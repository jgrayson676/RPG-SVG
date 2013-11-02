package rpgsvg;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;


public class SaveGUI {

	private JFrame frmSaveGUI;
	private JTextField txtFieldName;
	private int x;
	

	/**
	 * Create the application.
	 */
	public SaveGUI(int n) {
		x = n;
		initialize();
		frmSaveGUI.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmSaveGUI = new JFrame();
		frmSaveGUI.setTitle("File Utility");
		frmSaveGUI.setBounds(400, 400, 300, 160);
		frmSaveGUI.setResizable(false);
		frmSaveGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSaveGUI.getContentPane().setLayout(null);
		
		JLabel lbl = new JLabel("Enter the name of the file to save or load:");		//displays if the user is opening TeamGUI from MainGUI
		lbl.setFont(new Font("Helvetica", Font.PLAIN, 15));
		if(x == 2)
			lbl.setText("Enter the name of the file to load:");		//displays if the user is opening TeamGUI from SelectGUI
		lbl.setBounds(22, 28, 272, 16);
		frmSaveGUI.getContentPane().add(lbl);
		
		txtFieldName = new JTextField();
		txtFieldName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFieldName.setBounds(6, 56, 288, 28);
		frmSaveGUI.getContentPane().add(txtFieldName);
		txtFieldName.setColumns(10);
		
		
		JButton btnSave = new JButton("SAVE");					//SAVE BUTTON
		btnSave.setFont(new Font("Futura", Font.PLAIN, 13));
		if(x == 2)
			btnSave.setVisible(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					for(Pokemon x : MainGUI.battle.team1)
					{
						x.deleteImages();
					}
					for(Pokemon y : MainGUI.battle.team1)
					{
						y.deleteImages();
					}
					FileOutputStream saveFile = new FileOutputStream(txtFieldName.getText() + ".pkmn");
					ObjectOutputStream save = new ObjectOutputStream(saveFile);
					save.writeObject(MainGUI.battle);
					save.close();
					MainGUI.txtInfo.append("\nCurrent game has been saved as " + txtFieldName.getText()+ ".pkmn!\n");
					frmSaveGUI.dispose();
				}
				catch(Exception ef)
				{	
					for(Pokemon x : MainGUI.battle.team1)
					{
						x.addImages();
					}
					for(Pokemon y : MainGUI.battle.team1)
					{
						y.addImages();
					}
					ef.printStackTrace();
				}
			}
		});
		btnSave.setBounds(6, 96, 117, 29);
		frmSaveGUI.getContentPane().add(btnSave);
		
		
		JButton btnLoad = new JButton("LOAD");					//LOAD BUTTON
		btnLoad.setFont(new Font("Futura", Font.PLAIN, 13));
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					FileInputStream saveFile = new FileInputStream(txtFieldName.getText() + ".pkmn");
					ObjectInputStream save = new ObjectInputStream(saveFile);
					MainGUI.battle = (Battle)save.readObject();
					
					for(Pokemon x : MainGUI.battle.team1)
					{
						x.addImages();
					}
					for(Pokemon y : MainGUI.battle.team1)
					{
						y.addImages();
					}
					
					
					if(x == 1)
					{
						MainGUI.team1 = MainGUI.battle.team1;
						MainGUI.team2 = MainGUI.battle.team2;

						MainGUI.battle.refresh(1, MainGUI.battle.team1.get(0));
						MainGUI.battle.refresh(2, MainGUI.battle.team2.get(0));
						
						MainGUI.loadRefresh();
						MainGUI.txtInfo.setText(null);
						MainGUI.txtInfo.append("Loaded file " + txtFieldName.getText() + ".pkmn.\n" + "\nRESUME BATTLE!\n");
						MainGUI.refresh();
						MainGUI.frmMainGUI.repaint();
					}
					else if(x == 2)
					{
						SelectGUI.sequencer.stop();
						SelectGUI.frmSelectGUI.setVisible(false);
						new MainGUI(MainGUI.battle.team1, MainGUI.battle.team2, 0, new Random(), null);
						MainGUI.frmMainGUI.setVisible(true);
						MainGUI.loadRefresh();
						MainGUI.txtInfo.setText(null);
						MainGUI.txtInfo.append("Loaded file " + txtFieldName.getText() + ".pkmn.\n" + "\nRESUME BATTLE!\n");
						MainGUI.refresh();	
					}
					
					
					save.close();
					frmSaveGUI.dispose();	
					
				}
				catch(Exception ef)
				{
					ef.printStackTrace();
					txtFieldName.setText("File not found!");
				}
			}
		});
		btnLoad.setBounds(177, 96, 117, 29);
		frmSaveGUI.getContentPane().add(btnLoad);
	}
}
