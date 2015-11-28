package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Panel extends JPanel implements ActionListener{
	
	int[] BoardStates = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
	
	final int[][] WINNING_PATTERNS = new int[][] {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}}; 
	
	JButton[] BoardButtons = new JButton[9];
	
	JButton mute = new JButton("Mute");
	JButton unmute = new JButton("Unmute");
	JButton change = new JButton("Change Schemes");
	
	Graphics2D g2d;
	
	Sound sound = new Sound();
	
	public int scheme = 0;
	
	int turn = 0;
	
	public Panel() {
		
		setLayout(new GridLayout(4, 3));
		
		for (int i = 0; i < 9; i++) {
			BoardButtons[i] = new JButton("Click to make a move!");
			JButton button = BoardButtons[i];
			button.setPreferredSize(new Dimension(150, 150));
			button.setActionCommand("" + i);
			button.addActionListener(this);
			add(button);
		}
		
		mute.setPreferredSize(new Dimension(150, 150));
		mute.setActionCommand("mute");
		mute.addActionListener(this);
		add(mute);
		
		unmute.setPreferredSize(new Dimension(150, 150));
		unmute.setActionCommand("unmute");
		unmute.addActionListener(this);
		unmute.hide();
		add(unmute);
		
		change.setPreferredSize(new Dimension(150, 150));
		change.setActionCommand("change");
		change.addActionListener(this);
		add(change);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g2d = (Graphics2D) g;
		
		if (scheme == 0) {
			for (int y = 0; y<3; y++) {
				for (int x = 0; x < 3; x ++) {
					if (BoardStates[y*3 + x] == 1) {
						g.setColor(Color.CYAN);
						g.fillOval(x*150, y*150, 150, 150);
					}
					else if (BoardStates[y*3 + x] == 2) {
						g.setColor(Color.RED);
						g.fillOval(x*150, y*150, 150, 150);
					}
				}
			}
		}
		else if (scheme == 1) {
			for (int y = 0; y<3; y++) {
				for (int x = 0; x < 3; x ++) {
					if (BoardStates[y*3 + x] == 1) {
						g2d.setColor(Color.CYAN);
						g2d.setStroke(new BasicStroke(5));
						g2d.drawOval(x*150 + 4, y*150 + 4, 142, 142);
					}
					else if (BoardStates[y*3 + x] == 2) {
						g2d.setColor(Color.RED);
						g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
						g2d.drawLine(x*150, y*150, x*150 + 150, y*150 + 150);
						g2d.drawLine(x*150, y*150 + 150, x*150 + 150, y*150);
					}
				}
			}
		}
		
		g2d.setColor(Color.DARK_GRAY);
		g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		
		g2d.drawLine(0, 150, 450, 150);
		g2d.drawLine(0, 300, 450, 300);
		g2d.drawLine(150, 0, 150, 450);
		g2d.drawLine(300, 0, 300, 450);
		
		for (int p = 0; p < WINNING_PATTERNS.length; p++) {
			
			int[] pattern = WINNING_PATTERNS[p];
			int state = BoardStates[pattern[0]];
			
			if (state != 0 && BoardStates[pattern[1]] == state && BoardStates[pattern[2]] == state) {
				g2d.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				
				g2d.drawLine(getX(pattern[0]), getY(pattern[0]), getX(pattern[pattern.length-1]), getY(pattern[pattern.length-1]));
				win(state);
			}
			
		}
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 450, 450, 150);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 9; i++) {
			if (e.getActionCommand().equals("" + i)) {
				BoardStates[i] = turn%2 + 1;
				BoardButtons[i].hide();
				System.out.println("Action Detected");
				
				sound.playSound(Sound.PING);
				
				turn++;
			}
		}
		
		if (turn%2 + 1 == 1) {
			for (int i = 0; i < 9; i++) {
				BoardButtons[i].setText("Blue's turn");
			}
		}
		else {
			for (int i = 0; i < 9; i++) {
				BoardButtons[i].setText("Red's turn");
			}
		}
		
		if (e.getActionCommand().equals("mute")) {
			Main.sound = false;
			mute.hide();
			unmute.show();
		}
		else if (e.getActionCommand().equals("unmute")) {
			Main.sound = true;
			unmute.hide();
			mute.show();
		}
		else if (e.getActionCommand().equals("change")) {
			scheme = (scheme + 1) % 2;
		}
		
		repaint();
		System.out.println("General Action Detected. Action Command: " + e.getActionCommand());
		
	}
	
	private void win(int winner) {
		for (int i = 0; i < 9; i++) {
			BoardButtons[i].hide();
		}
	}
	
	private int getX(int l) {
		return l%3 * 150 + 75;
	}
	
	private int getY(int l) {
		return Math.floorDiv(l, 3)*150 + 75;
	}
	
}
