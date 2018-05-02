/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yassine.gui;

import com.allforkids.yassine.entities.evenement;
import com.allforkids.yassine.services.serviceReservation;
import com.codename1.components.MultiButton;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class membreEvenement {
Form f ;
int nbrR;
Image img ;
    public membreEvenement(evenement e, int ide,int p) throws IOException {
        
        f=new Form("Membres èvenement");
        Container c=new Container(BoxLayout.y());
        serviceReservation r=new serviceReservation();
     nbrR  = r.nbrReservation(ide);
     Container c2=new Container(BoxLayout.x());
     Label l=new Label("Nombre de reservation=");
      l.getAllStyles().setFgColor(0xFF0000);
     Label l2=new Label(String.valueOf(nbrR));
     c2.addAll(l,l2);
    
     Container c3=new Container(BoxLayout.x());
     Label l3=new Label("Nombre de places disponible=");
      l3.getAllStyles().setFgColor(0xFF0000);
        
     Label l4=new Label(String.valueOf(p-nbrR));
      ArrayList<evenement> list=r.getListReservation2(e.getId_evenement());
      
       EncodedImage  enc = EncodedImage.create("/giphy.gif");
       
       c3.addAll(l3,l4);
       Label mm=new Label("Les membres :");
        mm.getAllStyles().setFgColor(0xf5bf0a);
     c.add(c2);
     c.add(c3);
     c.add(mm);
      for(int i=0 ; i<list.size();i++)
      {
          
          MultiButton obj3 = new MultiButton();
    
          img = URLImage.createToStorage(enc, "image"+list.get(i).getImage(), "http://localhost/AllForKids/web/image_user/"+list.get(i).getImage(), URLImage.RESIZE_SCALE);
    img.rotate(100);
     obj3.setIcon(img.scaled(100, 100));
         obj3.setTextLine1("Nom : "+list.get(i).getNom());
         obj3.setTextLine2("Prenom : "+list.get(i).getDescription());
         obj3.setTextLine3("Reserver le : "+list.get(i).getDate_debut());
        c.add(obj3);
      }
      
     
     f.getToolbar().addCommandToLeftBar("back", null, (ev)->{detailEvenement lll;
           
                lll = new detailEvenement(e);
                lll.getF().show();
           
            
            });
     f.add(c);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

   
    
}
