package BANG;

/**
 * This class is used for anyone rolling a loudmouth die
 * @author Zach Carrillo
 */
public class Loudmouth_Die extends Die {
    /**This String holds what each face is of the die */
    public String [] faces = {"Double One","Double Two","Dynamite","Arrow","Gatling Gun","Silver Bullet"};

    /**
     * This method checks what face was rolled and does anny other corresponding actions
     */
    public void checkFace() {
        if(face == 0) {
            GameController.PlayerActions[0]++;
            GameController.PlayerActions[0]++;
            System.out.println(faces[face]);
        }
        else if(face == 1) {
            GameController.PlayerActions[1]++;
            GameController.PlayerActions[1]++;
            System.out.println(faces[face]);
        }
        else if(face == 2) {
            System.out.println(faces[face]);
            canReroll = false;
        }
        else if(face == 3) {
            System.out.println(faces[face]);
        }
        else if(face == 4) {
            System.out.println(faces[face]);
        }
        else if(face == 5) {
            System.out.println(faces[face]);
        }
    }

    /**
     * This method checks the face of the die to help set the items of the combobox which shows what actions you can take as the player
     * @return a String representing the die face you rolled
     */
    @Override
    String getFaceString() {
        if(face == 0) {
            return "Shoot One Away";
        }
        else if(face == 1) {
            return "Shoot Two Away";
        }
        else {
            return "no";
        }
    }

    /**
     * This method decrements values in the PlayerActions array if the die had been rerolled or used
     */
    @Override
    void removeAction() {
        if(face == 0) {
            GameController.PlayerActions[0]++;
            GameController.PlayerActions[0]++;
        }
        else if(face == 1) {
            GameController.PlayerActions[1]++;
            GameController.PlayerActions[1]++;
        }
    }
}
