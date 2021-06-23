package FGODmg;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Window {
	
	public static void main(String[] args) {
		
		JLabel appTitle = new JLabel();
		appTitle.setText("FGO Damage Calculator");
		appTitle.setHorizontalTextPosition(JLabel.CENTER);
		appTitle.setVerticalTextPosition(JLabel.TOP);
		appTitle.setForeground(Color.WHITE);
		appTitle.setFont(new Font("Arial Bold", Font.PLAIN, 24));
		appTitle.setVerticalAlignment(JLabel.TOP);
		
		AppFrame app = new AppFrame();
		
		app.add(appTitle);
		
	}
}
