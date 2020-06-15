/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Product1;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.InteractionDialog;
import com.codename1.components.MultiButton;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.SpanLabel;
import com.codename1.contacts.Contact;
import com.codename1.db.Database;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Product1;
import static com.mycompany.myapp.gui.listEntrepot.im;
import com.mycompany.myapp.services.ProductService;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class PDetail extends SideMenuGestionnaireForm  {

    private Form current;
    private Resources theme;
    String URL = "http://localhost/pidev/web/uploads/post/";
    String url = "http://localhost/images/";
    EncodedImage ecoEncodedImage;
    ImageViewer imgaViewer;
    Image imgaa;
    ImageViewer imgViewer;
    Image img;
    int i;
    ImageViewer imgaViewerdet;
    static String filePath;
    Container titleCmp=null;


    private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }

    public PDetail(Product1 c) {
        try {
            Form ProductDetail = new Form(new BoxLayout(BoxLayout.Y_AXIS));
            Toolbar tb = getToolbar();
                      tb=ProductDetail.getToolbar();

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
            setupSideMenu(theme);
            
            Image i = Image.createImage("/bes.jpg");
            ProductDetail.setBgImage(i);
            ProductDetail.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new listProduct_1().start());
            ProductDetail.getToolbar().getStyle().setBgColor(0x6ebab5);
            EncodedImage ecoEncodedImag = EncodedImage.create("/no.png");
            ecoEncodedImag.scale(800, 800);
            int idd = c.getId();
            Font largeBoldProportionalFont = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE);
            Font largePlainProportionalFont = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_LARGE);

            String mg = c.getPhoto();
            System.out.println(c.getPriceHT());
            Image ima = URLImage.createToStorage(ecoEncodedImag, c.getPhoto(), URL + mg, URLImage.RESIZE_SCALE);
            //imgaViewerdet = new ImageViewer(ima);
            Container detP = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container detPi = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container det = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container det2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container det3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container det4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container det5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container det6 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container det7 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container det8 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container det9 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container det10 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            int devise = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(devise, devise);
            EncodedImage encIm = EncodedImage.createFromImage(placeholder, false);
            // imgaViewerdet.setWidth(devise);
            // imgaViewerdet.setHeight(devise);
            int width = ima.getWidth() / 2;
            //Image capturedImage = Image.createImage(Capture.capturePhoto(width, -1));
            Image roundMask = Image.createImage(ima.getWidth(), ima.getHeight(), 0xff000000);
            Graphics gr = roundMask.getGraphics();
            gr.setColor(0xffffff);
            gr.fillArc(0, 0, ima.getWidth(), ima.getWidth(), 0, 360);
            Object mask = roundMask.createMask();
            ima = ima.applyMask(mask);

            Label l = new Label(ima);
            l.setIcon(ima.fill(800, 800));
            l.setWidth(1);
            l.setHeight(1);
            l.getAllStyles().setMargin(10, 0, 300, 300);
            l.getAllStyles().setPadding(0, 0, 0, 0);

            ProductDetail.add(l);
            //ProductDetail.addComponent(l);

            // ProductDetail.add(imgaViewerdet);
            Label n = new Label("jjj");
            n.setVisible(false);
            ProductDetail.add(n);
            ProductDetail.add(createForFont(largeBoldProportionalFont, "  Product name :   " + c.getProductName()));
            Label no = new Label("jjj");
            no.setVisible(false);
            ProductDetail.add(no);
            ProductDetail.add(createForFont(largeBoldProportionalFont, "  Marque            :   " + c.getTva()));
            Label npk = new Label("jjj");
            npk.setVisible(false);
            ProductDetail.add(npk);
            ProductDetail.add(createForFont(largeBoldProportionalFont, "  Product Type  :   " + c.getProductType()));
            Label nl = new Label("jjj");
            nl.setVisible(false);
            ProductDetail.add(nl);
            ProductDetail.add(createForFont(largeBoldProportionalFont, "  Reference       :   " + c.getReference()));
            Label np = new Label("jjj");
            np.setVisible(false);
            ProductDetail.add(np);
            ProductDetail.add(createForFont(largeBoldProportionalFont, "  PriceHT            :   " + String.valueOf(c.getPriceHT())));
            Label ni = new Label("jjj");
            ni.setVisible(false);
            ProductDetail.add(ni);
            ProductDetail.add(createForFont(largeBoldProportionalFont, "  PriceTTC          :   " + String.valueOf(c.getPriceTTC())));
            Label nv = new Label("jjj");
            nv.setVisible(false);
            ProductDetail.add(nv);
            ProductDetail.add(createForFont(largeBoldProportionalFont, "  TVA                   :   " + c.getTva()));
            Label npc = new Label("jjj");
            npc.setVisible(false);
            ProductDetail.add(npc);
            
            ProductDetail.add(createForFont(largeBoldProportionalFont, "  Weight             :   " + c.getWeight()));
            Label npp = new Label("jjj");
            npp.setVisible(false);
            ProductDetail.add(npp);           
            Picker pick = new Picker();
            pick.setStrings("Tweet", "Edit", "Delete");
            pick.setSelectedString("               Choose Option");

            pick.addActionListener((e) -> {
                String s = pick.getSelectedString().toLowerCase();
            });

            Button Edit = new Button("Edit Product");
            Button Delete = new Button("Delete Product");

            pick.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    switch (pick.getSelectedString()) {

                        case "Tweet":
                            pick.setText("Tweet");
                            break;

                        case "Edit":
                            pick.setText("Edit");
                            break;

                        case "Delete":
                            pick.setText("Delete");
                            break;

                    }
                    if (pick.getText() == "Edit") {
                        Form EditForm = new Form(new BoxLayout(BoxLayout.Y_AXIS));

                        EditForm.setTitle("   Edit Product");
                        Label LName = new Label("    Product name ");
                        TextField tfName = new TextField("");
                        tfName.getUnselectedStyle().setBorder(
                                RoundBorder.create().color(0xbbf0ed).rectangle(true));
                        tfName.setText(c.getProductName());
                        Label LType = new Label("    Product type ");
                        TextField tfType = new TextField("");
                        tfType.getUnselectedStyle().setBorder(
                                RoundBorder.create().color(0xbbf0ed).rectangle(true));
                        tfType.setText(c.getProductType());
                        Label LMarque = new Label("    Marque ");
                        TextField tfMarque = new TextField("");
                        tfMarque.getUnselectedStyle().setBorder(
                                RoundBorder.create().color(0xbbf0ed).rectangle(true));
                        tfMarque.setText(c.getMarque());
                        Label LPriceHt = new Label("    PriceHT ");
                        TextField tfPriceHT = new TextField("");
                        tfPriceHT.getUnselectedStyle().setBorder(
                                RoundBorder.create().color(0xbbf0ed).rectangle(true));
                        tfPriceHT.getUnselectedStyle().setBorder(
                                RoundBorder.create().color(0xbbf0ed).rectangle(true));
                        tfPriceHT.setText(String.valueOf(c.getPriceHT()));
                        Label LPriceTTc = new Label("    PriceTTC ");
                        TextField tfPriceTTC = new TextField("");
                        tfPriceTTC.getUnselectedStyle().setBorder(
                                RoundBorder.create().color(0xbbf0ed).rectangle(true));
                        tfPriceTTC.setText(String.valueOf(c.getPriceTTC()));
                        Label LTva = new Label("    TVA ");
                        TextField tfTva = new TextField("");
                        tfTva.getUnselectedStyle().setBorder(
                                RoundBorder.create().color(0xbbf0ed).rectangle(true));
                        tfPriceTTC.setText(String.valueOf(c.getPriceTTC()));
                        tfTva.setText(String.valueOf(c.getTva()));
                        Label LReference = new Label("    Reference ");
                        TextField tfReference = new TextField("");
                        tfReference.getUnselectedStyle().setBorder(
                                RoundBorder.create().color(0xbbf0ed).rectangle(true));
                        tfPriceTTC.setText(String.valueOf(c.getPriceTTC()));
                        tfReference.setText(c.getReference());
                        Label LWeight = new Label("    Weight ");
                        TextField tfWeight = new TextField("");
                        tfWeight.getUnselectedStyle().setBorder(
                                RoundBorder.create().color(0xbbf0ed).rectangle(true));
                        tfWeight.setText(String.valueOf(c.getWeight()));
                        ImageViewer iv = new ImageViewer();
                        //   Button btnCapture = new Button("Take Pic");
                        FloatingActionButton btnCapture = FloatingActionButton.createFAB(FontImage.MATERIAL_CAMERA_ALT);
                        btnCapture.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
                        btnCapture.getAllStyles().setMargin(BOTTOM, btnCapture.getPreferredH() / 2);
                        btnCapture.getAllStyles().setBgColor(0x2e4345);
                        btnCapture.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {

                                Form hi = new Form("Capture", new BorderLayout());
                                hi.setToolbar(new Toolbar());
                                Style s = UIManager.getInstance().getComponentStyle("Title");
                                FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_CAMERA, s);

                                ImageViewer iv = new ImageViewer(icon);
                                Button Done = new Button("Done");
                                hi.getToolbar().addCommandToRightBar(" Choose", icon, (ev) -> {
                                    filePath = Capture.capturePhoto();
                                    if (filePath != null) {
                                        try {
                                            DefaultListModel<Image> m = (DefaultListModel<Image>) iv.getImageList();
                                            Image img = Image.createImage(filePath);
                                            System.out.println(filePath);
                                            if (m == null) {
                                                m = new DefaultListModel<>(img);
                                                iv.setImageList(m);
                                                iv.setImage(img);

                                            } else {
                                                m.addItem(img);
                                            }
                                            m.setSelectedIndex(m.getSize() - 1);
                                        } catch (IOException err) {
                                            Log.e(err);
                                        }
                                    }
                                });
                                FontImage icon2 = FontImage.createMaterial(FontImage.MATERIAL_DONE, s);
                                ImageViewer iv2 = new ImageViewer(icon2);
                                hi.getToolbar().addCommandToLeftBar("Done", icon2, (ev) -> {
                                    if (ev != null && ev.getSource() != null) {
                                        try {
                                            if (filePath == null) {
                                                EditForm.showBack();
                                            } //                                 filePath = (String) ev.getSource();
                                            else {
                                                int fileNameIndex = filePath.lastIndexOf("/") + 1;
                                                String fileName = filePath.substring(fileNameIndex);
                                                EncodedImage encImage = EncodedImage.create("/giphy.gif");
                                                EditForm.showBack();
                                            }
                                        } catch (IOException ex) {

                                        }
                                    }
                                });
                                hi.add(BorderLayout.CENTER, iv);
                                hi.show();
                            }
                        });
                        Button btnEdit = new Button("Edit Product");
                        btnEdit.getUnselectedStyle().setBorder(
                                RoundBorder.create().color(0x40b888).rectangle(true)
                        );
                        Style s = UIManager.getInstance().getComponentStyle("Title");
                        FontImage icon2 = FontImage.createMaterial(FontImage.MATERIAL_DONE, s);
                        ImageViewer iv2 = new ImageViewer(icon2);
                        EditForm.getToolbar().addCommandToRightBar("Edit", icon2, (evtv) -> {

                            if ((tfName.getText().length() == 0) || (tfType.getText().length() == 0)
                                    || (tfMarque.getText().length() == 0) || (tfPriceHT.getText().length() == 0)
                                    || (tfPriceTTC.getText().length() == 0) || (tfTva.getText().length() == 0)
                                    || (tfReference.getText().length() == 0) || (tfWeight.getText().length() == 0)) {
                                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                            } else {
                                try {
                                    Object fileName = null;
                                    if (fileName == null) {
                                        System.out.println("vide photo");
                                        String v = c.getPhoto();

                                        Product1 t = new Product1(c.getId(), tfName.getText(), tfType.getText(), tfReference.getText(), tfMarque.getText(),
                                                Float.parseFloat(tfPriceHT.getText()), Float.parseFloat(tfPriceTTC.getText()), Float.parseFloat(tfWeight.getText()),
                                                Float.parseFloat(tfTva.getText()), v);

                                        if (ProductService.getInstance().editProduct(t)) {
                                            //Dialog.show("Success", "product edited successfully ", new Command("OK"));
                                            try {
                                                // Dialog.show("Success", "Connection accepted", new Command("OK"));

                                                InteractionDialog dialog = new InteractionDialog("Success");
                                                dialog.setLayout(new BorderLayout());
                                                SpanLabel ll = new SpanLabel("Product Edited Successfuly");
                                                Image ix = Image.createImage("/suuccess.png");
                                                Label imap = new Label(ix.fill(300, 300));
                                                dialog.add(BorderLayout.CENTER, GridLayout.encloseIn(2, imap, ll));
                                                Button close = new Button("Close");
                                                close.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent evt) {
                                                        dialog.disposeToTheTop();
                                                        // hi.show();
                                                        new listProduct_1().start();
                                                    }
                                                });
                                                dialog.add(BorderLayout.SOUTH, close);
                                                dialog.show(750, 750, 0, 0);
                                            } catch (IOException ex) {
                                            }

                                        } else {
                                            // Dialog.show("ERROR", "error please try again", new Command("OK"));
                                            try {
                                                //Dialog.show("ERROR", "Server error", new Command("OK"));
                                                InteractionDialog dialog = new InteractionDialog("ERROR");
                                                dialog.setLayout(new BorderLayout());
                                                SpanLabel ll = new SpanLabel("Error please try again");
                                                Image ix = Image.createImage("/err.jpg");
                                                Label imap = new Label(ix.fill(200, 200));
                                                dialog.add(BorderLayout.CENTER, GridLayout.encloseIn(2, imap, ll));
                                                Button close = new Button("Close");
                                                close.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent evt) {
                                                        dialog.disposeToTheTop();

                                                        // dest.show();
                                                    }
                                                });
                                                dialog.add(BorderLayout.SOUTH, close);
                                                dialog.show(750, 750, 0, 0);
                                            } catch (IOException ex) {
                                                System.out.println(ex);                                            }

                                        }
                                    } else {
                                        Product1 t = new Product1(c.getId(), tfName.getText(), tfType.getText(), tfReference.getText(), tfMarque.getText(),
                                                Float.parseFloat(tfPriceHT.getText()), Float.parseFloat(tfPriceTTC.getText()), Float.parseFloat(tfWeight.getText()),
                                                Float.parseFloat(tfTva.getText()), filePath);

                                        if (ProductService.getInstance().editProduct(t)) {
                                            //Dialog.show("Success", "product edited successfully ", new Command("OK"));
                                            try {
                                                // Dialog.show("Success", "Connection accepted", new Command("OK"));

                                                InteractionDialog dialog = new InteractionDialog("Success");
                                                dialog.setLayout(new BorderLayout());
                                                SpanLabel ll = new SpanLabel("Product Edited Successfuly");
                                                Image ix = Image.createImage("/suuccess.png");
                                                Label imap = new Label(ix.fill(300, 300));
                                                dialog.add(BorderLayout.CENTER, GridLayout.encloseIn(2, imap, ll));
                                                Button close = new Button("Close");
                                                close.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent evt) {
                                                        dialog.disposeToTheTop();
                                                        // hi.show();
                                                        new listProduct_1().start();
                                                    }
                                                });
                                                dialog.add(BorderLayout.SOUTH, close);
                                                dialog.show(750, 750, 0, 0);
                                            } catch (IOException ex) {
                                            }

                                        } else {
                                            //Dialog.show("ERROR", "error please try again", new Command("OK"));

                                            try {
                                                //Dialog.show("ERROR", "Server error", new Command("OK"));
                                                InteractionDialog dialog = new InteractionDialog("ERROR");
                                                dialog.setLayout(new BorderLayout());
                                                SpanLabel ll = new SpanLabel("Error please try again");
                                                Image ix = Image.createImage("/err.jpg");
                                                Label imap = new Label(ix.fill(200, 200));
                                                dialog.add(BorderLayout.CENTER, GridLayout.encloseIn(2, imap, ll));
                                                Button close = new Button("Close");
                                                close.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent evt) {
                                                        dialog.disposeToTheTop();

                                                        // dest.show();
                                                    }
                                                });
                                                dialog.add(BorderLayout.SOUTH, close);
                                                dialog.show(750, 750, 0, 0);
                                            } catch (IOException ex) {
                                            System.out.println(ex);
                                            }
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    //Dialog.show("ERROR", "Check the input please", new Command("OK"));
                                    try {
                                        //Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                                        InteractionDialog dialog = new InteractionDialog("ERROR");
                                        dialog.setLayout(new BorderLayout());
                                        SpanLabel ll = new SpanLabel("Check the input please");
                                        Image ix = Image.createImage("/err.jpg");
                                        Label imap = new Label(ix.fill(200, 200));
                                        dialog.add(BorderLayout.CENTER, GridLayout.encloseIn(2, imap, ll));
                                        Button close = new Button("Close");
                                        close.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent evt) {
                                                dialog.disposeToTheTop();

                                                // dest.show();
                                            }
                                        });
                                        dialog.add(BorderLayout.SOUTH, close);
                                        dialog.show(750, 750, 0, 0);

                                        // dest.show();
                                    } catch (IOException ex) {
                                    System.out.println(ex);
                                    }
                                }

                            }
                        });

                        EditForm.addAll(LName, tfName, LType, tfType, LReference, tfReference, LMarque, tfMarque, LPriceHt, tfPriceHT, LPriceTTc, tfPriceTTC, LTva, tfTva, LWeight, tfWeight, btnCapture);
                        EditForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> ProductDetail.showBack());
                        EditForm.show();
                    } else if (pick.getText() == "Delete") {
                        if (ProductService.getInstance().deleteProduct(c.getId())) {
                            // Dialog.show("Success", "Product deleted", new Command("OK"));
                            try {
                                // Dialog.show("Success", "Connection accepted", new Command("OK"));

                                InteractionDialog dialog = new InteractionDialog("Success");
                                dialog.setLayout(new BorderLayout());
                                SpanLabel ll = new SpanLabel("Product Deleted Successfuly");
                                Image ix = Image.createImage("/suuccess.png");
                                Label imap = new Label(ix.fill(300, 300));
                                dialog.add(BorderLayout.CENTER, GridLayout.encloseIn(2, imap, ll));
                                Button close = new Button("Close");
                                close.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        dialog.disposeToTheTop();
                                        // hi.show();
                                        new listProduct_1().start();
                                    }
                                });
                                dialog.add(BorderLayout.SOUTH, close);
                                dialog.show(750, 750, 0, 0);
                            } catch (IOException ex) {
                            }
                        } else {
                            // Dialog.show("ERROR", "Please try again", new Command("OK"));
                            try {
                                //Dialog.show("ERROR", "Server error", new Command("OK"));
                                InteractionDialog dialog = new InteractionDialog("ERROR");
                                dialog.setLayout(new BorderLayout());
                                SpanLabel ll = new SpanLabel("Error please try again");
                                Image ix = Image.createImage("/err.jpg");
                                Label imap = new Label(ix.fill(200, 200));
                                dialog.add(BorderLayout.CENTER, GridLayout.encloseIn(2, imap, ll));
                                Button close = new Button("Close");
                                close.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        dialog.disposeToTheTop();

                                        // dest.show();
                                    }
                                });
                                dialog.add(BorderLayout.SOUTH, close);
                                dialog.show(750, 750, 0, 0);
                            } catch (IOException ex) {
                            }
                        }
                    } else if (pick.getText() == "Tweet") {
                        try {
                            Dialog.show("Informarion", "You will redirect to tweet on the page tweeter of the Entrepot ", new Command("OK"));
                            InteractionDialog dlg = new InteractionDialog("Twitter Post");
                            dlg.setLayout(new BorderLayout());

                            //  SpanLabel  tfPost = new SpanLabel ("", "Write Tweet here ...");
                            TextComponent tfPost = new TextComponent().label("Write Tweet here ...").multiline(true);
                            int h = Display.getInstance().getDisplayHeight();

                            // dlg.add(BorderLayout.CENTER, new Label("","Write Tweet here ..."));
                            dlg.add(BorderLayout.CENTER, tfPost);
                            Image imo = Image.createImage("/icontwitter.png");
                            Label lone = new Label(imo.fill(300, 300));
                            lone.getStyle().setPaddingLeft(30);
                            //imgaViewerdet = new ImageViewer(im);
                            // dlg.add(BorderLayout.NORTH,imo.fill(300, 300));
                            // dlg.add(BorderLayout.NORTH,new Label("","Write Tweet here ..."));
                            Label l = new Label("");
                            l.setVisible(false);
                            l.setWidth(2);
                            l.setHeight(2);
                            dlg.add(BorderLayout.NORTH, GridLayout.encloseIn(1, lone));
                            Button close = new Button("Cancel");
                            Button Tweet = new Button("Tweet");
                            close.addActionListener((ee) -> dlg.dispose());
                            Tweet.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {

                                    if (ProductService.getInstance().Tweeter(tfPost.getText())) {
                                        //Dialog.show("Success", "Product deleted", new Command("OK"));
                                        try {
                                            // Dialog.show("Success", "Connection accepted", new Command("OK"));

                                            InteractionDialog dialog = new InteractionDialog("Success");
                                            dialog.setLayout(new BorderLayout());
                                            SpanLabel ll = new SpanLabel("Tweet has added to your account "+";)");
                                            Image ix = Image.createImage("/icontwitter.png.png");
                                            Label imap = new Label(ix.fill(300, 300));
                                            dialog.add(BorderLayout.CENTER, GridLayout.encloseIn(2, imap, ll));
                                            Button close = new Button("Close");
                                            close.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent evt) {
                                                    dialog.disposeToTheTop();
                                                    new listProduct_1().start();
                                                }
                                            });
                                            dialog.add(BorderLayout.SOUTH, close);
                                            dialog.show(750, 750, 0, 0);
                                        } catch (IOException ex) {
                                        }
                                       

                                    } else {
                                        //Dialog.show("ERROR", "Please try again", new Command("OK"));
                                        try {
                                            //Dialog.show("ERROR", "Server error", new Command("OK"));
                                            InteractionDialog dialog = new InteractionDialog("ERROR");
                                            dialog.setLayout(new BorderLayout());
                                            SpanLabel ll = new SpanLabel("Error please try again");
                                            Image ix = Image.createImage("/err.jpg");
                                            Label imap = new Label(ix.fill(200, 200));
                                            dialog.add(BorderLayout.CENTER, GridLayout.encloseIn(2, imap, ll));
                                            Button close = new Button("Close");
                                            close.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent evt) {
                                                    dialog.disposeToTheTop();

                                                    // dest.show();
                                                }
                                            });
                                            dialog.add(BorderLayout.SOUTH, close);
                                            dialog.show(750, 750, 0, 0);
                                        } catch (IOException ex) {
                                        }
                                    }

                                }

                            });
                            dlg.add(BorderLayout.SOUTH, GridLayout.encloseIn(2, close, Tweet));
                            // dlg.addComponent(BorderLayout.WEST, close);
                            // dlg.addComponent(BorderLayout.EAST, Tweet);
                            // Dimension pre = dlg.getContentPane().getPreferredSize();
                            dlg.show(0, 400, 0, 0);

                            /*      Form Tweeter = new Form(new BoxLayout(BoxLayout.Y_AXIS));
                            
                            Tweeter.setTitle("             Tweet Post");
                            Label LPost = new Label("    Message :");
                            TextField tfPost = new TextField("");
                            Tweeter.add(LPost).add(tfPost);
                            Button tweet =new Button("tweet");
                            final Button show = new Button("Show Dialog");
                            Button showPopup = new Button("Show Popup");
                            Tweeter.add(show).add(showPopup);
                            show.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                            
                    
                            }
                            });
                            showPopup.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                            Dialog d = new Dialog("Popup Title");
                            TextArea popupBody = new TextArea("This is the body of the popup", 3, 10);
                            popupBody.setUIID("PopupBody");
                            popupBody.setEditable(false);
                            d.setLayout(new BorderLayout());
                            d.add(BorderLayout.CENTER, popupBody);
                            d.showPopupDialog(showPopup);
                            }
                            });
                            tweet.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                            try {
                            if (ProductService.getInstance().Tweeter(tfPost.getText())) {
                            Dialog.show("Success", "Product deleted", new Command("OK"));
                            new listProduct().start();
                            
                            } else {
                            Dialog.show("ERROR", "Please try again", new Command("OK"));
                            }
                            
                            
                            }catch (NumberFormatException e) {
                            Dialog.show("ERROR", "Check the input please", new Command("OK"));
                            }
                            }
                            });
                            Tweeter.add(tweet);
                            Tweeter.show();
                             */
                        } catch (IOException ex) {
                        }
                    }

                }

            });

            //     detP.add(Edit);
            //   detP.add(Delete);
            //ProductDetail.add(ima);
            ProductDetail.add(pick);
            //    ProductDetail.add(detPi);
            //    ProductDetail.add(detP);
            ProductDetail.show();
        } catch (IOException ex) {
        }

    }

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
        new ProfileGestionnaireForm(res).show();
    }
}
