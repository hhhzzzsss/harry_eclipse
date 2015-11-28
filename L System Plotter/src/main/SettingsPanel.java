package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SettingsPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 640029470434166332L;
	
	private JButton reload = new JButton("Reload L-System");
	private JButton plant = new JButton("Fractal Plant");
	private JButton dragon = new JButton("Dragon Curve");
	private JButton koch = new JButton("Koch Curve");
	
	public SettingsPanel() {
		setPreferredSize(new Dimension(Main.SCREEN_WIDTH, 200));
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		reload.setActionCommand("reload");
		plant.setActionCommand("plant");
		dragon.setActionCommand("dragon");
		koch.setActionCommand("koch");
		reload.addActionListener(this);
		plant.addActionListener(this);
		dragon.addActionListener(this);
		koch.addActionListener(this);
		add(dragon);
		add(plant);
		add(koch);
		add(reload);
		setBackground(Color.DARK_GRAY);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("reload")) {
			Main.expand();
			Main.redraw();
		}
		else if (e.getActionCommand().equals("plant")) {
			Main.ANGLE_DEGREES = 25.0;
			Main.ANGLE_RADIANS = Main.ANGLE_DEGREES/180*Math.PI;
			Main.AXIOM = "FX";
			Main.F_RULE = "FF";
			Main.X_RULE = "F-[[X]+X]+F[+FX]-X";
			Main.Y_RULE = "";
			Main.START_X = 0;
			Main.START_Y = Main.SCREEN_HEIGHT;
			Main.STEP = 1.5;
			Main.RECURSION = 7;
		}
		else if (e.getActionCommand().equals("dragon")) {
			Main.ANGLE_DEGREES = 90.0;
			Main.ANGLE_RADIANS = Main.ANGLE_DEGREES/180*Math.PI;
			Main.AXIOM = "FX";
			Main.F_RULE = "";
			Main.X_RULE = "X+YF+";
			Main.Y_RULE = "-FX-Y";
			Main.START_X = Main.SCREEN_WIDTH*2/3;
			Main.START_Y = Main.SCREEN_HEIGHT/3;
			Main.STEP = 2;
			Main.RECURSION = 16;
		}
		else if (e.getActionCommand().equals("koch")) {
			Main.ANGLE_DEGREES = 90.0;
			Main.ANGLE_RADIANS = Main.ANGLE_DEGREES/180*Math.PI;
			Main.AXIOM = "F";
			Main.F_RULE = "F+F-F-F+F";
			Main.X_RULE = "";
			Main.Y_RULE = "";
			Main.START_X = Main.SCREEN_WIDTH;
			Main.START_Y = 0;
			Main.STEP = 3;
			Main.RECURSION = 5;
		}
	}
}
