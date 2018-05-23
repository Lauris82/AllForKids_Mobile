/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.raoudha.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import com.allforkids.raoudha.Services.serviceGarderie;
import com.allforkids.raoudha.entities.Garderie;
import com.allforkids.raoudha.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author user
 */
public class AfficheGarderie 
{
Form f;
int i;
List l;
  private ImageViewer imgv;
    private Image img;
     private Resources theme;
    Button suprimer;
     Button Modifier;
     Button AjouterEnfant;
public AfficheGarderie () throws IOException
{
f=new Form("liste des Garderies");
serviceGarderie s=new serviceGarderie();
ArrayList<Garderie> list =s.getList2();
 Container c=new Container(BoxLayout.y());
  
 for(i=0 ; i<list.size(); i++)
        {
            Garderie g=new Garderie();
            g.setNom(list.get(i).getNom());
             // System.out.println("debut");
            g.setDescription(list.get(i).getDescription());
            g.setCapacite(list.get(i).getCapacite());
            g.setEmplacement(list.get(i).getEmplacement());
            g.setNum_tel(list.get(i).getNum_tel());
         g.setUser_garderie(list.get(i).getUser_garderie());
        g.setId_garderie(list.get(i).getId_garderie());
            g.setNomImage(list.get(i).getNomImage());
            
            Container c2=new Container(BoxLayout.x());
               Label l =new Label();
            l.setText("Nom :"+list.get(i).getNom());
            Label l2 =new Label();
            l2.setText("A propos :"+list.get(i).getDescription());
            c2.add(l);
            c2.add(l2);
            
//                    
//         EncodedImage enc =EncodedImage.create("/giphy.gif") ;
//          img = URLImage.createToStorage(enc, "nomImage"+list.get(i).getNomImage(), "http://localhost/AllForKids/web/images/"+list.get(i).getNomImage(), URLImage.RESIZE_FAIL);
//                        imgv = new ImageViewer(img);
//            System.out.println(img);
                     
                        
           c.add(c2);
         //  c.add(imgv); 
           
         
         ///Detail garderie

            l.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
//                    detailGard d;
//                    d=new detailGard(g);
//                    d.getF().show();


 Form f1 = new Form(g.getNom(), BoxLayout.y());
               
                theme = UIManager.initFirstTheme("/theme");
            Container c=new Container(BoxLayout.y());       
                         Label l1 =new Label();
              
            l1.setText("Nous Sommes  :"+g.getNom());
     Label l2 =new Label();
            l2.setText("A propos Nous  :"+g.getDescription());
          Label l3 =new Label();
            l3.setText("Emplacement :"+g.getEmplacement()); 
              Label l4 =new Label();
           l4.setText("Numéro tél°:"+g.getNum_tel());
              Label l5 =new Label();
           l5.setText("Capacité:"+g.getCapacite());
                 c.add(l1);
           c.add(l2);
           c.add(l3);
           c.add(l4);
           c.add(l5);
           f1.add(c);
            f1.getAllStyles().setBgColor(0xFFFACD);
            
            
             f1.getToolbar().addCommandToLeftBar("Back", theme.getImage("cal_left_arrow.png"), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        f.showBack();
                    }
                });
             Container c0=new Container(BoxLayout.x());  
              Container c00=new Container(BoxLayout.x());  
             AjouterEnfant= new Button("Ajouter Enfant"); 
             suprimer =new Button("Delete");
             Modifier=new Button("Update");
            c00.add(AjouterEnfant);
             c0.add(suprimer);
             c0.add(Modifier);
             f1.add(c0);
             f1.add(c00);
              suprimer.addActionListener((e)->{
                  serviceGarderie s=new serviceGarderie();
                  s.supprimer(g);
                  
        
                  if(Dialog.show("Confirmation",
              "vous avez Supprimer la garderie du nom "+
                      g.getNom()
                      , "ok", "Cancel"))f1.showBack();

        });
             
          Modifier.addActionListener((e)->{
ModifierGarderie m =new ModifierGarderie(g);
m.getF().show();

        });
             
 AjouterEnfant.addActionListener((e)->{
AjouterEnfant enf =new AjouterEnfant(g);
enf.getF().show();

        });
                f1.show();
                    
                    
                    
                }
            });
            
         
    
         
        }
 f.add(c);
   f.getAllStyles().setBgColor(0xFFFACD);
 f.show();
 
 f.getToolbar().addCommandToLeftBar("Back", null, e -> {
     MyApplication hm = new MyApplication();
     hm.getF().show();
 });
}
   
   
     


    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }


}
