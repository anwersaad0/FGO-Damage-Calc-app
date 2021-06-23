package FGODmg;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class AppFrame extends JFrame implements ActionListener {
	
	ImageIcon img = new ImageIcon("Dmg_up.png");
	
	public final double NP_SCALE = 0.23;
	
	public final double BUSTER_MOD = 1.5;
	public final double ARTS_MOD = 1;
	public final double QUICK_MOD = 0.8;
	
	public final int EFFECTIVE_MOD = 2;
	public final double ZERK_EGO_EFF_MOD = 1.5;
	public final double WEAK_MOD = 0.5;
	
	int dmg;
	int atk;
	int flat;
	
	double atkPerc;
	double cardPerc;
	double cardMod;
	double cardRes;
	double npPerc;
	double npBuffPerc;
	double defPerc;
	double effMod;
	double traitPerc;
	double npTraitPerc;
	
	JButton calc;
	JButton reset;
	
	JLabel dmgText;
	JLabel atkStat;
	JLabel atkPercLabel;
	JLabel cardPercLabel;
	JLabel npMultLabel;
	JLabel npPercLabel;
	JLabel defLabel;
	JLabel cardResLabel;
	JLabel flatLabel;
	JLabel traitLabel;
	JLabel npTraitLabel;
	
	JTextField atkEntry;
	JTextField flatEntry;
	JTextField atkPercEntry;
	JTextField cardPercEntry;
	JTextField cardResEntry;
	JTextField npMultEntry;
	JTextField npPercEntry;
	JTextField defEntry;
	JTextField traitEntry;
	JTextField npTraitEntry;
	
	JTextField[] fieldList = {atkEntry, atkPercEntry, defEntry, traitEntry, flatEntry,
			npMultEntry, npPercEntry, cardPercEntry, cardResEntry, npTraitEntry}; //rewrite and rearrange this list
	
	JComboBox servantList;
	JComboBox cardList;
	JComboBox effList;
	
	Servant select = new Servant("Select Servant", 0, "Buster", 0);
	Servant exServant = new Servant("Example", 10000, "Arts", 600);
	
	Servant[] testServants = {
			new Servant("Select Servant", 0, "Buster", 0),
			new Servant("Lancelot (Saber)", 9949, "Arts", 900), 
			new Servant("Cu Chulainn", 7239, "Quick", 1200),
			new Servant("Ozymandias", 11971, "Buster", 600),
			new Servant("Cleopatra", 11088, "Buster", 300)
	};
	String[] testNames = {testServants[0].getName(), testServants[1].getName(), testServants[2].getName(),
			testServants[3].getName(), testServants[4].getName()};
	
	String[] cardTypes = {"Buster", "Arts", "Quick"};
	String[] effTypes = {"Neutral", "Strong (x2)", "Zerk/Ego (x1.5)", "Weak (0.5)"};
	
	AppFrame() {
		//creates the frame and layout of the application
		
		this.setTitle("FGO Damage Calculator");
		
		initLabels();
		
		initButtons();
		
		initTextfields();
		
		initComboBoxes();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(480, 720);
		this.setVisible(true);
		
		addComponents();
		
		this.setIconImage(img.getImage());
		
		this.getContentPane().setBackground(new Color(50, 50, 100));
	}

	public void initLabels() {
		//initializes the labels
		
		dmgText = new JLabel();
		dmgText.setText("Damage: " + dmg);
		dmgText.setBounds(240, 630, 200, 40);
		dmgText.setForeground(Color.WHITE);
		dmgText.setFont(new Font("Arial Bold", Font.PLAIN, 18));
		dmgText.setVisible(true);
		
		atkStat = new JLabel();
		atkStat.setText("Atk Stat:");
		atkStat.setBounds(10, 90, 100, 40);
		atkStat.setForeground(Color.WHITE);
		atkStat.setFont(new Font("Arial Bold", Font.PLAIN, 18));
		atkStat.setVisible(true);
		
		atkPercLabel = new JLabel();
		atkPercLabel.setText("Atk% Up:");
		atkPercLabel.setBounds(10, 170, 100, 40);
		atkPercLabel.setForeground(Color.WHITE);
		atkPercLabel.setFont(new Font("Arial Bold", Font.PLAIN, 18));
		atkPercLabel.setVisible(true);
		
		cardPercLabel = new JLabel();
		cardPercLabel.setText("Card% Up:");
		cardPercLabel.setBounds(140, 250, 100, 40);
		cardPercLabel.setForeground(Color.WHITE);
		cardPercLabel.setFont(new Font("Arial Bold", Font.PLAIN, 18));
		cardPercLabel.setVisible(true);
		
		npMultLabel = new JLabel();
		npMultLabel.setText("NP Mult %:");
		npMultLabel.setBounds(140, 90, 120, 40);
		npMultLabel.setForeground(Color.WHITE);
		npMultLabel.setFont(new Font("Arial Bold", Font.PLAIN, 18));
		npMultLabel.setVisible(true);
		
		npPercLabel = new JLabel();
		npPercLabel.setText("NP% Up:");
		npPercLabel.setBounds(140, 170, 100, 40);
		npPercLabel.setForeground(Color.WHITE);
		npPercLabel.setFont(new Font("Arial Bold", Font.PLAIN, 18));
		npPercLabel.setVisible(true);
		
		defLabel = new JLabel();
		defLabel.setText("Def% Down:");
		defLabel.setBounds(10, 250, 120, 40);
		defLabel.setForeground(Color.WHITE);
		defLabel.setFont(new Font("Arial Bold", Font.PLAIN, 18));
		defLabel.setVisible(true);
		
		traitLabel = new JLabel();
		traitLabel.setText("Trait% Up:");
		traitLabel.setBounds(10, 330, 120, 40);
		traitLabel.setForeground(Color.WHITE);
		traitLabel.setFont(new Font("Arial Bold", Font.PLAIN, 18));
		traitLabel.setVisible(true);
		
		cardResLabel = new JLabel();
		cardResLabel.setText("Card% Debuff:");
		cardResLabel.setBounds(140, 330, 140, 40);
		cardResLabel.setForeground(Color.WHITE);
		cardResLabel.setFont(new Font("Arial Bold", Font.PLAIN, 18));
		cardResLabel.setVisible(true);
		
		npTraitLabel = new JLabel();
		npTraitLabel.setText("NP Trait%:");
		npTraitLabel.setBounds(140, 410, 140, 40);
		npTraitLabel.setForeground(Color.WHITE);
		npTraitLabel.setFont(new Font("Arial Bold", Font.PLAIN, 18));
		npTraitLabel.setVisible(true);
		
		flatLabel = new JLabel();
		flatLabel.setText("+ Flat Atk:");
		flatLabel.setBounds(10, 410, 120, 40);
		flatLabel.setForeground(Color.WHITE);
		flatLabel.setFont(new Font("Arial Bold", Font.PLAIN, 18));
		flatLabel.setVisible(true);
		
	}
	
	public void initButtons() {
		//initializes the buttons
		
		calc = new JButton();
		calc.setBounds(120, 630, 100, 40);
		calc.addActionListener(this);
		calc.setText("Calculate");
		calc.setFocusable(false);
		
		reset = new JButton();
		reset.setBounds(10, 630, 100, 40);
		reset.addActionListener(this);
		reset.setText("Reset");
		reset.setFocusable(false);
	}
	
	public void initTextfields() {
		//initializes the textfields
		
		atkEntry = new JTextField();
		atkEntry.setBounds(10, 125, 100, 40);
		atkEntry.setText("0");
		
		atkPercEntry = new JTextField();
		atkPercEntry.setBounds(10, 205, 100, 40);
		atkPercEntry.setText("0");
		
		cardPercEntry = new JTextField();
		cardPercEntry.setBounds(140, 285, 100, 40);
		cardPercEntry.setText("0");
		
		npMultEntry = new JTextField();
		npMultEntry.setBounds(140, 125, 100, 40);
		npMultEntry.setText("0");
		
		npPercEntry = new JTextField();
		npPercEntry.setBounds(140, 205, 100, 40);
		npPercEntry.setText("0");
		
		defEntry = new JTextField();
		defEntry.setBounds(10, 285, 100, 40);
		defEntry.setText("0");
		
		cardResEntry = new JTextField();
		cardResEntry.setBounds(140, 365, 100, 40);
		cardResEntry.setText("0");
		
		traitEntry = new JTextField();
		traitEntry.setBounds(10, 365, 100, 40);
		traitEntry.setText("0");
		
		npTraitEntry = new JTextField();
		npTraitEntry.setBounds(140, 445, 100, 40);
		npTraitEntry.setText("0");
		
		flatEntry = new JTextField();
		flatEntry.setBounds(10, 445, 100, 40);
		flatEntry.setText("0");
	}
	
	public void initComboBoxes() {
		//initializes comboboxes
		
		servantList = new JComboBox(testNames);
		servantList.addActionListener(this);
		servantList.setBounds(10, 50, 150, 30);
		
		cardList = new JComboBox(cardTypes);
		cardList.addActionListener(this);
		cardList.setBounds(180, 50, 100, 30);
		
		effList = new JComboBox(effTypes);
		effList.addActionListener(this);
		effList.setBounds(10, 510, 120, 30);
	} 
	
	public void addComponents() {
		//adds all components into the frame
		
		this.add(calc);
		this.add(reset);
		
		this.add(dmgText);
		this.add(atkStat);
		this.add(atkPercLabel);
		this.add(cardPercLabel);
		this.add(npMultLabel);
		this.add(npPercLabel);
		this.add(defLabel);
		this.add(cardResLabel);
		this.add(flatLabel);
		this.add(traitLabel);
		this.add(npTraitLabel);
		
		this.add(atkEntry);
		this.add(flatEntry);
		this.add(atkPercEntry);
		this.add(cardPercEntry);
		this.add(npMultEntry);
		this.add(npPercEntry);
		this.add(defEntry);
		this.add(cardResEntry);
		this.add(traitEntry);
		this.add(npTraitEntry);
		
		this.add(servantList);
		this.add(cardList);
		this.add(effList);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//performs an action if a button is pressed
		
		if (e.getSource() == calc) {
			
			//checks if any textfield is empty, and set the text inside to 0 if so 
			/*
			for (int i = 0; i < fieldList.length; i++) {
				if (fieldList[i].getText() == null) {
					fieldList[i].setText("0");
				}
			} */
			
			//initializes all the entered values
			atk = Integer.parseInt(atkEntry.getText());
			flat = Integer.parseInt(flatEntry.getText());
			atkPerc = 1 + (Double.parseDouble(atkPercEntry.getText()) / 100);
			cardPerc = 1 + (Double.parseDouble(cardPercEntry.getText()) / 100);
			cardRes = 1 + (Double.parseDouble(cardResEntry.getText()) / 100);
			npPerc = Double.parseDouble(npMultEntry.getText()) / 100;
			npBuffPerc = 1 + (Double.parseDouble(npPercEntry.getText()) / 100);
			defPerc = 1 + (Double.parseDouble(defEntry.getText()) / 100);
			traitPerc = 1 + (Double.parseDouble(traitEntry.getText()) / 100);
			npTraitPerc = 1 + (Double.parseDouble(npTraitEntry.getText()) / 100);
			
			//calculates the values to give the overall damage
			dmg = (int) (atk * npPerc * (cardMod * cardPerc) * cardRes * atkPerc * NP_SCALE * defPerc * 
					traitPerc * npTraitPerc * npBuffPerc * effMod) + flat;
			dmgText.setText("Damage: " + dmg);
		}
		
		if (e.getSource() == reset) {
			atkEntry.setText("0");
			flatEntry.setText("0");
			atkPercEntry.setText("0");
			cardPercEntry.setText("0");
			cardResEntry.setText("0");
			npMultEntry.setText("0");
			npPercEntry.setText("0");
			defEntry.setText("0");
			traitEntry.setText("0");
			npTraitEntry.setText("0");
			
			dmg = 0;
			dmgText.setText("Damage: " + dmg);
		}
		
		if (e.getSource() == servantList) {
			for (int i = 0; i < testServants.length; i++) {
				if (testServants[i].getName() == servantList.getSelectedItem()) {
					atkEntry.setText(Integer.toString(testServants[i].getAtk()));
					npMultEntry.setText(Integer.toString(testServants[i].getNPPerc()));
					cardList.setSelectedItem(testServants[i].getCardType());
				}
			}
			
		}
		
		if (e.getSource() == cardList) {
			if (cardList.getSelectedItem() == "Buster") {
				cardMod = BUSTER_MOD;
			} else if (cardList.getSelectedItem() == "Quick") {
				cardMod = QUICK_MOD;
			} else {
				cardMod = ARTS_MOD;
			}
		}
		
		if (e.getSource() == effList) {
			if (effList.getSelectedItem() == effTypes[1]) {
				effMod = EFFECTIVE_MOD;
			} else if (effList.getSelectedItem() == effTypes[2]) {
				effMod = ZERK_EGO_EFF_MOD;
			} else if (effList.getSelectedItem() == effTypes[3]) {
				effMod = WEAK_MOD;
			} else {
				effMod = 1;
			}
		}
		
	}
	
}
