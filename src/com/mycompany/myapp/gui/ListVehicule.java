/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.VehiculeService;

/**
 *
 * @author FIRAS
 */
public class ListVehicule extends SideMenuTransportForm{
        Container titleCmp=null;
        

    public ListVehicule(Resources res){
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
        
        SpanLabel sp=new SpanLabel();
        sp.setText(VehiculeService.getInstance().getAllVehicule().toString());
        System.out.println(sp);
        //sp.setText("aaaa");
         
       add(sp);
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
