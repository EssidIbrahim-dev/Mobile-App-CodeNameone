/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.db.Database;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Vehicule;
import com.mycompany.myapp.services.VehiculeService;

/**
 *
 * @author FIRAS
 */
public class NewVehicule extends SideMenuTransportForm{
       private Database db;
    private Resources theme;
    Container titleCmp=null;

    
    public NewVehicule(Resources res){
         theme = UIManager.initFirstTheme("/theme");
          Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Container remainingTasks = BoxLayout.encloseY(
                new Label("12", "CenterTitle"),
                new Label("users", "CenterSubTitle")
        );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                new Label("32", "CenterTitle"),
                new Label("employees", "CenterSubTitle")
        );
        completedTasks.setUIID("CompletedTasks");
        titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label(LoginForm.nom + " " + LoginForm.prenom, "Title"),
                                new Label("Agent Transport", "SubTitle")
                        )
                ),
                GridLayout.encloseIn(2, remainingTasks, completedTasks)
        );
        tb.setTitleComponent(titleCmp);
         TextField matricule = new TextField("","matricule");
        TextField weight= new TextField("", "weight");
        TextField etat= new TextField("", "etat");
        TextField marque= new TextField("", "marque");
        TextField description= new TextField("", "description");
        Button btn = new Button("Ajouter");
        
        
        btn.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 Vehicule v=new Vehicule(matricule.getText(),Float.parseFloat(weight.getText()),etat.getText(),marque.getText(),description.getText());
                 new VehiculeService().addVehicule(v);
        
        }
         });
       add(matricule).add(weight).add(etat).add(marque).add(description).add(btn);
       setupSideMenu(res);

    }

    @Override
    protected void showOtherForm(Resources res) {
         new ProfileTransportForm(res).show();
    }

    @Override
    protected void showOtherForm1(Resources res) {
         new ProfileTransportForm(res).show();
    }

    @Override
    protected void showOtherForm2(Resources res) {
         new ProfileTransportForm(res).show();
    }

    @Override
    protected void showOtherForm3(Resources res) {
         new ProfileTransportForm(res).show();
    }
    
}
