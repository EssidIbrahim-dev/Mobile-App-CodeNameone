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
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.services.ServiceProduct;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuClientForm extends Form {
Container sidemenuTop ;
    public SideMenuClientForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuClientForm(String title) {
        super(title);
    }

    public SideMenuClientForm() {
    }

    public SideMenuClientForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) {
    
            
        
        Label profilePicLabel = new Label(ProfileClientForm.ch,  "SideMenuTitle");
    
         sidemenuTop = BorderLayout.center(profilePicLabel);
        
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("Mon Panier", FontImage.MATERIAL_SHOPPING_BASKET, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                 Resources theme=null;

                Panier panier=new Panier(theme,LoginForm.username);

                panier.show();
            }
        });
        getToolbar().addMaterialCommandToSideMenu("Accueil", FontImage.MATERIAL_HOME, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
 ArrayList<Product> products=ServiceProduct.getInstance().getAllProducts();
 System.out.println(products);
        Resources theme=null;
        
        ListProduct listProduct=new ListProduct(theme,products);
        
        listProduct.show();            }
        });
        getToolbar().addMaterialCommandToSideMenu("Mes Commandes", FontImage.MATERIAL_SHOPPING_CART,  new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Resources theme=null;

                Commandes commandes=new Commandes(theme,LoginForm.username);

                commandes.show();            }
        });
        getToolbar().addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_PEOPLE, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                                Resources theme=null;
                                new ProfileClientForm(theme).show();

                
            }
        });
          getToolbar().addMaterialCommandToSideMenu("Reclamations", FontImage.MATERIAL_PEOPLE, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                                Resources theme=null;
                                new ListReclamations(theme).show();

                
            }
        });

        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(res).show());

    }   
    
    protected abstract void showOtherForm(Resources res);
     protected abstract void showOtherForm1(Resources res);
      protected abstract void showOtherForm2(Resources res);
       protected abstract void showOtherForm3(Resources res);
}
