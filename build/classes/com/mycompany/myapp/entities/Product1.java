/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author bhk
 */
public class Product1 {
    private int id;
    private String productName; 
    private String productType;
    private String reference; 
    private String marque; 
    private float priceHT;
    private float priceTTC;
    private float weight;
    private String photo;
    private float tva;

    public Product1(String productName, String productType, String reference, String marque, float priceHT, float priceTTC, float weight, float tva) {
        this.productName = productName;
        this.productType = productType;
        this.reference = reference;
        this.marque = marque;
        this.priceHT = priceHT;
        this.priceTTC = priceTTC;
        this.weight = weight;
        this.tva = tva;
    }

    public Product1(String productName, String productType, String reference, String marque, float priceHT, float priceTTC, float weight, float tva, String photo) {
        this.productName = productName;
        this.productType = productType;
        this.reference = reference;
        this.marque = marque;
        this.priceHT = priceHT;
        this.priceTTC = priceTTC;
        this.weight = weight;
        this.photo = photo;
        this.tva = tva;
    }
  

     public Product1(int id, String productName, String productType, String reference, String marque, float priceHT, float priceTTC, float weight, float tva, String photo) {
        this.id = id;
        this.productName = productName;
        this.productType = productType;
        this.reference = reference;
        this.marque = marque;
        this.priceHT = priceHT;
        this.priceTTC = priceTTC;
        this.weight = weight;
        this.photo = photo;
        this.tva = tva;
    }

    public Product1(int id, String productName) {
        this.id = id;
        this.productName = productName;
    }

    public Product1() {
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public float getPriceHT() {
        return priceHT;
    }

    public void setPriceHT(float priceHT) {
        this.priceHT = priceHT;
    }

    public float getPriceTTC() {
        return priceTTC;
    }

    public void setPriceTTC(float priceTTC) {
        this.priceTTC = priceTTC;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public float getTva() {
        return tva;
    }

    public void setTva(float tva) {
        this.tva = tva;
    }

    @Override
    public String toString() {
        return "Product" + "id=" + id + ", productName=" + productName + ", productType=" + productType + ", reference=" + reference + ", marque=" + marque + ", priceHT=" + priceHT + ", priceTTC=" + priceTTC + ", weight=" + weight + ", photo=" + photo + ", tva=" + tva;
    }    
}
