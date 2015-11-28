import javax.swing.JFrame;

public class Frame extends JFrame{
	public Frame(String name) {
		super(name);
		Panel panel = new Panel();
		add(panel);
		pack();
		
	}
}
