package BANG;

/**
 * This is the class of the Black Jack Character
 * @author Zach Carrillo
 */
public class Black_Jack extends Actor {
    /**
     * This method sets the health of Black Jack (8 base, 10 if sheriff)
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
