/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
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
import java.util.Map;
import java.util.Random;

/**
 *
 * @author HP
 */
public class Recupererpass extends Form{
    public static String Nom;
    public static String Email;
    public static String pass;
    public static int Number;
    public static int nb; 
            
            
     public Recupererpass(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("Recupererpass");
        Container welcome1 = FlowLayout.encloseCenter(
                new Label("Enter your phone number Or Email", "welcome1")  
        );
        
        getTitleArea().setUIID("Container");
        Toolbar tb = getToolbar();
        tb.setUIID("Toolbar1");
        
        TextField choix = new TextField("", " Phone/Email", 10, TextField.TEXT_CURSOR) ;
        choix.setUIID("choix");
        Button SendButton = new Button("OK");
        SendButton.setUIID("SendButton");
        SendButton.addActionListener(e -> {
            ConnectionRequest con = new ConnectionRequest();
            String ch = choix.getText();
            if(ch.contains("@"))
            {
                con.setUrl("http://localhost/cnx/selectusermail.php?email="+ch);
               
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
                          Dialog.show("Authentification error", "                            invalid mail address", "OK", null);
                         }
                    else {
                        
                          Nom=my.get("username").toString();
                          Email=my.get("email").toString();
                          pass=my.get("password").toString();
                          System.out.println("nom : "+Nom+" email: "+Email);
                          System.out.println(generer(8000, 9000));
                           nb=generer(8000, 9000); 
                           new EnvoyerpasswithMail(theme).show();
                   }
                    
                } catch (IOException ex) {
                }   
                 }
                });
                     NetworkManager.getInstance().addToQueue(con);
                      
            
            }
            else{
                
            if(ch.length()==8)
            {
                int c=Integer.parseInt(ch);
                 con.setUrl("http://localhost/cnx/selectuserphone.php?phone="+c);
               
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
                          Dialog.show("Authentification error", "                            invalid phone number", "OK", null);
                         }
                    else {
                        
                          Nom=my.get("username").toString();
                          Number=Integer.parseInt(my.get("num_tel").toString());
                          pass=my.get("password").toString();
                          System.out.println("nom : "+Nom+" phone: "+Number);
                          nb=generer(8000, 9000); 
                          new EnvoyerpasswithPhone(theme).show();
                   }
                    
                } catch (IOException ex) {
                }   
                 }
                });
                     NetworkManager.getInstance().addToQueue(con);
                      
            
                
            }
     
        }
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
                welcome1,
                choix,
                SendButton
        );
        add(BorderLayout.CENTER, by);
        tb.addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK
                , new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                new LoginForm(theme).show();
                 }
        }); 
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
    public int generer(int borne_inf,int borne_sub)
    {
        Random r=new Random();
        int nb;
        nb=borne_inf+r.nextInt(borne_sub-borne_inf);
        return nb;
    }
}
