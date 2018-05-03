/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.raoudha.gui;

//import com.codename1.ui.Button;

import com.allforkids.Ettien.forms.HomeForm;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

//import com.codename1.ui.Component;
//
//import com.codename1.ui.Form;
//import com.codename1.ui.layouts.FlowLayout;
//import java.io.IOException;
//import java.util.logging.Level;
//
//import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Home 
{

    Form f;
    private final Resources theme;
    
    public Home() {
    theme = UIManager.initFirstTheme("/theme");
        f =new Form("Gestion Garderie",BoxLayout.y());
        Toolbar tb = f.getToolbar();
        tb.setUIID("ToolBarFont");
           Container c4 =new Container(BoxLayout.x());
        Container c2 =new Container(new FlowLayout(Component.TOP,Component.LEFT));
 Container c3 =new Container();
//    Container c1 =new Container(new FlowLayout(Component.TOP,Component.RIGHT));
     
   ImageViewer img = new ImageViewer();
     img.setImage(theme.getImage("enf1.png"));
img.getStyle().setBgColor(0xFFFACD);
//     c1.add(img);
      ImageViewer img1 = new ImageViewer();
     img1.setImage(theme.getImage("logop.png"));
// img1.getStyle().setBgColor(0xFFFACD);
     c2.add(img1);
//    f.add(c1);
   
Label l1=new Label("Plus on a d'enfants, " );
Label l2=new Label("plus il y a de bonheur");
    Button ajouterG,afficherG;
       ajouterG =new Button("Ajouter Garderie");
       afficherG =new Button("Mes Garderies");
      c4.add(ajouterG);
      c4.add( afficherG);
        c3.add(l1);
        c3.add(l2);
      c3.add(img);
         f.add(c2);
        f.add(c3);
        f.add(c4);
        f.getAllStyles().setBgColor(0xFFFACD);
        //f.getAllStyles().setFgColor(0xf5bf0a);
//        f.show();
//        Home h =new Home();
//        h.getF().show();
      
          ajouterG.addActionListener((e)->{
       AjouterGarderie a=new AjouterGarderie();
        a.getF().show();
        });
       afficherG.addActionListener((e)->{
       AfficheGarderie af;
            try {
                af = new AfficheGarderie();
                af.getF().show();
            } catch (IOException ex) {
                System.out.println(""+ex);
            }
       
       });
        f.getToolbar().addCommandToRightBar("back", null, (ev)->{
            HomeForm h=new HomeForm();
            h.getF().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    
}
