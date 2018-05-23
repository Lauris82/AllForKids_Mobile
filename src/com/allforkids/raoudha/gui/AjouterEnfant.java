/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.raoudha.gui;

import com.codename1.components.OnOffSwitch;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.allforkids.raoudha.entities.Garderie;

/**
 *
 * @author user
 */
public class AjouterEnfant 
{
    Form f;
        TextField txt1,txt2;
        Picker datePicker;
    Button ajouter;

    public AjouterEnfant( Garderie g) 
    {
        
     
    
     f =new Form("Ajout d'enfant",BoxLayout.y());
    TextField txt1=new TextField("","Nom");
    TextField txt2=new TextField("","Prenom");
           Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        f.add(txt1);
        f.add(txt2); 
        f.add(datePicker);
 Container c2=new Container(BoxLayout.x());
          OnOffSwitch on =new OnOffSwitch();
          Label lb=new Label("genre");
          c2.add(lb);
          c2.add(on);
           f.add(c2);
          CheckBox ch1 =new CheckBox("Nourrisson");
          CheckBox ch2 =new CheckBox("Tout Petit");
          CheckBox ch3 =new CheckBox("Préscolaire");
          CheckBox ch4 =new CheckBox("Scolaire");
          f.add(ch1);
          f.add(ch2);
          f.add(ch3);
          f.add(ch4);

    
    ajouter =new Button("Ajouter");
    f.add(ajouter);
    
    ajouter.addActionListener(new ActionListener(){ ///pour faire l'action
    @Override
    public void actionPerformed(ActionEvent evt)
    {String ch="";
    String sh="";
        if (on.isValue())
            ch="femme";
        else {ch= "homme";}
        
        if (ch1.isSelected())
                sh=ch1.getText();
         if (ch2.isSelected())
             sh=ch2.getText();
          if (ch3.isSelected())
             sh=ch3.getText();
           if (ch4.isSelected())
             sh=ch4.getText();
           
            ConnectionRequest req = new ConnectionRequest();
            
            String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd");
        dateString = sdfr.format( datePicker.getDate());
            req.setUrl("http://localhost/AllForKids/web/app_dev.php/AjouterEnfantMobile?nom="+ txt1.getText()+ "&prenom=" + txt2.getText()+"&dateNaissance="+dateString+"&categorie="+sh
                    +"&sexe="+ch+"");
            req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println(str);
             });
        NetworkManager.getInstance().addToQueueAndWait(req);  
           
    
    }});

    
    } 
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
            
    
}
