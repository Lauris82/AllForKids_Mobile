/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yassine.gui;

import com.allforkids.yassine.entities.evenement;
import com.allforkids.yassine.services.serviceEvenement;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import javafx.scene.control.DatePicker;

/**
 *
 * @author ASUS
 */
public class creerEvenement {
      Form f;
    Button btnajout; 
    public creerEvenement() {
        f = new Form("home");
        TextField nom = new TextField();
      TextField desc = new TextField();
        Calendar dateD=new Calendar();
       TextField emp = new TextField();
       TextField img = new TextField();
       TextField nbrPlace = new TextField();
        btnajout = new Button("ajouter");
      
        f.add(nom);
        f.add(desc);
        f.add(dateD);
        f.add(btnajout);

//        btnajout.addActionListener((e) -> {
//            serviceEvenement crud= new serviceEvenement();
//            evenement ev = new evenement(5,nom.getText(), desc.getText(),);
//            crud.ajoutEvenement(ev);
//            
//
//        });
}
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

 
}
