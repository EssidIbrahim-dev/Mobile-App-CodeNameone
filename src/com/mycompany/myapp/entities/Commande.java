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
public class Commande {
    private int id;
    private int qte;
    private int achat;
    private int product;
    private String date;
    private String reference;

    public Commande(int qte, int achat, int product, String date, String reference) {
        this.qte = qte;
        this.achat = achat;
        this.product = product;
        this.date = date;
        this.reference = reference;
    }
    public Commande(){
        
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", qte=" + qte + ", achat=" + achat + ", product=" + product + ", date=" + date + ", reference=" + reference + '}';
    }

    public Commande(int id, int qte, int achat, int product, String date, String reference) {
        this.id = id;
        this.qte = qte;
        this.achat = achat;
        this.product = product;
        this.date = date;
        this.reference = reference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getAchat() {
        return achat;
    }

    public void setAchat(int achat) {
        this.achat = achat;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
    
    
}
