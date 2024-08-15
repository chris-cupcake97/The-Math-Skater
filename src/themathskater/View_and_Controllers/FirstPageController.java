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
import java.util.ResourceBundle;

import themathskater.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static themathskater.MainApp.connexionStage;
import static themathskater.MainApp.main;

public class FirstPageController implements Initializable {

    public static Stage LoginSignInStage;

    @FXML
    private Button StartButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void show_loginPage() {

        connexionStage.setScene(MainApp.FXML_DOCUMENT_SCENE);
        /*Stage stage = new Stage();
        stage.setTitle("Skater - Inscription");// The title for this Stage
        Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.resizableProperty().set(false);// Wet set it not resizable
        stage.initOwner(connexionStage);// We set on it the same icon as the ownerStage
        stage.initModality(Modality.APPLICATION_MODAL);// Once it appears, you will have to close it if you want to access anothe Stage
        stage.show();// We display it
        LoginSignInStage = stage;*/

    }

}
