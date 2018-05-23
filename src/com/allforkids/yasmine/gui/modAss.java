/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yasmine.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.allforkids.yasmine.entities.AssociationEntity;
import com.allforkids.yasmine.services.AssService;
import com.codename1.ui.Toolbar;

/**
 *
 * @author DELL
 */
public class modAss {
    
    Form f;
    TextField nom;
    TextField desc;
    TextField numTel;
    TextField gouv;

    Button btnMod;

//////    private Command Home;
//////    private Command quit;
//////    private Command listeClub;
//////    private Command ajouterClub;
    public modAss(AssociationEntity ass) {
        f = new Form("Modification");
        f.setUIID("LoginForm");
        Toolbar tb = f.getToolbar();
        tb.setUIID("ToolBarFont");
        nom = new TextField();
        desc = new TextField();
        numTel = new TextField();
        gouv = new TextField();
        
         nom.setHint("nom");
        desc.setHint("description");
        numTel.setHint("Num Tel°");
        gouv.setHint("gouvernerat");
        
        btnMod = new Button("Modifier");

        nom.setText(ass.getNom());
        desc.setText(ass.getDescription());
        numTel.setText(String.valueOf(ass.getNum_tel()));
        gouv.setText(ass.getGouvernorat());

//////        Home = new Command("Home");
//////        listeClub = new Command("Liste des clubs");
//////        quit = new Command("Quitter l'application");
//////        ajouterClub = new Command("Ajouter Club");
        f.add(nom);
        f.add(desc);
        f.add(numTel);
        f.add(gouv);
        f.add(btnMod);

        btnMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AssService s = new AssService();
                AssociationEntity c = new AssociationEntity();
                c.setNom(nom.getText());
                c.setDescription(desc.getText());
                c.setNum_tel(Integer.valueOf(numTel.getText()));
                c.setGouvernorat(gouv.getText());
                //
                c.setId_aasociation(ass.getId_aasociation());
                s.modifierA(c);
                Dialog.show("Associations", "Association modifier !", "ok", null);
                homeAss h = new homeAss();
                h.getF().show();
                //            detailClub d;
                //            d = new detailClub(c);
                //            d.getF().show();

                ;
            }
        });

    }

//        f = new Form("home");
//        tnom = new TextField();
//        tetat = new TextField();
//        btnajout = new Button("ajouter");
//        btnaff=new Button("Affichage");
//        f.add(tnom);
//        f.add(tetat);
//        f.add(btnajout);
//        f.add(btnaff);
//        btnajout.addActionListener((e) -> {
//            ServiceTask ser = new ServiceTask();
//            Task t = new Task(0, tnom.getText(), tetat.getText());
//            ser.ajoutTask(t);
//            
//
//        });
//        btnaff.addActionListener((e)->{
//        Affichage a=new Affichage();
//        a.getF().show();
//        });

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
   
    
    
    
 }   

