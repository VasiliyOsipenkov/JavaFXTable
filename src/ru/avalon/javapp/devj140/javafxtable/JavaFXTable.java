/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.javapp.devj140.javafxtable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.avalon.javapp.devj140.javafxtable.models.Users;


/**
 *
 * @author VOsipenkov
 */
public class JavaFXTable extends Application {
    private Properties loginData;
    private TextField userName;
    private PasswordField password;
    private Text loginCheck;
   
    @Override
    public void start(Stage primaryStage) {
        loginData = new Properties();
        File propertyFile = new File("loginData.prop");
        
        try {
            loginData.load(new FileReader(propertyFile));        
        } catch (IOException ex) {
            Logger.getLogger(JavaFXTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url = loginData.getProperty("db.url");
        String user = loginData.getProperty("db.user");
        String pword = loginData.getProperty("db.password");
         
        Text welcome= new Text("Welcome");
        welcome.setFont(Font.font(25));
        
        Label dbUser = new Label("User Name");
        userName = new TextField();
        
        Label dbPassword = new Label("Password");
        password = new PasswordField();
        
        loginCheck = new Text("Login or password is incorrect");
        loginCheck.setStroke(Color.RED);
        loginCheck.setVisible(false);
        
        
        Button login = new Button("Sigh in");
        //login.setOnAction(e -> getlogin(propertyFile));//лямбдой удобнее, но по заданию надо EventHandler и ActionEvent
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(dbLoginCheck(url, user, pword)){
                    primaryStage.close();
                    new PersonTable(url, user, pword).init();
                } else loginCheck.setVisible(true);
                    
            }
        });
        GridPane.setHalignment(login, HPos.RIGHT);
        
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
    
    private void getlogin(String url, String user, String pword){
        new PersonTable(url, user, pword).init(); 
    }     
    
    private boolean dbLoginCheck(String url, String user, String pword){
        try(Connection conn = DriverManager.getConnection(url, user, pword);
                Statement stm = conn.createStatement()){
            List<Users> userList = new ArrayList<>();
            try(ResultSet rs = stm.executeQuery("select * from USERS")){
                while(rs.next()){
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String userPassword = rs.getString(3);
                    userList.add(new Users(id, name, userPassword));
                }                                 
            }
            for(Users u : userList){
            if(u.getName().equals(userName.getText()) && u.getPassword().equals(password.getText()))
                return true;
            }               
        } catch (SQLException ex) {               
        }
        return false;
    }
}
