import java.util.ArrayList;
import java.util.Random;

public class Dice {
	private ArrayList<String> possibility = new ArrayList<String>();
	private String value;
	//Constructor
	public Dice(ArrayList<String> possiblity) {
		this.possibility = possiblity;
	}
	
	public void roll() {
		Random r = new Random();
		//Following gives a randomized value between numbers 0 and 5 (inclusive on bounds) and gives the corresponding face value of the die to the parameter "value"
		this.value = possibility.get(r.nextInt(6));
	}
	//Returns the value of the face after the die has been "rolled"
	public String getValue() {
		return this.value;
	}
	//Sets the ArrayList possibility with the six possible faces this particular die has
	public void setPossibility(ArrayList<String> possibility) {
		this.possibility = possibility;
	}

}
