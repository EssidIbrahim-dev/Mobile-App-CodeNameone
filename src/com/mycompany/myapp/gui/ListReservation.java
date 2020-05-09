/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.Fournisseur;
import com.mycompany.myapp.entities.MatrielMag;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.entities.Reserver;
import com.mycompany.myapp.entities.Staff;
import java.util.ArrayList;
import com.mycompany.myapp.services.ServiceAchat;
import com.mycompany.myapp.services.ServiceFournisseur;
import com.mycompany.myapp.services.ServiceMatr;
import com.mycompany.myapp.services.ServiceProduct;
import com.mycompany.myapp.services.ServiceRes;
import com.mycompany.myapp.services.ServiceStaff;



/**
 * GUI builder created Form
 *
 * @author Ibrahim
 */
public class ListReservation extends SideMenuGestionnaireForm {
private ArrayList<Reserver> reservations;
    Container titleCmp=null;

    public ListReservation(Resources res,ArrayList<Reserver> reservations) {
        this.reservations=ServiceRes.getInstance().getReservs();
        Container c=new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
         
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
           TextField txtRecherche = new TextField("", "By Staff name", 15, TextArea.ANY);
           txtRecherche.setUIID("my");
                    Button rechercher=new Button("Search");

            rechercher.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                ArrayList<Reserver> rs=new ArrayList<>();
                String name=txtRecherche.getText().toString();
                ArrayList<Reserver> rsv=ServiceRes.getInstance().getReservs();
                if(name.length()==0)
                    {
                        rs=rsv;
                    }
                else{
                for( int i = 0; i < rsv.size(); i++ )
                     {
                    if(rsv.get(i).getStaff().equals(name))
                    {
                        rs.add(rsv.get(i));
                    }
                    
                    
                }
                }
                if(rs.size()==0) {
                        Dialog.show("search", " pas de reservation", "OK",null);
                    }
                ListReservation listRes=new ListReservation(res,rs);
        listRes.show();                
                              
            }
        });

         
        
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
       
       Container remainingTasks = BoxLayout.encloseX(
                        txtRecherche,
                        rechercher
                );
        remainingTasks.setUIID("mm1");  
       
        c.add(remainingTasks);
        
        
        if(reservations.isEmpty()){
            c.add(new SpanLabel("Pas de reservation"));
        }
        else{
             
            String nom="";
            String type="";
                    for(Reserver f: reservations){
                      Container c1=new Container(new GridLayout(2, 3));
                      c1.add(new Label("Staff_name"));
                      c1.add(new Label("Matriel_type"));
                      c1.add(new Label("Commande"));
                      c1.add(new SpanLabel(f.getStaff()));
                      c1.add(new SpanLabel(f.getMat()));
                              
                      Button details=new Button("SHOW");
                      details.addActionListener(new ActionListener() {

                          @Override
                          public void actionPerformed(ActionEvent evt) {
                             ShowReservation detailsF=new ShowReservation(res,f);
                              detailsF.show();
                          }
                      });
                      c1.setUIID("c12");
                      c1.add(details);
                      c.add(c1);

          }
        }
          
        
        add(c);
                setupSideMenu(res);

                }
    
    public ListReservation(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("ListFournisseur");
        setName("ListFournisseur");
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
