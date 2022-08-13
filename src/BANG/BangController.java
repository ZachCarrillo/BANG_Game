package BANG;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is the controller for the bang.fxml file (the main menu)
 * @author Zach Carrillo
 */
public class BangController implements Initializable {

    @FXML
    private ComboBox<String> characterSelector;
    ObservableList<String> characterList1 = FXCollections.observableArrayList("Black Jack","El Gringo", "Jourdonnais","Paul Regret","Pedro Ramirez","Suzy Lafayette", "Vulture Sam");
    ObservableList<String> characterList2 = FXCollections.observableArrayList("Apache Kid","Bill Noface","Black Jack","El Gringo", "Jourdonnais","Paul Regret","Pedro Ramirez","Suzy Lafayette", "Vulture Sam");
    ObservableList<String> characterList3 = FXCollections.observableArrayList("Belle Star","Black Jack","El Gringo","Greg Digger" ,"Jourdonnais","Paul Regret","Pedro Ramirez","Suzy Lafayette", "Vulture Sam");
    ObservableList<String> characterList4 = FXCollections.observableArrayList("Apache Kid","Belle Star","Bill Noface","Black Jack","El Gringo","Greg Digger" ,"Jourdonnais","Paul Regret","Pedro Ramirez","Suzy Lafayette", "Vulture Sam");

    @FXML
    private ComboBox<String> gamemodeButton;
    ObservableList<String> modeList = FXCollections.observableArrayList("Base Game", "Old Saloon", "Undead or Alive", "All Expansions");

    @FXML
    private ComboBox<Integer> numOfPlayers;
    ObservableList<Integer> playerList1 = FXCollections.observableArrayList(4, 5, 6, 7);
    ObservableList <Integer> playerList2 = FXCollections.observableArrayList(4, 5, 6, 7, 8);

    @FXML
    private Button startButton;


    /**
     * this initializes the scene
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gamemodeButton.setItems(modeList);
    }

    /**
     * This method sets the items in the Combobox characterSelector according to the gamemode selected
     */
    public void unlockCharacters() {
        if(gamemodeButton.getValue().equals("Base Game")) {
            characterSelector.setItems(characterList1);
            numOfPlayers.setItems(playerList1);
        }
        else if(gamemodeButton.getValue().equals("Old Saloon")) {
            characterSelector.setItems(characterList2);
            numOfPlayers.setItems(playerList2);
        }
        else if(gamemodeButton.getValue().equals("Undead or Alive")) {
            characterSelector.setItems(characterList3);
            numOfPlayers.setItems(playerList2);
        }
        else if(gamemodeButton.getValue().equals("All Expansions")) {
            characterSelector.setItems(characterList4);
            numOfPlayers.setItems(playerList2);
        }
        characterSelector.setDisable(false);
        numOfPlayers.setDisable(false);
        checkToStart();
    }

    /**
     * This method checks if the start button should be able to be pushed
     */
    public void checkToStart() {
        if(numOfPlayers.getValue() != null && characterSelector.getValue() != null) {
            startButton.setDisable(false);
        }
        else {
            startButton.setDisable(true);
        }
    }

    /**
     * This method changes the scene to the Game.fxml file
     * @param event
     * @throws IOException
     */
    public void switchScene(ActionEvent event) throws IOException {
        setMode();
        setPlayerActor();
        GameController.numOfPlayers = numOfPlayers.getValue();
        Parent gameSceneParent = FXMLLoader.load(getClass().getResource("Game.fxml"));
        Scene gameScene = new Scene(gameSceneParent);


        //This line gets the stage info
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(gameScene);
        window.show();

    }

    /**
     * This method sets the mode int in the GameController class
     */
    public void setMode() {
        if(gamemodeButton.getValue().equals("Base Game")) {
            GameController.mode = 0;
        }
        else if(gamemodeButton.getValue().equals("Old Saloon")) {
            GameController.mode = 1;
        }
        else if(gamemodeButton.getValue().equals("Undead or Alive")) {
            GameController.mode = 2;
        }
        else if(gamemodeButton.getValue().equals("All Expansions")) {
            GameController.mode = 3;
        }
    }

    /**
     * This method sets the values of playerVal and playerChar according to the character the player chose to play as
     */
    public void setPlayerActor() {
        if(characterSelector.getValue().equalsIgnoreCase("Apache Kid")) {
            GameController.playerChar = new Apache_Kid();
            GameController.playerVal = 7;
        }
        else if(characterSelector.getValue().equalsIgnoreCase("Belle Star")) {
            GameController.playerChar = new Belle_Star();
            GameController.playerVal = 9;
        }
        else if(characterSelector.getValue().equalsIgnoreCase("Bill Noface")) {
            GameController.playerChar = new Bill_Noface();
            GameController.playerVal = 8;
        }
        else if(characterSelector.getValue().equalsIgnoreCase("Black Jack")) {
            GameController.playerChar = new Black_Jack();
            GameController.playerVal = 0;
        }
        else if(characterSelector.getValue().equalsIgnoreCase("El Gringo")) {
            GameController.playerChar = new El_Gringo();
            GameController.playerVal = 1;
        }
        else if(characterSelector.getValue().equalsIgnoreCase("Greg Digger")) {
            GameController.playerChar = new Greg_Digger();
            GameController.playerVal = 10;
        }
        else if(characterSelector.getValue().equalsIgnoreCase("Jourdonnais")) {
            GameController.playerChar = new Jourdonnais();
            GameController.playerVal = 2;
        }
        else if(characterSelector.getValue().equalsIgnoreCase("Paul Regret")) {
            GameController.playerChar = new Paul_Regret();
            GameController.playerVal = 3;
        }
        else if(characterSelector.getValue().equalsIgnoreCase("Pedro Ramirez")) {
            GameController.playerChar = new Pedro_Ramirez();
            GameController.playerVal = 4;
        }
        else if(characterSelector.getValue().equalsIgnoreCase("Suzy Lafayette")) {
            GameController.playerChar = new Suzy_Lafayette();
            GameController.playerVal = 5;
        }
        else if(characterSelector.getValue().equalsIgnoreCase("Vulture Sam")) {
            GameController.playerChar = new Vulture_Sam();
            GameController.playerVal = 6;
        }
    }
}
