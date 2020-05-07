import java.util.ArrayList;
import java.util.Collections;

public class Board {
	private String[][] englishDiceData = {
			{"R", "I", "F", "O", "B", "X"},
			{"I", "F", "E", "H", "E", "Y"},
			{"D", "E", "N", "O", "W", "S"},
			{"U", "T", "O", "K", "N", "D"},
			{"H", "M", "S", "R", "A", "O"},
			{"L", "U", "P", "E", "T", "S"},
			{"A", "C", "I", "T", "O", "A"},
			{"Y", "L", "G", "K", "U", "E"},
			{"QU", "B", "M", "J", "O", "A"},
			{"E", "H", "I", "S", "P", "N"},
			{"V", "E", "T", "I", "G", "N"},
			{"B", "A", "L", "I", "Y", "T"},
			{"E", "Z", "A", "V", "N", "D"},
			{"R", "A", "L", "E", "S", "C"},
			{"U", "W", "I", "L", "R", "G"},
			{"P", "A", "C", "E", "M", "D"}
	};private String[][] frenchDiceData = {
			{"E", "T", "U", "K", "N", "O"},
			{"E", "V", "G", "T", "I", "N"},
			{"D", "E", "C", "A", "M", "P"},
			{"I", "E", "L", "R", "U", "W"},
			{"E", "H", "I", "F", "S", "E"},
			{"R", "E", "C", "A", "L", "S"},
			{"E", "N", "T", "D", "O", "S"},
			{"O", "F", "X", "R", "I", "A"},
			{"N", "A", "V", "E", "D", "Z"},
			{"E", "I", "O", "A", "T", "A"},
			{"G", "L", "E", "N", "Y", "U"},
			{"B", "M", "A", "Q", "J", "O"},
			{"T", "L", "I", "B", "R", "A"},
			{"S", "P", "U", "L", "T", "E"},
			{"A", "I", "M", "S", "O", "R"},
			{"E", "N", "H", "R", "I", "S"}
	};
	private String language;
	private ArrayList<Dice> diceList = new ArrayList<Dice>();
	
	//Constructor
	public Board(String language) {
		this.language = language;
	}

	//shuffle() creates 16 Dice objects and stores them in diceList, then "shuffles" them in order to randomize the diceList
	public ArrayList<Dice> shuffle() {
		ArrayList<String> oneDiePossibilities = new ArrayList<String>();
		for (int i = 0; i < 16; i++) {
			//Store the information from one of the rows (constitutes one die) in diceData into an ArrayList<String> to pass to the constructor of Dice class
			for (int j =0; j < 6; j++) {
				if (this.language.equalsIgnoreCase("english")) {
					oneDiePossibilities.add(englishDiceData[i][j]);
				}
				else if (this.language.equalsIgnoreCase("french")){
					oneDiePossibilities.add(frenchDiceData[i][j]);
				}
			}
			//Creates a total of 16 "die" to all be added to diceList by the end of the first for loop
			Dice die = new Dice(oneDiePossibilities);
			die.roll();
			diceList.add(die);
			oneDiePossibilities.clear();
		}

		Collections.shuffle(diceList);
		return diceList;
	}

}
