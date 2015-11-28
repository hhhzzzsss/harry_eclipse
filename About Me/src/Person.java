
public class Person {
	String name;
	short age;
	char gender;
	float height;
	boolean asian;
	
	public void PrintData() {
		System.out.println(name + "'s properties:\n");
		System.out.println("Age: " + age);
		System.out.print("Gender: ");
		if (gender == 'M') {
			System.out.println("Male");
		}
		else if (gender == 'F') {
			System.out.println("Female");
		}
		else {
			System.out.println("Unknown");
		}
		System.out.println("Height: " + height);
		System.out.println("Is asian: " + asian);
	}
}
