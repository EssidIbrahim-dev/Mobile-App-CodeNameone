/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Ibrahim
 */
public class Reclamation {
    private int id;
    private String priority;

    public Reclamation(int id, String priority, int idUser, int product, String username, String contenu, String etat, String date) {
        this.id = id;
        this.priority = priority;
        this.idUser = idUser;
        this.product = product;
        this.username = username;
        this.contenu = contenu;
        this.etat = etat;
        this.date = date;
    }

    public Reclamation(String priority, int idUser, int product, String username, String contenu, String etat, String date) {
        this.priority = priority;
        this.idUser = idUser;
        this.product = product;
        this.username = username;
        this.contenu = contenu;
        this.etat = etat;
        this.date = date;
    }
    private int idUser;
    private int product;

    public Reclamation(String priority, int product, String username, String contenu) {
        this.priority = priority;
        this.product = product;
        this.username = username;
        this.contenu = contenu;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }
    private String username;
    private String contenu;
    private String etat;
    private String date;

    public Reclamation(int id, String priority, String contenu) {
        this.id = id;
        this.priority = priority;
        this.contenu = contenu;
    }

    public Reclamation(int id, String priority, int idUser, String username, String contenu, String etat, String date) {
        this.id = id;
        this.priority = priority;
        this.idUser = idUser;
        this.username = username;
        this.contenu = contenu;
        this.etat = etat;
        this.date = date;
    }

    public Reclamation(String priority, String username, String contenu) {
        this.priority = priority;
        this.username = username;
        this.contenu = contenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public Reclamation(){
        
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", priority=" + priority + ", idUser=" + idUser + ", product=" + product + ", username=" + username + ", contenu=" + contenu + ", etat=" + etat + ", date=" + date + '}';
    }

   
}
