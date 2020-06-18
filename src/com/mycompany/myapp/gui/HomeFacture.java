/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
/**
 *
 * @author bazinfo
 */
public class HomeFacture extends Form  {
    Form current;
  public HomeFacture(Form previous) {
      current=this;
     setTitle("Facture");
     setLayout(BoxLayout.y());
     
     add(new Label("choose an option"));
Button btnAddfacture = new Button("Add Facture");
Button btnListFacture = new Button("List des Factures");

btnAddfacture.addActionListener(e-> new addFacture(current).show());
btnListFacture.addActionListener(e-> new ListFactures(current).show());
addAll(btnAddfacture,btnListFacture);

getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e->previous.showBack()); 
  }
}
  


 