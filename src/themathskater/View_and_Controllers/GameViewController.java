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
import java.util.*;

import themathskater.MainApp;
import themathskater.Model.PresentPlayer;
import javafx.animation.*;
import javafx.beans.binding.When;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import static themathskater.MainApp.connexionStage;

 // Here is our Controller Class, this is where we will manage all the actions and events
 
public class GameViewController implements Initializable {

    ScaleTransition scalingButtonAnimation; // A scaling animation when the cursor enter/exit a Button
    
    AudioClip skate_runing_audio;// Our audio runing sound
    //The @FXML annotation that you will see here just at the top of each our node declaration simply means that we are calling our declared nodes from the fxml, that fxml file contains our primary design aspect, i called it "primary design aspect" because we are also able to edit/modify it from here (the controller class)
    @FXML
    private Text question_text; //Our Question Node Text, it will display the questions
    @FXML
    private Button button1; // Our button 1 for the answer 1
    @FXML
    private Button button2; // Our button 2 for the answer 2
    @FXML
    private Button button3; // Our button 3 for the answer 3
    @FXML
    private Button button4; // Our button 4 for the answer 4
    ParallelTransition pt=new ParallelTransition();// our parralelTransition
    
    ArrayList<String>questions_array=new ArrayList();// Our array of questions, it will store the questions, questions will be String elements
    ArrayList<Integer>answer_array=new ArrayList();// Our array of answers, it will store the answers, answers wil be Integer elements
    
    Random rnd=new Random();// A Random object that will help us to randomly generate numbers
    int option_1,option_2,option_3,option_4; // Will will have 4 options of answers available, they will be integers values
    int a,b,index; 
    ArrayList<Button>buttons_array=new ArrayList();// The button_array that will store THE Buttons
    ArrayList<Integer> four_values_array=new ArrayList();// an array that will store our 4 optionnal answers
    
    
    ArrayList<Image> images_array=new ArrayList(); // an array that will store the images we will use.
    ArrayList<Timeline> skater_timelines=new ArrayList(); // an array that will store the skater timelines.
    TranslateTransition bacground_transition_1=new TranslateTransition();// The translate animation of our background
    Timeline skater_walking;
    TranslateTransition jump;
    ScaleTransition st , sc;

    private PresentPlayer presentPlayer;


    static BooleanProperty playing = new SimpleBooleanProperty(false);

    @FXML
    private Button pauseBtn;

    public static Stage playPauseStage;



    
    @FXML
    private Text skater_status;//The displayed text JUMP or FAIL
    
    @FXML
    private ImageView skater_view;// The skater imageView node
    @FXML
    private TextField score_textfield;
    static int score_value=0; // The integer value of our score
    @FXML
    private HBox background_HBox;// The background container

    @FXML
    private HBox hbox;// This hbox wich means Horizontal Box will contain our 4 buttons and the question label


    @Override
    public void initialize(URL url, ResourceBundle rb) { // The Initialize method is automatically called when we Run the application
       createStage();
        presentPlayer = PresentPlayer.getPlayerInstance();
        play_replay();// Our Play/RePlay game method
        pauseBtn.disableProperty().bind(playing.not());

        playing.addListener((ov, oldValue, newValue)->{
            if(!oldValue && newValue){
                resume();
            }
        });
      }


    void Numbers_distributions() { // This method will randomly distribute 4 Integers values on our buttons, but only one of them will be the good answer
        //since the proper answer will be the sum of a and b, knowing that a and b are took in the range [0,100], their sum will be <=200;
        // So, our 4 values will be in the range [0,200], but only one will be the correct answer
        //We will call our integer arrayList that will store our 4 optionals values/answers
        four_values_array.clear(); // we clear it first, we want it to only have 4 values when this method is called
        // we firstly insert the proper answer, 
        // the questions_array contains 10 questions, we want it to display questions randomly
       // to do so we will create a random index(contained in the range [0,9] because it is the size of our array) that will allow us to randomly pick a question
       index=rnd.nextInt(9)+0; // 
       // Now we randomly pick a question and display it on the Text Node
       question_text.setText(questions_array.get(index));
        four_values_array.add(answer_array.get(index));// we are currently at our_index value, so, for the question_array.get(index), the answer will be answer_array.get(index)
        //Now will will add 3 others values (without duplicates)
        
        do 
        {
            int x=rnd.nextInt(200)+0;// a raondom value in the range [0,200]
            if (four_values_array.contains(x)) {// we do not want duplicates values, so, if it already contains that value, we will reject it, and if it do not contains it, we will accept it
                //We do nothing
            }
            else 
            {
                // We insert it in our array
                four_values_array.add(x);
            }
        }
        while(four_values_array.size()<4);
        
         //We have 4 buttons that should randomly have values amongs our 4 pr-selected values
        //Our buttons are in our buttons_array, we want to have them randomly before they display answers
        // we will firstly shuffle their values inside their array
        Collections.shuffle(four_values_array);
        
        
    }
    
     @FXML
    private void handleOnMouseEntered(MouseEvent event) {
        
        Node source=(Node)event.getSource();// We catch our node and then we apply our animation on it
       
        // When the mouse enter a button, it will scale Up from 1 to 1.15 (both in X and Y)
        scalingButtonAnimation = new ScaleTransition(Duration.millis(200),source);// We create the animation and we set the duration
        scalingButtonAnimation.setCycleCount(1);// We set it on 1 Cycle
        scalingButtonAnimation.setToX(1.15); // We set the scale factor in X
        scalingButtonAnimation.setToY(1.15);// We set the scale factor in Y
        scalingButtonAnimation.playFromStart();// We launch the animation
    }


    @FXML
    private void handleOnMouseExited(MouseEvent event)
    {
        Node source=(Node)event.getSource();
         // When the mouse exit a button, it will scale-back to 1 (both in X and Y)
      scalingButtonAnimation = new ScaleTransition(Duration.millis(200),source); 
      scalingButtonAnimation.setCycleCount(1);// We set it on 1 Cycle
      scalingButtonAnimation.setToX(1); // We set the scale factor in X
      scalingButtonAnimation.setToY(1);// We set the scale factor in Y
      scalingButtonAnimation.playFromStart();// We launch the animation
    }
    
    @FXML
    void Our_Button_Method(Event event) { // when button is clicked
        
        Button source=(Button)event.getSource();// We catch the clicked button in our event
         
        if(source.getText().equals(""+answer_array.get(index)))// We check if the displayed text of our button is the string representation of the string proper answer
         {
             
             skater_status.setText("GOOD !");
             skater_status.setFill(Color.web("#5bae2a"));// When you succeed the text is displayed in green
             
             JUMP();// The jump animation
             score_value=score_value+1;// the player picked the right answer, so we increment the score by +1
             score_textfield.setText(score_value+"");// Wedisplay the updated score in our textfield
         }
         else {
             skater_status.setFill(Color.web("#d38817"));// When you fall the text is displayed in red
             skater_status.setText("NOOOO !");
             
             FAIL();
         }
        
        Numbers_distributions();
       
        // now we will make each of them(Button) to display one value of our four_values_array
        
        for(int i=0;i<=3;i++) {
            buttons_array.get(i).setText(four_values_array.get(i).toString());
        }
          
         
        
    }
    void Load_array_Images() {
        
        // the Failing status has 6 images: Image1.png, Image2.png, Image3.png, Image4.png, Image5.png, Image6.png
        // We will store the failing status images in our array
        for (int i=1; i<=6; i++){
            images_array.add(new Image(MainApp.class.getResourceAsStream("Resources/Image"+i+".png")));
        }
        // the Jumping status has 7 images: Img1.png, Img2.png, Img3.png, Img4.png, Img5.png, Img6.png, Img7.png
        // We will also store the failing status images in our array
        for (int i=1; i<=7; i++){
            images_array.add(new Image(MainApp.class.getResourceAsStream("Resources/Img"+i+".png")));
        }
        // The background buildings will use 2 images: building1 and building2;
        // We will also store it in our array
        for (int i=1; i<=2; i++){
            images_array.add(new Image(MainApp.class.getResourceAsStream("Resources/building"+i+".png")));
        }
        //So in our array, the failing status is from the index 0 to index 5. ( here we have 6 elements)
        //The jumping status, from index 6 to index 12. ( here we have 7 elements)
        //The bacgrounds images, from index 13 to index 14. ( here we have 2 elements)
        //System.out.println("ok");
    }
    void walking_path() { // The Walking Animation
    
        skater_walking=new Timeline();
        pt.stop();
        pt.getChildren().clear();//We stop the parallel animation, we clear it and we add to it a new timeline

        for(int i=6;i<=11;i++) {// We will animate chronologicaly our walking path, remember, the walking path has 6 images inside our array of images, from the index 6 to 11
           skater_walking.getKeyFrames().addAll(
                   new KeyFrame(Duration.millis(550*(i-6)), new KeyValue(skater_view.imageProperty(), images_array.get(i))),

                   new KeyFrame(new Duration(550*(i-5)), new KeyValue(skater_view.imageProperty(), images_array.get(i+1)))
           );

        }
  
      pt.getChildren().add(skater_walking); // We add the animation
      pt.play();// We launch it
      int f=0;
      do {
          f++;
          pt.setOnFinished(e -> {
                pt.play();

               });
      }while(f<500); // We set the duration of the animation (500 cycles)
  
    }


    void audio_of_the_walking_status () {// Our walking audio
        skate_runing_audio =  // We define the path, the volume and the duration
            new AudioClip(MainApp.class.getResource("View_and_Controllers/skate_run.mp3").toString());
        skate_runing_audio.setVolume(0.3);
        skate_runing_audio.setCycleCount(Timeline.INDEFINITE);
        skate_runing_audio.play();

    }


    private void JUMP (){ // The Jumping Animation
        
        jump= new TranslateTransition(Duration.millis(1000),skater_view);
        jump.setCycleCount(1);
        jump.setFromY(0);
        jump.setToY(-150);
        jump.setCycleCount(2);
        jump.setAutoReverse(true);
        jump.play();
                    //Now we will create a kind of "Empty" animation, just in order to add some delay between the dsplaying of the questions
    
        int delay=rnd.nextInt(4)+1; // The delay will be a random value between 1 and 4

        st= new ScaleTransition(Duration.millis(delay*1000),skater_view);// This is our empty animation wich does nothing, its duration will randomly be 1, 2, 3 our 4 seconds
        st.setCycleCount(1);
        st.setFromY(1);
        st.setToY(1);
        st.setCycleCount(2);

        st.play();

        sc= new ScaleTransition(Duration.millis(300),hbox);
        sc.setCycleCount(1);
        sc.setToY(0);
        sc.playFromStart();
                    //We will create an empty animation just to add a randmon delay for the apparition of ours buttons
                    
        skate_runing_audio.stop();//We pause the walking audio when he jumps
        st.setOnFinished(e -> {// When the empty animation is finished
         sc.setToY(1);
        sc.playFromStart();
                    
                }); 
                      jump.setOnFinished(e -> {// When the jump is finished
                     skate_runing_audio.play();//We put back the walking audio when he finish to perform his jump
                     skater_status.setText("");// We clear the skater_status text
        sc.playFromStart();
                    
                }); 
    
}
    void FAIL() {
        
         final Timeline skater_failing=new Timeline();
         pt.stop();
         pt.getChildren().clear();
         
        for(int i=0;i<=4;i++) { // we add all the failing image to create our FAIL Animation
            skater_failing.getKeyFrames().addAll(
                    new KeyFrame(Duration.millis(550*(i)), new KeyValue(skater_view.imageProperty(), images_array.get(i))),
                    
                    new KeyFrame(new Duration(550*(i+1)), new KeyValue(skater_view.imageProperty(), images_array.get(i+1)))
            );
        }
      
       pt.getChildren().add(skater_failing);
       
       pt.play();
       pt.setOnFinished(e -> { // When we finish to fall, we stop the animations and we launch the "GAME OVER" animation
                bacground_transition_1.pause();
                
                skate_runing_audio.stop();
                ScaleTransition sc= new ScaleTransition(Duration.millis(400),skater_status);
                sc.setCycleCount(1);
                sc.setFromX(0);
                skater_status.setText("GAME OVER");// Our label will display GAME OVER when you finish to fall, it will have a small animation
                sc.setToX(1);
                sc.play();

                pause();

                });
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Game Over");
        alert.setHeaderText("");
        alert.setContentText("Do you want to restart");
        alert.initOwner(connexionStage);
        alert.setResizable(false);
        alert.getButtonTypes().addAll(ButtonType.YES,ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.YES){
            score_textfield.setText("0");
            skater_status.setText("");
            play_replay();
        }
        else{
            System.exit(0);
        }
        System.out.println("Completed");
    }

    private void updateScores() {



    }

    @FXML
    void Replay() {
        skater_status.setText("");
        score_value=0;
        score_textfield.setText("0");
       play_replay();
    }
    
     private void BACKGROUND_ANIMATION (){ // The Background Animation
     bacground_transition_1= new TranslateTransition(Duration.millis(100000),background_HBox);
                    for(int i=1;i<=100;i++) {
                        ImageView imV=new ImageView(images_array.get(14));
                        background_HBox.getChildren().add(imV);
                    }
                    
                    bacground_transition_1.setFromX(0);
                    bacground_transition_1.setToX(-65000);
                    bacground_transition_1.setCycleCount(Timeline.INDEFINITE);
                    
                    bacground_transition_1.play();

    
    }
      void play_replay() { 
        playing.setValue(true);
       // Let us add somes questions to our question_array, also somes answers to our answer_array
       // We will create a loop to dynamically add questions and answers
       
       for(int i=0;i<=9;i++) 
       {
           
           a=rnd.nextInt(100)+0;// 0 and 100 indicate the range our our random value, so it means that the number "a" will be a random value contained int the range [0;100], it may seems a bit strange because for a range [a,b], the expression will be rnd.nextInt(b)+a, and NOT rnd.nextInt(a)+b;
           b=rnd.nextInt(100)+0; // Same as for number "a"
           questions_array.add((a+"+"+b));// We add the String field " a + b", example with 5 and 2, it will be : "5 + 2"
           answer_array.add(a+b);// We add the Integer value of a+b, example with 5 and 2, it will be: 7
           
       }
       // We successfully added 10 questions and 10 answers
       
       
       //We add the 4 buttons inside our Button array
       buttons_array.add(button1);
       buttons_array.add(button2);
       buttons_array.add(button3);
       buttons_array.add(button4);
       
       
       Numbers_distributions();// We launch the distribution of the numbers
       
       
        for(int i=0;i<=3;i++) { // now we will make each of them(Button) to display one value of our four_values_array
            buttons_array.get(i).setText(four_values_array.get(i).toString());
        }
        Load_array_Images();// We load our images
        walking_path();//We launch the walking animation
        audio_of_the_walking_status();//We play our audio
        BACKGROUND_ANIMATION();
        score_textfield.setId("score");// We set a JavaFX-Css ID to our textfield, to be able to add it some of our css values declared in our css file
    
    }

    @FXML
    void pauseGame(ActionEvent event) {
        pause();
        playPauseStage.showAndWait();
    }


    private void showAlertDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Unknown User !");
        alert.setHeaderText("Sorry but We do not reconize you");
        alert.setContentText("Forgotten password?");
        alert.initOwner(connexionStage);
        alert.setResizable(false);

        //alert.setDialogPane();
        /*ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeTwo = new ButtonType("No");
        ButtonType buttonTypeCancel = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);// Ajout des bouttons à notre fenetre d'avertissement

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);*/
        alert.showAndWait();
    }

    private void createStage(){
        playPauseStage = new Stage();
        try {
            Parent root = loadFXMLFile("View_and_Controllers/Pauseplay.fxml");
            playPauseStage.setScene(new Scene(root));
            playPauseStage.setResizable(false);
            playPauseStage.initStyle(StageStyle.UNDECORATED);
            playPauseStage.initOwner(connexionStage);
            playPauseStage.initModality(Modality.APPLICATION_MODAL);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void resume(){
        if(pt.getStatus() == Animation.Status.RUNNING) return;
        else pt.play();

        if(bacground_transition_1.getStatus() == Animation.Status.RUNNING) return;
        else bacground_transition_1.play();

        if(skate_runing_audio.isPlaying()) return;
        else skate_runing_audio.play();
    }

    private void pause(){
        playing.setValue(false);
        pt.pause();
        bacground_transition_1.pause();
        skate_runing_audio.stop();
    }

    private Parent loadFXMLFile(String url) throws IOException {
        return FXMLLoader.load(MainApp.class.getResource(url));
    }

}
