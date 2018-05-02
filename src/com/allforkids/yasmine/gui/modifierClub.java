/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yasmine.gui;

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

/**
 *
 * @author DELL
 */
public class modifierClub {

    Form f;
    TextField nom;
    TextField desc;
    TextField numTel;
    TextField gouv;

    Button modifier;

    private Command Home;
    private Command quit;
    private Command listeClub;
    private Command ajouterClub;

    
    //String n, String d, String tel, String g, int id
    public modifierClub(ClubEntity NV) {

        modifier = new Button();
nom=new TextField();
desc=new TextField();
numTel=new TextField();
gouv=new TextField();


        nom.setText(NV.getNom());
        desc.setText(NV.getDescription());
        numTel.setText(NV.getNumTel());
        gouv.setText(NV.getGouvernorat());
        
        modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClubService s = new ClubService();
                ClubEntity clu = new ClubEntity();
                clu.setNom(nom.getText());
                clu.setDescription(desc.getText());
                clu.setNumTel(numTel.getText());
                clu.setGouvernorat(gouv.getText());
                clu.setIdclub(NV.getIdclub());

//
                s.modifierC(clu);
                Dialog.show("Clubs", "Club modifié !", "ok", null);
            }
        });

        f.add(nom);
        f.add(desc);
        f.add(numTel);
        f.add(gouv);
        f.add(modifier);

        //--Ajout des menu (command) a la fenetre
        f.getToolbar().addCommandToSideMenu(Home);
        f.getToolbar().addCommandToSideMenu(quit);
        f.getToolbar().addCommandToSideMenu(listeClub);
        f.getToolbar().addCommandToSideMenu(ajouterClub);

        f.addCommandListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                Command cmd = evt.getCommand();
                if (cmd == Home) {
                    homeClub h;
                    h = new homeClub();

                } else if (cmd == quit) {

                    Display.getInstance().exitApplication();

                } else if (cmd == listeClub) {

                    listerC list = new listerC();
                    list.getF().show();

                } else if (cmd == ajouterClub) {

                    ajoutClub ajoutC = new ajoutClub();
                    ajoutC.getF().show();

                }

            }
        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.setText(nom);
    }

    public TextField getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc.setText(desc);
    }

    public TextField getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel.setText(numTel);
    }

    public TextField getGouv() {
        return gouv;
    }

    public void setGouv(String gouv) {
        this.gouv.setText(gouv);
    }

}
