/**
 * 
 */
package connect4;

import java.util.Scanner;

/**
 * Jun 5, 2017
 * @author Andrew Niklas
 *
 */
public class UserInterface {

	private Scanner sc = new Scanner(System.in);

	/**
	 * @return
	 */
	public char welcome() {
		char answer;
		System.out.println("Welcome to 4 in a Line\nWould you like to go first? ");
		answer = sc.nextLine().toLowerCase().charAt(0);
		while (answer != 'y' && answer != 'n') {
			System.out.println("Invalid Input!\n Would you like to go first?(y/n)");
			answer = sc.nextLine().toLowerCase().charAt(0);
		}
		return answer;
	}

	/**
	 * @return
	 */
	public Position enterPosition() {
		String input;
		System.out.println("Choose your next move: ");
		input = sc.nextLine();
		while (!input.matches("[a-hA-H][1-8]")) {
			System.out.println("Not a legal move!\\nChoose your next move: ");
			input = sc.nextLine();
		}
		return new Position(input.charAt(0), Integer.parseInt(input.substring(1, 2)));
	}

	/**
	 * @return
	 */
	public int thinkTime() {
		int answer = 5;
		System.out.println("Enter the amount of time in seconds for the bot to think: ");
		answer = Integer.parseInt(sc.nextLine());
		while (answer > 5) {
			System.out.println("Time cannot be greater than 5 seconds!\\nEnter the amount of time in seconds for the bot to think: ");
			answer = Integer.parseInt(sc.nextLine());
		}
		return answer;
	}
	
	public void exit(){
		System.out.println("Press enter to exit . . . ");
		sc.nextLine();
	}

}
