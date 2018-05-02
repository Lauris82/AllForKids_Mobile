/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yasmine.gui;

import com.allforkids.Ettien.forms.HomeForm;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.allforkids.yasmine.entities.ClubEntity;
import com.allforkids.yasmine.services.ClubService;
import com.codename1.ui.Toolbar;

/**
 *
 * @author DELL
 */
public class ajoutClub {

    Form f;
    TextField nom;
    TextField desc;
    TextField numTel;
    TextField gouv;

    Button btnajout;

    private Command Home;
    private Command quit;
    private Command listeClub;
    private Command ajouterClub;

    public ajoutClub() {
        f = new Form("Ajout Club");
        f.setUIID("LoginForm");
        Toolbar tb = f.getToolbar();
        tb.setUIID("ToolBarFont");
        nom = new TextField();
        desc = new TextField();
        numTel = new TextField();
        gouv = new TextField();
        btnajout = new Button("ajouter");

        Home = new Command("Home");
        listeClub = new Command("Liste des clubs");
        quit = new Command("Quitter l'application");
        ajouterClub = new Command("Ajouter Club");

        f.add(nom);
        f.add(desc);
        f.add(numTel);
        f.add(gouv);
        f.add(btnajout);

        f.getToolbar().addCommandToSideMenu("Home", null,(ActionListener) (ActionEvent evt) -> {
            HomeForm hm = new HomeForm();
            hm.getF().show();
            System.out.println("Home Confirme");
        });
        f.getToolbar().addCommandToSideMenu("Ajouter Club", null,(ActionListener) (ActionEvent evt) -> {
            ajoutClub ajoutC = new ajoutClub();
            ajoutC.getF().show();
        });
        f.getToolbar().addCommandToSideMenu("Lister Club", null,(ActionListener) (ActionEvent evt) -> {
            listerC list = new listerC();
            list.getF().show();
        });
        f.getToolbar().addCommandToSideMenu("Quitter l'application", null,(ActionListener) (ActionEvent evt) -> {
            Display.getInstance().exitApplication();
        });

        btnajout.addActionListener((e) -> {

            ClubService s = new ClubService();
            ClubEntity c = new ClubEntity();
            c.setNom(nom.getText());
            c.setDescription(desc.getText());
            c.setNumTel(numTel.getText());
            c.setGouvernorat(gouv.getText());

            s.ajoutClub(c);
            Dialog.show("Clubs", "Club ajouté !", "ok", null);
        });

//        f.addCommandListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//
//                Command cmd = evt.getCommand();
//                if (cmd == Home) {
//                    homeClub h;
//                    h = new homeClub();
//
//                } else if (cmd == quit) {
//
//                    Display.getInstance().exitApplication();
//
//                } else if (cmd == listeClub) {
//
//                    listerC list = new listerC();
//                    list.getF().show();
//
//                } else if (cmd == ajouterClub) {
//
//                    ajoutClub ajoutC = new ajoutClub();
//                    ajoutC.getF().show();
//
//                }
//
//            }
//        });

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

    public TextField getNom() {
        return nom;
    }

    public void setNom(TextField nom) {
        this.nom = nom;
    }

    public TextField getDesc() {
        return desc;
    }

    public void setDesc(TextField desc) {
        this.desc = desc;
    }

    public TextField getNumTel() {
        return numTel;
    }

    public void setNumTel(TextField numTel) {
        this.numTel = numTel;
    }

    public TextField getGouv() {
        return gouv;
    }

    public void setGouv(TextField gouv) {
        this.gouv = gouv;
    }

    public Button getBtnajout() {
        return btnajout;
    }

    public void setBtnajout(Button btnajout) {
        this.btnajout = btnajout;
    }

}
