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
import com.codename1.messaging.Message;
import static com.codename1.messaging.Message.MIME_TEXT;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author HP
 */
public class ChangePassword  extends Form{
   
     public ChangePassword(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("Envoyerpass");
        Container welcome1 = FlowLayout.encloseCenter(
                new Label("CHANGE PASSWORD", "welcome1")  
        );
        getTitleArea().setUIID("Container");
        Toolbar tb=getToolbar();
        tb.setUIID("Toolbar1");
        TextField password = new TextField("", "Your code...", 20, TextField.PASSWORD) ;
        password.setUIID("pass1");
        TextField password1 = new TextField("", "New Password...", 20, TextField.PASSWORD) ;
        password1.setUIID("pass2");
        
        
        Button SendButton = new Button("CHANGE");
        SendButton.setUIID("SendButton");
        SendButton.addActionListener(e -> {
                         int v=Integer.parseInt(password.getText().toString());
                          if(v==Recupererpass.nb)
                          {
                          if(password1.getText().length()>=8)
                          {ConnectionRequest con1 = new ConnectionRequest();
                              con1.setUrl("http://localhost/cnx/changeuserpass.php?pass1="+password1.getText()+"&pass="+password.getText());
                con1.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                    String rep = new String( con1.getResponseData());
                    if (rep.equals("success"))
                       {
                           Dialog.show("Update", "    Change OK ", "OK", null);
                       new LoginForm(theme).show();
                       }
                        else
                           Dialog.show("Update", rep, "OK", "CANCEL");
                    }});
                 NetworkManager.getInstance().addToQueue(con1);
                          }
                          else
                          {
                             Dialog.show("New Password"," the length of ,new password should be grater then 8 charater", "OK", null); 
                          }
                   } 
                          else Dialog.show("New Password","  invalid access code", "OK", null); 

                });
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        
      
        Container by = BoxLayout.encloseY(
                welcome1,
                password,
                password1,
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
