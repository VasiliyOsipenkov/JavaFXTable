/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.javapp.devj140.javafxtable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.avalon.javapp.devj140.javafxtable.models.Person;

/**
 *
 * @author VOsipenkov
 */
public class PersonTable extends Stage{
    
    String url;
    String user;
    String pword;

    public PersonTable(String url, String user, String pword) {
        this.url = url;
        this.user = user;
        this.pword = pword;
    }    
             
    public void init(){
        ObservableList<Person> persons = FXCollections.observableArrayList(new DBObjectBilder(url, user, pword).getPerson());
        TableView<Person> table = new TableView<>(persons);
        
        table.setOnMouseClicked(e -> {
            if(e.getClickCount()==2){
                Person person = table.getSelectionModel().getSelectedItem();
                new DomainsTable(person.getId(), url, user, pword).init();
            }
        });
        
        TableColumn<Person,Integer> idCol = new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setMinWidth(20);
        table.getColumns().add(idCol);       
                
        TableColumn<Person, String> jobTitleCol = new TableColumn<>("Job Title");
        jobTitleCol.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        jobTitleCol.setMinWidth(100);
        table.getColumns().add(jobTitleCol);
        
        TableColumn<Person, String> firstNameLastNameCol = new TableColumn<>("First Name, Last Name");
        firstNameLastNameCol.setCellValueFactory(new PropertyValueFactory<>("firstNameLastName"));
        firstNameLastNameCol.setMinWidth(150);
        table.getColumns().add(firstNameLastNameCol);
        
        TableColumn<Person, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneCol.setMinWidth(150);
        table.getColumns().add(phoneCol);
        
        TableColumn<Person, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setMinWidth(200);
        table.getColumns().add(emailCol);
        
        TableColumn<Person, Integer> domainsCountCol = new TableColumn<>("Domains count");
        domainsCountCol.setCellValueFactory(new PropertyValueFactory<>("domainsCount"));
        domainsCountCol.setMinWidth(20);
        table.getColumns().add(domainsCountCol);
        
        StackPane root = new StackPane();
        root.getChildren().add(table);
        
        Scene scene = new Scene(root, 750, 600);
        
        setTitle("Person list");
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        showAndWait();
    }
}
