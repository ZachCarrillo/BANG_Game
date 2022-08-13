package BANG;

/**
 * This is the class of the Belle Star Character
 * @author Zach Carrillo
 */
public class Belle_Star extends Actor {
    /**
     * This method sets the health of Belle Star (8 base, 10 if sheriff)
     */
    @Override
    void setHealth() {
        health = 8;
        maxHealth=8;
        if(role.equals("Sheriff")) {
            health+=2;
            maxHealth+=2;
        }
    }



}
