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

import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
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
import java.io.IOException;


/**
 * Represents a user profile in the app, the first form we open after the walkthru
 *
 * @author Shai Almog
 */
public class ProfileForm extends SideMenuBaseForm {
    public String type;
    public String type1;
    public static String ch;
    Image profilePic,mask;
    Label profilePicLabel;
    public static String val;
     String rl="";
     Container titleCmp=null;
     Container titleCmp1=null;

     public ProfileForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
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
            }
            if(type.equals("a:1:{i:0;s:11:\"ROLE_CLIENT\";}"))
            {
                rl="Client";
            }
         titleCmp= BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label(LoginForm.nom+" "+LoginForm.prenom, "Title"),
                                    new Label(rl.toString(), "SubTitle")
                                )
                            ),
                         GridLayout.encloseIn(2, remainingTasks, completedTasks)
                );
        }
        
        if(ProfileForm.val!=null)
        {   try {
                    System.out.println(""+ProfileForm.val);
            profilePic= Image.createImage(ProfileForm.val);
            mask = res.getImage("round-mask.png");
            profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
            profilePicLabel = new Label(profilePic, "ProfilePicTitle");
            profilePicLabel.setMask(mask.createMask());
            titleCmp.addComponent(2,profilePicLabel);
            } catch (IOException ex) {
            }
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
//        add(new Label("Today", "TodayTitle"));
//        
//        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
//        
//        addButtonBottom(arrowDown, "Finish landing page concept", 0xd997f1, true);
//        addButtonBottom(arrowDown, "Design app illustrations", 0x5ae29d, false);
//        addButtonBottom(arrowDown, "Javascript training ", 0x4dc2ff, false);
//        addButtonBottom(arrowDown, "Surprise Party for Matt", 0xffc06f, false);
       setupSideMenu(res);
    }
    
//    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
//        MultiButton finishLandingPage = new MultiButton(text);
//        finishLandingPage.setEmblem(arrowDown);
//        finishLandingPage.setUIID("Container");
//        finishLandingPage.setUIIDLine1("TodayEntry");
//        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
//        finishLandingPage.setIconUIID("Container");
//        add(FlowLayout.encloseIn(finishLandingPage));
//    }
    
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

    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }
    
    @Override
    protected void showOtherForm1(Resources res) {
        new StatsForm(res).show();
    }
    @Override
    protected void showOtherForm2(Resources res) {
        new StatsForm(res).show();
    }
    @Override
    protected void showOtherForm3(Resources res) {
        new UpdateaccForm(res).show();
    }
}

