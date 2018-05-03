/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.raoudha.gui;

import com.codename1.components.ImageViewer;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

import com.allforkids.raoudha.Services.serviceGarderie;
import com.allforkids.raoudha.entities.Garderie;
import com.codename1.ui.Toolbar;
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
    
public AfficheGarderie () throws IOException
{
f=new Form("liste des Garderies");
    Toolbar tb = f.getToolbar();
    tb.setUIID("ToolBarFont");
        
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
                    detailGard d;
                    d=new detailGard(g);
                    d.getF().show();
                    
                    
                    
                }
            });
            
         
         
         
         
         
        }
 f.add(c);
   f.getAllStyles().setBgColor(0xFFFACD);
// f.show();
}
   
   
     


    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }


}
