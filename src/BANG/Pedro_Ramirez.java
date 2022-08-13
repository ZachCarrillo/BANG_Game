package BANG;

/**
 * This is the class of the Pedro Ramirez Character
 * @author Zach Carrillo
 */
public class Pedro_Ramirez extends Actor {
    /**
     * This method sets the health of Pedro Ramirez (8 base, 10 if sheriff)
     */
    @Override
    void setHealth() {
        health = 8;
        maxHealth = 8;
        if(role.equals("Sheriff")) {
            health+=2;
            maxHealth+=2;
        }
    }



    /**
     * This method is used for Pedro Ramirez's passive, when he loses health he loses an arrow
     */
    @Override
    public void subtractHealth() {
        super.subtractHealth();
        if(getArrows() > 0) {
            subtractArrows();
        }
    }

    void usePassive() {

    }

}
