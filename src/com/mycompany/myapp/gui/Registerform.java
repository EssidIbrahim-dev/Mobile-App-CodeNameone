/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
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
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;
import java.io.InputStream;

import javafx.scene.control.ChoiceBox;

/**
 *
 * @author HP
 */
public class Registerform extends Form{
    TextField photo;
    public static String usr_type;
public static int id;
public static String mynom;
public static String myprenom;
    public Registerform(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("RegisterForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Sign In", "SignWhite")  
        );
        
        getTitleArea().setUIID("Container");
        
        Toolbar tb=getToolbar();
        tb.setUIID("Toolbar1");
        TextField username = new TextField("", "Username...", 20, TextField.TEXT_CURSOR) ;
        TextField mail =new TextField("","Email...",20,TextField.EMAILADDR);
        TextField name = new TextField("", "Name...", 20, TextField.TEXT_CURSOR) ;
        TextField surname = new TextField("", "Surname...", 20, TextField.TEXT_CURSOR) ;
        TextField numtel = new TextField("", "Phone...", 20, TextField.PHONENUMBER);
        username.setUIID("username");
        mail.setUIID("mail");
        name.setUIID("name");
        surname.setUIID("surname");
        numtel.setUIID("numtel");
        Image icon = FontImage.createMaterial(FontImage.MATERIAL_CAMERA_ALT, UIManager.getInstance().getComponentStyle("Label"));
       
        ComboBox<String> combo=new ComboBox<>("Client","AGENTGESTIONNAIRE","AGENTFINANCIER","AGENTTRANSPORT");
        combo.setUIID("comb");
        TextField password = new TextField("", "Password...", 20, TextField.PASSWORD) ;
        password.setUIID("pass");
        Button signButton = new Button("SIGNIN");
        signButton.setUIID("SignButton");
        signButton.addActionListener(e -> {
            ConnectionRequest con = new ConnectionRequest();
            String usrname=username.getText();    
            String prenom = name.getText();
                String nom =surname.getText();
                String email= mail.getText();
                String pswd = password.getText();
                int tel =Integer.parseInt(numtel.getText());
                String type = (String)combo.getSelectedItem();
                String role="";
                if(type.equals("Client"))
                {
                    role="a:1:{i:0;s:11:\"ROLE_CLIENT\";}";
                }   
                if(type.equals("AGENTGESTIONNAIRE"))
                {
                 role="a:1:{i:0;s:17:\"ROLE_GESTIONNAIRE\";}";
                }
                if(type.equals("AGENTFINANCIER"))
                {
                 role="a:1:{i:0;s:19:\"ROLE_AGENTFINANCIER\";}";
                }
                  if(type.equals("AGENTTRANSPORT"))
                {
                 role="a:1:{i:0;s:19:\"ROLE_AGENTTRANSPORT\";}";
                }
                 usr_type=role;
                 mynom=nom;
                 myprenom=prenom;
                 
//                Date date =p.getDate();
              if(nom.length()!=0 && prenom.length()!=0 && email.length()!=0 && numtel.getText().length()!=0 && 
           pswd.length()!=0 && usrname.length()!=0)
   {
        if(pswd.length()>=8)
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
            {
                con.setUrl("http://localhost/cnx/insertuser.php?prenom="+prenom+"&username="+usrname+"&pwd="+pswd+"&mail="+email+"&phone="+tel+"&type="+role+"&surname="+nom);
                con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                     String rep = new String( con.getResponseData());
                     LoginForm login = new LoginForm(theme);
       
                       if (rep.equals("success"))
                       {
                           Dialog.show("Insert", "     AJOUT OK ", "OK", null);
                       login.show();}
                        else
                           Dialog.show("Insert", rep, "OK", "CANCEL");
                   }
                });
                        NetworkManager.getInstance().addToQueue(con);
        }
         else Dialog.show("Insert", "      invalid email addresse ", "OK", null);
            }
        else Dialog.show("Insert", "      invalid phone number ", "OK", null);
        }
        else Dialog.show("Insert", "      invalid password ", "OK", null);

   }
       else Dialog.show("Insert", "    all fields should be not null ", "OK", null);
        }
        );
            
        
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        
        
        
        Container by = BoxLayout.encloseY(
                welcome,
                spaceLabel,
                username,
                mail,
                name,
                surname,
                numtel,
                password,
                combo,
                signButton
        );
        add(BorderLayout.CENTER, by);
        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> new LoginForm(theme).show());
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
//     private void showToast(String text) {
//        Image errorImage = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"), 4);
//        ToastBar.Status status = ToastBar.getInstance().createStatus();
//        status.setMessage(text);
//        status.setIcon(errorImage);
//        status.setExpires(2000);
//        status.show();
//    }

}
