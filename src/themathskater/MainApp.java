/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themathskater;

/**
 *
 * @author Christina.2114893 <christinadeborah458@gmail.com>
 */

import java.io.IOException;
import java.net.URL;

//import TheMathSkater.View_and_Controllers.RegistrationFXMLController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainApp extends Application {
    
    public static Stage connexionStage, registrationStage;
    public static Scene ADMIN_SCENE ,FXML_DOCUMENT_SCENE, FIRST_PAGE ;

    @Override
    public void start(Stage stage) throws Exception {

        ADMIN_SCENE = new Scene(loadFXMLFile("View_and_Controllers/AdminPanel.fxml"));
       FXML_DOCUMENT_SCENE = new Scene(loadFXMLFile("View_and_Controllers/FXMLDocument.fxml"));
        FIRST_PAGE = new Scene(loadFXMLFile("View_and_Controllers/FirstPage.fxml"));

        // Our start method that will launch the first stage
         // We set the root as the scene
        //scene.setFill(Color.TRANSPARENT);
        stage.setScene(FIRST_PAGE);// We add a stage to our scene
        stage.setTitle("The New Skater");// We set a title to our stage
        stage.setResizable(false);
        //we will add a logo/icon to our stage
        // stage.getIcons().add(new Image(("Skater/resources/logo2.png")));// We define the path of out logo image, it is in the package resources, named logo2
        // stage.initStyle(StageStyle.TRANSPARENT);
        stage.show(); // We display our first stage
        connexionStage=stage;  
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    // Now we will create somes other statics methods, to allow us to create and to call the others stages
    
     public static void show_Registration_Screen()  {
        try {
            // Load the fxml file and create a new stage
            FXMLLoader loader = new FXMLLoader(); // We load the FXML file
            loader.setLocation(MainApp.class.getResource("RegistrationFXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            
           Stage stage = new Stage();
            stage.setTitle("Skater - Inscription");// The title for this Stage
            Scene scene = new Scene(page);
            stage.setScene(scene);        
            stage.resizableProperty().set(false);// Wet set it not resizable
            stage.initOwner(connexionStage);// We set on it the same icon as the ownerStage
            stage.initModality(Modality.APPLICATION_MODAL);// Once it appears, you will have to close it if you want to access anothe Stage
            stage.show();// We display it
            registrationStage=stage;
             
        }
        catch (IOException e) {
        }        
    }

    private Parent loadFXMLFile(String url) throws IOException {
        return FXMLLoader.load(MainApp.class.getResource(url));
    }
    
}

