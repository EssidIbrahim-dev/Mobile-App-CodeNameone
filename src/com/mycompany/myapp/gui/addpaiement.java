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
import entity.paiement;
import services.PaiementService;

/**
 *
 * @author bazinfo
 */
public class addpaiement extends Form {
     public addpaiement(Form previous) {
     setTitle("add a new facture"); 
     setLayout(BoxLayout.y());
     
     TextField ref =new TextField("", "reference");
     TextField clname =new TextField("", "client_name");
     TextField ptype =new TextField("", "paiement_type");
     TextField rib =new TextField("", "rib");
     TextField numcheque =new TextField("", "num_cheque");
     
     Button btnvalider =new Button ("add paiement");     
     
     btnvalider.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
             if (ref.getText().length()==0||clname.getText().length()==0||ptype.getText().length()==0
                     ||rib.getText().length()==0||numcheque.getText().length()==0)
                     
        Dialog.show("Alert", " please fill all the fields", new Command("ok"));
             
             else {
             
                 try {
                   paiement p =new paiement(ref.getText(),clname.getText(),ptype.getText(),rib.getText(),numcheque.getText());
                   System.out.print(p);
                   if(new PaiementService().addpaiement(p))
                       Dialog.show("success", "connection accepted", new Command("ok"));
                   else{
                    Dialog.show("error", "server error", new Command("ok"));
                   
                   }
                 } catch (Exception e) {
                    Dialog.show("error", " totht or totttc must be a number", new Command("ok"));  
                 }
             
             }
           
         }
     });
     
        addAll(ref,clname,ptype,rib,numcheque,btnvalider);
     getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e->previous.showBack()); 
      
     
    }
}
