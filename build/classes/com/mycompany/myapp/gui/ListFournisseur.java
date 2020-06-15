/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ScaleImageButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
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
import com.mycompany.myapp.entities.Fournisseur;
import com.mycompany.myapp.entities.Product;
import java.util.ArrayList;
import com.mycompany.myapp.services.ServiceAchat;
import com.mycompany.myapp.services.ServiceFournisseur;



/**
 * GUI builder created Form
 *
 * @author Ibrahim
 */
public class ListFournisseur extends SideMenuGestionnaireForm {
private ArrayList<Fournisseur> fournisseurs;
    Container titleCmp=null;

    public ListFournisseur(Resources res,ArrayList<Fournisseur> fournisseurs) {
        this.fournisseurs=ServiceFournisseur.getInstance().getFournisseurs();
        Container c=new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
         
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
           TextField txtRecherche = new TextField("", "By Name", 15, TextArea.ANY);
                    Button rechercher=new Button("Search");

            rechercher.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                fournisseurs.clear();
                String name=txtRecherche.getText().toString();
                ArrayList<Fournisseur> fs=ServiceFournisseur.getInstance().getFournisseurByName(name);
                ListFournisseur listFournisseur=new ListFournisseur(res,fs);
        listFournisseur.show();
                //System.out.println(products);
               
                
            }
        });

         Container remainingTasks = BoxLayout.encloseX(
                        txtRecherche,
                        rechercher
                );
        remainingTasks.setUIID("RemainingTasks");
        
         titleCmp= BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label(LoginForm.nom+" "+LoginForm.prenom, "Title"),
                                    new Label("Client", "SubTitle")
                                )
                            ),
                         GridLayout.encloseIn(1, remainingTasks)
                );
         tb.setTitleComponent(titleCmp);

       /* Toolbar allProductsToolbar=null;
        allProductsToolbar=this.getToolbar();
Style s = UIManager.getInstance().getComponentStyle("Button");
Image icon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
ScaleImageButton fillButton = new ScaleImageButton(icon);
 allProductsToolbar.addComponent(BorderLayout.CENTER,txtRecherche); 
                allProductsToolbar.addComponent(BorderLayout.EAST,fillButton);
               
*/
        if(fournisseurs.isEmpty()){
            c.add(new SpanLabel("Pas de fournisseurs"));
        }
        else{
          for(Fournisseur f: fournisseurs){
                      Container c1=new Container(new GridLayout(1, 3));
                      c1.getAllStyles().setBgTransparency(255);

                   c1.getStyle().setBgColor(ColorUtil.WHITE);
                   c1.getStyle().setMarginTop(30);
                   c1.getStyle().setPaddingTop(50);
                   c1.getStyle().setPaddingBottom(50);
                   c1.getStyle().setMarginLeft(150);
                   c1.getStyle().setMarginRight(50);
                      c1.add(new SpanLabel(f.getFirstname()));
                      c1.add(new SpanLabel(f.getLastname()));
                      Button details=new Button("Details");
                      details.addActionListener(new ActionListener() {

                          @Override
                          public void actionPerformed(ActionEvent evt) {
                              DetailsF detailsF=new DetailsF(res,f);
                              detailsF.show();
                          }
                      });
                      c1.add(details);
                      c.add(c1);

          }
          
        }
        add(c);
                setupSideMenu(res);

                }
    
    public ListFournisseur(com.codename1.ui.util.Resources resourceObjectInstance) {
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
