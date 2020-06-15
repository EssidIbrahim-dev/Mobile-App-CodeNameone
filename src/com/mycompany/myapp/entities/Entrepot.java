/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author hp
 */
public class Entrepot {
     private int id;
     private String address;
     private int nbrRangs;
     private String phone;
     private String phone_bis;
     private String latitude;
     private String longitude;
     private int stock_id;
    

     
     
    public Entrepot(String address, int nbrRangs, String phone, String phone_bis, String latitude, String longitude) {
        this.address = address;
        this.nbrRangs = nbrRangs;
        this.phone = phone;
        this.phone_bis = phone_bis;
        this.latitude = latitude;
        this.longitude = longitude;
        
    }

    public Entrepot(int id, String address, int nbrRangs, String phone, String phone_bis, String latitude, String longitude) {
        this.id = id;
        this.address = address;
        this.nbrRangs = nbrRangs;
        this.phone = phone;
        this.phone_bis = phone_bis;
        this.latitude = latitude;
        this.longitude = longitude;
        
    }

    public Entrepot(int id, String address, int nbrRangs, String phone, String phone_bis, String latitude, String longitude, int stock_id) {
        this.id = id;
        this.address = address;
        this.nbrRangs = nbrRangs;
        this.phone = phone;
        this.phone_bis = phone_bis;
        this.latitude = latitude;
        this.longitude = longitude;
        this.stock_id = stock_id;
        
    }
    public Entrepot() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNbrRangs() {
        return nbrRangs;
    }

    public void setNbrRangs(int nbrRangs) {
        this.nbrRangs = nbrRangs;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone_bis() {
        return phone_bis;
    }

    public void setPhone_bis(String phone_bis) {
        this.phone_bis = phone_bis;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }
    
  
    @Override
    public String toString() {
        return "Entrepot{" + "id=" + id + ", address=" + address + ", nbrRangs=" + nbrRangs + ", phone=" + phone + ", phone_bis=" + phone_bis + ", latitude=" + latitude + ", longitude=" + longitude + ", stock_id=" + stock_id + '}';
    }
  
}
