/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Staff;

/**
 *
 * @author HP
 */
public class ShowStaff extends SideMenuGestionnaireForm{

    private Staff stf;
    Container titleCmp=null;

    public ShowStaff(Resources res,Staff f) {
           Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
         
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
         
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
     /*   Toolbar cmdToolbar=null;
        cmdToolbar=this.getToolbar();
        cmdToolbar.addMaterialCommandToLeftBar("Back",FontImage.MATERIAL_KEYBOARD_ARROW_LEFT, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
               ArrayList<Fournisseur> fournisseurs=ServiceFournisseur.getInstance().getFournisseurs();

        ListFournisseur listFournisseur=new ListFournisseur(fournisseurs);
        listFournisseur.show();
                }
            });*/
      Container c=new Container(new BoxLayout(BoxLayout.Y_AXIS));

      Container c1=new Container(new GridLayout(8, 4));
      Container cc=new Container(new FlowLayout(CENTER));
      Label l1=new Label("Staff Details");
           l1.setUIID("sp");
           cc.add(l1);
           cc.setUIID("cont1"); 
        c1.add(new SpanLabel(""));
       c1.add(new SpanLabel(""));
       c1.add(new SpanLabel(""));
       c1.add(new SpanLabel(""));    
      c1.add(new SpanLabel("FirstName"));
       c1.add(new SpanLabel(""));
       c1.add(new SpanLabel(""));
      c1.add(new SpanLabel(f.getNom()));
      c1.add(new SpanLabel("LastName"));
       c1.add(new SpanLabel(""));
       c1.add(new SpanLabel(""));
      c1.add(new SpanLabel(f.getPrenom()));
      c1.add(new SpanLabel("Post"));
       c1.add(new SpanLabel(""));
       c1.add(new SpanLabel(""));
      c1.add(new SpanLabel(f.getPost()));
      c1.add(new SpanLabel("Salary"));
       c1.add(new SpanLabel(""));
       c1.add(new SpanLabel(""));
      String sal=""+f.getSalary();
      c1.add(new SpanLabel(sal));
      c1.add(new SpanLabel("Phone"));
       c1.add(new SpanLabel(""));
       c1.add(new SpanLabel(""));
      String tel=""+f.getNumber();
      c1.add(new SpanLabel(tel));
      c.add(cc);
      c.add(c1);
      add(c);
      setupSideMenu(res);
       this.stf=f;
       c1.setUIID("cc1");
    }
    
    public ShowStaff(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("DetailsF");
        setName("DetailsF");
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
   

