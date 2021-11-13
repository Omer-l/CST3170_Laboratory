package Lab4;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {
	
	public static void main(String[] args) {
		prompt();
	}
	
	public static void prompt() {
		State stateOfGame = new State(0, 0, 3, 3, Position.RIGHT);
//		printStateOfGame(stateOfGame);
		Scanner input = new Scanner(System.in);
		
//		String userInput = input.next();
		
		Depth_First_Search dfs = new Depth_First_Search();
		System.out.println(stateOfGame);
		System.out.println(dfs.getPossibleMoves(stateOfGame, 0));
		
	}
	
	
	public static void processUserInput(String userInput) {
	}
	
	public static void printStateOfGame(State stateOfGame) {
		System.out.println(stateOfGame.toString());
		System.out.print("c -> cannibal\nm -> missionary\ni.e. ccm -> 2 cannibals and 1 misssionary to the left.\n>_ ");
	}
}
