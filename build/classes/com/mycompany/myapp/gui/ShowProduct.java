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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Product;
import java.util.ArrayList;
import com.mycompany.myapp.services.ServiceProduct;
/**
 * GUI builder created Form
 *
 * @author Ibrahim
 */
public class ShowProduct extends SideMenuClientForm {
    private Product p;
    String URL="http://localhost/pi3/pidev/web/uploads/post/";
    EncodedImage ecoEncodedImage;
    ImageViewer imgViewer;
    Image img;
    Container titleCmp=null;

    

    public ShowProduct(Resources res,Product p) {
        this.p=p;
        /* Toolbar cmdToolbar=null;
        cmdToolbar=this.getToolbar();
        cmdToolbar.addMaterialCommandToLeftBar("Back",FontImage.MATERIAL_KEYBOARD_ARROW_LEFT, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                ArrayList<Product> products=ServiceProduct.getInstance().getAllProducts();
                              /*ListProduct listProduct=new ListProduct(products);
                              listProduct.show();*/
                /*}
            });*/
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
        
        Container c = new Container(new GridLayout(1, 2));
           c.getAllStyles().setBgTransparency(255);

                   c.getStyle().setBgColor(ColorUtil.WHITE);
                   c.getStyle().setMarginTop(30);
                   c.getStyle().setMarginLeft(50);
                   c.getStyle().setMarginRight(50);

       // setTitle("welcome "+p.getProduct_name());
          /*Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                   Container c2=new Container(new BoxLayout(BoxLayout.X_AXIS));*/

          /*   img = URLImage.createToStorage(ecoEncodedImage,p.getPhoto() , URL +p.getPhoto(), URLImage.RESIZE_SCALE);
            imgViewer = new ImageViewer(img);
         SpanLabel sp = new SpanLabel();
         sp.setText(p.getProduct_name()+" "+p.getProduct_type());
          c1.add(imgViewer);
        c1.add(sp);
        c.add(c1);
        add(c);*/
        
        for(Product p1:ServiceProduct.getInstance().getProducts(p.getId_product())){
           
                   Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                   Container c2=new Container(new BoxLayout(BoxLayout.X_AXIS));
                

             img = URLImage.createToStorage(ecoEncodedImage,p.getPhoto() , URL +p.getPhoto(), URLImage.RESIZE_SCALE);
            imgViewer = new ImageViewer(img);
         SpanLabel sp = new SpanLabel();
         SpanLabel sp1 = new SpanLabel();
         SpanLabel sp2 = new SpanLabel();
         SpanLabel sp3 = new SpanLabel();
         SpanLabel sp4 = new SpanLabel();
         SpanLabel sp5 = new SpanLabel();
         sp.setText(p1.getProduct_name()+" "+p1.getProduct_type());
         sp1.setText("Marque :"+p1.getMarque());
         sp2.setText("Prix HT :"+p1.getPriceHT());
         sp3.setText("Prix TTC :"+p1.getPriceTTC());
         sp4.setText("TVA :"+p1.getTVA());
         sp5.setText("Weight :"+p1.getWeight());
         

         c2.add(imgViewer);
        c1.add(sp); c1.add(sp1);c1.add(sp2);c1.add(sp3);c1.add(sp4);c1.add(sp5);




        c.add(c2);
        c.add(c1);
        }
        add(c);
        setupSideMenu(res);

                

    }
    
    public ShowProduct(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("ShowProduct");
        setName("ShowProduct");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!

    @Override
    protected void showOtherForm(Resources res) {
        new StatsStaff(res).show();
    }

    @Override
    protected void showOtherForm1(Resources res) {
        new StatsStaff(res).show();
    }

    @Override
    protected void showOtherForm2(Resources res) {
        new StatsStaff(res).show();
    }

    @Override
    protected void showOtherForm3(Resources res) {
        new StatsStaff(res).show();
    }
}
