package BANG;

/**
 * This is the class of the El gringo Character
 * @author Zach Carrillo
 */
public class El_Gringo extends Actor {
    /**
     * This method sets the health of El Gringo (7 base, 9 if sheriff)
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



    void usePassive() {

    }

}
