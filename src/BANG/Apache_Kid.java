package BANG;

/**
 * This is the class of the Apache Kid Character
 * @author Zach Carrillo
 */
public class Apache_Kid extends Actor {

    /**
     * This method sets the health of apache kid (9 base, 11 if sheriff)
     */
    @Override
    void setHealth() {
        health = 9;
        maxHealth=9;
        if(role.equals("Sheriff")) {
            health+=2;
            maxHealth+=2;
        }
    }

}
