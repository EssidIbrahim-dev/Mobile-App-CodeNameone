/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Form;

/**
 *
 * @author FIRAS
 */
public class Map extends Form{
    
    //haka ?
    BrowserComponent bc=new BrowserComponent();
    //bc.setURL("http://localhost:8989/maps/");
    public Map(){
        bc.setURL("http://localhost:8989/maps/");
        add(bc);
    }
    
}
