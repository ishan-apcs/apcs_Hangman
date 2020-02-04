package hangman;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Words {
    private Path path = FileSystems.getDefault().getPath(System.getProperty("user.home"), "apcs", "apcs_Hangman", "Hangman", "dictionary.txt");
    private String wordList;
    private String[] words;
    
    public Words() {
        try {
            wordList = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Reading the file failed: \n" + e);
        }
        
        words = wordList.split(",");
    }

    public String getRandomWord() {
        int index = (int)(Math.random() * words.length);
        return words[index];
    }
}