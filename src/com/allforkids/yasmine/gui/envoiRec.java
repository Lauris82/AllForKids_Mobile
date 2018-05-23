/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yasmine.gui;

import allforkids.yasmine.entities.ReclamationEntity;
import com.allforkids.Ettien.forms.HomeForm;
import com.allforkids.yasmine.entities.AssociationEntity;
import com.allforkids.yasmine.services.AssService;
import com.allforkids.yasmine.services.RecService;
import com.allforkids.yasmine.services.validation;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author DELL
 */
public class envoiRec {

    Form f;
    TextField mail;
    TextField objet;
    TextField contenu;

    Button btnenvoi;

    private Command Home;
    private Command quit;

    private Command rec;

    public envoiRec() {
        f = new Form("Envoi Reclamation");
        f.setUIID("LoginForm");
        Toolbar tb = f.getToolbar();
        tb.setUIID("ToolBarFont");
        mail = new TextField();
        objet = new TextField();
        contenu = new TextField();

        mail.setHint("mail");
        objet.setHint("objet");
        contenu.setHint("contenu de la reclamation");

        btnenvoi = new Button("Envoyer");

        Home = new Command("Home");
        quit = new Command("Quitter l'application");
        rec = new Command("Envoyer Reclamation");

        f.add(mail);
        f.add(objet);
        f.add(contenu);

        f.add(btnenvoi);

        f.getToolbar().addCommandToSideMenu("Home", null, (ActionListener) (ActionEvent evt) -> {
            HomeForm hm = new HomeForm();
            hm.getF().show();
            System.out.println("Home Confirme");
        });

        f.getToolbar().addCommandToSideMenu("Envoyer Reclamation", null, (ActionListener) (ActionEvent evt) -> {
            envoiRec r = new envoiRec();
            r.getF().show();

        });

        f.getToolbar().addCommandToSideMenu("Quitter l'application", null, (ActionListener) (ActionEvent evt) -> {
            Display.getInstance().exitApplication();
        });

        btnenvoi.addActionListener((e) -> {

            boolean isnotempty = validation.isTextFieldNotEmpty(mail);
            boolean isnotempty1 = validation.isTextFieldNotEmpty(objet);
            boolean isnotempty2 = validation.isTextFieldNotEmpty(contenu);

            if (isnotempty && isnotempty1 && isnotempty2) {
                if (ValidInputs()) {
                    RecService s = new RecService();
                    ReclamationEntity c = new ReclamationEntity();
                    c.setMail(mail.getText());
                    c.setObjetRec(objet.getText());
                    c.setContenuRec(contenu.getText());

                    s.envoiRec(c);
                    Dialog.show("Reclamations", "Reclamtion envoyée !", "ok", null);

                    HomeForm hm = new HomeForm();
                    hm.getF().show();

                }
            } else {
                Dialog.show("Alert", "veuillez saisir tous les champs !", "ok", null);
            }

        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public boolean ValidInputs() {

        if ((!mail.getText().contains("@")) || (!mail.getText().contains("."))) {
            Dialog.show("Alerte", "Email non valide", "OK", null);
            return false;
        }

        return true;
    }

}
