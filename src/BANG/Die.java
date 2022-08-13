package BANG;

/**
 * This abstract class is used for every die in the game
 * @author Zach Carrillo
 */
abstract class Die {
    /** this int stores what face the die rolled on*/
    public int face;
    /** this Boolean says if the die can be rerolled*/
    public Boolean canReroll = true;
    /** this int stores how many times the die has been rolled*/
    public int timesRolled;

    /**
     * This method rolls the die and sets the canReroll Boolean to false if dynamite is rolled
     * or if the die has been rolled 3 times
     */
    public void roll() {
        if(canReroll) {
            int random = (int) (Math.random() * 6);
            face = random;
            timesRolled++;
            if(timesRolled >=3 || face == 2) {
                setCanReroll(false);
            }
        }
    }

    /**
     * This method sets the canReroll Boolean to b
     * @param b a Boolean which canReroll is set to
     */
    public void setCanReroll(Boolean b) {
        canReroll = b;
    }

    /**
     * an abstract method used to check what face the die rolled on
     */
    abstract void checkFace();

    /**
     * an abstract method used to return a String corresponding to the face rolled on
     * @return
     */
    abstract String getFaceString();

    /**
     * an abstract method used to decrement values in the PlayerActions array
     * if the die had been rerolled or used
     */
    abstract void removeAction();

    /**
     * This method resets the canReroll and timesRolled variables
     */
    public void resetDie() {
        canReroll = true;
        timesRolled = 0;
    }
}
