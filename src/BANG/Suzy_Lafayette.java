package BANG;

/**
 * This is the class of the Suzy LafayetteCharacter
 * @author Zach Carrillo
 */
public class Suzy_Lafayette extends Actor {
    /**
     * This method sets the health of Susy Lafayette (8 base, 10 if sheriff)
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



    void usePassive() {

    }

}
