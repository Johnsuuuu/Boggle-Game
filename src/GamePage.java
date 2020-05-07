/**
 * This is the GUI for the game page.
 *
 * When the player clicks the diceButtons, the button will change its color and add the character on the button to the textbox,
 * and the textbox will display the character.
 *
 * When the player click the submitButton, it will clear the color on the diceButtons,
 * append the text on the textbox to the wordlistArea and the wordlist, and the textbox will be cleared.
 *
 * When the player click the undoButton, it will remove the last word from the wordlistArea and the wordlist.
 *
 * When the player click the endGameButton, it will call the end(wordlist:ArrayList<String>,dicelist:ArrayList<Dice>) method in Game class,
 * and it will send the wordlist and the diceList as parameters. The same thing happens when the time runs out.
 *
 * authored by Zhenghui Su
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class GamePage {
    private JFrame gameWindow;
    private JPanel boardPanel;
    private JPanel textboxPanel;
    private JPanel wordlistPanel;
    private JPanel bottomPanel;
    private JButton endGameButton;
    private JButton submitButton;
    private JButton undoButton;
    private ArrayList<JButton> diceButtons = new ArrayList<>();
    private JLabel textbox;
    private JTextArea wordlistArea;
    private JLabel clock;
    private JScrollPane scrollWordlistArea;
    private ArrayList<String> wordlist = new ArrayList<>();
    private ArrayList<Dice> diceList = new ArrayList<>();
    private ArrayList<String> tempDiceButtonNameList = new ArrayList<>();
    private Timer timer;
    private int totalTime;
    private int minute;
    private int second;
    private Game game;
    private int animationTime;
    private Timer animationTimer;
    private String language;


    /**
     * Constructor for the GamePage class
     */
    public GamePage(ArrayList<Dice> diceList, String language){
        this.language = language;
        game =new Game(this.language);
        this.diceList = diceList;
        addJFrame();
        addComponent();
        createGameWindow();
        addAnimation();
        gameWindow.setVisible(true);


    }
    /**
     * add components to JFrame
     */
    private void addComponent(){
        addBoardPanel();
        addTextboxPanel();
        addWordlistpanel();
        addBottompanel();
    }

    /**
     * initialize the gameWindow
     */
    private void addJFrame(){
    	if (this.language.equalsIgnoreCase("english")) {
            gameWindow = new JFrame("Boggle Game");       }
        else if (this.language.equalsIgnoreCase("french")){
           	gameWindow = new JFrame("Jeu Boggle");        }
        gameWindow.setSize(500,400);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setLayout(new BorderLayout(5,10));
    }

    /**
     * add JPanels to the gameWindow
     */
    private void createGameWindow(){
        gameWindow.add(textboxPanel,BorderLayout.NORTH);
        gameWindow.add(boardPanel,BorderLayout.CENTER);
        gameWindow.add(wordlistPanel,BorderLayout.EAST);
        gameWindow.add(bottomPanel,BorderLayout.SOUTH);
    }

    /**
     * add simple animation before the board is shuffled
     */
    private void addAnimation(){
        animationTime = 4;
        animationTimer = new Timer(1000, new AnimationTimerListener());
        animationTimer.start();
    }


    /**
     * create all the diceButtons and add it to boardPanel
     */
    private void addBoardPanel(){
        boardPanel = new JPanel(new GridLayout(4,4));
        for(int i=1;i<=4;i++){
            for(int j=1;j<=4;j++){
                //JButton diceButton = new JButton(diceList.get(i-1).getValue());
                JButton diceButton = new JButton();
                diceButton.setPreferredSize(new Dimension(50,50));
                diceButton.setName(String.valueOf(i)+" "+String.valueOf(j));
                diceButton.addActionListener(new DiceButtonListener());
                diceButton.setEnabled(false);
                diceButtons.add(diceButton);
                boardPanel.add(diceButton);
            }

        }
    }

    /**
     * add textbox and clock to the textboxPanel
     */
    private void addTextboxPanel(){

        textboxPanel = new JPanel(new BorderLayout(100,2));
        textbox = new JLabel("");
        Border loweredBevelBorder = BorderFactory.createLoweredBevelBorder();
        textbox.setBorder(loweredBevelBorder);
        textbox.setPreferredSize(new Dimension(110,45));
        textbox.setFont(new Font("Monospaced",1,20));
        textbox.setForeground(Color.black);
        clock = new JLabel("  " +"3:00"+"  ");
        clock.setPreferredSize(new Dimension(100,45));
        clock.setBorder(loweredBevelBorder);
        clock.setForeground(Color.MAGENTA);
        clock.setFont(new Font("Monospaced",1,20));
        textboxPanel.add(textbox,BorderLayout.CENTER);
        textboxPanel.add(clock,BorderLayout.EAST);
    }

    /**
     * add wordlistArea to the wordlistPanel
     */
    private void addWordlistpanel(){
        wordlistPanel = new JPanel();
        wordlistArea = new JTextArea();
        wordlistArea.setEditable(false);
        Border loweredBevelBorder = BorderFactory.createLoweredBevelBorder();
        scrollWordlistArea = new JScrollPane(wordlistArea);
        scrollWordlistArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollWordlistArea.setPreferredSize(new Dimension(180,270));
        scrollWordlistArea.setBorder(loweredBevelBorder);
        wordlistPanel.add(scrollWordlistArea);
    }

    /**
     * add submitButton,undoButton, and endGameButton to the bottomPanel
     */
    private void addBottompanel(){
        bottomPanel = new JPanel(new FlowLayout(1,50,0));
        if (this.language.equalsIgnoreCase("english")) {
            submitButton = new JButton("Submit");       }
        else if (this.language.equalsIgnoreCase("french")){
            submitButton = new JButton("Soumettre");       }
        submitButton.addActionListener(new SubmitButtonListener());
        if (this.language.equalsIgnoreCase("english")) {
            undoButton = new JButton("Undo");       }
        else if (this.language.equalsIgnoreCase("french")){
          	undoButton = new JButton("Annuler");       }
        undoButton.addActionListener(new UndoButtonListener());
        if (this.language.equalsIgnoreCase("english")) {
           	endGameButton = new JButton("Finish");       }
        else if (this.language.equalsIgnoreCase("french")){
          	endGameButton = new JButton("Terminer");       }
        endGameButton.addActionListener(new EndGameButtonListener());

        bottomPanel.add(undoButton);
        bottomPanel.add(submitButton);
        bottomPanel.add(endGameButton);
    }

    /**
     * initialize the timer
     */
    private void countDown(){
        totalTime = 180;
        timer = new Timer(1000,new TimerListener());
        timer.start();
    }

    /**
     * TimerListener implements interface ActionListener
     * This inner class print the formatted time every 1000 millisecond(1 second)
     * The game ends when time runs out
     */
    private class TimerListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            minute = totalTime/60;
            second = totalTime%60;

            if(totalTime == 15){
                new WarningTonePlayer().play();
            }

            if(totalTime>0){
                totalTime=totalTime-1;
            }
            else{
                timer.stop();
                new WarningTonePlayer().play();
                gameWindow.dispose();
                game.end(wordlist);
            }


            if(second<10){
                clock.setText("  "+minute+":0"+second+"  ");
            }
            else{
                clock.setText("  "+minute+":"+second+"  ");
            }

        }
    }

    /**
     * DiceButtonListener implements interface ActionListener
     * This inner class makes the diceButton change its color after being clicked
     * This inner class makes the textbox display the word after the diceButton being clicked
     */
    private class DiceButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            for(int i=0;i<16;i++){
                if(!(tempDiceButtonNameList.contains(diceButtons.get(i).getName()))){
                    diceButtons.get(i).setEnabled(true);
                }
            }
            JButton button = (JButton)e.getSource();
            String name = button.getName();
            int x = Integer.parseInt(name.substring(0,1));
            int y = Integer.parseInt(name.substring(2,3));
            tempDiceButtonNameList.add(name);
            for(int i=1;i<=16;i++){
                JButton button1 =diceButtons.get(i-1);
                String name1 = button1.getName();
                int x1 = Integer.parseInt(name1.substring(0,1));
                int y1 = Integer.parseInt(name1.substring(2,3));
                if(!((x==x1&&(y1==y+1||y1==y-1))||(y1==y&&(x1==x+1||x1==x-1))||((x1==x+1||x1==x-1)&&(y1==y+1||y1==y-1)))){
                    button1.setEnabled(false);
                }
            }

            String value = button.getText();
            String word = textbox.getText();
            word = word + value;
            textbox.setText(word);
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.setBackground(Color.LIGHT_GRAY);


        }
    }

    /**
     * SubmitButtonListener implements interface ActionListener
     * This inner class realizes the "submit" requirement
     */
    private class SubmitButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String word = textbox.getText();
            for(int i=0;i<diceButtons.size();i++){
                diceButtons.get(i).setBackground(null);
                diceButtons.get(i).setBorderPainted(true);
            }
            textbox.setText("");
            if(!word.equals("")){
                wordlist.add(word);
                wordlistArea.append(word+'\n');
            }
            tempDiceButtonNameList.clear();

            if(!animationTimer.isRunning()){
                for(int i=0;i<16;i++){
                    diceButtons.get(i).setEnabled(true);
                }
            }

            /*for(String i : wordlist){
                System.out.print(i + ' ');
                }
            System.out.println('\n');*/

        }
    }

    /**
     * UndoButtonListener implements interface ActionListener
     * This inner class realizes the "undo" requirement
     */
    private class UndoButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(wordlistArea.getLineCount()<=2){
                wordlistArea.setText("");
                wordlist.clear();
            }
            else if (wordlistArea.getLineCount()>2){
                String temp =wordlistArea.getText().substring(0,wordlistArea.getText().lastIndexOf('\n'));
                temp = temp.substring(0,temp.lastIndexOf('\n'));
                wordlistArea.setText(temp+'\n');
                wordlist.remove(wordlist.size()-1);

            }
            /*for(String i : wordlist){
                System.out.print(i + ' ');
                }
            System.out.println('\n');*/

        }
    }

    /**
     * EndGameButtonListener implements interface ActionListener
     * This inner class realizes the "finish game" requirement
     */
    private class EndGameButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(timer!=null){
                timer.stop();
            }
            animationTimer.stop();
            gameWindow.dispose();
            game.end(wordlist);
        }
    }

    /**
     * AnimationTimerListener implements interface ActionListener
     * THis inner uses a Timer to realize simple animation
     */
    private class AnimationTimerListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int i;
            if(animationTime==4){
                diceButtons.get(5).setText("3");
                animationTime--;
            }
            else if(animationTime==3){
                diceButtons.get(5).setText("");
                diceButtons.get(6).setText("2");
                animationTime--;
            }
            else if(animationTime==2){
                diceButtons.get(6).setText("");
                diceButtons.get(9).setText("1");
                animationTime--;
            }
            else if(animationTime==1){
                diceButtons.get(9).setText("");
                if(language.equalsIgnoreCase("English")){
                    diceButtons.get(10).setText("go!");
                }
                else if(language.equalsIgnoreCase("French")){
                    diceButtons.get(10).setText("Aller!");
                }
                animationTime--;
            }
            else{
                animationTimer.stop();
                for(i=0;i<16;i++){
                    diceButtons.get(i).setText(diceList.get(i).getValue());
                    diceButtons.get(i).setEnabled(true);

                }

                countDown();
            }

        }
    }

}
