package processingtest;

public class Circle {
	int x;
	int y;
	int size;
	double speed; //0 is no speed, 1 is infinite speed
	boolean[] colorType = new boolean[]{true, true, true};
	double dir;
	double mag;
	float[] color = new float[]{0.0f, 0.0f, 0.0f};
	
	public Circle(int xPos, int yPos, int circleSize, double cSpeed, boolean[] color) {
		x = xPos;
		y = yPos;
		size = circleSize;
		speed = cSpeed;
		colorType = color;
		System.out.println(color[0]);
		System.out.println(colorType[0]);
	}
	
	public void updateCircle(int xPos, int yPos) {
		int dy=y-yPos;
		int dx=x-xPos;
		dir = Math.atan2(y-yPos, x-xPos);
		mag = Math.sqrt(dx*dx + dy*dy);
		mag = mag * (1.0-speed);
		
		y = (int) (Math.sin(dir) * mag);
		x = (int) (Math.cos(dir) * mag);
		
		y+=yPos;
		x+=xPos;
	}
}
