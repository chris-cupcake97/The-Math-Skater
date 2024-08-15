package themathskater.View_and_Controllers;

/**
 *
 * @author Christina.2114893 <christinadeborah458@gmail.com>
 */

import themathskater.Model.PresentPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.util.ResourceBundle;

import static themathskater.MainApp.connexionStage;
import static themathskater.View_and_Controllers.GameViewController.playPauseStage;
import static themathskater.View_and_Controllers.GameViewController.playing;

public class PausePlayController implements Initializable {

    @FXML
    private VBox rootNode;

    private PresentPlayer presentPlayer;

    @FXML
    void checkHighScore(ActionEvent event) {
        int num = presentPlayer.getPresentPlayer().getHighscores();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("High score");
        alert.setHeaderText("");
        alert.setContentText("Your high score so far is "+num);
        alert.initOwner(rootNode.getScene().getWindow());
        alert.setResizable(false);
        alert.show();
    }

    @FXML
    void exitGame(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void resumeAction(ActionEvent event) {
        if(playPauseStage != null && playPauseStage.isShowing()) {
            playPauseStage.hide();
            playing.setValue(true);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        presentPlayer = PresentPlayer.getPlayerInstance();
    }
}
