/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.MatrielMag;
import com.mycompany.myapp.entities.Staff;
import java.io.IOException;

/**
 *
 * @author HP
 */
public class ShowMatriel extends SideMenuGestionnaireForm{
    private MatrielMag mat;
    Container titleCmp=null;
 ImageViewer imgViewer;
 String URL="http://localhost/post/";
     EncodedImage ecoEncodedImage;
Image img;
    public ShowMatriel(Resources res,MatrielMag f) {
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
     
      Container c=new Container(new BoxLayout(BoxLayout.Y_AXIS));

      Container c1=new Container(new GridLayout(5, 4));
      Container c2=new Container(new FlowLayout(CENTER));
       try {
            ecoEncodedImage=EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            
        }
            System.out.println(""+f.getPhoto());
            img = URLImage.createToStorage(ecoEncodedImage,f.getPhoto() , URL +f.getPhoto(), URLImage.RESIZE_SCALE_TO_FILL);
            img.scaled(200, 200);
            Label lbl=new Label();
            ScaleImageLabel fillLabel = new ScaleImageLabel(img);
            fillLabel.setUIID("dd");
            fillLabel.setWidth(200);
            fillLabel.setHeight(200);
            fillLabel.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);


             lbl.setIcon(img);
            imgViewer = new ImageViewer(img);
            imgViewer.setWidth(this.getWidth());
           Container cc=new Container(new FlowLayout(CENTER));
           
            Label l1=new Label("Details");
           l1.setUIID("sp");
           cc.add(l1);
           cc.setUIID("cont"); 
       c1.add(new SpanLabel(""));
       c1.add(new SpanLabel(""));
       c1.add(new SpanLabel(""));
       c1.add(new SpanLabel(""));
       c1.add(new SpanLabel("Type"));
       c1.add(new SpanLabel(""));
       c1.add(new SpanLabel(""));
       c1.add(new SpanLabel(f.getType()));
       
       c1.add(new SpanLabel("Reference"));
       c1.add(new SpanLabel(""));
       c1.add(new SpanLabel(""));
       c1.add(new SpanLabel(f.getReference()));      
       c.add(fillLabel);
       c.add(cc);
       c.add(c1);
       add(c);
       setupSideMenu(res);
       this.mat=f;
       c1.setUIID("ccc");       
    }
    
    public ShowMatriel(com.codename1.ui.util.Resources resourceObjectInstance) {
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
   

