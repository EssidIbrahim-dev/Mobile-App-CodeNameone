/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import entity.Facture;
import services.FactureService;

/**
 *
 * @author bazinfo
 */
public class addFacture extends Form {

    public addFacture(Form previous) {
     setTitle("add a new facture"); 
     setLayout(BoxLayout.y());
     
     TextField ref =new TextField("", "reference");
    
     TextField clname =new TextField("", "client_name");
     TextField cltype =new TextField("", "client_type");
     TextField typefact =new TextField("", "type_facture");
     TextField statfact =new TextField("", "statut_facture");
     TextField totht =new TextField("", "totalHT");
     TextField totTTC =new TextField("", "totalTC");
     TextField echeance =new TextField("", "echeance");
   
     TextField Datfact =new TextField("", "DateFact");
     Button btnvalider =new Button ("add facture");
     FontImage.setMaterialIcon(btnvalider,FontImage.MATERIAL_DONE);
    
      btnvalider.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
             if (ref.getText().length()==0||clname.getText().length()==0||cltype.getText().length()==0
                     ||typefact.getText().length()==0||statfact.getText().length()==0||totht.getText().length()==0||totTTC.getText().length()==0
                     ||echeance.getText().length()==0||Datfact.getText().length()==0)
        Dialog.show("Alert", " please fill all the fields", new Command("ok"));
             
             else {
             
                 try {
                   Facture f =new Facture(ref.getText(), clname.getText(),cltype.getText(),typefact.getText(),statfact.getText(), echeance.getText(),Datfact.getText());
                  
                   System.out.print(f);
                   if(new FactureService().addFacture(f))
                   {    System.out.println("connexion success");
                       Dialog.show("success", "connection accepted", new Command("ok"));}
                   else{
                    Dialog.show("error", "server error", new Command("ok"));
                   
                   }
                 } catch (NumberFormatException e) {
                    Dialog.show("erroe", " totht or totttc must be a number", new Command("ok"));  
                 }
             
             }
           
         }
     });
     
        addAll(ref,clname,cltype,typefact,statfact,totht,totTTC,echeance,Datfact,btnvalider);
     getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e->previous.showBack()); 
      
     
    } }
    

