package BANG;

/**
 * This is the class of the Bill Noface Character
 * @author Zach Carrillo
 */
public class Bill_Noface extends Actor {
    /**
     * This method sets the health of Bill Noface (9 base, 11 if sheriff)
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


    void usePassive() {

    }
    public void testerTest() {
        System.out.println(position);
    }

}
