/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.util.Scanner;

/**
 *
 * @author ishanmadan
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Figure figure = new Figure();
        Words words = new Words();
        Scanner input = new Scanner(System.in);

        EvilHangman evilHangman = new EvilHangman(figure, words, input);
        Hangman hangman = new Hangman(figure, words, input);
            
        boolean run = true;

        while (run) {
            System.out.println("Hello there, and welcome to Hangman!");

            System.out.println("Would you like to play Regular Hangman or Evil Hangman? Please answer \"regular\" or \"evil\". If you don't know the difference, pick regular.");
            String version = input.nextLine().toLowerCase().replaceAll("[^regularevil]", "");
            while (!(version.equals("regular") || version.equals("reg") || version.equals("evil"))) {
                System.out.println("I'm sorry, that's not a valid answer. Please answer yes or no.");
                version = input.nextLine().toLowerCase().replaceAll("[^regularevil]", "");
            }

            if (version.equals("regular") || version.equals("reg")){
                hangman.run();
            } else {
                evilHangman.run();
            }

            System.out.println("Play again? (yes or no)");
            String playAgain = input.nextLine().toLowerCase().replaceAll("[^yesno]", "");
            while (!(playAgain.equals("yes") || playAgain.equals("no"))) {
                System.out.println("I'm sorry, that's not a valid answer. Please answer yes or no.");
                playAgain = input.nextLine().toLowerCase().replaceAll("[^yesno]", "");
            }

            if (playAgain.equals("yes")) {
                run = true;
            } else {
                run = false;
                System.out.println("Bye!");
            }
        }
    }
}
