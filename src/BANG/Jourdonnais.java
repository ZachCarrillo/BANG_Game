package BANG;

/**
 * This is the class of the Jourdonnais Character
 * @author Zach Carrillo
 */
public class Jourdonnais extends Actor {
    /**
     * This method sets the health of Jourdonnias (7 base, 9 if sheriff)
     */
    @Override
    void setHealth() {
        health = 7;
        maxHealth=7;
        if(role.equals("Sheriff")) {
            health+=2;
            maxHealth+=2;
        }
    }

}
