import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.net.URL;
public class Game {
    private ArrayList<Dice> diceList = new ArrayList<>();
    private String language;
    /**
     * Constructor for the Game class
     */
    public Game(String language){
        this.language = language;
    }

    /**
     * Create a new MenuPage
     */
    public void load(String language){
        MenuPage menuPage =new MenuPage(this.language);
    }

    /**
     * Create a new GamePage
     */
    public void start(){
        Board board = new Board(this.language);
        diceList = board.shuffle();
        GamePage gamePage = new GamePage(diceList, this.language);
    }

    /**
     * Calculate the score and create a new ScorePage
     */
    public void end(ArrayList<String> wordlist){
        Score score = new Score(wordlist, this.language);
        score.calculateTotal();
        score.displayScore();
    }

    /**
     * Get the helping guide for Boggle
     */
    public void help(){
        try {
        	if (this.language.equalsIgnoreCase("english")) {
        		Desktop.getDesktop().browse(new URL("https://www.hasbro.com/common/instruct/boggle.pdf").toURI());
        	}
        	else if (this.language.equalsIgnoreCase("french")) {
        		Desktop.getDesktop().browse(new URL("https://www.hasbro.com/common/instruct/Boggle(French).PDF").toURI());
        	}
        }catch (Exception b){
            b.printStackTrace();
        }

    }

    public static void main(String[] args){
        LanguageMenuPage languageMenuPage = new LanguageMenuPage();
    }
}
