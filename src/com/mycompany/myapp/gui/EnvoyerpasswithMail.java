/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.io.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
 *
 * @author HP
 */
public class EnvoyerpasswithMail  extends Form{
   
     public EnvoyerpasswithMail(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("Envoyerpass");
        Container welcome1 = FlowLayout.encloseCenter(
                new Label("WELCOM "+Recupererpass.Nom, "welcome1")  
        );
        
        getTitleArea().setUIID("Container");
        Toolbar tb=getToolbar();
        tb.setUIID("Toolbar1");
        Label lab=new Label("Sir if you want to receive your");
        lab.setUIID("lab");
        Label lab1=new Label("access code in your mail address click send!");
        lab1.setUIID("lab1");
        Button SendButton = new Button("SEND");
        SendButton.setUIID("SendButton");
        SendButton.addActionListener(e -> {
            ConnectionRequest con = new ConnectionRequest();
             con.setUrl("http://localhost/cnx/envoyermail.php?mail="+Recupererpass.Email+"&pass="+Recupererpass.nb);
              System.out.println(""+Recupererpass.Email+Recupererpass.pass); 
                con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                     String rep = new String( con.getResponseData());
                     if (rep.equals("success"))
                       {
                           Dialog.show("MAIL", "     code sent with success ", "OK", null);
                            new ChangePassword(theme).show();
                      }
                        else
                           Dialog.show("Insert", rep, "OK", "CANCEL");
                   }
                    });
         NetworkManager.getInstance().addToQueue(con);
//Properties props = new Properties();
//props.put("mail.smtp.auth", "true");
//props.put("mail.smtp.starttls.enable","true");
//props.put("mail.smtp.host","smtp.gmail.com");
//props.put("mail.smtp.port","587");
//props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
// String username = "kalboussihazem67@gmail.com";
// String password = "hazem2020";
//Session session = Session.getInstance(props,new Authenticator() {
//    @Override
//protected PasswordAuthentication getPasswordAuthentication() {
//return new PasswordAuthentication(username, password);
//}
//});
// javax.mail.Message message = new MimeMessage(session);
//    try {
//        message.setFrom(new InternetAddress(username));
//        message.setRecipient(javax.mail.Message.RecipientType.TO,new InternetAddress(Recupererpass.Email));
//        message.setSubject("Find password");
//        message.setText("Welcome Sir your access code is "+Recupererpass.nb);
//       Transport.send(message);
//       Dialog.show("MAIL", "     code sent with success ", "OK", null);        
//       new ChangePassword(theme).show(); 
//    } catch (Exception ex) {
//     }
//    

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
                lab,
                lab1,
                SendButton
        );
        add(BorderLayout.CENTER, by);
         tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> new Recupererpass(theme).show());
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
  
}
