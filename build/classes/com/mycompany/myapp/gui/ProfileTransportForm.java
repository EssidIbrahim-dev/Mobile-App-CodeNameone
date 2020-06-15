/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.util.Resources;
import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import static com.mycompany.myapp.gui.LoginForm.username;
import com.mycompany.myapp.services.ServiceStaff;
import java.io.IOException;
import java.util.Map;

/**
 * GUI builder created Form
 *
 * @author Ibrahim
 */
public class ProfileTransportForm extends SideMenuTransportForm{
                public String type;
    public String type1;
    public static String ch;
    Image profilePic,mask;
    Label profilePicLabel;
    public  String username;
    public static String val;
     String rl="";
     Container titleCmp=null;
     Container titleCmp1=null;




    public ProfileTransportForm(Resources res) {
         super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        username=LoginForm.username;
         System.out.println(username);
         
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
        
        type=LoginForm.usr_type;
        type1=Registerform.usr_type;
        if(type!=null)
        {
            ch=LoginForm.nom+" "+LoginForm.prenom;
            if(type.equals("a:1:{i:0;s:17:\"ROLE_GESTIONNAIRE\";}"))
            {
                rl="Agent_Gestionnaire";
            }
            if(type.equals("a:1:{i:0;s:19:\"ROLE_AGENTTRANSPORT\";}"))
            {
                rl="Agent_Transport";
            }
            if(type.equals("a:1:{i:0;s:19:\"ROLE_AGENTFINANCIER\";}"))
            {
                rl="Agent_Financier";
            } if(type.equals("a:1:{i:0;s:11:\"ROLE_CLIENT\";}"))
            {
                rl="Cl";
            }
           
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
        }
         FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_CAMERA_ALT);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        
          fab.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                
                //This will trigger the native camera to display
                Capture.capturePhoto(new ActionListener() {

                    public void actionPerformed(final ActionEvent evt) {
                        //if a user cancels the camera the evt will be null
                        if (evt == null) {
                            ToastBar.Status s = ToastBar.getInstance().createStatus();
                            s.setMessage("User Cancelled Camera");
                            s.setMessageUIID("Title");
                            Image i = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"));
                            s.setIcon(i);
                            s.setExpires(2000);
                            s.show();
                            return;
                        }
                        //Create a component to display from the image path
                        //InputStream is = null;
                         Image i;
        try {
            
           val=(String)evt.getSource();
            System.out.println(""+evt.getSource());
            profilePic= Image.createImage((String)evt.getSource());
             mask = res.getImage("round-mask.png");
             profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
             profilePicLabel = new Label(profilePic, "ProfilePicTitle");
             profilePicLabel.setMask(mask.createMask());
            titleCmp.addComponent(2,profilePicLabel);
            titleCmp.refreshTheme();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        

                    }
                });

            }
        });
          tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
                 setupSideMenu(res);

        
    }
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }
    
    public ProfileTransportForm() {
    }

////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("ProfileTransportForm");
        setName("ProfileTransportForm");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!

    @Override
    protected void showOtherForm(Resources res) {
         new ProfileTransportForm(res).show();
    }

    @Override
    protected void showOtherForm1(Resources res) {
         new ProfileTransportForm(res).show();
    }

    @Override
    protected void showOtherForm2(Resources res) {
         new ProfileTransportForm(res).show();
    }

    @Override
    protected void showOtherForm3(Resources res) {
         new ProfileTransportForm(res).show();
    }
}
