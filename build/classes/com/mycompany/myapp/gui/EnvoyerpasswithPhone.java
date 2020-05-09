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
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
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
import com.codename1.util.Base64;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author HP
 */
public class EnvoyerpasswithPhone  extends Form{
   public static final String ACCOUNT_SID = "ACf51516e23e780017cb89656157edd25c";
    public static final String AUTH_TOKEN ="b29cc33df9f62ca7b1ea396a006a9362";
     public EnvoyerpasswithPhone(Resources theme) {
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
        Label lab1=new Label("access code in your phone click send!");
        lab1.setUIID("lab1");
        Button SendButton = new Button("SEND");
        SendButton.setUIID("SendButton");
        
        SendButton.addActionListener(e -> {
            Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + ACCOUNT_SID + "/Messages.json").
        queryParam("To", "+216"+Recupererpass.Number).
        queryParam("From", "+12673135103").
        queryParam("Body", "your access code is "+Recupererpass.nb).
        header("Authorization", "Basic " + Base64.encodeNoNewline((ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes())).
        getAsJsonMap();
            new ChangePassword(theme).show();
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
