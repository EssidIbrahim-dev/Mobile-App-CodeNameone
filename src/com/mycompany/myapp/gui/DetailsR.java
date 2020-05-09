/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;

/**
 * GUI builder created Form
 *
 * @author Ibrahim
 */
public class DetailsR extends SideMenuClientForm {
        Container titleCmp=null;

    public DetailsR(Resources res,Reclamation r) {
        
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
         Container c=new Container(new BoxLayout(BoxLayout.Y_AXIS));
         c.getAllStyles().setBgTransparency(255);

                   c.getStyle().setBgColor(ColorUtil.WHITE);
                   c.getStyle().setMarginTop(30);
                   c.getStyle().setMarginLeft(50);
                   c.getStyle().setMarginRight(50);
         Container c1=new Container(new BoxLayout(BoxLayout.X_AXIS));
         Container c2=new Container(new BoxLayout(BoxLayout.X_AXIS));
         Container c3=new Container(new BoxLayout(BoxLayout.X_AXIS));
         Container c4=new Container(new BoxLayout(BoxLayout.X_AXIS));
         Container c5=new Container(new BoxLayout(BoxLayout.X_AXIS));
         Container c6=new Container(new BoxLayout(BoxLayout.X_AXIS));
         Button delete=new Button("Delete");
         Button edit =new Button("Edit");
         delete.addActionListener(new ActionListener() {

               @Override
               public void actionPerformed(ActionEvent evt) {
                   ServiceReclamation.getInstance().deleteRec(r.getId());
                    Dialog d = new Dialog("Reclamation");
                             TextArea popupBody = new TextArea("Reclamation supprimer", 3, 10);
                              popupBody.setUIID("PopupBody");
                              popupBody.setEditable(false);
                              d.setLayout(new BorderLayout());
                              d.add(BorderLayout.CENTER, popupBody);
                              d.showPopupDialog(delete);
               }
           });
         edit.addActionListener(new ActionListener() {

               @Override
               public void actionPerformed(ActionEvent evt) {
                   EditR editR=new EditR(res,r);
                   editR.show();
               }
           });

         c1.add(new SpanLabel("Date"));
         c1.add(new SpanLabel(r.getDate()));
         c2.add(new SpanLabel("Priorite"));
         c2.add(new SpanLabel(r.getPriority()));
         c3.add(new SpanLabel("Username"));
         c3.add(new SpanLabel(r.getUsername()));
         c4.add(new SpanLabel("Etat"));
         c4.add(new SpanLabel(r.getEtat()));
         c5.add(new SpanLabel("Contenu"));
         c5.add(new SpanLabel(r.getContenu()));
         c6.add(delete);
         c6.add(edit);
         c.add(c1);
         c.add(c2);
         c.add(c3);
         c.add(c4);
         c.add(c5);
         c.add(c6);
         add(c);









         
                setupSideMenu(res);

        
    }
    
    public DetailsR(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("DetailsR");
        setName("DetailsR");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileClientForm(res).show();
    }

    @Override
    protected void showOtherForm1(Resources res) {
        new ProfileClientForm(res).show();
    }

    @Override
    protected void showOtherForm2(Resources res) {
        new ProfileClientForm(res).show();
    }

    @Override
    protected void showOtherForm3(Resources res) {
        new ProfileClientForm(res).show();
    }
}
