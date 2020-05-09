/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
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
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Fournisseur;
import com.mycompany.myapp.entities.Staff;
import java.util.ArrayList;
import com.mycompany.myapp.services.ServiceAchat;
import com.mycompany.myapp.services.ServiceFournisseur;
import com.mycompany.myapp.services.ServiceStaff;



/**
 * GUI builder created Form
 *
 * @author Ibrahim
 */
public class ListStaff extends SideMenuGestionnaireForm {
private ArrayList<Staff> staffs;
    Container titleCmp=null;

    public ListStaff(Resources res,ArrayList<Staff> staffs) {
        this.staffs=ServiceStaff.getInstance().getStaffs();
        Container c=new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
         
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
           TextField txtRecherche = new TextField("", "By Name", 15, TextArea.ANY);
           txtRecherche.setUIID("my");
                    Button rechercher=new Button("Search");

            rechercher.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                //staffs.clear();
                String name=txtRecherche.getText().toString();
                ArrayList<Staff> fs=new ArrayList<>();
                ArrayList<Staff> stf=ServiceStaff.getInstance().getStaffs();
                if(name.length()==0)
                    {
                        fs=stf;
                    }
                else{
                for( int i = 0; i < stf.size(); i++ )
                     {
                         System.out.println(""+stf.get(i));
                         System.out.println(""+stf.get(i).getNom()+name);
                    if(stf.get(i).getNom().equals(name))
                    {
                        fs.add(stf.get(i));
                    }
                    
                    
                }
                }
                if(fs.size()==0) {
                        Dialog.show("search", " pas de staff", "OK",null);
                    }
                ListStaff listStaff=new ListStaff(res,fs);
        listStaff.show();
               
            }
        });

         
        
         titleCmp= BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label(LoginForm.nom+" "+LoginForm.prenom, "Title"),
                                     new Label(ProfileGestionnaireForm.rl, "SubTitle")
                                )
                            )
                );
         tb.setTitleComponent(titleCmp);
       
       Container remainingTasks = BoxLayout.encloseX(
                        txtRecherche,
                        rechercher
                );
        remainingTasks.setUIID("mm");  
       
        c.add(remainingTasks);
        
        
        if(staffs.isEmpty()){
            c.add(new SpanLabel("Pas de staff"));
        }
        else{
          for(Staff f: staffs){
                      Container c1=new Container(new GridLayout(2, 3));
                      c1.add(new Label("First_Name"));
            c1.add(new Label("Last_Name"));
            c1.add(new Label("Commande"));
                      c1.add(new SpanLabel(f.getNom()));
                      c1.add(new SpanLabel(f.getPrenom()));
                      Button details=new Button("SHOW");
                      details.addActionListener(new ActionListener() {

                          @Override
                          public void actionPerformed(ActionEvent evt) {
                              ShowStaff staf=new ShowStaff(res,f);
                              staf.show();
                          }
                      });
                      c1.setUIID("c1");
                      c1.add(details);
                      c.add(c1);

          }
          
        }
        add(c);
                setupSideMenu(res);

                }
    
    public ListStaff(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("ListFournisseur");
        setName("ListFournisseur");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileGestionnaireForm(res).show();
    }

    @Override
    protected void showOtherForm1(Resources res) {
        new ProfileGestionnaireForm(res).show();
    }

    @Override
    protected void showOtherForm2(Resources res) {
        new ProfileGestionnaireForm(res).show();
    }

    @Override
    protected void showOtherForm3(Resources res) {
        new UpdateaccForm(res).show();
    }
}
