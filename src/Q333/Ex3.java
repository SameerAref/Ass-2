/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q333;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author lenovo
 */
public class Ex3 extends Application {
  
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
 
        MenuBar menuBar=new MenuBar();
        Menu menufile=new Menu("File");
        MenuItem open=new MenuItem("open");
        MenuItem close=new MenuItem("close");
        MenuItem exite=new MenuItem("exite");
        menufile.getItems().addAll(open,close,exite);
        Menu menuEdit=new Menu("Edite");
        MenuItem color=new MenuItem("color");
        MenuItem font=new MenuItem("font");
        menuEdit.getItems().addAll(color,font);
        TextArea text=new TextArea();
        text.setPrefColumnCount(10);
        text.setPrefRowCount(15);
        
        
        open.setOnAction(event->{
            FileChooser fileChooser = new FileChooser();
            File selctedFile=fileChooser.showOpenDialog(primaryStage);
            
            if(selctedFile!=null){
                String file=selctedFile.getPath();
                try {
                   BufferedReader br = new BufferedReader(new FileReader(file));
                    String text1 = "";
                     String line = "";                   
                    while ((line = br.readLine()) != null) {
                        text1 += line + "\n";
                } 
                     text.setText(text1);
                     text.setEditable(true);
                     br.close();
                }catch (FileNotFoundException ex) {
                    Logger.getLogger(Ex3.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Ex3.class.getName()).log(Level.SEVERE, null, ex);
                }         
            }
        });
        
        close.setOnAction(event->{
            text.clear();
            text.setEditable(false);
        });
        
         exite.setOnAction(event->{
            primaryStage.close();
        });
         
         color.setOnAction(event->{
             Alert conf=new Alert(Alert.AlertType.CONFIRMATION);
             conf.setTitle("color selction");
             conf.setHeaderText("choose color from list ");
             ComboBox<String> list=new ComboBox<>();
             list.setEditable(false);
             list.getItems().addAll("red","blue","black","gray","yellow","green","tomato");
             conf.setContentText("Avaliable color");
             conf.getDialogPane().setContent(list);
             conf.show();
             
             list.setOnAction(event1->{
                 String selcted=list.getSelectionModel().getSelectedItem();
                  text.setStyle("-fx-text-fill:"+selcted+";");
             }); 
         });
         
         font.setOnAction(event->{
             Alert conf=new Alert(Alert.AlertType.CONFIRMATION);
             conf.setTitle("Font Selction");
             conf.setHeaderText("Write Font");
             TextField textf=new TextField();
             conf.getDialogPane().setContent(textf);
             Optional<ButtonType> result = conf.showAndWait();
             if(result.get()==ButtonType.OK)
                 text.setStyle("-fx-font-size:"+Integer.parseInt(textf.getText())+";");
         });
         
        menuBar.getMenus().addAll(menufile,menuEdit);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        VBox box=new VBox(borderPane,text);
        box.setAlignment(Pos.CENTER);
        Scene s3=new Scene(box,500,400);
        primaryStage.setScene(s3);
       primaryStage.setResizable(false);
        primaryStage.show();
             
    }
  
        
    
    
}