package main;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.*;

public class Offset extends JPanel implements MouseListener{
	
	Sound sound = new Sound();
	
	JLabel label;
	
	int x = 300;
	int y = 250;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillOval(295, 245, 10, 10);
		g.drawLine(300, 250, x, y);
		g.fillRect(x-5, y-5, 10, 10);
	}
	
	public Offset(Color color) {
		setBackground(color);
		addMouseListener(this);
		setPreferredSize(new Dimension(600, 500));
		setLayout(new GridLayout(3, 1));
		
		label = new JLabel("", JLabel.CENTER);
		label.setSize(350, 100);
		add(label);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		sound.playSound(Sound.PING);
		
		x = e.getX();
		y = e.getY();
		
		label.setText("x: " + x + " y: " + y + " Offset: " + (Math.sqrt((double) (x-300)*(x-300) + (y-250)*(y-250))));
		
		repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
