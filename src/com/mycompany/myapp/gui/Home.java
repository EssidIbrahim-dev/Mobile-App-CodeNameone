/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;



/**
 *
 * @author bazinfo
 */
public class Home extends Form {

    Form current;
  public Home()  
 {
     
      current=this;
     setTitle("Gestion financiere");
     setLayout(BoxLayout.y());
     
    Form hi = new Form("ImageViewer", new BorderLayout());



     add(new Label("choose an option"));
Button btnAddfacture = new Button("gestion des factures");
Button btnListFacture = new Button("gestion des paiements");

btnAddfacture.addActionListener(e-> new HomeFacture(current).show());
btnListFacture.addActionListener(e-> new Homepaiement(current).show());
addAll(btnAddfacture,btnListFacture);

  }

 
}
