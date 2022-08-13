package BANG;

import java.util.ArrayList;

/**
 * A class used to test player creation and AI turn taking
 * @author Zach Carrillo
 */
public class tester {
    public static ArrayList <Actor> players = new ArrayList<>();

    /**
     * THis method tests the creation of multiple characters and runs one of their turns
     * @param args
     */
    public static void main(String[]args) {

        players.add(new Black_Jack());
        players.add(new El_Gringo());
        players.add(new Bill_Noface());
        players.add(new Suzy_Lafayette());
        players.add(new Apache_Kid());
        players.get(2).setRole("Sheriff");
        players.get(2).setPosition(2);
        players.get(2).play(2);
    }
}
