
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		String name;
		int month;
		int day;
		int year;
		String hobby;
		String book;
		String movie;
		
		System.out.print("Please enter your name:");
		name = in.next();
		System.out.println("\nPlease enter your birthdate:");
		System.out.print("Month (1 - 12):");
		month = in.nextInt();
		while (month > 12 || month < 1) {
			System.out.print("Invalid month, you can only enter a month from 1 - 12:");
			month = in.nextInt();
		}
		System.out.print("Day (1 - 31):");
		day = in.nextInt();
		while (day > 31 || day < 1) {
			System.out.print("Invalid day, you can only enter a day from 1 - 31:");
			day = in.nextInt();
		}
		System.out.print("Year (1 - 2015):");
		year = in.nextInt();
		while (year > 2015 || year < 1) {
			System.out.print("Invalid year, you can only enter a year from 1 - 2015:");
			year = in.nextInt();
		}
		System.out.print("\nPlease enter your hobby:");
		hobby = in.next();
		System.out.print("\nPlease enter your favorite book:");
		book = in.next();
		System.out.print("\nPlease enter your favorite movie:");
		movie = in.next();
		System.out.println("\n\nHere is the data I gathered:");
		System.out.println("\tName: " + name);
		System.out.println("\tBirthday: " + month + "/" + day + "/" + year);
		System.out.println("\tHobby: " + hobby);
		System.out.println("\tFavorite book: " + book);
		System.out.println("\tFavorite movie: " + movie);
	}
}
