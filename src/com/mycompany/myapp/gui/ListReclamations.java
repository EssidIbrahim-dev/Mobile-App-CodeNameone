/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceProduct;
import com.mycompany.myapp.services.ServiceReclamation;
import java.io.IOException;
import java.util.ArrayList;

/**
 * GUI builder created Form
 *
 * @author Ibrahim
 */
public class ListReclamations extends SideMenuClientForm {
        Container titleCmp=null;
        ArrayList<Reclamation> reclamations;
        String URL="http://localhost/pidev/web/uploads/post/";
    EncodedImage ecoEncodedImage;
    ImageViewer imgViewer;
    Image img;


    public ListReclamations(Resources res) {
           Container c=new Container(new BoxLayout(BoxLayout.Y_AXIS));
           Container c1=new Container(new BoxLayout(BoxLayout.Y_AXIS));

  Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
         
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
         Button rechercher=new Button("Ajouter");

          rechercher.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
               AjouterR ajouterR=new AjouterR(res);
               ajouterR.show();
                //System.out.println(products);
               
                
            }
        });
        /*  Container searchC=new Container(new BoxLayout(BoxLayout.X_AXIS));
          searchC.add(txtRecherche);
          searchC.add(rechercher);*/
           Container remainingTasks = BoxLayout.encloseX(
                        new SpanLabel("Ajouter Reclamation"),
                   rechercher
                );
        remainingTasks.setUIID("RemainingTasks");
       /* Container completedTasks = BoxLayout.encloseX(
                      searchC
        );*/
      //  completedTasks.setUIID("CompletedTasks");
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
        
           try {
            ecoEncodedImage=EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            
        }
         for(Reclamation r:ServiceReclamation.getInstance().getReclamation()){
             Container c2=new Container(new BoxLayout(BoxLayout.Y_AXIS));
             c2.setUIID("c2");
             c2.getAllStyles().setBgTransparency(255);

                   c2.getStyle().setBgColor(ColorUtil.WHITE);
                   c2.getStyle().setMarginLeft(50);
                   c2.getStyle().setMarginRight(50);

             Container c3=new Container(new GridLayout(1,3));
             Container c4=new Container(new BoxLayout(BoxLayout.Y_AXIS));
             for(Product p:ServiceProduct.getInstance().getProducts(r.getProduct())){
                    img = URLImage.createToStorage(ecoEncodedImage,p.getPhoto() , URL +p.getPhoto(), URLImage.RESIZE_SCALE_TO_FILL);

             }
             Button details=new Button("details");
             details.addActionListener(new ActionListener() {

                 @Override
                 public void actionPerformed(ActionEvent evt) {
                     DetailsR detailsR=new DetailsR(res,r);
                     detailsR.show();
                 }
             });
             c3.add(img);
             c3.add(r.getPriority());
             c3.add(details);
             
             SpanLabel sp=new SpanLabel(r.getContenu());
             c4.add(sp);
             c2.add(c3);
             c2.add(c4);
             c1.add(c2);

             


         }
         add(c1);
         setupSideMenu(res);

        // reclamations=ServiceReclamation.getInstance().getReclamation();
      //   System.out.println(reclamations);
    }
    
    public ListReclamations() {
    }

////////////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("ListReclamations");
        setName("ListReclamations");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileClientForm(res).show();
    }

    @Override
    protected void showOtherForm1(Resources res) {
        new ProfileClientForm(res).show();
    }

    @Override
    protected void showOtherForm2(Resources res) {
        new ProfileClientForm(res).show();
    }

    @Override
    protected void showOtherForm3(Resources res) {
        new ProfileClientForm(res).show();
    }
}
