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
public class Note {
    private String client;
    private int product;
    private int valeur;

    public Note(String client, int product) {
        this.client = client;
        this.product = product;
    }

    @Override
    public String toString() {
        return "Note{" + "client=" + client + ", product=" + product + ", valeur=" + valeur + '}';
    }

    public Note(String client, int product, int valeur) {
        this.client = client;
        this.product = product;
        this.valeur = valeur;
    }

    public Note() {
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
    
}
