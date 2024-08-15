/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themathskater.View_and_Controllers;;

/**
 *
 * @author Christina.2114893 <christinadeborah458@gmail.com>
 */

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import themathskater.Model.Players;
import themathskater.Model.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import static themathskater.MainApp.FXML_DOCUMENT_SCENE;
import static themathskater.MainApp.connexionStage;


public class AdminPanelController implements Initializable {

    @FXML
    private TableColumn<Players, String> colage;

    @FXML
    private TableColumn<Players, String>  colemail;

    @FXML
    private TableColumn<Players, String>  colhighscore;

    @FXML
    private TableColumn<Players, String>  colpassword;

    @FXML
    private TableColumn<Players, String>  colusername;

    @FXML
    private TableView<Players> player;


    ObservableList<Players> PlayerList = FXCollections.observableArrayList();

    @FXML
    void logOut(ActionEvent event) {
        connexionStage.setScene(FXML_DOCUMENT_SCENE);
    }

    //populating the registered player details in admin panel
    @FXML
    void loadDatails(ActionEvent event) {
        player.getItems().clear();
        try {
            Connection con= dbConnection.getInstance().creatConnection();
            ResultSet rs=con.createStatement().executeQuery("select email,name,age,password,high_score from Game");

            while(rs.next()){
                PlayerList.add(new Players(rs.getString("name")
                        ,rs.getString("email"),
                        rs.getString("age"),
                        rs.getInt("password"),
                        rs.getInt("high_score")
                ));
            }

            colusername.setCellValueFactory(new PropertyValueFactory<>("Name"));
            colemail.setCellValueFactory(new PropertyValueFactory<>("Email"));
            colage.setCellValueFactory(new PropertyValueFactory<>("Age"));
            colpassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
            colhighscore.setCellValueFactory(new PropertyValueFactory<>("Highscores"));

        } catch (SQLException ex) {
            Logger.getLogger(AdminPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        player.setItems(PlayerList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}


