/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Ibrahim
 */
public class Achat {
    private int id_achat;	
    private String client_name;	
    private String client_type;	
    private String client_address;	
    private float quantite;
    private int  etat;
    private String date;

    
     

    public int getId_achat() {
        return id_achat;
    }
   

   

    public String getClient_name() {
        return client_name;
    }

    public String getClient_type() {
        return client_type;
    }

    public String getClient_address() {
        return client_address;
    }

    public float getQuantite() {
        return quantite;
    }

    public int getEtat() {
        return etat;
    }

    public void setId_achat(int id_achat) {
        this.id_achat=id_achat;
    }

    public Achat(String client_name, String client_type, String client_address, float quantite, int etat,String date) {
       this.client_name=client_name;
        this.client_type=client_type;
        this.client_address=client_address;
        this.quantite=quantite;
        this.etat=etat;
        this.date=date;
    }

    public void setClient_name(String client_name) {
        this.client_name=client_name;
    }

    public void setClient_type(String client_type) {
        this.client_type=client_type;
    }

    public void setClient_address(String client_address) {
        this.client_address=client_address;
    }

    public void setQuantite(float quantite) {
        this.quantite=quantite;
    }

    public void setEtat(int etat) {
        this.etat=etat;
    }

    public Achat(int id_achat, String client_name, String client_type, String client_address, float quantite, int etat,String date) {
        this.id_achat=id_achat;
        this.client_name=client_name;
        this.client_type=client_type;
        this.client_address=client_address;
        this.quantite=quantite;
        this.etat=etat;
        this.date=date;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date=date;
    }
  
    public Achat(String client_name, String client_type, String client_address) {
        this.client_name=client_name;
        this.client_type=client_type;
        this.client_address=client_address;
    }
    public Achat(){
        
    }

    @Override
    public String toString() {
        return "Achat{" + "id_achat=" + id_achat +  ", client_name=" + client_name + ", client_type=" + client_type + ", client_address=" + client_address + ", quantite=" + quantite + ", etat=" + etat + ", Date=" + date + '}';
    }
    
    

    
    
}
