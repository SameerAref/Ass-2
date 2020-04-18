 
package Q333;

import Q333.Ex3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javax.xml.bind.DatatypeConverter;
 
public class Ex4 extends Application{
    private static PrintWriter output;
  public static void main(String[] args) throws Exception{
       String password = "123456";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest);
        System.out.println(myHash);
       output = new PrintWriter(new File("C:\\Users\\SameerAref\\Downloads\\Documents\\sameer"));
      output.write(myHash);
      output.close();
      launch(args);
  }  
  private Scene s1,s2;
    @Override
    public void start(Stage primaryStage) throws Exception {
       
        
     Label l1=new Label("Welcom");
     TextField t1=new TextField();
     Label l2=new Label("User Name:", t1);
     TextField t2=new TextField();
     Label l3=new Label("Password:", t2);
     Button b1=new Button("Sign in");
     Button b2=new Button("Exite");
        HBox h1=new HBox(10,b1,b2);
     Pane p1=new VBox(15,l1,l2,l3,h1);
     ((VBox)p1).setAlignment(Pos.CENTER);
     p1.setStyle("-fx-background-color:blue");
     h1.setAlignment(Pos.CENTER);
         s1=new Scene(p1,350,450);
         primaryStage.setScene(s1);
         
     b1.setOnAction(event-> {
      String text1 = "";
         try {
                   BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\SameerAref\\Downloads\\Documents\\sameer"));
                   
                     String line = "";                   
                    while ((line = br.readLine()) != null) {
                        text1 += line ;
                } 
                     br.close();
                }catch (FileNotFoundException ex) {
                    Logger.getLogger(Ex3.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Ex3.class.getName()).log(Level.SEVERE, null, ex);
                }   
        
     MessageDigest md1;
          String myHash1=null;
         try {
             md1 = MessageDigest.getInstance("MD5");
              md1.update(t2.getText().getBytes());
        byte[] digests = md1.digest();
         myHash1 = DatatypeConverter.printHexBinary(digests);
         } catch (NoSuchAlgorithmException ex) {
             Logger.getLogger(Ex4.class.getName()).log(Level.SEVERE, null, ex);
         }
         if(myHash1.equals(text1)){
            primaryStage.setScene(s2);
            primaryStage.setTitle("Optinal Page");}
        });
     
     Button b3 = new Button("Add student");
     Button b4 = new Button("View student");
     Pane p2= new VBox(7,b3,b4);
     ((VBox)p2).setAlignment(Pos.CENTER);
      s2=new Scene(p2,350,450);
      p2.setStyle("-fx-background-color:gray");
      primaryStage.setTitle("Login Page");
        primaryStage.show();
    }
    
}