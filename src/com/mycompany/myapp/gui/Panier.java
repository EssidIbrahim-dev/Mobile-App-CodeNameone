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
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Achat;
import com.mycompany.myapp.entities.ProdAchat;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.entities.ProductAchat;
import java.io.IOException;
import java.util.ArrayList;
import com.mycompany.myapp.services.ServiceAchat;
import com.mycompany.myapp.services.ServiceProduct;

/**
 * GUI builder created Form
 *
 * @author Ibrahim
 */
public class Panier extends SideMenuClientForm {

    private String username;
    String URL = "http://localhost/pidev/web/uploads/post/";
    EncodedImage ecoEncodedImage;
    ImageViewer imgViewer;
    Image img;
    Container titleCmp = null;

    public Panier(Resources res, String username) {
        /* Toolbar pannierToolbar=null;
         pannierToolbar=this.getToolbar();
         pannierToolbar.addMaterialCommandToLeftBar("Back",FontImage.MATERIAL_KEYBOARD_ARROW_LEFT, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
         ArrayList<Product> products=ServiceProduct.getInstance().getAllProducts();
         /*    ListProduct listProduct=new ListProduct(products);
         listProduct.show();*/
        /* }
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
        titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label(LoginForm.nom + " " + LoginForm.prenom, "Title"),
                                new Label("Client", "SubTitle")
                        )
                ),
                GridLayout.encloseIn(2, remainingTasks, completedTasks)
        );
        tb.setTitleComponent(titleCmp);
        try {
            ecoEncodedImage = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {

        }

        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c.getStyle().setBgColor(0xff0000);

        this.username = username;
        double prixTotal = 0;
        System.out.println("achat");
        for (ProdAchat p : ServiceAchat.getInstance().getProdAchat("bra19")) {
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container c2 = new Container(new GridLayout(1, 3));
            Container c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            c2.getAllStyles().setBgTransparency(255);

            c2.getStyle().setBgColor(ColorUtil.WHITE);
            c2.getStyle().setMarginTop(30);
            c2.getStyle().setMarginLeft(50);
            c2.getStyle().setMarginRight(50);

            SpanLabel spanLabel = new SpanLabel();
            SpanLabel spanLabel1 = new SpanLabel();
            SpanLabel spanLabel2 = new SpanLabel();

            String ch = "";
            System.out.println("eee");
            double prix = 0;
            for (Product p1 : ServiceProduct.getInstance().getProducts(p.getProduct())) {
                ch += p1.getProduct_name();
                prix = p1.getPriceTTC();
                img = URLImage.createToStorage(ecoEncodedImage, p1.getPhoto(), URL + p1.getPhoto(), URLImage.RESIZE_SCALE);
                imgViewer = new ImageViewer(img);

            }
            Label l = new Label(ch);
            l.setUIID("l");
            l.getStyle().setFgColor(ColorUtil.BLACK);
            spanLabel.setText(ch);
            spanLabel1.setText(ch);
            spanLabel2.setText(ch);
            c2.add(imgViewer);
            c3.add(new SpanLabel("Product name"));
            c3.add(l);
            c3.add(new SpanLabel("Prix"));
            double d = (double) Math.round(p.getQte() * prix * 100) / 100;
            prixTotal = prixTotal + d;
            SpanLabel sp = new SpanLabel(Double.toString(d));
            sp.setUIID("sp");
            sp.getStyle().setFgColor(ColorUtil.BLACK);
            c3.add(sp);
            c2.add("");
            c2.add(c3);
            c2.setLeadComponent(l);

            l.addPointerPressedListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    Dialog d = null;
                    boolean b = d.show("Panier", "Vous voulez ajouter ou supprimer", "Edit", "Delete");
                    if (b) {
                        Edit edit = new Edit(res, p, username);
                        edit.show();
                    } else {
                        ServiceAchat.getInstance().deleteAchatProduct(p.getId(), username);
                        Panier panier = new Panier(res, username);
                        panier.show();

                    }
                }
            });

            c.add(c2);
        }
        Button btnConfirmer = new Button("Confirmer");
        btnConfirmer.setUIID("btnConfirmer");
        btnConfirmer.getStyle().setBgTransparency(255);
        btnConfirmer.getStyle().setBgColor(ColorUtil.rgb(18, 97, 160));
        btnConfirmer.getStyle().setFgColor(ColorUtil.WHITE);
        btnConfirmer.getStyle().setMarginTop(60);
        btnConfirmer.getStyle().setMarginLeft(60);
        btnConfirmer.getStyle().setMarginRight(60);

        btnConfirmer.getStyle().setPaddingLeft(20);
        btnConfirmer.getStyle().setPaddingTop(3);
        btnConfirmer.getStyle().setPaddingBottom(3);

        btnConfirmer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                ArrayList<ProdAchat> prodAchats = ServiceAchat.getInstance().getProdAchat(LoginForm.username);
                if (prodAchats.isEmpty()) {
                    System.out.println("Pas de confirmation");
                    Dialog d = new Dialog("Confirmation");
                    TextArea popupBody = new TextArea("tu na pas de produit dans le panier", 3, 10);
                    popupBody.setUIID("PopupBody");
                    popupBody.setEditable(false);
                    d.setLayout(new BorderLayout());
                    d.add(BorderLayout.CENTER, popupBody);
                    d.showPopupDialog(btnConfirmer);
                } else {
                    ServiceAchat.getInstance().Confirmer(LoginForm.username);
                    System.out.println("Achat confirmer");
                    Dialog d = new Dialog("Confirmation");
                    TextArea popupBody = new TextArea("Achat confirmer mail a envoyer", 3, 10);
                    popupBody.setUIID("PopupBody");
                    popupBody.setEditable(false);
                    d.setLayout(new BorderLayout());
                    d.add(BorderLayout.CENTER, popupBody);
                    d.showPopupDialog(btnConfirmer);
                    ArrayList<Product> products = ServiceProduct.getInstance().getAllProducts();
                    ListProduct listProduct = new ListProduct(res, products);
                    listProduct.show();
                }

                /*  ListProduct listProduct=new ListProduct(products);
                 listProduct.show();*/
            }
        });
        Achat a = new Achat();
        for (Achat a1 : ServiceAchat.getInstance().getAchat(username)) {
            a = a1;
        }
        Container c4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c5 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c5.add(new SpanLabel("Prix totale"));
        c5.add(new SpanLabel(Double.toString(prixTotal)));
        c5.getAllStyles().setBgTransparency(255);

        c5.getStyle().setBgColor(ColorUtil.WHITE);
        c5.getStyle().setMarginTop(30);
        c5.getStyle().setMarginLeft(50);
        c5.getStyle().setMarginRight(50);
      // c4.add(btnConfirmer);
        // c4.add(c5);

        c.add(c5);

        c.add(btnConfirmer);

        add(c);

        setupSideMenu(res);

    }

    public Panier(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

////////////////////////////////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setInlineStylesTheme(resourceObjectInstance);
        setTitle("Panier");
        setName("Panier");
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
