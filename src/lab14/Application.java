package lab14;

import java.util.Scanner;

public class Application {
    private static ChatBot chatBot = new ChatBot();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String userInput = "";

        while(!userExit(userInput)); {
            System.out.print("USER > ");
            userInput = input.nextLine();
            chatBot.processQuery(userInput);
        }

    }

    private static boolean userExit(String userInput) {
        String exitCommand = "stop";
        return userInput.equals(exitCommand);
    }
}
