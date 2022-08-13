package BANG;

/**
 * This class is used for anyone rolling a Black Die
 * @author Zach Carrillo
 */
public class Black_Die extends Die{
    /**This String holds what each face is of the die */
    public String [] faces = {"Duel","Duel","Dynamite","Arrow","Gatling Gun","Whisky Jar"};
    /**
     * This method checks what face was rolled and does anny other corresponding actions
     */
    public void checkFace() {
        if(face == 0) {
            GameController.PlayerActions[2]++;
            System.out.println(faces[face]);
        }
        else if(face == 1) {
            GameController.PlayerActions[2]++;
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
            GameController.PlayerActions[5]++;
            if(GameController.players.get(0).getClass().equals(Greg_Digger.class)) {
                GameController.PlayerActions[5]++;
            }
        }

    }

    /**
     * This method checks the face of the die to help set the items of the combobox which shows what actions you can take as the player
     * @return a String representing the die face you rolled
     */
    @Override
    String getFaceString() {
        if(face == 0) {
            return "Duel";
        }
        else if(face == 1) {
            return "Duel";
        }
        else if(face == 5) {
            return "Use Whiskey";
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
            GameController.PlayerActions[2]--;
        }
        else if(face == 1) {
            GameController.PlayerActions[2]--;
        }
        else if(face == 5) {
            GameController.PlayerActions[5]--;
        }

    }

}
