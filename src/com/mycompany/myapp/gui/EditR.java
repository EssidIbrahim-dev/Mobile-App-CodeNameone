/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;

/**
 * GUI builder created Form
 *
 * @author Ibrahim
 */
public class EditR extends SideMenuClientForm {

    Container titleCmp=null;

    public EditR(Resources res,Reclamation r) {
        Container c=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c.getAllStyles().setBgTransparency(255);

                   c.getStyle().setBgColor(ColorUtil.WHITE);
                   c.getStyle().setMarginTop(30);
                   c.getStyle().setMarginLeft(50);
                   c.getStyle().setMarginRight(50);

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
          ComboBox cbP=new ComboBox();
         cbP.addItem("Haut");
         cbP.addItem("Moyenne");
         cbP.addItem("Bas");
         c.add(new SpanLabel("Priorite"));

         c.add(cbP);
          c.add(new SpanLabel("Contenu"));
         TextField txtRecherche = new TextField("", "Contenu", 20, TextArea.ANY);
         txtRecherche.setUIID("txtRecherche");
         txtRecherche.getStyle().setFgColor(ColorUtil.BLACK);
         txtRecherche.setText(r.getContenu());
         c.add(txtRecherche);
          Button ajouter=new Button("Ajouter");
         ajouter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                String contenu=txtRecherche.getText().toString();
                String priorite=cbP.getSelectedItem().toString();
                ServiceReclamation.getInstance().editRec(r.getId(),priorite,contenu);
                 Dialog d = new Dialog("Reclamation");
                             TextArea popupBody = new TextArea("Reclamation editer", 3, 10);
                              popupBody.setUIID("PopupBody");
                              popupBody.setEditable(false);
                              d.setLayout(new BorderLayout());
                              d.add(BorderLayout.CENTER, popupBody);
                              d.showPopupDialog(ajouter);
            }
        });
         c.add(ajouter);
         add(c);
         setupSideMenu(res);
        
    }
    
    public EditR(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("EditR");
        setName("EditR");
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
