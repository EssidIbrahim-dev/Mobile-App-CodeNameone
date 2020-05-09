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
public class MyData {
    private String x;
    private int y;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public MyData(String x, int y) {
        this.x = x;
        this.y = y;
    }
    public MyData(){
        
    }

    @Override
    public String toString() {
        return "MyData{" + "x=" + x + ", y=" + y + '}';
    }
    
}
