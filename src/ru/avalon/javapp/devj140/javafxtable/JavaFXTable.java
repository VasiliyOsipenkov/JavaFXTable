/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.javapp.devj140.javafxtable;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author VOsipenkov
 */
public class JavaFXTable extends Application {
    private Properties loginData;
    private TextField userName;
    private TextField password;
    private Text loginCheck;
    @Override
    public void start(Stage primaryStage) {
        loginData = new Properties();
        File propertyFile = new File("loginData.prop");
         
        Text welcome= new Text("Welcome");
        welcome.setFont(Font.font(25));
        
        Label dbUser = new Label("User Name");
        userName = new TextField();
        
        Label dbPassword = new Label("Password");
        password = new TextField();
        
        loginCheck = new Text("Login or password is incorrect");
        loginCheck.setStroke(Color.RED);
        loginCheck.setVisible(false);
        
        
        Button login = new Button("Sigh in");
        login.setOnAction(e -> getlogin(propertyFile));
        GridPane.setHalignment(login, HPos.RIGHT);
        /*
        read.setOnAction(e -> getProperties(propertyFile));
        Button save = new Button("Save");
        save.setOnAction(e -> setProperties(propertyFile));
        */ //приладить взаимодействие с настройками
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setGridLinesVisible(false);
        root.getColumnConstraints().add(new ColumnConstraints(100));
        root.setHgap(5);
        root.setVgap(10);
        
        root.add(welcome, 0, 0);
        root.add(dbUser, 0, 1, 1, 1);
        root.add(userName, 1, 1, 1, 1);
        
        root.add(dbPassword, 0, 2, 1, 1);
        root.add(password, 1, 2, 1, 1);
       
        root.add(loginCheck, 0, 3);
        
        root.add(login, 1, 3, 1, 1);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("JavaFXTable");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void getlogin(File propertyFile){
        try {
            loginData.load(new FileReader(propertyFile));
         
            loginData.getProperty("db.url");
            if (!loginData.getProperty("db.user").equals(userName.getText()) || !loginData.getProperty("db.password").equals(password.getText()) )
                loginCheck.setVisible(true);
            else
                loginCheck.setVisible(false);
        } catch (IOException ex) {
            Logger.getLogger(JavaFXTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      
}
