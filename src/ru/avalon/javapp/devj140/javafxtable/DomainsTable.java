/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.javapp.devj140.javafxtable;

import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.avalon.javapp.devj140.javafxtable.models.Domains;

/**
 *
 * @author Vasiliy
 */
public class DomainsTable extends Stage{
    
    private Integer personId;
    private String url;
    private String userName;
    private String password;

    public DomainsTable(int personId, String url, String userName, String password) {
        this.personId = personId;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }   
    
    public void init(){
        ObservableList<Domains> domains = FXCollections.observableArrayList(new DBObjectBilder(url, userName, password).getDomainsByPersonId(personId));
        TableView<Domains> table = new TableView<>(domains);
        
        TableColumn<Domains, Integer> idCol = new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setMinWidth(100);
        table.getColumns().add(idCol);
        
        TableColumn<Domains, String> webNameCol = new TableColumn<>("Web name");
        webNameCol.setCellValueFactory(new PropertyValueFactory<>("webName"));
        webNameCol.setMinWidth(100);
        table.getColumns().add(webNameCol);
        
        TableColumn<Domains, String> domainNameCol = new TableColumn<>("Domain name");
        domainNameCol.setCellValueFactory(new PropertyValueFactory<>("domainName"));
        domainNameCol.setMinWidth(100);
        table.getColumns().add(domainNameCol);
        
        TableColumn<Domains, String> ipCol = new TableColumn<>("Ip");
        ipCol.setCellValueFactory(new PropertyValueFactory<>("ip"));
        ipCol.setMinWidth(100);
        table.getColumns().add(ipCol);
        
        TableColumn<Domains, Timestamp> dateRegCol = new TableColumn<>("Registration date");
        dateRegCol.setCellValueFactory(new PropertyValueFactory<>("dateReg"));
        dateRegCol.setMinWidth(100);
        table.getColumns().add(dateRegCol);
        
        TableColumn<Domains, String> countryRegCol = new TableColumn<>("Сountry of registration");
        countryRegCol.setCellValueFactory(new PropertyValueFactory<>("countryReg"));
        countryRegCol.setMinWidth(100);
        table.getColumns().add(countryRegCol);       
       
        
        StackPane root = new StackPane();
        root.getChildren().add(table);
        
        Scene scene = new Scene(root, 660, 500);
        
        setTitle("List of user id = " + personId + " domains");
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        showAndWait();
    }
    
}
