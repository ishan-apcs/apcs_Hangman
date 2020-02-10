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

public class EvilHangman {
    private Figure figure;
    private Words words;
    private Scanner input;
    
    private int stage;
    private String word;
    private ArrayList<String> wordList = new ArrayList<String>();
    private ArrayList<String> usedLetters = new ArrayList<String>();
    private List<String> resultLetters = new ArrayList<String>();

    public EvilHangman(Figure figure, Words words, Scanner input) {
        this.figure = figure;
        this.words = words;
        this.input = input;
    }

    public void run() {
        stage = 0;
        
        usedLetters.clear();
        resultLetters.clear();
        wordList.clear();
        String[] wordLetters;

        for (String newWord : words.getWordList()) {
            wordList.add(newWord);
        }
        
        while (wordList.size() > 1 && gameOver() != 2) {
            System.out.println("\nGuess a letter:");
            String guess = input.nextLine().toLowerCase().replaceAll("[^abcdefghijklmnopqrstuvwxyz]", "");
            
            while (guess.length() != 1 || usedLetters.indexOf(guess) != -1) {
                System.out.println("I'm sorry, that's not a valid guess. Please try again:");
                guess = input.nextLine().toLowerCase().replaceAll("[^abcdefghijklmnopqrstuvwxyz]", "");
            }

            int i = 0;
            while (wordList.size() > 1 && i < wordList.size()) {
                boolean containsLetter = false;
                String checkWord = wordList.get(i);
                for (int j = 0; j < checkWord.length(); j++) {
                    if (checkWord.substring(j,j+1).equals(guess)) {
                        containsLetter = true;
                    }
                }
                if (containsLetter) {
                    wordList.remove(i);
                } else {
                    i++;
                }
            }

            usedLetters.add(guess);

            if (wordList.size() == 1) {
                word = wordList.get(0);
                wordLetters = word.split("");

                for (int j = 0; j < word.length(); j++) {
                    resultLetters.add("_");
                }

                for (int j = 0; j < wordLetters.length; j++) {
                    if (wordLetters[j].equals(guess)) {
                        resultLetters.set(j, guess);
                    }
                }

                print();
            } else {
                stage++;
                evilPrint();
            }
        }

        while (gameOver() == 0) {
            print();
            wordLetters = word.split("");
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
            }
        }
        
        if (gameOver() == 1) {
            System.out.println("\nCongratulations! You've succeeded at Hangman!");
        } else if (gameOver() == 2) {
            System.out.print("\nYou lost!");
            if (word != null) {
                System.out.println("The word was " + word + ".");
            } else {
                System.out.println("");
            }
        }
    }
    
    public int gameOver() {
        if (stage == figure.getNumStages() - 1) {
            return 2;
        }

        for (String letter : resultLetters) {
            if (letter.equals("_")) {
                return 0;
            }
        }

        return 1;
    }

    public void evilPrint() {
        System.out.println(figure.print(stage));

        System.out.println("Used Letters:");
        for (String letter : usedLetters) {
            System.out.print(letter);
        }

        System.out.println("");
    }

    public void print() {
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
    }
}
