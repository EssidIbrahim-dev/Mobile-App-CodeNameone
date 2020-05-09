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
public class ProdAchat {
    private int id;
    private int qte;
    private int product;
    private int achat;

    public int getId() {
        return id;
    }

    public ProdAchat(int id, int qte) {
        this.id = id;
        this.qte = qte;
    }

    public ProdAchat(int qte, int product, int achat) {
        this.qte = qte;
        this.product = product;
        this.achat = achat;
    }

    public ProdAchat(int id, int qte, int product, int achat) {
        this.id = id;
        this.qte = qte;
        this.product = product;
        this.achat = achat;
    }
    public ProdAchat(){
        
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

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getAchat() {
        return achat;
    }

    public void setAchat(int achat) {
        this.achat = achat;
    }
        
}
