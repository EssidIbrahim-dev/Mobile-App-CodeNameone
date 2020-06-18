/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import entity.Facture;
import entity.paiement;
import java.util.ArrayList;
import services.FactureService;
import services.PaiementService;

/**
 *
 * @author bazinfo
 */
public class Listpaiement extends Form {
    
    public Listpaiement(Form previous) {
    

      setTitle("Liste des paiements"); 
         SpanLabel sp = new SpanLabel();
         sp.setText(PaiementService.getInstance().GetAllpaiement().toString());
          ArrayList<paiement> cmds = new ArrayList<>();
         cmds = PaiementService.getInstance().GetAllpaiement();
         System.out.print("2");
         System.out.print(cmds);
       
         Form fe = new Form("Paiements ", BoxLayout.y());
         Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        int i=0;
        
         for (paiement c : cmds){
           
        
            i++;
            MultiButton mb = new MultiButton("Paiement n°:"+ i +" | "+c.getReference());
            mb.getStyle().setMarginLeft(5);
            mb.getStyle().setMarginBottom(10);
            mb.setTextLine2("Client:"+c.getClientName());
            mb.setTextLine3("Rib: "+ c.getRib());
            mb.setTextLine4("Paiement: "+ c.getPaiementType());
            //mb.setTextLine5("Cheque: "+ c.getNumCheque());


            list.add(mb);
        
        

           TextField username = new TextField(c.getClientName());
            username.setUIID("TextFieldBlack");
            addStringValue("Client Name", username);
       
        TextField NumCheque = new TextField(c.getNumCheque());
            NumCheque.setUIID("TextFieldBlack");
            addStringValue("Num Cheque", NumCheque);
        
                TextField PaiementType = new TextField(c.getPaiementType());
            PaiementType.setUIID("TextFieldBlack");
            addStringValue("Paiement Type", PaiementType);
        
               TextField Reference = new TextField(c.getReference());
            Reference.setUIID("TextFieldBlack");
            addStringValue("Reference", Reference);
        
           TextField Rib = new TextField(c.getRib());
            Rib.setUIID("TextFieldBlack");
            addStringValue("Reference", Rib);
       
            //sp.setText(FactureService.getInstance().GetAllfactures().toString());
          Button delete =new Button("delete")  ;
          delete.addActionListener(e-> PaiementService.getInstance().deletePaiement(c.getId()));
           add(delete);
                  
           Button edit =new Button("edit")  ;
           edit.addActionListener(e-> PaiementService.getInstance().editPaiement(c.getId(),username.getText(),NumCheque.getText(),PaiementType.getText(),Reference.getText(),Rib.getText()));
          add(edit);
           
                
       // System.out.print(FactureService.getInstance().GetAllfactures().toString());
        }  
        
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e->previous.showBack()); 
          
}
     void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        
    }
}
