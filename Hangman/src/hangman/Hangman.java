/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ishanmadan
 */
public class Hangman {
    private static Figure figure = new Figure();
    private static Words words = new Words();
    private static Scanner input = new Scanner(System.in);
    
    private static int stage;
    private static String word;
    private static ArrayList<String> usedLetters = new ArrayList<String>();
    private static List<String> resultLetters = new ArrayList<String>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean run = true;

        System.out.println("Hello there, and welcome to Hangman!");

        while (run) {
            stage = 0;
            word = words.getRandomWord();
            
            usedLetters.clear();
            resultLetters.clear();
            String[] wordLetters = word.split("");

            for (int i = 0; i < word.length(); i++) {
                resultLetters.add("_");
            }

            print();
            
            while (gameOver() == 0) {
                System.out.println("Guess a letter (or the word):");
                String guess = input.nextLine().toLowerCase().replaceAll("[^abcdefghijklmnopqrstuvwxyz]", "");
                
                while (guess.length() < 1 || usedLetters.indexOf(guess) != -1) {
                    System.out.println("I'm sorry, that's not a valid guess. Please try again:");
                    guess = input.nextLine().toLowerCase().replaceAll("[^abcdefghijklmnopqrstuvwxyz]", "");
                }

                if (word.equals(guess)) {
                    resultLetters = Arrays.asList(guess.split(""));
                } else if (guess.length() > 1) {
                    System.out.println("\nI'm sorry, that's not the word.");
                    stage++;
                    print();
                } else {
                    usedLetters.add(guess);

                    boolean correct = false;
                    for (int i = 0; i < wordLetters.length; i++) {
                        if (wordLetters[i].equals(guess)) {
                            resultLetters.set(i, guess);
                            correct = true;
                        }
                    }
        
                    if (correct) {
                        System.out.println("\nThe letter " + guess + " is in the word!");
                    } else {
                        System.out.println("\nOops! The letter " + guess + " is not in the word!");
                        stage++;
                    }
        
                    print();
                }
            }
            
            if (gameOver() == 1) {
                System.out.println("\nCongratulations! You've succeeded at Hangman!");
            } else if (gameOver() == 2) {
                System.out.println("\nYou lost! The word was " + word + ".");
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

    public static int gameOver() {
        int game = 1;
        for (String letter : resultLetters) {
            if (letter.equals("_")) {
                game = 0;
            }
        }

        if (stage == figure.getNumStages() - 1) {
            game = 2;
        }

        return game;
    }

    public static void print() {
        System.out.println(figure.print(stage));
        
        System.out.println("\nWord:");
        for (String letter : resultLetters) {
            System.out.print(letter);
        }
        System.out.println("");

        boolean hasIncorrect = false;
        for (String letter : usedLetters) {
            if (resultLetters.indexOf(letter) == -1) {
                hasIncorrect = true;
            }
        }

        if (hasIncorrect) {
            System.out.println("\nIncorrect Letters:");
            for (String letter : usedLetters) {
                if (resultLetters.indexOf(letter) == -1) {
                    System.out.print(letter);
                }
            }
        }

        System.out.println("");
        
        // System.out.println("***" + word + "***");
    }
}
