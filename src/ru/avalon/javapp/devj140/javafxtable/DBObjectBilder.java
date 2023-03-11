/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.javapp.devj140.javafxtable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import ru.avalon.javapp.devj140.javafxtable.models.Domains;
import ru.avalon.javapp.devj140.javafxtable.models.Person;

/**
 *
 * @author VOsipenkov
 */
public class DBObjectBilder {
    private String url;
    private String userName;
    private String password;

    public DBObjectBilder(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }
    public List<Person> getPerson(){
        List<Person> persons = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, userName, password);
                Statement stm = conn.createStatement()){
            try(ResultSet rs = stm.executeQuery("select p.*, (select count(*) from DOMAINS where PERSONID = p.ID) as DOMAINSCOUNT from PERSON p")){
                while (rs.next()) {                    
                    int id = rs.getInt(1);
                    String jobTitle = rs.getString(2);
                    String firstNameLastName = rs.getString(3);    
                    String phone = rs.getString(4);
                    String email = rs.getString(5);
                    int domainsCount = rs.getInt(6);
                    persons.add(new Person(id, jobTitle, firstNameLastName, phone, email, domainsCount));
                }
            }
        } catch (SQLException e) {
        }
        return persons;
    }
    
    public List<Domains> getDomainsByPersonId(int personId){
        List<Domains> domains = new ArrayList<>();        
        try(Connection conn = DriverManager.getConnection(url, userName, password);
                Statement stm = conn.createStatement()){
            try(ResultSet rs = stm.executeQuery("select * from DOMAINS where PERSONID =" + personId)){
                while(rs.next()) {
                    int id = rs.getInt(1);
                    String webName = rs.getString(2);
                    String domainName = rs.getString(3);
                    String ip = rs.getString(4);
                    Timestamp dateReg = rs.getTimestamp(5);
                    String countryReg = rs.getString(6);
                    domains.add(new Domains(id, webName, domainName, ip, dateReg, countryReg, personId));
                }
            }
        }catch (SQLException e){
        }
        return domains;
    }
    
}
