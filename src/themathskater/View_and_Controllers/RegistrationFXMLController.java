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
import java.sql.SQLException;
import java.util.ResourceBundle;

import themathskater.Model.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import themathskater.Model.MailUtil;


public class RegistrationFXMLController implements Initializable {
    
    PreparedStatement ps,ps1; 

    @FXML
    private Button OK_btn;
    
    @FXML
    private TextField txtname;
    
    @FXML
    private TextField txtemail;

    @FXML
    private PasswordField txtpassword;
    
    @FXML
    private PasswordField txtconpassword;
    
    @FXML
    private TextField txtage;
    
    @FXML
    private TextField txtgender;

    dbConnection connect ;
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = dbConnection.getInstance();
    }    
    
    
     @FXML
    void register(ActionEvent event) throws IOException {

         String username = txtname.getText().trim();
         String email = txtemail.getText().trim();
         String password = txtpassword.getText().trim();
         String ConfirmPwd = txtconpassword.getText().trim();
         String agee= txtage.getText().trim();
         String Gender=txtgender.getText().trim();

         if(username.isEmpty() || password.isEmpty() || txtage.getText().trim().isEmpty()
                 || email.isEmpty() || Gender.isEmpty() ||
                 ConfirmPwd.isEmpty() || Gender.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill-up all fields!", ButtonType.OK);
             alert.setTitle("Sign Up");
             alert.show();
             return;
         }

         if (password.length()<6 || ConfirmPwd.length() <6){
             Alert alert = new Alert(Alert.AlertType.WARNING, "Password should have more than 6 characters!", ButtonType.OK);
             alert.setTitle("Sign-up");
             alert.show();
             return;
         }

         if(!password.equals(ConfirmPwd)){
             Alert alert = new Alert(Alert.AlertType.WARNING, "Password is not matching", ButtonType.OK);
             alert.setTitle("Sign-up");
             alert.show();
             return;
         }

        try{

            Connection con = connect.creatConnection();
            String sql = "select * from Game where name = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                 Alert alert = new Alert(Alert.AlertType.WARNING, "Username already taken, Please try another!", ButtonType.OK);
                    alert.setTitle("Sign-up");
                    alert.show();
            }
            
            else{
                
                String sql2 = "insert into game (name, email, age,password,gender,high_score) values(?,?,?,?,?,?)";
                ps = con.prepareStatement(sql2);

                ps.setString(1, txtname.getText().trim());
                ps.setString(2, txtemail.getText().trim());
                ps.setInt(3, Integer.parseInt(agee));
                ps.setString(4, txtpassword.getText().trim());
                ps.setString(5, txtgender.getText().trim());
                ps.setInt(6, 0);
                ps.execute();

                txtname.setText("");
                txtemail.setText("");
                txtpassword.setText("");
                txtconpassword.setText("");
                txtage.setText("");
                txtgender.setText("");
            
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Successfully Registered!", ButtonType.OK);
             alert.setTitle("Sign up");
             alert.show();
             MailUtil.sendmail("ebemsheron@gmail.com");
         
        }
       } catch (SQLException e) {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            System.out.println("error" + e);
        }

        finally {

        }
    }
    

    
    
    
}
