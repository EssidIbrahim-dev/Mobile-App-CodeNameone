/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author HP
 */
public class Reserver {
    public int id;
 public String staff;
 public String mat;
 public String dateres;
 public String dateret;

    public Reserver() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }


    public String getDateres() {
        return dateres;
    }

    public void setDateres(String dateres) {
        this.dateres = dateres;
    }

    public String getDateret() {
        return dateret;
    }

    public void setDateret(String dateret) {
        this.dateret = dateret;
    }
 
}
