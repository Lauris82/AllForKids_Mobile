/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yassine.gui;

import com.allforkids.Ettien.entities.User;
import com.allforkids.Ettien.forms.LoginForm;
import com.allforkids.yassine.entities.evenement;
import com.allforkids.yassine.services.serviceEvenement;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.validation.Constraint;
import com.codename1.ui.validation.Validator;

import java.io.IOException;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class creerEvenement {
      Form f;
    Button btnajout; 
     ImageViewer im;
     
     User user_Connect;
    
    public creerEvenement() {
        f = new Form("Crèer evenement");
        Container c=new Container(BoxLayout.y());
        TextField nom = new TextField("","Nom");
      TextField desc = new TextField("","Description");
       TextField emp = new TextField("","Emplacement");
       Button image=new Button("Choisir image");
        Label l=new Label("Nombre places");
        Slider p=new Slider();
         p.setMaxValue(100);
    p.setMinValue(0); 
    p.setProgress(1); // Set  the starting value
    p.setEditable(true);
    Label ll=new Label();
     im=new ImageViewer();
    p.addDataChangedListener(new DataChangedListener() {

            @Override
            public void dataChanged(int type, int index) {
                ll.setText(String.valueOf(index));
                    f.refreshTheme();
            }
        });
    Container dated = new Container(BoxLayout.x());
        Picker dateD = new Picker();
        dateD.setType(Display.PICKER_TYPE_DATE);
        Label dd0=new Label("Date dèbut :");
         dated.addAll(dd0,dateD);
        Container datef = new Container(BoxLayout.x());
        Picker dateF = new Picker();
        dateD.setType(Display.PICKER_TYPE_DATE);
        Label dd=new Label("Date fin :    ");
        datef.addAll(dd,dateF);
      
       
   
     
        btnajout = new Button("ajouter");
      
        c.add(nom);
        c.add(desc);
        c.add(emp);
        c.add(l);
        c.add(p);
         c.add(ll);
        c.add(dated);
        c.add(datef);
       // c.add(im);
        c.add(image);
        c.add(btnajout);
         image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                serviceEvenement e=new serviceEvenement();
                e.browseImage(im);
            }
        });
          Validator validator = new Validator();
                validator.addSubmitButtons(btnajout);
                           validator.addConstraint(nom, new Constraint() {
          @Override
          public boolean isValid(Object value) {
  return !String.valueOf(value).equals("");
                           }

          @Override
          public String getDefaultFailMessage() {
return "invalid nom";}
      });
validator.addConstraint(desc, new Constraint() {
          @Override
          public boolean isValid(Object value) {
  return !String.valueOf(value).equals("");
                           }

          @Override
          public String getDefaultFailMessage() {
return "invalid username";}
      });
 validator.addConstraint(emp, new Constraint() {
          @Override
          public boolean isValid(Object value) {
  return !String.valueOf(value).equals("");
                           }

          @Override
          public String getDefaultFailMessage() {
return "invalid username";}
      });
 validator.addConstraint(dateD, new Constraint() {
          @Override
          public boolean isValid(Object value) {
  return !dateD.getValue().equals(null);
                           }

          @Override
          public String getDefaultFailMessage() {
return "invalid username";}
      });

           
        LoginForm log = new LoginForm();
        user_Connect = log.getUser();

          btnajout.addActionListener((e) -> {
            serviceEvenement ser = new serviceEvenement();
            evenement ev=new evenement();
             SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd");
           String date1=formatter.format(dateD.getValue());
           date1=date1.replace('/', '-');
           ev.setDate_debut(date1);
           String date2=formatter.format(dateF.getValue());
           date2=date2.replace('/', '-');
           ev.setDate_fin(date2);
            ev.setNom(nom.getText());
            ev.setDescription(desc.getText());
            ev.setEmplacement(emp.getText());
            ev.setNbr_place(Integer.valueOf(ll.getText()));
            ev.setImage(im.getImage().getImageName());
            ev.setEvenement_user(user_Connect.getId());
            ser.ajoutEvenement(ev);
           listEvenement li;
          
            try {
                li = new listEvenement();
                li.getF().show();

            } catch (IOException ex) {
            }
            

        });
           f.getToolbar().addCommandToLeftBar("back", null, (ev)->{listEvenement lll;
             try {
                 lll = new listEvenement();
                   lll.getF().show();
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

 
}
