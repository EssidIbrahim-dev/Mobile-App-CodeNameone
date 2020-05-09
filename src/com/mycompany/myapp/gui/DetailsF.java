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
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Fournisseur;
import com.mycompany.myapp.entities.Product;
import java.util.ArrayList;
import com.mycompany.myapp.services.ServiceFournisseur;
import com.mycompany.myapp.services.ServiceProduct;

/**
 * GUI builder created Form
 *
 * @author Ibrahim
 */
public class DetailsF extends SideMenuGestionnaireForm {
    private Fournisseur f;
    Container titleCmp=null;

    public DetailsF(Resources res,Fournisseur f) {
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
         titleCmp= BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label(LoginForm.nom+" "+LoginForm.prenom, "Title"),
                                    new Label("Client", "SubTitle")
                                )
                            ),
                         GridLayout.encloseIn(2, remainingTasks, completedTasks)
                );
         tb.setTitleComponent(titleCmp);
     /*   Toolbar cmdToolbar=null;
        cmdToolbar=this.getToolbar();
        cmdToolbar.addMaterialCommandToLeftBar("Back",FontImage.MATERIAL_KEYBOARD_ARROW_LEFT, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
               ArrayList<Fournisseur> fournisseurs=ServiceFournisseur.getInstance().getFournisseurs();

        ListFournisseur listFournisseur=new ListFournisseur(fournisseurs);
        listFournisseur.show();
                }
            });*/
      Container c=new Container(new BoxLayout(BoxLayout.Y_AXIS));

      Container c1=new Container(new GridLayout(6, 2));
      c1.add(new SpanLabel("ID"));
      c1.add(new SpanLabel(Integer.toString(f.getId())));
      c1.add(new SpanLabel("FirstName"));
      c1.add(new SpanLabel(f.getFirstname()));
      c1.add(new SpanLabel("LastName"));
      c1.add(new SpanLabel(f.getLastname()));
      c1.add(new SpanLabel("Address"));
      c1.add(new SpanLabel(f.getAddress()));
      c1.add(new SpanLabel("Email"));
      c1.add(new SpanLabel(f.getEmail()));
      c1.add(new SpanLabel("Phone"));
      c1.add(new SpanLabel(f.getPhoneNumber()));
      c.add(c1);
      add(c);
      setupSideMenu(res);












       this.f=f;
       
    }
    
    public DetailsF(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("DetailsF");
        setName("DetailsF");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileGestionnaireForm(res).show();
    }

    @Override
    protected void showOtherForm1(Resources res) {
        new ProfileGestionnaireForm(res).show();
    }

    @Override
    protected void showOtherForm2(Resources res) {
        new ProfileGestionnaireForm(res).show();
    }

    @Override
    protected void showOtherForm3(Resources res) {
        new UpdateaccForm(res).show();
    }
}
