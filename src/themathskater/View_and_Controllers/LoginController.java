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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import themathskater.MainApp;
import themathskater.Model.Players;
import themathskater.Model.PresentPlayer;
import themathskater.Model.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static themathskater.MainApp.*;

public class LoginController implements Initializable {

    @FXML
    private TextField txtusername;
    @FXML
    private PasswordField txtpass;
    @FXML
    private Button Login_btn;// The Connexion_btn inside our fxml file
    @FXML
    private Button Registration_Button;// SignIn_Button inside our fxml file

    dbConnection connect;

    private PresentPlayer presentPlayer = PresentPlayer.getPlayerInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        connect = dbConnection.getInstance();
    }

    @FXML
    private void Tentative_Connexion() {

    }

    @FXML
    private void close_button() {
        // If this button is clicked, we close the application, we will put it in the close_btn FXML Node as the onAction(Clicked) method
        // The application is launched from the connexionStage in the start() method inside the main class
        // So, closing this starting stage will close the whole application
        connexionStage.close();
    }

    @FXML
    private void Open_Registration() { // The displaying Stage Registration, it will be insert in the Registration button

        // We simply call our public static method we already defined inside the main class
        //show_Registration_Screen();
    }

    @FXML
    private void Connexion() { // for now we will just display a warning dialog when the user click on this button
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Unknown User !");
        alert.setHeaderText("Sorry but We do not reconize you");
        alert.setContentText("Forgotten password?");
        alert.initOwner(connexionStage);
        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeTwo = new ButtonType("No");
        ButtonType buttonTypeCancel = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);// Ajout des bouttons à notre fenetre d'avertissement

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
        alert.showAndWait();
    }

    @FXML
    void login(ActionEvent event) throws IOException {

        String username = txtusername.getText().trim();
        String password = txtpass.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Enter Password!", ButtonType.OK);
            alert.setTitle("Login");
            alert.show();
            return;
        }

        try {

            if (username.equals("Christina") && password.equals("chris1234")) {
                connexionStage.setScene(ADMIN_SCENE);
            } else {
                //PreparedStatement ps = con.prepareStatement("SELECT * FROM signup WHERE `username`=" + "'" + txtusername + "'" + " AND `password`=" + "'"
                //+ txtpass + "'" + "");
                Connection con = connect.creatConnection();
                System.out.println("connected1");
                PreparedStatement ps = con.prepareStatement("select * from Game where email=?"
                        + " and password=?");

                ps.setString(1, txtusername.getText().trim());
                ps.setString(2, txtpass.getText().trim());
                System.out.println("hi");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    int age = rs.getInt("age");
                    String password1 = rs.getString("password");
                    int highscore = rs.getInt("high_score");

                    presentPlayer.setPresentPlayer(new Players(
                            name, email, password1, age, highscore
                    ));

                    Parent parent = loadFXMLFile("View_and_Controllers/GameView.fxml");
                    Scene scene = new Scene(parent);
                    connexionStage.setScene(scene);

                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid credentials!", ButtonType.OK);
                    alert.setTitle("Login");
                    alert.show();
                }
            }
        } catch (Exception ex) {
            System.out.println("error  " + ex.toString());
        }

    }

    private Parent loadFXMLFile(String url) throws IOException {
        return FXMLLoader.load(MainApp.class.getResource(url));
    }

}
