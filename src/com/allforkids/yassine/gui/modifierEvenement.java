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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;
import java.util.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author ASUS
 */
public class modifierEvenement {
     User user_Connect;
    Form f ;
    Button modif ;
  private  ImageViewer imgv ;
  private  Image img ;
    public modifierEvenement(evenement e) throws IOException{
         EncodedImage  enc = EncodedImage.create("/giphy.gif");
         
        LoginForm log = new LoginForm();
        user_Connect = log.getUser();
         
        try {
            f = new Form("Modifier evenement");
            Container c=new Container(BoxLayout.y());
            TextField nom = new TextField("","Nom");
            TextField desc = new TextField("","Description");
            TextField emp = new TextField("","Emplacement");
            Button image=new Button("Choisir image");
            Slider p=new Slider();
            p.setMaxValue(100);
            p.setMinValue(0);
            p.setProgress(1); // Set  the starting value
            p.setEditable(true);
            Label ll=new Label();
            p.setText("Nombre de places");
            ll.setText(String.valueOf(e.getNbr_place()));
             
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
            Container datef = new Container(BoxLayout.x());
            dated.addAll(dd0,dateD);
            Picker dateF = new Picker();
            dateD.setType(Display.PICKER_TYPE_DATE);
            Label dd=new Label("Date fin :    ");
            datef.addAll(dd,dateF);
            
            modif = new Button("Modifier");
              img = URLImage.createToStorage(enc, "image"+e.getImage(), "http://localhost/AllForKids/web/image_evenement/"+e.getImage(), URLImage.RESIZE_SCALE);
            imgv = new ImageViewer(img);
            nom.setText(e.getNom());
            desc.setText(e.getDescription());
            emp.setText(e.getEmplacement());
            SimpleDateFormat formatterr=new SimpleDateFormat("dd-MM-yyyy");
            String datea=e.getDate_debut();
            Date datee = formatterr.parse(datea);
             String dateb=e.getDate_fin();
            Date dateee = formatterr.parse(dateb);           
            dateD.setDate(datee);
            dateF.setDate(dateee);
       
            modif.addActionListener((ee) -> {
                serviceEvenement ser = new serviceEvenement();
                evenement ev=new evenement();
                SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd");
                String date1=formatter.format(dateD.getValue());
                date1=date1.replace('/', '-');
                String date2=formatter.format(dateF.getValue());
                date2=date2.replace('/', '-');
                ev.setDate_debut(date1);
                ev.setDate_fin(date2);
                ev.setNom(nom.getText());
                ev.setDescription(desc.getText());
                ev.setEmplacement(emp.getText());
                ev.setNbr_place(Integer.valueOf(ll.getText()));
                System.out.println(imgv.getImage().getImageName());
                if(imgv.getImage().getImageName()==null)
                {
                    ev.setImage(e.getImage());
                }else{
                ev.setImage(imgv.getImage().getImageName());
                }
                ev.setEvenement_user(user_Connect.getId());
                ev.setId_evenement(e.getId_evenement());
                ser.modifierEvenement(ev);
                try {
                     listEvenement li;

                    li = new listEvenement();
                    li.getF().show();
                    
                } catch (IOException ex) {
                }
                
                
            });
            f.getToolbar().addCommandToLeftBar("back", null, (ev)->{detailEvenement lll;
           
                lll = new detailEvenement(e);
                lll.getF().show();
           
            
            });
              c.add(nom);
            c.add(desc);
            c.add(emp);
            c.add(p);
            c.add(ll);
            c.add(dated);
            c.add(datef);
            c.add(imgv);
              c.add(image);
            c.add(modif);
                image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                serviceEvenement e=new serviceEvenement();
                e.browseImage(imgv);
               
            }
        });
            f.add(c);
        } catch (ParseException ex) {
            System.out.println("erreuuuuuuuuuur");
        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
