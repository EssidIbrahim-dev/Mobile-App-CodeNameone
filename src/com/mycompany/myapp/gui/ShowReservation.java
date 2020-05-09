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
import com.mycompany.myapp.entities.Reserver;
import com.mycompany.myapp.services.ServiceRes;

/**
 *
 * @author HP
 */
public class ShowReservation extends SideMenuGestionnaireForm{

    private Reserver reserv;
    Container titleCmp=null;

    public ShowReservation(Resources res,Reserver f) {
                      Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
         
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
         
         titleCmp= BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label(LoginForm.nom+" "+LoginForm.prenom, "Title"),
                                    new Label(ProfileGestionnaireForm.rl, "SubTitle")
                                )
                            )
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
      c1.add(new SpanLabel("Staff"));
      c1.add(new SpanLabel(f.getStaff()));
      c1.add(new SpanLabel("Mat_Type")); 
      c1.add(new SpanLabel(f.getMat()));
      c1.add(new SpanLabel("DateRes"));
      c1.add(new SpanLabel(f.getDateres()));
      c1.add(new SpanLabel("Dateret"));
      c1.add(new SpanLabel(f.getDateret()));
      c1.setUIID("ccc");
      Button bot=new Button("Delete");
      bot.setUIID("update");
      bot.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
                   ServiceRes.getInstance().deleteRes(f.getId());
               }
           });
      c1.add(bot);
      c.add(c1);
      add(c);
      setupSideMenu(res);
       this.reserv=f;
       
    }
    
    public ShowReservation(com.codename1.ui.util.Resources resourceObjectInstance) {
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
   

