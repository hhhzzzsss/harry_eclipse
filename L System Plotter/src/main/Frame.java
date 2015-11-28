package main;
// hello I just changed something
import javax.swing.JFrame;

public class Frame extends JFrame{
	
	private static final long serialVersionUID = 4887525192006201710L;
	
	public Frame(String title) {
		super(title);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
