/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.javapp.devj140.javafxtable.models;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author VOsipenkov
 */
public class Domains {
    private Integer id;
    private String webName;
    private String domainName;
    private String ip;
    private Timestamp dateReg;
    private String countryReg;
    private Integer personId;

    public Domains(Integer id, String webName, String domainName, String ip, Timestamp dateReg, String countryReg, Integer personId) {
        this.id = id;
        this.webName = webName;
        this.domainName = domainName;
        this.ip = ip;
        this.dateReg = dateReg;
        this.countryReg = countryReg;
        this.personId = personId;
    }

    public Integer getId() {
        return id;
    }

    public String getWebName() {
        return webName;
    }

    public String getDomainName() {
        return domainName;
    }

    public String getIp() {
        return ip;
    }

    public Timestamp getDateReg() {
        return dateReg;
    }

    public String getCountryReg() {
        return countryReg;
    }

    public Integer getPersonId() {
        return personId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.webName);
        hash = 73 * hash + Objects.hashCode(this.domainName);
        hash = 73 * hash + Objects.hashCode(this.ip);
        hash = 73 * hash + Objects.hashCode(this.dateReg);
        hash = 73 * hash + Objects.hashCode(this.countryReg);
        hash = 73 * hash + Objects.hashCode(this.personId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Domains other = (Domains) obj;
        if (!Objects.equals(this.webName, other.webName)) {
            return false;
        }
        if (!Objects.equals(this.domainName, other.domainName)) {
            return false;
        }
        if (!Objects.equals(this.ip, other.ip)) {
            return false;
        }
        if (!Objects.equals(this.countryReg, other.countryReg)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dateReg, other.dateReg)) {
            return false;
        }
        if (!Objects.equals(this.personId, other.personId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Domains{" + "id=" + id + ", webName=" + webName + ", domainName=" + domainName + 
                ", ip=" + ip + ", dateReg=" + dateReg + ", countryReg=" + countryReg + ", personId=" + personId + '}';
    }           
}
