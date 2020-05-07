import java.awt.event.*;
import javax.swing.*;

public class LanguageMenuPage {
    private JFrame languageMenuWindow;
    private JPanel panel;
    private JLabel gameLabel;
    private JLabel languageLabel;
    private JComboBox languageSelector;
    private Game game;

    /**
     * Constructor for the LanguageMenuPage class
     */
    public LanguageMenuPage(){
        languageMenuWindow = new JFrame("Boggle Game");
        languageMenuWindow.setSize(400,300);
        languageMenuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        languageMenuWindow.setLocationRelativeTo(null);
        addComponent();
        languageMenuWindow.setVisible(true);
    }

    /**
     * add components to the JFrame
     */
    private void addComponent(){
        panel = new JPanel();
        languageMenuWindow.add(panel);
        panel.setLayout(null);
        gameLabel = new JLabel("Boggle Game");
        gameLabel.setBounds(157,10,100,50);
        languageLabel = new JLabel("Language:");
        languageLabel.setBounds(90,79,100,50);
        panel.add(gameLabel);
        panel.add(languageLabel);
        languageSelector = new JComboBox();
        languageSelector.addItem("--Please select--");
        languageSelector.addItem("English");
        languageSelector.addItem("French");
        languageSelector.setBounds(157,80,165,50);
        panel.add(languageSelector);

        languageSelector.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    if(languageSelector.getSelectedIndex() == 1){
                        languageMenuWindow.dispose();
                        game = new Game("English");
                        game.load("English");
                    }
                    if(languageSelector.getSelectedIndex() == 2){
                        languageMenuWindow.dispose();
                        game = new Game("French");
                        game.load("French");
                    }
                }
            }
        });

    }

}


