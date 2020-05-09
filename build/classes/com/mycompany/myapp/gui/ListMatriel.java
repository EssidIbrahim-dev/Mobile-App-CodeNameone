/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.TextArea;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;

import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
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
import com.mycompany.myapp.entities.MatrielMag;
import com.mycompany.myapp.entities.Staff;
import java.util.ArrayList;
import com.mycompany.myapp.services.ServiceAchat;

import com.mycompany.myapp.services.ServiceMatr;
import com.mycompany.myapp.services.ServiceStaff;
import java.io.IOException;



/**
 * GUI builder created Form
 *
 * @author Ibrahim
 */
public class ListMatriel extends SideMenuGestionnaireForm {
private ArrayList<MatrielMag> matriels;
    Container titleCmp=null;
 ImageViewer imgViewer;
 String URL="http://localhost/post/";
     EncodedImage ecoEncodedImage;
Image img;
    public ListMatriel(Resources res,ArrayList<MatrielMag> matriels) {
        this.matriels=ServiceMatr.getInstance().getMatriels();
        Container c=new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
         
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
           TextField txtRecherche = new TextField("", "By Type", 15, TextArea.ANY);
           txtRecherche.setUIID("my");
                    Button rechercher=new Button("Search");

            rechercher.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                                 String type=txtRecherche.getText().toString();
                ArrayList<MatrielMag> mat=new ArrayList<>();
                ArrayList<MatrielMag> m=ServiceMatr.getInstance().getMatriels();
                if(type.length()==0)
                    {
                        mat=m;
                    }
                else{
                for( int i = 0; i < m.size(); i++ )
                     {
                         
                    if(m.get(i).getType().equals(type))
                    {
                        mat.add(m.get(i));
                    }
                    
                    
                }
                }
                if(mat.size()==0) {
                        Dialog.show("search", " pas de matriel", "OK",null);
                    }
                ListMatriel listMat=new ListMatriel(res,mat);
        listMat.show();
              
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
        remainingTasks.setUIID("mm");  
       
        c.add(remainingTasks);
        
        
        if(matriels.isEmpty()){
            c.add(new SpanLabel("Pas de matriel"));
        }
        else{
//             Container c11=new Container(new GridLayout(1, 3));
//            
//            c11.setUIID("c11");
//            c.add(c11);
          for(MatrielMag f: matriels){
                      Container c11=new Container(new GridLayout(2, 3));
               try {
            ecoEncodedImage=EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            
        }
               System.out.println(""+f.getPhoto());
                   img = URLImage.createToStorage(ecoEncodedImage,f.getPhoto() , URL +f.getPhoto(), URLImage.RESIZE_SCALE_TO_FILL);
             img.scaled(100, 100);
             Label lbl=new Label();
            ScaleImageLabel fillLabel = new ScaleImageLabel(img);
            
            fillLabel.setWidth(100);
            fillLabel.setHeight(100);
            fillLabel.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);


             lbl.setIcon(img);
            imgViewer = new ImageViewer(img);
            imgViewer.setWidth(this.getWidth());
                      c11.add(new Label("Image"));
            c11.add(new Label("Type"));
            c11.add(new Label("Commande"));
                      c11.add(fillLabel);
                      c11.add(new SpanLabel(f.getType()));
                      Button details=new Button("SHOW");
                      details.addActionListener(new ActionListener() {

                          @Override
                          public void actionPerformed(ActionEvent evt) {
                              ShowMatriel mat=new ShowMatriel(res,f);
                              mat.show();
                          }
                      });
                      c11.setUIID("c11");
                      c11.add(details);
                      c.add(c11);

          }
          
        }
        add(c);
                setupSideMenu(res);

                }
    
    public ListMatriel(com.codename1.ui.util.Resources resourceObjectInstance) {
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
