/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;


/**
 *
 * @author HP
 */
public class UpdateaccForm extends SideMenuGestionnaireForm{
 private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};

    public UpdateaccForm(Resources res) {
        super(new BorderLayout());
     
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        
     try {
         Image profilePic = Image.createImage(ProfileGestionnaireForm.val);
     
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
      
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
        
        tb.getUnselectedStyle().setBgImage(tintedImage);
        } catch (IOException ex) {
     }
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
        
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent = 
                BorderLayout.north(
                    BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH, 
                        FlowLayout.encloseIn(
                                new Label(ProfileGestionnaireForm.ch, "WelcomeBlue")
                                
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(BorderLayout.NORTH, separator);
         setupSideMenu(res);
        TextField username = new TextField("", "Username...", 20, TextField.TEXT_CURSOR) ;
        TextField mail =new TextField("","Email...",20,TextField.EMAILADDR);
        TextField numtel = new TextField("", "Phone...", 20, TextField.PHONENUMBER);
        mail.setUIID("mail1");
        username.setUIID("username1");
        numtel.setUIID("numtel1");
        TextField password = new TextField("", "Password...", 20, TextField.PASSWORD) ;
        password.setUIID("passs");
        Label lab=new Label("UPDATE ACCOUNT");
        lab.setUIID("lab11");
        Button signButton = new Button("UPDATE");
        signButton.setUIID("update");
        signButton.addActionListener(e -> {
            ConnectionRequest con = new ConnectionRequest();
              if(username.getText().length()!=0 && mail.getText().length()!=0 && numtel.getText().length()!=0 && 
           password.getText().length()!=0 )
   {
        if(password.getText().length()>=8)
        {
            Validator v1=new Validator();
                        RegexConstraint phoneConstraint = new RegexConstraint("[(0-9)]{8}", "Invalid phone number");
                        v1.addConstraint(numtel, phoneConstraint);
          if(v1.isValid())
                        {
                Validator v=new Validator();
                        RegexConstraint emailConstraint = new RegexConstraint("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$", "Invalid Email Address");
                        v.addConstraint(mail, emailConstraint);

            if(v.isValid())    
            {    int l=Integer.parseInt(numtel.getText().toString());
                System.out.println(""+LoginForm.id);
                con.setUrl("http://localhost/cnx/updateUser.php?username="+username.getText().toString()+"&pass="+password.getText().toString()+"&old="+LoginForm.id+"&phone="+l+"&mail="+mail.getText());
                con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                     String rep = new String( con.getResponseData());
                    
                       if (rep.equals("success"))
                       {
                           Dialog.show("Update", "      UPDATE OK ", "OK", null);
                       }
                        else
                           Dialog.show("Insert", rep, "OK", "CANCEL");
                   }
                });
                        NetworkManager.getInstance().addToQueue(con);
        }
         else Dialog.show("UPDATE", "      invalid email addresse ", "OK", null);
            }
        else Dialog.show("UPDATE", "      invalid phone number ", "OK", null);
        }
        else Dialog.show("UPDATE", "      invalid password ", "OK", null);

   }
       else Dialog.show("UPDATE", "    all fields should be not null ", "OK", null);
        }
        );
        Container by = BoxLayout.encloseY(
                lab,
                username,
                mail,
                numtel,
                password,
                signButton
        );
       by.setUIID("cc");
        add(BorderLayout.WEST, by);
      
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
