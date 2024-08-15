/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themathskater.View_and_Controllers;

/**
 *
 * @author Christina.2114893 <christinadeborah458@gmail.com>
 */

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import themathskater.MainApp;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLDocumentController implements Initializable {

    @FXML
    private VBox vbox;

    @FXML
    private Button close_signin;

    @FXML
    private Button close_signup;

    @FXML
    private Label lbl_time;

    @FXML
    private Label lbl_time2;

    @FXML
    private Pane pane;

    private Parent login, signup;
    double x, y = 0;

    @FXML
    private void openSignUp(ActionEvent event) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(2), vbox);
        t.setToX(10);
        t.play();
        close_signup.setVisible(false);
        close_signin.setVisible(true);
        t.setOnFinished((e) -> {
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(signup);
        });
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) vbox.getScene().getWindow();
        stage.setScene(MainApp.FIRST_PAGE);
    }

    @FXML
    private void openSIgnIn(ActionEvent event) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(2), vbox);
        t.setToX(vbox.getLayoutX() * 32);
        t.play();
        close_signup.setVisible(true);
        close_signin.setVisible(false);
        t.setOnFinished((e) -> {
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(login);
        });
    }

    public void updateTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Calendar time = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                lbl_time.setText(sdf.format(time.getTime()));
                lbl_time2.setText(sdf.format(time.getTime()));
            }
        }), new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTime();
        loadFXML();
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 32);
        t.play();
        close_signin.setVisible(false);
        t.setOnFinished((e) -> {
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(login);
        });
        makeDraggable();
    }

    public void makeDraggable() {
        pane.setOnMousePressed((event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        pane.setOnMouseDragged((event) -> {
            Stage stage = (Stage) vbox.getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(0.8f);
        });
        pane.setOnDragDone((event) -> {
            Stage stage = (Stage) vbox.getScene().getWindow();
            stage.setOpacity(1f);
        });
        pane.setOnMouseReleased((event) -> {
            Stage stage = (Stage) vbox.getScene().getWindow();
            stage.setOpacity(1f);
        });
    }

    private void loadFXML() {
        try {
            signup = FXMLLoader.load(MainApp.class.getResource("View_and_Controllers/RegistrationFXML.fxml"));
            login = FXMLLoader.load(MainApp.class.getResource("View_and_Controllers/Login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
