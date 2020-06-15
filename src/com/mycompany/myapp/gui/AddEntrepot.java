/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.services.ServiceEntrepot;
import com.mycompany.myapp.services.ProductService;
import java.io.IOException;


/**
 *
 * @author bhk
 */
public class AddEntrepot extends SideMenuGestionnaireForm  {
        Container titleCmp=null;
            private Resources theme;



    public AddEntrepot() {
        Form hin = new Form("      New Entrepot ", new BoxLayout(BoxLayout.Y_AXIS));
         Toolbar tb = getToolbar();
                      tb=hin.getToolbar();

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
        //setTitle("   New Entrepot");
        hin.setLayout(BoxLayout.y());

        Label LAddress = new Label("      Addresse ");
        TextField tfAddress = new TextField("");
        tfAddress.getUnselectedStyle().setBorder(
                RoundBorder.create().color(0xbbf0ed).rectangle(true));

        Label LnbrRangs = new Label("      nbrRangs ");
        TextField tfnbrRangs = new TextField("","", 20, TextField.PHONENUMBER);
        tfnbrRangs.getUnselectedStyle().setBorder(
                RoundBorder.create().color(0xbbf0ed).rectangle(true));

        Label Lphone = new Label("     phone ");
        TextField tfphone = new TextField("","", 20, TextField.PHONENUMBER);
        tfphone.getUnselectedStyle().setBorder(
                RoundBorder.create().color(0xbbf0ed).rectangle(true));
        tfphone.setConstraint(TextField.PHONENUMBER);
        Label Lphone_bis = new Label("     phone_bis ");
        TextField tfphone_bis = new TextField("","", 20, TextField.PHONENUMBER);
        tfphone_bis.getUnselectedStyle().setBorder(
                RoundBorder.create().color(0xbbf0ed).rectangle(true));

        Label Llatitude = new Label("      latitude ");
        TextField tflatitude = new TextField("");
        tflatitude.getUnselectedStyle().setBorder(
                RoundBorder.create().color(0xbbf0ed).rectangle(true));

        Label Llongitude = new Label("      longitude ");
        TextField tflongitude = new TextField("");
        tflongitude.getUnselectedStyle().setBorder(
                RoundBorder.create().color(0xbbf0ed).rectangle(true));

        //TextField tfAddress = new TextField("","Addresse",20, TextField.TEXT_CURSOR);
        // TextField tfnbrRangs= new TextField("", "nbrRangs",20, TextField.TEXT_CURSOR);
        //TextField tfphone = new TextField("","phone",20, TextField.PHONENUMBER);
        //TextField tfphone_bis = new TextField("","phone_bis",20, TextField.PHONENUMBER);
        // TextField tflatitude = new TextField("","latitude",20, TextField.TEXT_CURSOR);
        // TextField tflongitude = new TextField("","longitude",20, TextField.TEXT_CURSOR);
        //Button btnValider = new Button("Add");
        Style s = UIManager.getInstance().getComponentStyle("Title");
        FontImage icon2 = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
        ImageViewer iv2 = new ImageViewer(icon2);
        hin.getToolbar().addCommandToRightBar("Add", icon2, (evtv) -> {
          Validator v1=new Validator();
                        RegexConstraint phoneConstraint = new RegexConstraint("[(0-9)]{8}", "Invalid phone number");
                        v1.addConstraint(tfphone, phoneConstraint);
                        v1.addConstraint(tfphone_bis, phoneConstraint);
            if ((tfAddress.getText().length() == 0) || (tfphone.getText().length() == 0)|| (tfnbrRangs.getText().length() == 0)|| (tflatitude.getText().length() == 0)
                    || (tflongitude.getText().length() == 0)|| (tfphone_bis.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            }
            
             
                        
                else {
                try {
                    Entrepot E = new Entrepot(tfAddress.getText(), Integer.parseInt(tfnbrRangs.getText()), tfphone.getText(), tfphone_bis.getText(), tflatitude.getText(), tflongitude.getText());
                    if (ServiceEntrepot.getInstance().addEntrepot(E)&&v1.isValid()) {
                        try {
                           // Dialog.show("Success", "Connection accepted", new Command("OK"));

                            InteractionDialog dialog = new InteractionDialog("Success");
                            dialog.setLayout(new BorderLayout());
                            SpanLabel ll = new SpanLabel("Entrepot Added Successfuly");
                            Image ix = Image.createImage("/suuccess.png");
                            Label imap = new Label(ix.fill(300, 300));
                            dialog.add(BorderLayout.CENTER, GridLayout.encloseIn(2, imap, ll));
                            Button close = new Button("Close");
                            close.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    dialog.disposeToTheTop();
                                    // hi.show();
                                }
                            });
                            dialog.add(BorderLayout.SOUTH, close);
                            dialog.show(750, 750, 0, 0);
                        } catch (IOException ex) {
                        }
                        //dlg.dispose();
                    } else {
                        try {
                            //Dialog.show("ERROR", "Server error", new Command("OK"));
                            InteractionDialog dialog = new InteractionDialog("ERROR");
                            dialog.setLayout(new BorderLayout());
                            SpanLabel ll = new SpanLabel("Server error");
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
                } catch (NumberFormatException e) {
                    //Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    try {
                        //Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                        InteractionDialog dialog = new InteractionDialog("ERROR");
                        dialog.setLayout(new BorderLayout());
                        SpanLabel ll = new SpanLabel("Status must be a number");
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
                    }
                }

            }

        });

        hin.addAll(LAddress, tfAddress, LnbrRangs, tfnbrRangs, Lphone, tfphone, Lphone_bis, tfphone_bis, Llatitude, tflatitude, Llongitude, tflongitude);
     //  hin.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeForm());
       hin.show();
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
