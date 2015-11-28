package myfirstprocessing;

public class LineEffect {
	public int ColorR;
	public int ColorG;
	public int ColorB;
	public float[] Weight = new float[MyFirstProcessing.EllipseNum];
	public int Fade;
	public int x;
	public int y;
	
	public LineEffect(int r, int g, int b, float[] weight, int X, int Y) {
		ColorR = r;
		ColorG = g;
		ColorB = b;
		Weight = weight;
		Fade = 255;
		x = X;
		y = Y;
	}
}
