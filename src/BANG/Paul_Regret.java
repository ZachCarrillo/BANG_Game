package BANG;

/**
 * This is the class of the Paul Regret character
 * @author Zach Carrillo
 */
public class Paul_Regret extends Actor {
    /**
     * This method sets the health of Paul Regret (9 base, 11 if sheriff)
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



    void usePassive() {

    }

}
