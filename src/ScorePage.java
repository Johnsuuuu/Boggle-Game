import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScorePage {

    private int total;
    private ArrayList<String> validWordList;
    private ArrayList<String> invalidWordList;
    private JFrame scoreWindow;
    private JPanel validWordListPanel;
    private JPanel inValidWordListPanel;
    private JPanel totalScorePanel;
    private JLabel validWordListLabel;
    private JLabel invalidWordListLabel;
    private JTextArea validWordListArea;
    private JScrollPane scrollValidWordListArea;
    private JTextArea invalidWordListArea;
    private JScrollPane scrollInvalidWordListArea;
    private JLabel totalScoreLabel;
    private JButton newGame;
    private String language;

    public ScorePage(ArrayList<String> validWordList, ArrayList<String> invalidWordList, int total, String language) {
        this.validWordList = validWordList;
        this.invalidWordList = invalidWordList;
        this.total = total;
        this.language = language;
        addJFrame();
        addValidWordlistPanel();
        addInvalidWordlistPanel();
        createScorePanel();
        createScoreWindow();
    }

    private void addJFrame() {
        if (this.language.equalsIgnoreCase("english")) {
            scoreWindow = new JFrame("Score Page");
        }
        else if (this.language.equalsIgnoreCase("french")){
            scoreWindow = new JFrame("Page de Score");
        }

        scoreWindow.setSize(500,400);
        scoreWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scoreWindow.setLocationRelativeTo(null);
        scoreWindow.setVisible(true);
    }

    private void addValidWordlistPanel(){
        validWordListPanel = new JPanel();
        validWordListArea = new JTextArea();
        validWordListArea.setEditable(false);
        Border loweredBevelBorder = BorderFactory.createLoweredBevelBorder();
        scrollValidWordListArea = new JScrollPane(validWordListArea);
        scrollValidWordListArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollValidWordListArea.setPreferredSize(new Dimension(180,270));
        scrollValidWordListArea.setBorder(loweredBevelBorder);
        validWordListPanel.add(scrollValidWordListArea);
        if (this.language.equalsIgnoreCase("english")) {
            validWordListArea.append("Valid Words:\n");        }
        else if (this.language.equalsIgnoreCase("french")){
            validWordListArea.append("Mots Valides:\n");        }
        for (String word: validWordList) {
            validWordListArea.append(word+"\n");
        }
    }
    private void addInvalidWordlistPanel(){
        inValidWordListPanel = new JPanel();
        invalidWordListArea = new JTextArea();
        invalidWordListArea.setEditable(false);
        Border loweredBevelBorder = BorderFactory.createLoweredBevelBorder();
        scrollInvalidWordListArea = new JScrollPane(invalidWordListArea);
        scrollInvalidWordListArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollInvalidWordListArea.setPreferredSize(new Dimension(180,270));
        scrollInvalidWordListArea.setBorder(loweredBevelBorder);
        inValidWordListPanel.add(scrollInvalidWordListArea);
        if (this.language.equalsIgnoreCase("english")) {
            invalidWordListArea.append("Invalid Words:\n");        }
        else if (this.language.equalsIgnoreCase("french")){
            invalidWordListArea.append("Mots Invalides:\n");        }
        for (String word: invalidWordList) {
            invalidWordListArea.append(word+"\n");
        }
    }

    private void createScorePanel() {
        totalScorePanel = new JPanel(new FlowLayout(1,50,0));
        totalScoreLabel = new JLabel("Score: " + total);
        totalScoreLabel.setPreferredSize(new Dimension(100, 45));
        Border loweredBevelBorder = BorderFactory.createLoweredBevelBorder();
        totalScoreLabel.setBorder(loweredBevelBorder);
        if (this.language.equalsIgnoreCase("english")) {
            newGame = new JButton("Start New Game");       }
        else if (this.language.equalsIgnoreCase("french")){
            newGame = new JButton("Commencer une nouvelle partie");        }


        newGame.addActionListener(new newGameListener(this.language));
        totalScorePanel.add(totalScoreLabel,BorderLayout.CENTER);
        totalScorePanel.add(newGame);
    }

    private void createScoreWindow() {
        scoreWindow.add(validWordListPanel,BorderLayout.WEST);
        scoreWindow.add(inValidWordListPanel,BorderLayout.EAST);
        scoreWindow.add(totalScorePanel,BorderLayout.SOUTH);
    }

    private class newGameListener implements ActionListener{
    	private String language;
    	public newGameListener(String language) {
    		this.language = language;
    	}
        @Override
        public void actionPerformed(ActionEvent e) {
            scoreWindow.dispose();
            Game game = new Game(this.language);
            game.start();
        }
    }





}
