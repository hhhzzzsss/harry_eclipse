import java.util.*;

public class Hello_World {
	public static void main(String[] args) {
		int repeat;
		Scanner scan = new Scanner(System.in);
		System.out.println("Print the number of times you want \"Hello World\" to be printed");
		repeat = scan.nextInt();
		for (int i = 0; i<repeat; i++) {
			System.out.println("Hello World #" + (i+1));
		}
	}
}
