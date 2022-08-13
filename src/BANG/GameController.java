package BANG;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox;

/**
 * This class is the controller class for the Game.fxml file
 * @author Zach Carrillo
 */
public class GameController implements Initializable {
    @FXML
    private ImageView pic1;

    @FXML
    private ImageView pic2;

    @FXML
    private ImageView pic3;

    @FXML
    private ImageView pic4;

    @FXML
    private ImageView pic5;

    @FXML
    private ImageView pic6;

    @FXML
    private ImageView pic7;

    @FXML
    private ImageView pic8;

    @FXML
    private ImageView dice1;

    @FXML
    private ImageView dice2;

    @FXML
    private ImageView dice3;

    @FXML
    private ImageView dice4;

    @FXML
    private ImageView dice5;

    @FXML
    private Button rollButton;

    @FXML
    private Button endButton;


    @FXML
    private ComboBox<String> diceChooser;

    @FXML
    private Button ai1;

    @FXML
    private Button ai2;
    @FXML
    private Button ai3;
    @FXML
    private Button ai4;
    @FXML
    private Button ai5;
    @FXML
    private Button ai6;
    @FXML
    private Button ai7;

    @FXML
    private Button playerButton;

    @FXML
    private ComboBox<String> playerChoice;

    @FXML
    private Label p1Stats;

    @FXML
    private Label p2Stats;

    @FXML
    private Label p3Stats;

    @FXML
    private Label p4Stats;

    @FXML
    private Label p5Stats;

    @FXML
    private Label p6Stats;

    @FXML
    private Label p7Stats;

    @FXML
    private Label p8Stats;

    @FXML
    private Label end;

    /**this arraylist holds all of the characters for the game */
    public static ArrayList<Actor> players = new ArrayList<>();
    /**this actor variable is the players actor */
    public static Actor playerChar;
    /**this is the gamemode being played 0 = base 1 = old saloon 2 = undead 3 = all */
    public static int mode;
    /**this int is the playerVal of the players character (used for setting up picture) */
    public static int playerVal;
    /**this int is how many players are in the game */
    public static int numOfPlayers;
    /**this array of die store the 5 die used for the game */
    public Die[] handOfDie = new Die[5];
    /**this int stores how many arrows are left */
    public static int arrows;
    /**this int stores how many indian chief arrows are left */
    public static int chiefArrow;
    // Ones, Twos, Duels, Broken Arrows, Beers, whisky
    /**this int stores how many actions the player can do */
    public static int [] PlayerActions = {0,0,0,0,0,0};
    /** This int stores how many dynamite have been rolled*/
    public static int dynamite = 0;
    /** This int stores how many gatling guns have been rolled */
    public static int gat = 0;
    /**This Boolean array stores who is already dead */
    private static Boolean[] alreadyDead = {false, false, false, false, false, false, false, false};
    /**This Boolean keeps track of if susy lafayette's passive can be used */
    private static Boolean suzyPassive = false;
    /**This int stores the number of zombie hands pulled (undead or alive only)*/
    private static int zombieHands = 0;
    /**This Boolean stores if there was a zombie outbreak or not (undead or alive only)*/
    private static Boolean zombieOutbreak = false;
    /**This arraylist stores the unused boneyard cards (undead or alive only) */
    private static ArrayList<Integer> boneCards = new ArrayList<>();

    /**
     * this method sets up the game
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boneCards.add(0);
        boneCards.add(0);
        boneCards.add(0);
        boneCards.add(1);
        boneCards.add(1);
        boneCards.add(1);
        boneCards.add(1);
        boneCards.add(2);
        boneCards.add(2);
        setPlayers();
        setRoles();
        if(mode == 0) {
            diceChooser.setDisable(true);
            diceChooser.setVisible(false);
            handOfDie[0] = new Base_Die();
            handOfDie[1] = new Base_Die();
            handOfDie[2] = new Base_Die();
            handOfDie[3] = new Base_Die();
            handOfDie[4] = new Base_Die();
        }
        else if(mode == 1) {
            ObservableList<String> dieChoice1 = FXCollections.observableArrayList("All White Die", "White Die and The Loudmouth", "White Die and The Coward");
            diceChooser.setItems(dieChoice1);
        }
        else if(mode == 2) {
            diceChooser.setDisable(true);
            diceChooser.setVisible(false);
            handOfDie[0] = new Base_Die();
            handOfDie[1] = new Base_Die();
            handOfDie[2] = new Base_Die();
            handOfDie[3] = new Black_Die();
            handOfDie[4] = new Black_Die();
        }
        else{
            ObservableList<String> dieChoice1 = FXCollections.observableArrayList("White with 2 Duel Dice","White with 2 Duel Dice and The Loudmouth", "White with 2 Duel Dice and The Coward");
            diceChooser.setItems(dieChoice1);
        }
        setArrows();
        updateStats();
    }

    /**
     * this method sets up how many arrows will be in the game
     */
    public void setArrows() {
        if(mode == 1 || mode == 3) {
            chiefArrow = 1;
        }
        arrows = 9;
    }

    /**
     * this method sets the dice to what the player chooses
     */
    public void setDice() {
        if(diceChooser.getValue().equals("All White Die")) {
            handOfDie[0] = new Base_Die();
            handOfDie[1] = new Base_Die();
            handOfDie[2] = new Base_Die();
            handOfDie[3] = new Base_Die();
            handOfDie[4] = new Base_Die();
        }
        else if(diceChooser.getValue().equals("White Die and The Loudmouth")) {
            handOfDie[0] = new Base_Die();
            handOfDie[1] = new Base_Die();
            handOfDie[2] = new Base_Die();
            handOfDie[3] = new Base_Die();
            handOfDie[4] = new Loudmouth_Die();
        }
        else if(diceChooser.getValue().equals("White Die and The Coward")) {
            handOfDie[0] = new Base_Die();
            handOfDie[1] = new Base_Die();
            handOfDie[2] = new Base_Die();
            handOfDie[3] = new Base_Die();
            handOfDie[4] = new Coward_Die();
        }
        else if(diceChooser.getValue().equals("White with 2 Duel Dice")) {
            handOfDie[0] = new Base_Die();
            handOfDie[1] = new Base_Die();
            handOfDie[2] = new Base_Die();
            handOfDie[3] = new Black_Die();
            handOfDie[4] = new Black_Die();
        }
        else if(diceChooser.getValue().equals("White with 2 Duel Dice and The Loudmouth")) {
            handOfDie[0] = new Base_Die();
            handOfDie[1] = new Base_Die();
            handOfDie[2] = new Black_Die();
            handOfDie[3] = new Black_Die();
            handOfDie[4] = new Loudmouth_Die();
        }
        else if(diceChooser.getValue().equals("White with 2 Duel Dice and The Coward")) {
            handOfDie[0] = new Base_Die();
            handOfDie[1] = new Base_Die();
            handOfDie[2] = new Black_Die();
            handOfDie[3] = new Black_Die();
            handOfDie[4] = new Coward_Die();
        }
    }

    /**
     * this method gives out roles to every character & sets their health
     */
    public void setRoles() {
        int sheriff=0;
        int deputies=0;
        int outlaws=0;
        int renegades=0;
        if(numOfPlayers == 4) {
            sheriff = 1;
            deputies = 0;
            outlaws = 2;
            renegades = 1;
        }
        else if(numOfPlayers == 5) {
            sheriff = 1;
            deputies = 1;
            outlaws = 2;
            renegades = 1;

        }
        else if(numOfPlayers == 6) {
            sheriff = 1;
            deputies = 1;
            outlaws = 3;
            renegades = 1;

        }
        else if(numOfPlayers == 7) {
            sheriff = 1;
            deputies = 2;
            outlaws = 3;
            renegades = 1;

        }
        else if(numOfPlayers == 8) {
            sheriff = 1;
            deputies = 2;
            outlaws = 3;
            renegades = 2;
        }

        for(int i = 0; i < numOfPlayers; i++) {
            int rand = (int) (Math.random()*4);
            if(rand == 0 && sheriff > 0) {
                players.get(i).setRole("Sheriff");
                players.get(i).setHealth();
                sheriff--;
            }
            else if(rand == 1 && deputies > 0) {
                players.get(i).setRole("Deputy");
                players.get(i).setHealth();
                deputies--;
            }
            else if(rand == 2 && outlaws > 0) {
                players.get(i).setRole("Outlaw");
                players.get(i).setHealth();
                outlaws--;
            }
            else if(rand == 3 && renegades > 0) {
                players.get(i).setRole("Renegade");
                players.get(i).setHealth();
                renegades--;
            }
            else {
                i--;
            }
        }
    }

    /**
     * This methods puts the player and AI into the players array list
     */
    public void setPlayers() {
        players.add(playerChar);
        Boolean isUsed[] = new Boolean[11];
        Arrays.fill(isUsed, false);
        isUsed[playerVal] = true;
        setPic(-1, playerVal);
        int multiplier;
        if(mode == 0) {
            multiplier = 7;
        }
        else if(mode == 1) {
            multiplier = 9;
        }
        else if(mode == 2) {
            multiplier = 11;
        }
        else if(mode == 3) {
            multiplier = 11;
        }
        else {
            multiplier = 7;
        }
        System.out.println("Multi is "+ multiplier);
        System.out.println("numOfPlayers is "+ numOfPlayers);
        for(int i = 0; i < numOfPlayers-1; i++) {
            int picked = (int) (Math.random()*multiplier);
            System.out.println("picked is "+picked);

            if(picked == 0 && !isUsed[picked]) {
                isUsed[picked] = true;
                players.add(new Black_Jack());
                setPic(i, picked);

            }
            else if(picked == 1 && !isUsed[picked]) {
                isUsed[picked] = true;
                players.add(new El_Gringo());
                setPic(i, picked);

            }
            else if(picked == 2 && !isUsed[picked]) {
                isUsed[picked] = true;
                players.add(new Jourdonnais());
                setPic(i, picked);
            }
            else if(picked == 3 && !isUsed[picked]) {
                isUsed[picked] = true;
                players.add(new Paul_Regret());
                setPic(i, picked);

            }
            else if(picked == 4 && !isUsed[picked]) {
                isUsed[picked] = true;
                players.add(new Pedro_Ramirez());
                setPic(i, picked);

            }
            else if(picked == 5 && !isUsed[picked]) {
                isUsed[picked] = true;
                players.add(new Suzy_Lafayette());
                setPic(i, picked);

            }
            else if(picked == 6 && !isUsed[picked]) {
                isUsed[picked] = true;
                players.add(new Vulture_Sam());
                setPic(i, picked);

            }
            else if(picked == 7 && !isUsed[picked] && mode!=2) {
                isUsed[picked] = true;
                players.add(new Apache_Kid());
                setPic(i, picked);
            }
            else if(picked == 8 && !isUsed[picked] && mode!=2) {
                isUsed[picked] = true;
                players.add(new Bill_Noface());
                setPic(i, picked);

            }
            else if(picked == 9 && !isUsed[picked]) {
                isUsed[picked] = true;
                players.add(new Belle_Star());
                setPic(i, picked);
            }
            else if(picked == 10 && !isUsed[picked]) {
                isUsed[picked] = true;
                players.add(new Greg_Digger());
                setPic(i, picked);
            }
            else {
                --i;
            }
        }
        System.out.println("size is "+ players.size());


    }

    /**
     * This method sets the pictures of each character
     * @param i an int used to tell what picture to be changed
     * @param picked an int used to tell what picture to use
     */
    private void setPic(int i, int picked) {
        String paths[] = {"/assets/BlackJack.png","/assets/ElGringo.png","/assets/Jourdonnais.png","/assets/PaulRegret.png","/assets/PedroRamirez.png","/assets/SuzyLafayette.png","/assets/VultureSam.png", "/assets/ApacheKid.png","/assets/BillNoface.png","/assets/BelleStar.png","/assets/GregDigger.png",};
        if(i == 0) {
            pic2.setImage(new Image(getClass().getResource(paths[picked]).toExternalForm()));
        }
        else if(i == 1) {
            pic3.setImage(new Image(getClass().getResource(paths[picked]).toExternalForm()));
        }
        else if(i == 2) {
            pic4.setImage(new Image(getClass().getResource(paths[picked]).toExternalForm()));
        }
        else if(i == 3) {
            pic5.setImage(new Image(getClass().getResource(paths[picked]).toExternalForm()));
        }
        else if(i == 4) {
            pic6.setImage(new Image(getClass().getResource(paths[picked]).toExternalForm()));
        }
        else if(i == 5) {
            pic7.setImage(new Image(getClass().getResource(paths[picked]).toExternalForm()));
        }
        else if(i == 6) {
            pic8.setImage(new Image(getClass().getResource(paths[picked]).toExternalForm()));
        }
        if(i == -1) {
            pic1.setImage(new Image(getClass().getResource(paths[picked]).toExternalForm()));
        }
    }

    /**
     * this method rolls all the die that can be rolled
     */
    public void rollDice() {
        playerChoice.setDisable(true);
        if(players.get(0).role.equals("Zombie")) {
            disableDice4();
            disableDice5();
        }
        for(int i = 0; i< PlayerActions.length; i++) {
            PlayerActions[i] = 0;
        }
        String baseDieImages[] = {"/assets/One.png","/assets/Two.png","/assets/Dynamite.png","/assets/Arrow.png","/assets/Gat.png","/assets/Beer.png"};
        String blackDieImages[] = {"/assets/Duel.png","/assets/Duel.png","/assets/Dynamite.png","/assets/Arrow.png","/assets/Gat.png","/assets/Whiskey.png"};
        String cowardDieImages[] = {"/assets/One.png","/assets/DoubleBeer.png","/assets/Dynamite.png","/assets/Arrow.png","/assets/Broken.png","/assets/Beer.png"};
        String loudmouthDieImages[] = {"/assets/DoubleOne.png","/assets/DoubleTwo.png","/assets/Dynamite.png","/assets/Arrow.png","/assets/Gat.png","/assets/Bullet.png"};
        for(int i = 0; i < 5; i++) {
            if (handOfDie[i].canReroll) {
                handOfDie[i].roll();
                if(handOfDie[i].face == 2) {
                    dynamite++;
                    if(dynamite >= 3) {
                        dynamite = 0;
                        players.get(0).subtractHealth();
                    }
                }
                else if(handOfDie[i].face == 3) {
                    if(arrows > 0) {
                        players.get(0).addArrow();
                    }
                    else if(chiefArrow > 0) {
                        players.get(0).addChiefArrow();
                    }
                    else {
                        for(int j = 0; j < players.size(); j++) {
                            while(players.get(j).getArrows() > 0) {
                                players.get(j).subtractHealth();
                                players.get(j).subtractArrows();
                                if(players.get(j).getClass().equals(Jourdonnais.class)) {
                                    while(players.get(j).getArrows() > 0) {
                                        players.get(j).subtractArrows();
                                    }
                                }
                            }
                        }
                    }
                }
                else if(handOfDie[i].face == 4 && !handOfDie[i].getClass().equals(Coward_Die.class)) {
                    gat++;
                    if(gat>=3) {
                        while(players.get(0).getArrows() > 0) {
                            players.get(0).subtractArrows();
                        }
                        for(int j = 1; j < GameController.players.size(); j++) {
                            if(!players.get(j).getClass().equals(Paul_Regret.class)) {
                                GameController.players.get(j).subtractHealth();
                            }

                        }
                    }
                }
                updateStats();
                if (handOfDie[i].getClass().equals(Base_Die.class)) {
                    if (i == 0) {
                        dice1.setImage(new Image(getClass().getResource(baseDieImages[handOfDie[i].face]).toExternalForm()));
                    } else if (i == 1) {
                        dice2.setImage(new Image(getClass().getResource(baseDieImages[handOfDie[i].face]).toExternalForm()));
                    } else if (i == 2) {
                        dice3.setImage(new Image(getClass().getResource(baseDieImages[handOfDie[i].face]).toExternalForm()));
                    } else if (i == 3) {
                        dice4.setImage(new Image(getClass().getResource(baseDieImages[handOfDie[i].face]).toExternalForm()));
                    } else if (i == 4) {
                        dice5.setImage(new Image(getClass().getResource(baseDieImages[handOfDie[i].face]).toExternalForm()));
                    }
                } else if (handOfDie[i].getClass().equals(Black_Die.class)) {
                    if (i == 0) {
                        dice1.setImage(new Image(getClass().getResource(blackDieImages[handOfDie[i].face]).toExternalForm()));
                    } else if (i == 1) {
                        dice2.setImage(new Image(getClass().getResource(blackDieImages[handOfDie[i].face]).toExternalForm()));
                    } else if (i == 2) {
                        dice3.setImage(new Image(getClass().getResource(blackDieImages[handOfDie[i].face]).toExternalForm()));
                    } else if (i == 3) {
                        dice4.setImage(new Image(getClass().getResource(blackDieImages[handOfDie[i].face]).toExternalForm()));
                    } else if (i == 4) {
                        dice5.setImage(new Image(getClass().getResource(blackDieImages[handOfDie[i].face]).toExternalForm()));
                    }
                } else if (handOfDie[i].getClass().equals(Loudmouth_Die.class)) {
                    if (i == 0) {
                        dice1.setImage(new Image(getClass().getResource(loudmouthDieImages[handOfDie[i].face]).toExternalForm()));
                    } else if (i == 1) {
                        dice2.setImage(new Image(getClass().getResource(loudmouthDieImages[handOfDie[i].face]).toExternalForm()));
                    } else if (i == 2) {
                        dice3.setImage(new Image(getClass().getResource(loudmouthDieImages[handOfDie[i].face]).toExternalForm()));
                    } else if (i == 3) {
                        dice4.setImage(new Image(getClass().getResource(loudmouthDieImages[handOfDie[i].face]).toExternalForm()));
                    } else if (i == 4) {
                        dice5.setImage(new Image(getClass().getResource(loudmouthDieImages[handOfDie[i].face]).toExternalForm()));
                    }
                } else if (handOfDie[i].getClass().equals(Coward_Die.class)) {
                    if (i == 0) {
                        dice1.setImage(new Image(getClass().getResource(cowardDieImages[handOfDie[i].face]).toExternalForm()));
                    } else if (i == 1) {
                        dice2.setImage(new Image(getClass().getResource(cowardDieImages[handOfDie[i].face]).toExternalForm()));
                    } else if (i == 2) {
                        dice3.setImage(new Image(getClass().getResource(cowardDieImages[handOfDie[i].face]).toExternalForm()));
                    } else if (i == 3) {
                        dice4.setImage(new Image(getClass().getResource(cowardDieImages[handOfDie[i].face]).toExternalForm()));
                    } else if (i == 4) {
                        dice5.setImage(new Image(getClass().getResource(cowardDieImages[handOfDie[i].face]).toExternalForm()));
                    }
                }
            }
            handOfDie[i].checkFace();

        }
        if(endButton.isDisabled()) {
            endButton.setDisable(false);
        }
        if(dynamite >= 3) {
            players.get(0).subtractHealth();
            dynamite = 0;
        }
        else if(players.get(0).getClass().equals(Black_Jack.class)) {
            for(int i = 0; i < handOfDie.length; i++) {
                if(handOfDie[i].face == 2) {
                    handOfDie[i].canReroll = true;
                }
            }
        }
        playerChoice.setDisable(false);
        setPlayerChoices();
    }

    /**
     * this method makes it so you cant roll die1, makes it so you can roll it
     * if it was previously disabled
     */
    public void disableDice1() {
        if(handOfDie[0].canReroll) {
            handOfDie[0].setCanReroll(false);
            dice1.setEffect(new BoxBlur());
        }
        else if(handOfDie[0].timesRolled < 3 && handOfDie[0].face != 2) {
            handOfDie[0].setCanReroll(true);
            dice1.setEffect(null);
        }
    }
    /**
     * this method makes it so you cant roll die2, makes it so you can roll it
     * if it was previously disabled
     */
    public void disableDice2() {
        if(handOfDie[1].canReroll) {
            handOfDie[1].setCanReroll(false);
            dice2.setEffect(new BoxBlur());
        }
        else if(handOfDie[1].timesRolled < 3 && handOfDie[1].face != 2) {
            handOfDie[1].setCanReroll(true);
            dice2.setEffect(null);
        }
    }
    /**
     * this method makes it so you cant roll die3, makes it so you can roll it
     * if it was previously disabled
     */
    public void disableDice3() {
        if(handOfDie[2].canReroll) {
            handOfDie[2].setCanReroll(false);
            dice3.setEffect(new BoxBlur());
        }
        else if(handOfDie[2].timesRolled < 3 && handOfDie[2].face != 2) {
            handOfDie[2].setCanReroll(true);
            dice3.setEffect(null);
        }
    }
    /**
     * this method makes it so you cant roll die4, makes it so you can roll it
     * if it was previously disabled
     */
    public void disableDice4() {
        if(handOfDie[3].canReroll) {
            handOfDie[3].setCanReroll(false);
            dice4.setEffect(new BoxBlur());
        }
        else if(handOfDie[3].timesRolled < 3 && handOfDie[3].face != 2) {
            handOfDie[3].setCanReroll(true);
            dice4.setEffect(null);
        }
    }
    /**
     * this method makes it so you cant roll die5, makes it so you can roll it
     * if it was previously disabled
     */
    public void disableDice5() {
        if(handOfDie[4].canReroll) {
            handOfDie[4].setCanReroll(false);
            dice5.setEffect(new BoxBlur());
        }
        else if(handOfDie[4].timesRolled < 3 && handOfDie[4].face != 2) {
            handOfDie[4].setCanReroll(true);
            dice5.setEffect(null);
        }
    }

    /**
     * This method plays the AI's turn and checks if the game should end
     */
    public void playAI() {
        if(players.get(0).getClass().equals(Suzy_Lafayette.class)) {
            if(!suzyPassive) {
                players.get(0).addHealth();
                players.get(0).addHealth();
            }
        }
        Boolean SheriffAlive=false;
        Boolean GameOver = false;
        int numOfOutlawsAlive=0;
        int numOfRenegadesAlive=0;
        int numOfDeputiesAlive=0;
        int numOfSurvivors = 0;
        int numOfZombies = 0;
        for(int i = 1; i < players.size(); i++) {
            if(!zombieOutbreak) {
                for(int j = 0; j < players.size(); j++) {
                    if(players.get(j).getHealth()>0) {
                        if(players.get(j).role.equals("Sheriff")) {
                            SheriffAlive = true;
                        }
                        else if(players.get(j).role.equals("Outlaw")) {
                            numOfOutlawsAlive++;
                        }
                        else if(players.get(j).role.equals("Renegade")) {
                            numOfRenegadesAlive++;
                        }
                        else if(players.get(j).role.equals("Deputy")) {
                            numOfDeputiesAlive++;
                        }
                    }
                }
                updateStats();
                if(SheriffAlive && numOfOutlawsAlive==0 && numOfRenegadesAlive==0) {
                    System.out.println("Sheriff & Deputies Win");
                    end.setText("Sheriff & Deputies Win");
                    end.setVisible(true);
                    i = players.size();
                    GameOver = true;
                    updateStats();
                }
                else if(!SheriffAlive && numOfOutlawsAlive>0) {
                    System.out.println("Outlaws Win");
                    end.setText("Outlaws Win");
                    end.setVisible(true);
                    i = players.size();
                    GameOver = true;
                    updateStats();
                }
                else if(!SheriffAlive && numOfOutlawsAlive==0 && numOfDeputiesAlive==0 && numOfRenegadesAlive==1) {
                    System.out.println("The Renegade Wins");
                    end.setText("The Renegade Wins");
                    end.setVisible(true);
                    i = players.size();
                    GameOver = true;
                    updateStats();
                }
                else {
                    if (players.get(i).getHealth() > 0) {
                        players.get(i).play(i);
                    }
                }

                SheriffAlive=false;
                GameOver = false;
                numOfOutlawsAlive=0;
                numOfRenegadesAlive=0;
                numOfDeputiesAlive=0;

                for(int j = 0; j < players.size(); j++) {
                    if(players.get(j).getHealth()>0) {
                        if(players.get(j).role.equals("Sheriff")) {
                            SheriffAlive = true;
                        }
                        else if(players.get(j).role.equals("Outlaw")) {
                            numOfOutlawsAlive++;
                        }
                        else if(players.get(j).role.equals("Renegade")) {
                            numOfRenegadesAlive++;
                        }
                        else if(players.get(j).role.equals("Deputy")) {
                            numOfDeputiesAlive++;
                        }
                    }
                }
                updateStats();
                if(SheriffAlive && numOfOutlawsAlive==0 && numOfRenegadesAlive==0) {
                    System.out.println("Sheriff & Deputies Win");
                    end.setText("Sheriff & Deputies Win");
                    end.setVisible(true);
                    i = players.size();
                    GameOver = true;
                }
                else if(!SheriffAlive && numOfOutlawsAlive>0) {
                    System.out.println("Outlaws Win");
                    end.setText("Outlaws Win");
                    end.setVisible(true);
                    i = players.size();
                    GameOver = true;
                }
                else if(!SheriffAlive && numOfOutlawsAlive==0 && numOfDeputiesAlive==0 && numOfRenegadesAlive==1) {
                    System.out.println("The Renegade Wins");
                    end.setText("The Renegade Wins");
                    end.setVisible(true);
                    i = players.size();
                    GameOver = true;
                }
            }
            else {
                for(int j = 0; j < players.size(); j++) {
                    if(players.get(j).getHealth()>0) {
                        if(players.get(j).role.equals("Zombie") || players.get(j).role.equals("Zombie Master")) {
                            numOfZombies++;
                        }
                        else if(players.get(j).role.equals("Survivor")) {
                            numOfSurvivors++;
                        }
                    }
                }
                updateStats();
                if(numOfSurvivors <= 0) {
                    System.out.println("Zombies Win");
                    end.setText("Zombies Win");
                    end.setVisible(true);
                    i = players.size();
                    GameOver = true;
                }
                else if(numOfZombies <= 0) {
                    System.out.println("The Survivors Win");
                    end.setText("The Survivors Win");
                    end.setVisible(true);
                    i = players.size();
                    GameOver = true;
                }
                else {
                    if (players.get(i).getHealth() > 0) {
                        players.get(i).play(i);
                    }
                }
            }
        }
        if(!GameOver) {
            updateStats();
            dynamite = 0;
            for(int i = 0; i < players.size(); i++) {
                System.out.println("Health for player "+i+" is "+players.get(i).getHealth());
                if (players.get(i).getHealth() <= 0 && !alreadyDead[i]) {
                    alreadyDead[i] = true;
                    if((mode == 3 || mode == 2) && !zombieOutbreak) {
                        int rand = (int) (Math.random()*boneCards.size());
                        zombieHands += boneCards.get(rand);
                        int alive = 0;
                        for(int check = 0; check < players.size(); check++) {
                            if(players.get(check).getHealth() > 0 && !players.get(check).role.equals("Zombie") && !players.get(check).role.equals("Zombie Master")) {
                                alive++;
                            }
                        }
                        if(zombieHands > alive) {
                            zombieOutbreak = true;
                            int zombieMasters = 0;
                            for(int check = 0; check < players.size(); check++) {
                                if(players.get(check).getHealth() > 0) {
                                    if(players.get(check).role.equals("Renegade") && zombieMasters == 0) {
                                        players.get(check).setRole("Zombie Master");
                                        zombieMasters++;
                                    }
                                    else {
                                        players.get(check).setRole("Survivor");
                                    }
                                }
                                else {
                                    players.get(check).setRole("Zombie");
                                    players.get(check).setHealthZombie(alive);
                                }
                            }
                            if(players.get(0).role.equals("Zombie")) {
                                handOfDie[0] = new Base_Die();
                                handOfDie[2] = new Base_Die();
                                handOfDie[3] = new Base_Die();
                                disableDice4();
                                disableDice5();
                                diceChooser.setDisable(true);
                            }
                            updateStats();
                        }
                    }
                    int rand = (int) (Math.random()*boneCards.size());
                    zombieHands += boneCards.get(rand);
                    boneCards.remove(rand);
                    for(int j = 0; j < players.size(); j++) {
                        if(players.get(j).getClass().equals(Vulture_Sam.class)) {
                            players.get(j).addHealth();
                            players.get(j).addHealth();
                        }
                    }
                }
            }
            System.out.println("It is now your turn");
            for(int d = 0; d<handOfDie.length; d++) {
                handOfDie[d].resetDie();
            }
        }

    }

    /**
     * this method enables buttons corresponding to the action the player is taking
     */
    public void playerActionChoice() {
        try {
            if(playerChoice.getValue().equals("Shoot One Away")) {
                enableButtons(0);
            }
            else if(playerChoice.getValue().equals("Shoot Two Away")) {
                enableButtons(1);
            }
            else if(playerChoice.getValue().equals("Duel")) {
                    enableButtons(2);
                }
                else if(playerChoice.getValue().equals("Use Broken Arrow")) {
                    enableButtons(3);
                }
                else if(playerChoice.getValue().equals("Use Beer")) {
                    enableButtons(4);
                }
                else if(playerChoice.getValue().equals("Use Whiskey")) {
                    enableButtons(5);
            }
        }
        catch (java.lang.NullPointerException e) {

        }

        playerChoice.getItems().removeAll();

        //setPlayerChoices();


    }

    /**
     * This method sets the chooses the player can make depending on what they rolled
     */
    private void setPlayerChoices() {
        ObservableList<String> playerChoices = FXCollections.observableArrayList();
        playerChoices.removeAll();
        if(PlayerActions[0] > 0) {
            suzyPassive = true;
            playerChoices.add("Shoot One Away");
        }
        if(PlayerActions[1] > 0) {
            suzyPassive = true;
            playerChoices.add("Shoot Two Away");
        }
        if(PlayerActions[2] > 0) {
            playerChoices.add("Duel");
        }
        if(PlayerActions[3] > 0) {
            playerChoices.add("Use Broken Arrow");
        }
        if(PlayerActions[4] > 0) {
            playerChoices.add("Use Beer");
        }
        if(PlayerActions[5] > 0) {
            playerChoices.add("Use Whiskey");
        }
        playerChoice.getItems().removeAll();
        try {
            playerChoice.setItems(playerChoices);
        }
        catch (Exception e) {

        }
    }

    /**
     * This method enables buttons to take an action
     * @param choice an int representing the choice chosen
     */
    private void enableButtons(int choice) {



        int distance[] = {0,0,0,0,0,0,0,0,0};
        int d1 = 0;
        int d2 = 0;
        playerButton.setVisible(false);
        ai1.setVisible(false);
        ai2.setVisible(false);
        ai3.setVisible(false);
        ai4.setVisible(false);
        ai5.setVisible(false);
        ai6.setVisible(false);
        ai7.setVisible(false);
        if(choice == 2) {
            ai1.setVisible(true);
            ai2.setVisible(true);
            ai3.setVisible(true);
            ai4.setVisible(true);
            ai5.setVisible(true);
            ai6.setVisible(true);
            ai7.setVisible(true);
        }
        else if(choice == 3 || choice == 4) {
            playerButton.setVisible(true);
            ai1.setVisible(true);
            ai2.setVisible(true);
            ai3.setVisible(true);
            ai4.setVisible(true);
            ai5.setVisible(true);
            ai6.setVisible(true);
            ai7.setVisible(true);
        }
        else if(choice == 5) {
            playerButton.setVisible(true);
        }
        else if(choice == 0 || choice == 1) {
            for(int i = 1; i < players.size(); i++) {
                d1 = 0;
                d2 = 0;
                for(int j = i; j > 0; j--) {
                    if(players.get(j).getHealth() > 0) {
                        d1++;
                    }
                }
                distance[i] = d1;
                for(int j = i; j < players.size(); j++) {
                    if(players.get(j).getHealth() > 0) {
                        d2++;
                    }
                }
                if(d2 < d1 && d1 > 2) {
                    distance[i] = d2;
                }
                else if((d1 == 1 && d2 == 2)||(d1 == 2 && d2 == 1)) {
                    distance[i] = -1;
                }
            }
            for(int i = 0; i < distance.length; i ++) {
                if((distance[i] == 1 || distance[i] == -1) && choice == 0) {
                    if(i == 1) {
                        ai1.setVisible(true);
                    }
                    else if(i == 2) {
                        ai2.setVisible(true);
                    }
                    else if(i == 3) {
                        ai3.setVisible(true);
                    }
                    else if(i == 4) {
                        ai4.setVisible(true);
                    }
                    else if(i == 5) {
                        ai5.setVisible(true);
                    }
                    else if(i == 6) {
                        ai6.setVisible(true);
                    }
                    else if(i == 7) {
                        ai7.setVisible(true);
                    }

                }
                else if((distance[i] == 2 || distance[i] == -1) && choice == 1) {
                    if(i == 1) {
                        ai1.setVisible(true);
                    }
                    else if(i == 2) {
                        ai2.setVisible(true);
                    }
                    else if(i == 3) {
                        ai3.setVisible(true);
                    }
                    else if(i == 4) {
                        ai4.setVisible(true);
                    }
                    else if(i == 5) {
                        ai5.setVisible(true);
                    }
                    else if(i == 6) {
                        ai6.setVisible(true);
                    }
                    else if(i == 7) {
                        ai7.setVisible(true);
                    }
                }
            }
        }
    }

    /**
     * Does the action selected to the corresponding character
     * @param event the event of the buttton being pressed, used to tell what character it is being used on
     */
    public void doAction(ActionEvent event) {
        String action = playerChoice.getValue();
        int target=0;

        if(event.getSource().equals(playerButton)) {
            target = 0;
        }
        else if(event.getSource().equals(ai1)) {
            target = 1;
        }
        else if(event.getSource().equals(ai2)) {
            target = 2;
        }
        else if(event.getSource().equals(ai3)) {
            target = 3;
        }
        else if(event.getSource().equals(ai4)) {
            target = 4;
        }
        else if(event.getSource().equals(ai5)) {
            target = 5;
        }
        else if(event.getSource().equals(ai6)) {
            target = 6;
        }
        else if(event.getSource().equals(ai7)) {
            target = 7;
        }
        try {
            if(action.equals("Shoot One Away")) {
                players.get(target).subtractHealth();
                PlayerActions[0]--;
            }
            else if(action.equals("Shoot Two Away")) {
                players.get(target).subtractHealth();
                PlayerActions[1]--;
            }
            else if(action.equals("Duel")) {
                PlayerActions[2]--;
                Die duel = new Black_Die();
                duel.face = 1;
                int target2 = 0;
                int temp = 0;
                while(duel.face == 1 || duel.face==2) {
                    duel.canReroll = true;
                    duel.roll();
                    if(duel.face == 1 || duel.face==2) {
                        temp = target;
                        target = target2;
                        target2 = temp;
                    }
                }
                players.get(target).subtractHealth();


            }
            else if(action.equals("Use Broken Arrow")) {
                players.get(target).subtractArrows();
                PlayerActions[3]--;
            }
            else if(action.equals("Use Beer")) {
                players.get(target).addHealth();
                PlayerActions[4]--;
            }
            else if(action.equals("Use Whiskey")) {
                players.get(target).addHealth();
                PlayerActions[5]--;
            }
            for(int i = 0; i < handOfDie.length; i++) {
                if(handOfDie[i].canReroll && handOfDie[i].getFaceString().equals(action)) {
                    handOfDie[i].canReroll = false;
                }
            }
        }
        catch (java.lang.NullPointerException e) {

        }
        updateStats();
        setPlayerChoices();
    }

    /**
     * updates the stats for each player card
     */
    public void updateStats() {
        for(int i = 0; i < players.size(); i++) {
            if(players.get(i).getHealth() > 0 && !zombieOutbreak) {
                if(i == 0) {
                    p1Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: "+players.get(i).role);
                }
                else if(i == 1) {
                    if(players.get(i).role.equals("Sheriff")) {
                        p2Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: "+players.get(i).role);
                    }
                    else {
                        p2Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: Unknown");
                    }
                }
                else if(i == 2) {
                    if(players.get(i).role.equals("Sheriff")) {
                        p3Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: "+players.get(i).role);
                    }
                    else {
                        p3Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: Unknown");
                    }
                }
                else if(i == 3) {
                    if(players.get(i).role.equals("Sheriff")) {
                        p4Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: "+players.get(i).role);
                    }
                    else {
                        p4Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: Unknown");
                    }
                }
                else if(i == 4) {
                    if(players.get(i).role.equals("Sheriff")) {
                        p5Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: "+players.get(i).role);
                    }
                    else {
                        p5Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: Unknown");
                    }
                }
                else if(i == 5) {
                    if(players.get(i).role.equals("Sheriff")) {
                        p6Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: "+players.get(i).role);
                    }
                    else {
                        p6Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: Unknown");
                    }
                }
                else if(i == 6) {
                    if(players.get(i).role.equals("Sheriff")) {
                        p7Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: "+players.get(i).role);
                    }
                    else {
                        p7Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: Unknown");
                    }
                }
                else if(i == 7) {
                    if(players.get(i).role.equals("Sheriff")) {
                        p8Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: "+players.get(i).role);
                    }
                    else {
                        p8Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: Unknown");
                    }
                }
            }
            else if(players.get(i).getHealth() > 0 && zombieOutbreak) {
                if(i == 0) {
                    p1Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: "+players.get(i).role);
                }
                else if(i == 1) {
                    p2Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: "+players.get(i).role);
                }
                else if(i == 2) {
                    p3Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: "+players.get(i).role);
                }
                else if(i == 3) {
                    p4Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: "+players.get(i).role);
                }
                else if(i == 4) {
                    p5Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: "+players.get(i).role);
                }
                else if(i == 5) {
                    p6Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: "+players.get(i).role);
                }
                else if(i == 6) {
                    p7Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: "+players.get(i).role);
                }
                else if(i == 7) {
                    p8Stats.setText("HP: "+players.get(i).getHealth()+"/"+players.get(i).getMaxHealth()+" Arrows: "+players.get(i).getArrows()+" Role: "+players.get(i).role);
                }
            }
            else  {
                if(i == 0) {
                    p1Stats.setText("HP: 0/"+players.get(i).getMaxHealth()+" Arrows: 0 Role: "+players.get(i).role);
                }
                else if(i == 1) {
                    p2Stats.setText("HP: 0/"+players.get(i).getMaxHealth()+" Arrows: 0 Role: "+players.get(i).role);
                }
                else if(i == 2) {
                    p3Stats.setText("HP: 0/"+players.get(i).getMaxHealth()+" Arrows: 0 Role: "+players.get(i).role);
                }
                else if(i == 3) {
                    p4Stats.setText("HP: 0/"+players.get(i).getMaxHealth()+" Arrows: 0 Role: "+players.get(i).role);
                }
                else if(i == 4) {
                    p5Stats.setText("HP: 0/"+players.get(i).getMaxHealth()+" Arrows: 0 Role: "+players.get(i).role);
                }
                else if(i == 5) {
                    p6Stats.setText("HP: 0/"+players.get(i).getMaxHealth()+" Arrows: 0 Role: "+players.get(i).role);
                }
                else if(i == 6) {
                    p7Stats.setText("HP: 0/"+players.get(i).getMaxHealth()+" Arrows: 0 Role: "+players.get(i).role);
                }
                else if(i == 7) {
                    p8Stats.setText("HP: 0/"+players.get(i).getMaxHealth()+" Arrows: 0 Role: "+players.get(i).role);
                }
            }
        }
        for(int j = players.size(); j <  8; j++) {
            if(j == 2) {
                p3Stats.setText("");
            }
            else if(j == 3) {
                p4Stats.setText("");
            }
            else if(j == 4) {
                p5Stats.setText("");

            }
            else if(j == 5) {
                p6Stats.setText("");

            }
            else if(j == 6) {
                p7Stats.setText("");

            }
            else if(j == 7) {
                p8Stats.setText("");

            }
        }
    }
}


