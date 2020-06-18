/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import entity.Facture;

import java.util.ArrayList;
import services.FactureService;


/**
 *
 * @author bazinfo
 */
public class ListFactures extends Form {

    public ListFactures(Form previous) {
        
        setTitle("Liste des factures"); 
         SpanLabel sp = new SpanLabel();
         sp.setText(FactureService.getInstance().GetAllfactures().toString());
          ArrayList<Facture> cmds = new ArrayList<>();
         cmds = FactureService.getInstance().GetAllfactures();
         System.out.print("2");
         System.out.print(cmds);
         
       for (Facture c : cmds){
           
            TextField username = new TextField(c.getReference());
            username.setUIID("TextFieldBlack");
            addStringValue("reference", username);
       
        TextField NumCheque = new TextField(c.getType_facture());
            NumCheque.setUIID("TextFieldBlack");
            addStringValue("type_facture", NumCheque);
            
         String a=String.valueOf(c.getTotalHT()); 
                TextField PaiementType = new TextField(a);
            PaiementType.setUIID("TextFieldBlack");
            addStringValue("totalHT", PaiementType);
            
        String b=String.valueOf(c.getTotalTTC());
               TextField Reference = new TextField(b);
            Reference.setUIID("TextFieldBlack");
            addStringValue("totalTTC", Reference);
        
           TextField Rib = new TextField(c.getEcheance());
            Rib.setUIID("TextFieldBlack");
            addStringValue("echeance", Rib);
       
             TextField datfact  = new TextField(c.getDateFact());
            datfact.setUIID("TextFieldBlack");
            addStringValue("datefact", datfact);
            
          String gf=String.valueOf(c.getAchat_id());
             TextField ach = new TextField(gf);
            ach.setUIID("TextFieldBlack");
            addStringValue("achat_id", ach);
            
             TextField clname = new TextField(c.getClientName());
            clname.setUIID("TextFieldBlack");
            addStringValue("client_name", clname);
            
            TextField cltype = new TextField(c.getClientType());
            cltype.setUIID("TextFieldBlack");
            addStringValue("client_type", cltype);
            
            TextField statfact = new TextField(c.getStatutFacture());
            statfact.setUIID("TextFieldBlack");
            addStringValue("statut_facture", statfact);
            
            //sp.setText(FactureService.getInstance().GetAllfactures().toString());
          Button delete =new Button("delete")  ;
          delete.addActionListener(e-> FactureService.getInstance().deletefacture(c.getId()));
           add(delete);
        
               
               
           Button edit =new Button("edit")  ;
           edit.addActionListener(e-> FactureService.getInstance().editFacture(c.getId(),username.getText(), Integer.parseInt(ach.getText()), clname.getText(),cltype.getText(),NumCheque.getText(),statfact.getText(), Float.parseFloat(PaiementType.getText()),  Float.parseFloat(Reference.getText()), Rib.getText()));
           add(edit);
        
                
       // System.out.print(FactureService.getInstance().GetAllfactures().toString());
        }  
     getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e->previous.showBack()); 
          
}
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        
    }
    
}
