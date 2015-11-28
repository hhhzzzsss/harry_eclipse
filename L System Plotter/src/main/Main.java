package main;

public class Main {
	
	public static final int SCREEN_WIDTH = 600;
	public static final int SCREEN_HEIGHT = 600;
	public static double ANGLE_DEGREES = 25.0;
	public static double ANGLE_RADIANS = ANGLE_DEGREES/180*Math.PI;
	public static String AXIOM = "FX";
	public static String F_RULE = "FF";
	public static String X_RULE = "F-[[X]+X]+F[+FX]-X";
	public static String Y_RULE = "";
	public static int START_X = 0;
	public static int START_Y = 600;
	public static double STEP = 1.5;
	public static int RECURSION = 2;
	
	static Panel panel = new Panel();
	
	public static void main(String[] args) {
		Frame frame = new Frame("L-System Plotter");
		frame.setResizable(false);
		frame.add(new LayoutPanel());
		frame.pack();
	}
	
	public static void redraw() {
		panel.repaint();
	}
	
	public static void expand() {
		panel.expand();
	}
	
}
