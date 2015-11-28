package main;

public class Main {
	
	public static boolean sound = true;
	
	public static void main(String[] args) {
		
		Layout l = new Layout();
		
		Frame frame = new Frame("Layout");
		frame.add(l);
		frame.pack();
		
		l.startLoop();
	}
}
