/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.raoudha.gui;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.allforkids.raoudha.Services.serviceGarderie;
import com.allforkids.raoudha.entities.Garderie;
import com.allforkids.raoudha.myapp.MyApplication;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class detailGard 
{
    Form f ;
int i;
    public detailGard( Garderie g) 
    
    {
        f=new Form("Ma Garderie");
        serviceGarderie s=new serviceGarderie();
ArrayList<Garderie> list =s.AffichedetailGard(g);
     Container c=new Container(BoxLayout.y());
     
      
         //Container c1=new Container(BoxLayout.x());
         for(i=0 ; i<list.size(); i++)
        {
             Garderie g1=new Garderie();
            g1.setNom(list.get(i).getNom());
             // System.out.println("debut");
            g1.setDescription(list.get(i).getDescription());
            g1.setCapacite(list.get(i).getCapacite());
            g1.setEmplacement(list.get(i).getEmplacement());
            g1.setNum_tel(list.get(i).getNum_tel());
         g1.setUser_garderie(list.get(i).getUser_garderie());
        g1.setId_garderie(list.get(i).getId_garderie());
            g1.setNomImage(list.get(i).getNomImage());
        
              Label l1 =new Label();
              
            l1.setText("Nom :"+list.get(i).getNom());
     Label l2 =new Label();
            l2.setText("A propos :"+list.get(i).getDescription());
          Label l3 =new Label();
            l3.setText("Emplacement :"+list.get(i).getEmplacement()); 
              Label l4 =new Label();
           l4.setText("Numéro tél°:"+list.get(i).getNum_tel());
              Label l5 =new Label();
           l5.setText("Capacité:"+list.get(i).getCapacite());
           c.add(l1);
           c.add(l2);
           c.add(l3);
           c.add(l4);
           c.add(l5);
  
            
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
