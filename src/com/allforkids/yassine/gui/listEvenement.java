/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yassine.gui;

import com.allforkids.Ettien.forms.HomeForm;
import com.allforkids.yassine.entities.evenement;
import com.allforkids.yassine.services.serviceEvenement;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */

public class listEvenement {
 Form f ;   
int i ;
List l ;
    private ImageViewer imgv;
    private Image img;
    public listEvenement() throws IOException {
     f=new Form("List Evenements",new FlowLayout(Component.CENTER,Component.CENTER));
     serviceEvenement s=new serviceEvenement();
        ArrayList<evenement> list=s.getListEvenement2();
        Container c=new Container(BoxLayout.y());
        for(i=0 ; i<list.size(); i++)
        {  
            evenement e=new evenement();
            e.setNom(list.get(i).getNom());
            e.setDescription(list.get(i).getDescription());
            e.setEmplacement(list.get(i).getEmplacement());
            e.setEvenement_user(list.get(i).getEvenement_user());
            e.setId_evenement(list.get(i).getId_evenement());
            e.setDate_debut(list.get(i).getDate_debut());
            e.setDate_fin(list.get(i).getDate_fin());
            e.setImage(list.get(i).getImage());
            e.setNbr_place(list.get(i).getNbr_place());
            Label l =new Label();
            l.setText(list.get(i).getNom());
            l.getAllStyles().setFgColor(0xf5bf0a);
            EncodedImage  enc = EncodedImage.create("/giphy.gif");
          img = URLImage.createToStorage(enc, "image"+list.get(i).getImage(), "http://localhost/AllForKids/web/image_evenement/"+list.get(i).getImage(), URLImage.RESIZE_SCALE);
                        imgv = new ImageViewer(img);
            l.addPointerPressedListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    detailEvenement d;
                    d = new detailEvenement(e);
                    d.getF().show();
                }
            });
            c.add(l);
            c.add(imgv);
            
        }
          f.getToolbar().addCommandToOverflowMenu("crèer", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                creerEvenement c=new creerEvenement();
                c.getF().show();
            }
        });
          
          f.getToolbar().addCommandToLeftBar("back", null, (ev)->{
              HomeForm hm = new HomeForm();
              hm.getF().show();
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
