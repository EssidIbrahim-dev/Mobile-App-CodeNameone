/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.services.ServiceProduct;
import java.io.IOException;
import java.util.ArrayList;
/**
 * GUI builder created Form
 *
 * @author Ibrahim
 */
public abstract class SideMenuTransportForm extends com.codename1.ui.Form {

  Container sidemenuTop ;
    public SideMenuTransportForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuTransportForm(String title) {
        super(title);
    }

    public SideMenuTransportForm() {
    }
    public void setupSideMenu(Resources res) {
          getToolbar().addMaterialCommandToSideMenu("Accueil", FontImage.MATERIAL_HOME, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
        Resources theme=null;
        new ListVehicule(theme).show();
        
                   }
        });
             getToolbar().addMaterialCommandToSideMenu("Ajouter Vehicule", FontImage.MATERIAL_HOME, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
        Resources theme=null;
        new NewVehicule(theme).show();
        
                   }
        });
      getToolbar().addMaterialCommandToSideMenu("  Update Account", FontImage.MATERIAL_SETTINGS,  e -> new UpdateaccForm(res).show());
       
        getToolbar().addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP,e -> new LoginForm(res).show());  
    }

    public SideMenuTransportForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
        protected abstract void showOtherForm(Resources res);
     protected abstract void showOtherForm1(Resources res);
      protected abstract void showOtherForm2(Resources res);
       protected abstract void showOtherForm3(Resources res);
    
////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("SideMenuTransportForm");
        setName("SideMenuTransportForm");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
