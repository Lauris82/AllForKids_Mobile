/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yassine.gui;

import com.allforkids.Ettien.entities.User;
import com.allforkids.Ettien.forms.LoginForm;
import com.allforkids.yassine.entities.evenement;
import com.allforkids.yassine.services.fbConnect;
import com.allforkids.yassine.services.serviceEvenement;
import com.allforkids.yassine.services.serviceReservation;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class detailEvenement {
     User user_Connect;
    Form f ;
   public static evenement ev ;
    private ImageViewer imgv;
    private Image img;
    public detailEvenement(evenement e) {
        
        LoginForm log = new LoginForm();
        user_Connect = log.getUser();
        
       ev=e;
         serviceReservation ser=new serviceReservation();
        f=new Form("Dètail evenement");
                    Container c =new Container(BoxLayout.y());
        try {
            
            Label l2=new Label(e.getNom());
             l2.getAllStyles().setFgColor(0xFF0000);
             
            EncodedImage  enc = EncodedImage.create("/giphy.gif");
            img = URLImage.createToStorage(enc, "image"+e.getImage(), "http://localhost/AllForKids/web/image_evenement/"+e.getImage(), URLImage.RESIZE_SCALE);
            imgv = new ImageViewer(img);
            Label l3=new Label("Description : ");
            l3.getAllStyles().setFgColor(0xFF0000);
            SpanLabel l4=new SpanLabel(e.getDescription());
            Label l5=new Label("Emplacement : ");
            l5.getAllStyles().setFgColor(0xFF0000);
            Label l6=new Label(e.getEmplacement());
            Label l7=new Label("Date dèbut : ");
            l7.getAllStyles().setFgColor(0xFF0000);
            Label l8=new Label(e.getDate_debut());
            Label l9=new Label("Date fin : ");
            l9.getAllStyles().setFgColor(0xFF0000);
            Label l10=new Label(e.getDate_fin());
            Label l11=new Label("Nombre de place : ");
            l11.getAllStyles().setFgColor(0xFF0000);
            Label l12=new Label();
            Button modif=new Button("Modifier");
            Button supp=new Button("Supprimer");
            Button mbr=new Button("Membres evenement");
            Button res=new Button("res");
         
                 f.getToolbar().addCommandToOverflowMenu("Partager ", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                     fbConnect fb=new fbConnect();
                     
                    fb.show();

            }
        });
                     
           
            if(e.getEvenement_user()==user_Connect.getId()){
                 f.getToolbar().addCommandToOverflowMenu("Modifier", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                                   modifierEvenement m;
                    try {
                        m = new modifierEvenement(e);
                          m.getF().show();
                    } catch (IOException ex) {
                    }
            }
        });
                       f.getToolbar().addCommandToOverflowMenu("Supprimer ", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                                      serviceEvenement s=new serviceEvenement();
                    s.supprimerEvenement(e.getId_evenement());
                    try {
                         listEvenement l;
                        l = new listEvenement();
                        l.getF().show();
                    } catch (IOException ex) {
                    }

            }
        });
                               f.getToolbar().addCommandToOverflowMenu("Voir membres ", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                                        try {
                        membreEvenement m;
                        m = new membreEvenement(e,e.getId_evenement(),e.getNbr_place());
                        m.getF().show();

                    } catch (IOException ex) {
                    }

            }
        });
                
//            modif.setVisible(true);
//            supp.setVisible(true);
//            mbr.setVisible(true);
            res.setVisible(false);
            
            
            }else{
//                 modif.setVisible(false);
//            supp.setVisible(false);
//            mbr.setVisible(false);
            res.setVisible(true);
            }
            if(e.getNbr_place()-ser.nbrReservation(e.getId_evenement())==0)
            {
             l12.setText("Complet");
             res.setVisible(false);
             if(ser.estReserver(user_Connect.getId(), e.getId_evenement())==false)
            {
                res.setVisible(false);
            }
                    }else{
                     l12.setText("Encore disponible");

            }
            if(ser.estReserver(user_Connect.getId(), e.getId_evenement())==true)
            {
                res.setText("Annuler participation");
            }else{
            res.setText("Participer");

            }
            res.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                  if(res.getText()=="Participer")
                  {
                      ser.Reserver(user_Connect.getId(), e.getId_evenement());
                    res.setText("Annuler participation");

                  }else{
                      ser.dereserver(user_Connect.getId(), e.getId_evenement());
                     res.setText("Participer");

                  }
                }
            });
            modif.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    modifierEvenement m;
                    try {
                        m = new modifierEvenement(e);
                          m.getF().show();
                    } catch (IOException ex) {
                    }
                  
                }
            });
            mbr.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        membreEvenement m;
                        m = new membreEvenement(e,e.getId_evenement(),e.getNbr_place());
                        m.getF().show();

                    } catch (IOException ex) {
                    }
                }
            });
            supp.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                  
                    serviceEvenement s=new serviceEvenement();
                    s.supprimerEvenement(e.getId_evenement());
                    try {
                         listEvenement l;
                        l = new listEvenement();
                        l.getF().show();
                    } catch (IOException ex) {
                    }
                    
                }
            });
           
           
            c.add(l2);
            c.add(imgv);
            c.add(l3);
            c.add(l4);
            c.add(l5);
            c.add(l6);
            c.add(l7);
            c.add(l8);
            c.add(l9);
            c.add(l10);
            c.add(l11);
            c.add(l12);
            c.add(res);
          //  c.add(modif);
            //c.add(supp);
            //c.add(mbr);
        } catch (IOException ex) {
        }
        f.getToolbar().addCommandToLeftBar("back", null, (ev)->{listEvenement l;
             try {
                 l = new listEvenement();
                   l.getF().show();
             } catch (IOException ex) {
             }
        
          });
         f.add(c);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public static evenement getEv() {
        return ev;
    }

    public static void setEv(evenement ev) {
        detailEvenement.ev = ev;
    }
    
}
