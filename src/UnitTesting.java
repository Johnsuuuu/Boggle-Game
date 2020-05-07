import junit.framework.TestCase;
import org.junit.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class UnitTesting extends TestCase {
    @Test
    public void testScore1(){
        ArrayList<String> wordlist = new ArrayList<>();
        wordlist.add("bow");
        Score score = new Score(wordlist,"English");
        int total = score.calculateTotal();
        //System.out.print(total);

        assertEquals(1,total);
    }

    public void testScore2(){
        ArrayList<String> wordlist = new ArrayList<>();
        wordlist.add("dice");
        Score score = new Score(wordlist,"English");
        int total = score.calculateTotal();
        assertEquals(1,total);
    }


    public void testScore3(){
        ArrayList<String> wordlist = new ArrayList<>();
        wordlist.add("score");
        Score score = new Score(wordlist,"English");
        int total = score.calculateTotal();
        assertEquals(2,total);
    }

    public void testScore4(){
        ArrayList<String> wordlist = new ArrayList<>();
        wordlist.add("player");
        Score score = new Score(wordlist,"English");
        int total = score.calculateTotal();
        assertEquals(3,total);
    }

    public void testScore5(){
        ArrayList<String> wordlist = new ArrayList<>();
        wordlist.add("animate");
        Score score = new Score(wordlist,"English");
        int total = score.calculateTotal();
        assertEquals(5,total);
    }

    public void testScore6(){
        ArrayList<String> wordlist = new ArrayList<>();
        wordlist.add("careless");
        Score score = new Score(wordlist,"English");
        int total = score.calculateTotal();
        assertEquals(11,total);
    }

    public void testScore7() {
        ArrayList<String> wordlist = new ArrayList<>();
        wordlist.add("geography");
        Score score = new Score(wordlist,"English");
        int total = score.calculateTotal();
        assertEquals(11, total);
    }

    public void testScore8(){
        ArrayList<String> wordlist = new ArrayList<>();
        wordlist.add("software");
        wordlist.add("software");
        wordlist.add("software");
        Score score = new Score(wordlist,"English");
        int total = score.calculateTotal();
        assertEquals(11,total);
    }

    public void testScore9(){
        ArrayList<String> wordlist = new ArrayList<>();
        wordlist.add("is");
        wordlist.add("hi");
        wordlist.add("am");
        Score score = new Score(wordlist,"English");
        int total = score.calculateTotal();
        assertEquals(0,total);
    }

    public void testScore10(){
        ArrayList<String> wordlist = new ArrayList<>();
        wordlist.add("dsghfd");
        wordlist.add("hjgkjg");
        wordlist.add("oghfkjpklk");
        Score score = new Score(wordlist,"English");
        int total = score.calculateTotal();
        assertEquals(0,total);
    }

    public void testScore11(){
        ArrayList<String> wordlist = new ArrayList<>();
        wordlist.add("LYRE");
        wordlist.add("LYRES");
        wordlist.add("LUIRE");
        wordlist.add("LUIE");
        wordlist.add("LAST");
        wordlist.add("TRLLYF");
        wordlist.add("SREN");
        Score score = new Score(wordlist,"french");
        int total = score.calculateTotal();
        assertEquals(6,total);
    }

    public void testScore12(){
        ArrayList<String> wordlist = new ArrayList<>();
        wordlist.add("sick");
        wordlist.add("sick");
        wordlist.add("is");
        wordlist.add("happy");
        wordlist.add("software");
        Score score = new Score(wordlist,"English");
        int total = score.calculateTotal();
        assertEquals(14,total);
    }

    public void testScore13(){
        ArrayList<String> wordlist = new ArrayList<>();
        wordlist.add("ghjfdfg");
        wordlist.add("computer");
        wordlist.add("fly");
        wordlist.add("llllllll");
        wordlist.add("game");
        Score score = new Score(wordlist,"English");
        int total = score.calculateTotal();
        assertEquals(13,total);
    }

    public void testScore14(){
        ArrayList<String> wordlist = new ArrayList<>();
        wordlist.add("ghjfdhfj");
        wordlist.add("computer");
        wordlist.add("computer");
        wordlist.add("fly");
        wordlist.add("fly");
        wordlist.add("llldfgdl");
        wordlist.add("final");
        Score score = new Score(wordlist,"English");
        int total = score.calculateTotal();
        assertEquals(14,total);
    }

    public void testScore15(){
        ArrayList<String> wordlist = new ArrayList<>();
        wordlist.add("ghjfhfj");
        wordlist.add("he");
        wordlist.add("basketball");
        wordlist.add("careful");
        wordlist.add("last");
        wordlist.add("llldfdfghfhg");
        wordlist.add("write");
        Score score = new Score(wordlist,"English");
        int total = score.calculateTotal();
        assertEquals(19,total);
    }


}
