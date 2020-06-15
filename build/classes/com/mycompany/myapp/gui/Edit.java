/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
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
import com.mycompany.myapp.entities.ProdAchat;
import com.mycompany.myapp.services.ServiceAchat;

/**
 * GUI builder created Form
 *
 * @author Ibrahim
 */
public class Edit extends SideMenuClientForm {
    private ProdAchat prodAchat;
    private String username;
    Container titleCmp=null;


    public Edit(Resources res,ProdAchat prodAchat,String username) {
      /*  Toolbar edtToolbar=null;
        edtToolbar=this.getToolbar();
        edtToolbar.addMaterialCommandToLeftBar("Back",FontImage.MATERIAL_KEYBOARD_ARROW_LEFT, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    Panier panier=new Panier(res,username);
                    panier.show();
                }
            });*/
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
                          )//,
               // GridLayout.encloseIn(2, remainingTasks, completedTasks)
                );
         tb.setTitleComponent(titleCmp);
        this.prodAchat=prodAchat;
        this.username=username;
      //  setTitle("Edit");
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c.getStyle().setBgColor(0xff0000);

        TextField txtQte=new TextField("", "1234", 20, TextArea.NUMERIC);
        txtQte.setUIID("txtQte");
        txtQte.getStyle().setBgTransparency(255);

        txtQte.setHint("Donner la nouveau quantite");
        Button valider=new Button("Editer");
         valider.setUIID("valider");
        valider.getStyle().setBgTransparency(255);
        valider.getStyle().setBgColor(ColorUtil.rgb(18, 97, 160));
        valider.getStyle().setFgColor(ColorUtil.WHITE);
        valider.getStyle().setMarginTop(60);
        valider.getStyle().setMarginLeft(60);
        valider.getStyle().setMarginRight(60);

        valider.getStyle().setPaddingLeft(20);
        valider.getStyle().setPaddingTop(3);
        valider.getStyle().setPaddingBottom(3);
        valider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                try{
                    int qte=Integer.parseInt(txtQte.getText().toString());
                ServiceAchat.getInstance().editAchatProduct(prodAchat.getId(), username, qte);
                System.out.println("qUANTITE modifie");
                Panier panier=new Panier(res,username);
                panier.show();
                }
                catch(Exception e){
                    Dialog d = new Dialog("Popup Title");
        TextArea popupBody = new TextArea("valeur pas numeric", 3, 10);
        popupBody.setUIID("PopupBody");
        popupBody.setEditable(false);
        d.setLayout(new BorderLayout());
        d.add(BorderLayout.CENTER, popupBody);
        d.showPopupDialog(valider);
                }
                
                
            }
        });
       
        c.add(txtQte);
        c.add(valider);
        add(c);
        setupSideMenu(res);



        
    }
    
    public Edit(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//////////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("Edit");
        setName("Edit");
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
