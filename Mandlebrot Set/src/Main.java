
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main {
	
	public static int width = 800;
	public static int height = 800;
	public static int iter = 1000;
	public static double[] selectionArea = new double[]{-2, -2, 4}; //{xPos, yPos, distance}
	
	public static Panel panel = new Panel();
	public static controlPanel cpanel = new controlPanel();
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Mandlebrot Set");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width+40, height+20);
		frame.add(panel);
		frame.setResizable(false);
		frame.setVisible(true);
		
		JFrame control = new JFrame("Control Panel");
		control.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		control.setPreferredSize(new Dimension(500, 300));
		control.pack();
		control.add(cpanel);
		
		control.setVisible(true);
		
	}
	
	public static void reloadPanel() {
		panel.reload();
	}

}
