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
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.Product;
import java.io.IOException;
import java.util.ArrayList;
import com.mycompany.myapp.services.ServiceAchat;
import com.mycompany.myapp.services.ServiceProduct;

/**
 * GUI builder created Form
 *
 * @author Ibrahim
 */
public class Commandes extends SideMenuClientForm {
    private String username;
    String URL="http://localhost/pidev/web/uploads/post/";
    EncodedImage ecoEncodedImage;
    ImageViewer imgViewer;
    Image img;
    Container titleCmp=null;

    

    public Commandes(Resources res,String username) {
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
      /*  Toolbar cmdToolbar=null;
        cmdToolbar=this.getToolbar();
        cmdToolbar.addMaterialCommandToLeftBar("Back",FontImage.MATERIAL_KEYBOARD_ARROW_LEFT, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                ArrayList<Product> products=ServiceProduct.getInstance().getAllProducts();
                            /*  ListProduct listProduct=new ListProduct(products);
                              listProduct.show();*/
               /* }
            });*/
      //  setTitle("Mes Commandes");
        this.username=username;

         try {
            ecoEncodedImage=EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            
        }
          
                Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                c1.getStyle().setBgColor(0xff0000);


        for(Commande c:ServiceAchat.getInstance().getCommandes(username)){
            Container c2 = new Container(new GridLayout(1,3));
               c2.getAllStyles().setBgTransparency(255);

                   c2.getStyle().setBgColor(ColorUtil.WHITE);
                   c2.getStyle().setMarginTop(30);
                   c2.getStyle().setMarginLeft(50);
                   c2.getStyle().setMarginRight(50);
            String ch="";
           System.out.println("eee");
            double prix=0;

           for(Product p1:ServiceProduct.getInstance().getProducts(c.getProduct())){
               ch+=p1.getProduct_name();
               prix=p1.getPriceTTC();

               img = URLImage.createToStorage(ecoEncodedImage,p1.getPhoto() , URL +p1.getPhoto(), URLImage.RESIZE_SCALE);
            imgViewer = new ImageViewer(img);
               
           }
            SpanLabel spanLabel1=new SpanLabel();

            spanLabel1.setText(ch);

            c2.add(imgViewer);
            c2.add(new SpanLabel(""));
           Container c6 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           c6.add(new SpanLabel("Product"));
           c6.add(spanLabel1);
           c6.add(new SpanLabel("Quantite "+c.getQte()));
           c6.add(new SpanLabel("Prix"));
           c6.add(Double.toString(prix));
           c2.add(c6);



           c1.add(c2);


           // System.out.println(c);
        }
        add(c1);
       setupSideMenu(res);

        
    }
    
    public Commandes(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

////////////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("Commandes");
        setName("Commandes");
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
