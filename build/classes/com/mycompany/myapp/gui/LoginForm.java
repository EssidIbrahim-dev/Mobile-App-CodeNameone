/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Toolbar;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.util.Resources;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.UIBuilder;
import com.mycompany.myapp.entities.User;
import java.io.IOException;
import java.util.List;
import java.util.Map;



/**
 * The Login form
 *
 * @author Shai Almog
 */
public class LoginForm extends Form {
    public static String usr_type;
public static String nom;
public static String prenom;
public static String username;
public static int id;
    public LoginForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        try {
            setUIID("LoginForm");
            Container welcome = FlowLayout.encloseCenter(
                    new Label("Welcome To THE MAZE", "WelcomeWhite")
            );
            
            getTitleArea().setUIID("Container");
            
            Image profilePic = Image.createImage("/Image.png");
            
            profilePic = profilePic.fill(700, 350);
            Label profilePicLabel = new Label(profilePic, "ProfilePic");
            profilePicLabel.setIcon(profilePic);
            
            TextField login = new TextField("", "Username...", 20, TextField.EMAILADDR) ;
            TextField password = new TextField("", "Password...", 20, TextField.PASSWORD) ;
            
            login.getAllStyles().setMargin(LEFT, 0);
            password.getAllStyles().setMargin(LEFT, 0);
            Label loginIcon = new Label("", "TextField");
            Label passwordIcon = new Label("", "TextField");
            loginIcon.getAllStyles().setMargin(RIGHT, 0);
            passwordIcon.getAllStyles().setMargin(RIGHT, 0);
            FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
            FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
            
            Button loginButton = new Button("LOGIN");
            loginButton.setUIID("LoginButton");
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ConnectionRequest con = new ConnectionRequest();
                    String name = login.getText();
                    String pswd = password.getText();
                    username=name;
                    
                    
                    
                    con.setUrl("http://localhost/cnx/selectuser.php?username="+name+"&password="+pswd);
                    
                    con.addResponseListener(new ActionListener<NetworkEvent>() {
                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                            
                            try {
                                String json=new String(con.getResponseData());
                                JSONParser j = new JSONParser();
                                
                                Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
                                System.out.println(""+users.get("root"));
                                Map<String, Object> my=  (Map<String, Object>)users.get("root");
                                if(users.get("root")==null)
                                {
                                    Dialog.show("Erreur d'authentification", "      Verifier votre Nom d'utilisateur ou mot de passe!!", "OK", null);
                                }
                                else {
                                    nom=my.get("surname").toString();
                                    prenom=my.get("name").toString();
                                    usr_type=my.get("roles").toString();
                                    username=name;
                                    id=Integer.parseInt(my.get("idu").toString());
                                    System.out.println(usr_type+id);
                                    Dialog.show("Authentification", "                                            SUCCESS","OK",null);
                                    if(usr_type.equals("a:1:{i:0;s:11:\"ROLE_CLIENT\";}"))
                                    {
                                        new ProfileClientForm(theme).show();
                                    }
                                    else if(usr_type.equals("a:1:{i:0;s:17:\"ROLE_GESTIONNAIRE\";}"))
                                    {
                                        new ProfileGestionnaireForm(theme).show();
                                    }
//                             else{
//                                                           new ProfileForm(theme).show() ;
//
//                             }
                                }
                                
                            } catch (IOException ex) {
                            }
                        }
                    });
                    NetworkManager.getInstance().addToQueue(con);
                    
                    
                }
            });
            
            Button createNewAccount = new Button("CREATE NEW ACCOUNT");
            Button forgotPassword = new Button("FORGOT PASSWORD?");
            
            createNewAccount.setUIID("CreateNewAccountButton");
            forgotPassword.setUIID("forgot");
            createNewAccount.addActionListener(e->{
                new Registerform(theme).show();
            });
            forgotPassword.addActionListener(e->{
                
                new Recupererpass(theme).show();
                
                
            });
            // We remove the extra space for low resolution devices so things fit better
            Label spaceLabel;
            if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
                spaceLabel = new Label();
            } else {
                spaceLabel = new Label(" ");
            }
            
            
            Container by = BoxLayout.encloseY(
                    welcome,
                    profilePicLabel,
                    spaceLabel,
                    BorderLayout.center(login).
                            add(BorderLayout.WEST, loginIcon),
                    BorderLayout.center(password).
                            add(BorderLayout.WEST, passwordIcon),
                    loginButton,
                    BorderLayout.center(createNewAccount).
                            add(BorderLayout.WEST, forgotPassword)
            );
            add(BorderLayout.CENTER, by);
            
            // for low res and landscape devices
            by.setScrollableY(true);
            by.setScrollVisible(false);
        } catch (IOException ex) {
        }
    }
}


