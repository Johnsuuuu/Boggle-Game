import java.awt.event.*;
import javax.swing.*;

public class MenuPage {
    private JFrame menuWindow;
    private JPanel panel;
    private JLabel gameLabel;
    private JButton startNewGameButton;
    private JButton getHelpButton;
    private Game game;
    private String language;
    
    /**
     * Constructor for the MenuPage class
     */
    public MenuPage(String language){
    	this.language = language;
        game = new Game(this.language);
        if (this.language.equalsIgnoreCase("english")) {
            menuWindow = new JFrame("Boggle Game");       }
        else if (this.language.equalsIgnoreCase("french")){
            menuWindow = new JFrame("Jeu Boggle");        }
        menuWindow.setSize(400,300);
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuWindow.setLocationRelativeTo(null);
        addComponent();
        menuWindow.setVisible(true);
    }

    /**
     * add components like JPanel and JButton to the JFrame
     */
    private void addComponent(){
        panel = new JPanel();
        menuWindow.add(panel);
        panel.setLayout(null);
        if (this.language.equalsIgnoreCase("english")) {
            gameLabel = new JLabel("Boggle Game");        }
        else if (this.language.equalsIgnoreCase("french")){
            gameLabel = new JLabel("Jeu Boggle");        }
        gameLabel.setBounds(157,10,100,50);
        panel.add(gameLabel);
        if (this.language.equalsIgnoreCase("english")) {
            startNewGameButton = new JButton("Start New Game");        }
        else if (this.language.equalsIgnoreCase("french")){
            startNewGameButton = new JButton("Commencer Une Nouvelle Partie");        }
        startNewGameButton.setBounds(90, 100,220,20);
        panel.add(startNewGameButton);

        if (this.language.equalsIgnoreCase("english")) {
            getHelpButton = new JButton("Get Help");        }
        else if (this.language.equalsIgnoreCase("french")){
            getHelpButton = new JButton("Obtenir De L'aide");        }
        getHelpButton.setBounds(90,150,220,20);
        panel.add(getHelpButton);

        startNewGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuWindow.dispose();
                game.start();
            }
        });
        getHelpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent b) {
                game.help();
            }
        });

    }

}
