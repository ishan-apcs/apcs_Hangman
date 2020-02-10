package hangman;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Words {
    private Path path;
    private String wordList;
    private String[] words;

    public Words() {

        FindFile ff = new FindFile();
        path = ff.findFile("dictionary.txt", new File("hangman"));

        try {
            wordList = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        } catch (final IOException e) {
            System.out.println("Reading the file failed:\n" + e);
        }
        words = wordList.split(",");
    }

    public String getRandomWord() {
        int index = 0;
        String word = "";
        while (word.length() < 3) {
            index = (int)(Math.random() * words.length);
            word = words[index];
        }

        return word;
    }

    public String[] getWordList() {
        return words;
    }
}