/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;

/**
 * GUI builder created Form
 *
 * @author Ibrahim
 */
public abstract class SideMenuFinanceMenu extends com.codename1.ui.Form {
      Container sidemenuTop ;


      public SideMenuFinanceMenu(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuFinanceMenu(String title) {
        super(title);
    }

    public SideMenuFinanceMenu() {
    }
    public void setupSideMenu(Resources res) {
      getToolbar().addMaterialCommandToSideMenu("  Update Account", FontImage.MATERIAL_SETTINGS,  e -> new UpdateaccForm(res).show());
       
        getToolbar().addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP,e -> new LoginForm(res).show());  
    }

    public SideMenuFinanceMenu(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
        protected abstract void showOtherForm(Resources res);
     protected abstract void showOtherForm1(Resources res);
      protected abstract void showOtherForm2(Resources res);
       protected abstract void showOtherForm3(Resources res);
//-- DON'T EDIT ABOVE THIS LINE!!!
}
