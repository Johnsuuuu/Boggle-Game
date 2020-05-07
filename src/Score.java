/**
 * The score class that takes the list of words that the user picked from the dice on the board and generates a valid list
 * and an invalid list.
 *
 * It is important that the remove methods in this class are used in the correct order, because of this
 * I call them within the calculate total method to ensure that they cannot accidentally be called in the wrong
 * order.
 *
 * @author Jared Ladd
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Score {
    private int total=0;
    private ArrayList<String> wordlist;
    private ArrayList<String> validWordlist;
    private ArrayList<String> invalidWordlist;
    private File dict;
    private String language;
    private HashSet<String> dict_words;

    /**
     * Constructor for the Score class
     * @param wordlist will always be given to the score class because it taken from a completed game
     */
    public Score(ArrayList<String> wordlist, String language) {
        this.language = language;
        this.wordlist = wordlist;
        this.validWordlist = new ArrayList<>();
        this.invalidWordlist = new ArrayList<>();
        dict_words = new HashSet<>();

        if (this.language.equalsIgnoreCase("english")) {
            dict = new File("/Users/john/Desktop/school/csce_361/boggle-game/src/Boggle/src/dict.txt");
        }
        else if (this.language.equalsIgnoreCase("french")){
            dict = new File("/Users/john/Desktop/school/csce_361/boggle-game/src/Boggle/src/francais.txt");
        }
        try {
            Scanner s = new Scanner(dict);
            while (s.hasNextLine()) {
                dict_words.add(s.nextLine().toUpperCase());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * returns the total and calls methods that remove duplicates and remove illegals
     * @return
     */

    public int calculateTotal() {
        removeDuplicate();
        removeIllegal();
        for(String word: validWordlist) {
            if (word.length() <= 4) {
                total += 1;
            }
            else if (word.length() == 5) {
                total += 2;
            }
            else if (word.length() == 6) {
                total += 3;
            }
            else if (word.length() == 7) {
                total += 5;
            }
            else {
                total += 11;
            }
        }
        return total;
    }

    /**
     * If a word is not in the valid word list, then it can be added. This means
     * the same word cannot be added twice to the valid word list
     */

    private void removeDuplicate() {

        for (String word: wordlist) {
            if(this.validWordlist.indexOf(word) == -1) {
                this.validWordlist.add(word);
            }
        }

    }

    /**
     * Remove illegal goes through the word list and adds any word not in the dictionary to the
     * invalid word list. Then it goes through the valid word list and removes any word that is in both lists.
     * This method assumes that valid word list does not have any duplicates.
     */

    private void removeIllegal() {
        for (String word : wordlist) {
            if (word.length() < 3) {
                invalidWordlist.add(word);
            } else {
                if (!dict_words.contains(word.toUpperCase())){
                    invalidWordlist.add(word);
                }
            }

        }
        for(String word: invalidWordlist) {
            this.validWordlist.remove(word);
        }

    }

    /**
     *
     */
    public void displayScore() {
        ScorePage scorePage = new ScorePage(validWordlist, invalidWordlist,total, this.language);
    }


}
