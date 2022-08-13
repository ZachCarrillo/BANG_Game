package BANG;

/**
 * This is the class of the Vulture Sam Character
 * @author Zach Carrillo
 */
public class Vulture_Sam extends Actor {
    /**
     * This method sets the health of vulture sam (9 base, 11 if sheriff)
     */
    @Override
    void setHealth() {
        health = 9;
        maxHealth = 9;
        if(role.equals("Sheriff")) {
            health+=2;
            maxHealth+=2;
        }
    }




}
