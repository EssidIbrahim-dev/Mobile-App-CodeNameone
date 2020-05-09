/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
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
import com.codename1.ui.list.ListCellRenderer;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Achat;
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
public class ListProduct extends SideMenuClientForm {
    String URL="http://localhost/pi3/pidev/web/uploads/post/";
    EncodedImage ecoEncodedImage;
    ImageViewer imgViewer;
    Image img;
    Achat achat;
    ArrayList<Product> products;
    Container titleCmp=null;


 



    
    

    public ListProduct(Resources res,ArrayList<Product> products) {
                super(BoxLayout.y());
              
       String username=LoginForm.username;
        System.out.println("Login "+username);
        this.products=products;
         Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
         
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
         Button rechercher=new Button("Search");
                 TextField txtRecherche = new TextField("", "By product", 15, TextArea.ANY);

          rechercher.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                products.clear();

                String name=txtRecherche.getText().toString();
               ArrayList<Product> products1=ServiceProduct.getInstance().findProducts(name);
               ListProduct listProduct=new ListProduct(res,products1);
               listProduct.show();
                //System.out.println(products);
               
                
            }
        });
        /*  Container searchC=new Container(new BoxLayout(BoxLayout.X_AXIS));
          searchC.add(txtRecherche);
          searchC.add(rechercher);*/
           Container remainingTasks = BoxLayout.encloseX(
                        txtRecherche,
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
        
        

     /*   Toolbar allProductsToolbar=null;
        allProductsToolbar=this.getToolbar();

        allProductsToolbar.addMaterialCommandToOverflowMenu("Panier",FontImage.MATERIAL_ADD_SHOPPING_CART, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Panier panier=new Panier("bra19");

                panier.show();
                
                
            }
        });
        allProductsToolbar.addMaterialCommandToOverflowMenu("Commandes",FontImage.MATERIAL_PAYMENT, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Commandes commandes=new Commandes("bra19");

                commandes.show();
                
                
            }
        });*/
         try {
            ecoEncodedImage=EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            
        }
        
         

       Container c=new Container(new BoxLayout(BoxLayout.Y_AXIS));
       


       Container c6=new Container(new BoxLayout(BoxLayout.X_AXIS));


                  // Button rechercher=new Button(new Image(fillButton));
           //      allProductsToolbar.addComponent(BorderLayout.CENTER,txtRecherche); 
            //    allProductsToolbar.addComponent(BorderLayout.EAST,fillButton);
           /*     allProductsToolbar.addMaterialCommandToLeftSideMenu("Accueil",FontImage.MATERIAL_HOME,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                ArrayList<Product> products1=ServiceProduct.getInstance().getAllProducts();
               ListProduct listProduct=new ListProduct(products1);
               listProduct.show();
            }
        });
                    allProductsToolbar.addMaterialCommandToLeftSideMenu("Mon panier",FontImage.MATERIAL_SHOP,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
               Panier panier=new Panier("bra19");
               panier.show();
            }
        });
                     allProductsToolbar.addMaterialCommandToLeftSideMenu("Mes commandes",FontImage.MATERIAL_HOME,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
               Commandes commandes=new Commandes("bra19");
               commandes.show();
            }
        });
                     allProductsToolbar.addMaterialCommandToLeftSideMenu("Deconnexion",FontImage.MATERIAL_HOME,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
               
            }
        });*/



                   //c6.add(txtRecherche);
                  // c6.add(rechercher);
                   //c.add(c6);
/*allProductsToolbar.addSearchCommand(e -> {
     String text = (String)e.getSource();

    if(!text.equals("")){
    System.out.println(text);
    this.products=ServiceProduct.getInstance().findProducts(text);
        System.out.println(this.products);
    }


});*/
                   
   /*     fillButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                products.clear();
                String name=txtRecherche.getText().toString();
               ArrayList<Product> products1=ServiceProduct.getInstance().findProducts(name);
               ListProduct listProduct=new ListProduct(products1);
               listProduct.show();
                //System.out.println(products);
               
                
            }
        });*/
        for(Achat a:ServiceAchat.getInstance().getAchat(LoginForm.username)){
            achat=a;
            
        }
        System.out.println(achat.getClient_address());
       

        for(Product p:products){
            System.out.println(p);
                   Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                   Container c2=new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                      c2.setUIID("c2");
                   c2.getStyle().setBgColor(ColorUtil.WHITE);

                   Container c4=new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                                         c4.setUIID("c4");
                   c4.getStyle().setBgColor(ColorUtil.WHITE);

                   Container c5=new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                                         c5.setUIID("c5");
                   c5.getStyle().setBgColor(ColorUtil.WHITE);



                   Container c3=new Container(new GridLayout(1,3));
                   c3.setUIID("c3");
                   
                   


                   

            
                   img = URLImage.createToStorage(ecoEncodedImage,p.getPhoto() , URL +p.getPhoto(), URLImage.RESIZE_SCALE_TO_FILL);
                 //  c3.getStyle().setBgImage(img);
                   c3.getAllStyles().setBgTransparency(255);

                   c3.getStyle().setBgColor(ColorUtil.WHITE);
                   c3.getStyle().setMarginTop(30);
                   c3.getStyle().setMarginLeft(35);
                   c3.getStyle().setMarginRight(35);
                   //c3.getStyle().set
             img.scaled(100, 100);
             Label lbl=new Label();
            ScaleImageLabel fillLabel = new ScaleImageLabel(img);
            fillLabel.setUIID("filllabel");
            fillLabel.getStyle().setBgColor(ColorUtil.WHITE);
            
            fillLabel.setWidth(100);
            fillLabel.setHeight(100);
            fillLabel.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);


             lbl.setIcon(img);
            imgViewer = new ImageViewer(img);
            imgViewer.setWidth(this.getWidth());
         SpanLabel sp = new SpanLabel();
         
         SpanLabel sp1 = new SpanLabel();
          sp1.setUIID("sp1");
         sp1.setText(Double.toString(p.getPriceHT())+" Dt");
         c5.add(sp);
         c5.add(sp1);

         sp.setText(p.getProduct_name());
         Button btnPanier = new Button("Panier");
         
Style s = UIManager.getInstance().getComponentStyle("Button");
Style s1 = UIManager.getInstance().getComponentStyle("Button");



 int size = Display.getInstance().convertToPixels(0.42f);
 
s.setFgColor(ColorUtil.rgb(72,99,160));
s.setMarginTop(0);
s.setAlignment(0);
s1.setFgColor(ColorUtil.rgb(161,134,72));
s1.setMarginTop(0);
s1.setAlignment(0);

Image icon = FontImage.createMaterial(FontImage.MATERIAL_ADD_SHOPPING_CART, s,size);
Image icon1 = FontImage.createMaterial(FontImage.MATERIAL_VISIBILITY, s1,size);



ScaleImageButton fillButton = new ScaleImageButton(icon);
ScaleImageButton fillButton1 = new ScaleImageButton(icon1);
                  fillButton.getStyle().setBgTransparency(255);

         fillButton.setUIID("panierbtn");
                  fillButton.getStyle().setFgColor(ColorUtil.rgb(72,99,160));

                  
         
         fillButton.addActionListener(new ActionListener() {

                       @Override
                       public void actionPerformed(ActionEvent evt) {
                           ServiceAchat.getInstance().addAchatProduct(achat.getId_achat(),p.getId_product());
                           System.out.println("Prod achat ajouter au panier");
                             Dialog d = new Dialog("Produit");
                             TextArea popupBody = new TextArea("Ajouter au panier", 3, 10);
                              popupBody.setUIID("PopupBody");
                              popupBody.setEditable(false);
                              d.setLayout(new BorderLayout());
                              d.add(BorderLayout.CENTER, popupBody);
                              d.showPopupDialog(fillButton);
                       }
                   });
         Button btnShow = new Button("Show");
                  fillButton1.getStyle().setBgTransparency(255);

         fillButton1.setUIID("btnShow");
         fillButton1.getStyle().setFgColor(ColorUtil.rgb(161,134,72));


         fillButton1.addActionListener(new ActionListener() {

                       @Override
                       public void actionPerformed(ActionEvent evt) {
                           ShowProduct showProduct=new ShowProduct(res,p);
                           showProduct.show();
                       }
                   });
         c4.add(fillLabel);
     
         c2.add(fillButton);
         c2.add(fillButton1);
         c3.add(c4);
         c3.add(c5);
         c3.add(c2);
         
         //c1.add(lbl);
        // c1.add(imgViewer);
       
        c.add(c3);
        }
         add(c);
       setupSideMenu(res);

        /*  add(BorderLayout.CENTER, 
                c);*/
        
    }
    
    public ListProduct(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("ListProduct");
        setName("ListProduct");
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
