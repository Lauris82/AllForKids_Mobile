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
import com.allforkids.yasmine.entities.AssociationEntity;
import com.allforkids.yasmine.services.AssService;
import com.allforkids.yasmine.services.validation;
import com.codename1.ui.Toolbar;

/**
 *
 * @author DELL
 */
public class ajoutAss {

    Form f;
    TextField nom;
    TextField desc;
    TextField numTel;
    TextField gouv;

    Button btnajout;

    private Command Home;
    private Command quit;
    private Command listeAss;
    private Command ajouterAss;
    private Command rec;

    public ajoutAss() {
        f = new Form("Ajout Association");
        f.setUIID("LoginForm");
        Toolbar tb = f.getToolbar();
        tb.setUIID("ToolBarFont");
        nom = new TextField();
        desc = new TextField();
        numTel = new TextField();
        gouv = new TextField();
        btnajout = new Button("ajouter");

        
        nom.setHint("nom");
        desc.setHint("description");
        numTel.setHint("Num Tel°");
        gouv.setHint("gouvernerat");
        
        Home = new Command("Home");
        listeAss = new Command("Liste des Association");
        quit = new Command("Quitter l'application");
        ajouterAss = new Command("Ajouter Association");
        rec = new Command("Envoyer Reclamation");

        f.add(nom);
        f.add(desc);
        f.add(numTel);
        f.add(gouv);
        f.add(btnajout);

        f.getToolbar().addCommandToSideMenu("Home", null, (ActionListener) (ActionEvent evt) -> {
            HomeForm hm = new HomeForm();
            hm.getF().show();
            System.out.println("Home Confirme");
        });
        f.getToolbar().addCommandToSideMenu("Ajouter Association", null, (ActionListener) (ActionEvent evt) -> {
            ajoutAss ajoutA = new ajoutAss();
            ajoutA.getF().show();
        });
        f.getToolbar().addCommandToSideMenu("Lister Association", null, (ActionListener) (ActionEvent evt) -> {
            listeAss list = new listeAss();
            list.getF().show();
        });

        f.getToolbar().addCommandToSideMenu("Envoyer Reclamation", null, (ActionListener) (ActionEvent evt) -> {
            envoiRec r = new envoiRec();
            r.getF().show();

        });

        f.getToolbar().addCommandToSideMenu("Quitter l'application", null, (ActionListener) (ActionEvent evt) -> {
            Display.getInstance().exitApplication();
        });

        btnajout.addActionListener((e) -> {

            boolean isnotempty = validation.isTextFieldNotEmpty(nom);
            boolean isnotempty1 = validation.isTextFieldNotEmpty(desc);
            boolean isnotempty2 = validation.isTextFieldNotEmpty(gouv);
            boolean isnotempty3 = validation.isTextFieldNotEmpty(numTel);

         boolean isespace;
            isespace=numTel.getText().trim().length() > 0;
  
             if (isnotempty && isnotempty1 && isnotempty2 && isnotempty3 && isespace ){
                 if(ValidInputs()){
                    AssService s = new AssService();
                    AssociationEntity c = new AssociationEntity();
                    c.setNom(nom.getText());
                    c.setDescription(desc.getText());
                    c.setNum_tel(Integer.valueOf(numTel.getText()));
                    c.setGouvernorat(gouv.getText());

                    s.ajoutAss(c);
                    Dialog.show("Associations", "Association ajouté !", "ok", null);
                    
                        listeAss list = new listeAss();
            list.getF().show();
                }
            } else {
                Dialog.show("Alerte", "veuillez saisir tous les champs !", "ok", null);
            }

        });

    }

    public boolean ValidInputs() {

        if (isNotInteger(numTel.getText())) {
            Dialog.show("Alerte", "Numéro de téléphone non valide", "OK", null);
            return false;
        }
        

        return true;
    }

    public static boolean isNotInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return true;
        }

        return false;
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
