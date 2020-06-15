/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Fournisseur;
import com.mycompany.myapp.entities.MatrielMag;
import com.mycompany.myapp.entities.Reserver;
import com.mycompany.myapp.entities.Staff;
import com.mycompany.myapp.services.ServiceFournisseur;
import com.mycompany.myapp.services.ServiceMatr;
import com.mycompany.myapp.services.ServiceRes;
import com.mycompany.myapp.services.ServiceStaff;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuGestionnaireForm extends Form {
Container sidemenuTop ;
    public SideMenuGestionnaireForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuGestionnaireForm(String title) {
        super(title);
    }

    public SideMenuGestionnaireForm() {
    }

    public SideMenuGestionnaireForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) {
    
            
        
        Label profilePicLabel = new Label(ProfileGestionnaireForm.ch,  "SideMenuTitle");
    
         sidemenuTop = BorderLayout.center(profilePicLabel);
        
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Dashboard", FontImage.MATERIAL_DASHBOARD,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
               Resources res=null;
               new StatsAchat(res).show();
            }
        });
        getToolbar().addMaterialCommandToSideMenu("Fournisseurs", FontImage.MATERIAL_FACE,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                               Resources res=null;

ArrayList<Fournisseur> fournisseurs=ServiceFournisseur.getInstance().getFournisseurs();

        ListFournisseur listFournisseur=new ListFournisseur(res,fournisseurs);
        listFournisseur.show();            }
        });
        getToolbar().addMaterialCommandToSideMenu("Staffs", FontImage.MATERIAL_FACE,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                               Resources res=null;

ArrayList<Staff> staffs=ServiceStaff.getInstance().getStaffs();

        ListStaff liststaff=new ListStaff(res,staffs);
        liststaff.show();       }
        });
             getToolbar().addMaterialCommandToSideMenu("Matriels", FontImage.MATERIAL_WATCH,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                               Resources res=null;
ArrayList<MatrielMag> matriels=ServiceMatr.getInstance().getMatriels();

        ListMatriel listmat=new ListMatriel(res,matriels);
        listmat.show();         
          }
        });
               getToolbar().addMaterialCommandToSideMenu("Reservations", FontImage.MATERIAL_BOOK,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                               Resources res=null;
ArrayList<Reserver> reservations=ServiceRes.getInstance().getReservs();
        ListReservation listres=new ListReservation(res,reservations);
        listres.show();         
          }
        });
             getToolbar().addMaterialCommandToSideMenu("Staff Stat", FontImage.MATERIAL_TRENDING_UP,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                              Resources res=null;
               new StatsStaff(res).show();
          }
        });
                 getToolbar().addMaterialCommandToSideMenu("Achat Stat", FontImage.MATERIAL_TRENDING_UP,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                              Resources res=null;
               new StatsAchat(res).show();
          }
        });
                     getToolbar().addMaterialCommandToSideMenu("List entrepot", FontImage.MATERIAL_TRENDING_UP,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                              new listEntrepot().start();
          }
        });
                     
                     getToolbar().addMaterialCommandToSideMenu("List product", FontImage.MATERIAL_TRENDING_UP,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                              new listProduct_1().start();
          }
        });
        getToolbar().addMaterialCommandToSideMenu("  Update Account", FontImage.MATERIAL_SETTINGS,  e -> new UpdateaccForm(res).show());
       
        getToolbar().addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP,e -> new LoginForm(res).show());  
    }
    
    protected abstract void showOtherForm(Resources res);
     protected abstract void showOtherForm1(Resources res);
      protected abstract void showOtherForm2(Resources res);
       protected abstract void showOtherForm3(Resources res);
}
