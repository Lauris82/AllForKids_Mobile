/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yasmine.gui;

import com.allforkids.Ettien.forms.HomeForm;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.allforkids.yasmine.entities.ClubEntity;
import com.allforkids.yasmine.services.ClubService;
import com.codename1.ui.Dialog;
import com.codename1.ui.Toolbar;
import java.io.IOException;

/**
 *
 * @author DELL
 */
class detailClub {

    Form f;
    ClubEntity e;

    private Command Home;
    private Command quit;
    private Command listeClub;
    private Command ajouterClub;
    private Command rec;

    Button modifier;
    Button supprimer;
    Button emp;

    int idClub;
    String num;
    String nom;
    String desc;
    String gouve;
    listerC list;

    public detailClub(ClubEntity c) throws IOException{

        f = new Form(c.getNom() + " : ", new FlowLayout(Component.CENTER, Component.CENTER));
        f.setUIID("LoginForm");
        Toolbar tb = f.getToolbar();
        tb.setUIID("ToolBarFont");

        System.out.println("test id :" + c.getIdclub());

        Home = new Command("Home");
        listeClub = new Command("Liste des clubs");
        quit = new Command("Quitter l'application");
        ajouterClub = new Command("Ajouter Club");
        rec = new Command("Envoyer Reclamation");

        Container c1 = new Container(BoxLayout.y());
        Container cc2 = new Container(BoxLayout.x());
        Container cc3 = new Container(BoxLayout.x());

        Container cc = new Container(BoxLayout.x());
        Label ll = new Label("Num Tel :");
        Label l = new Label(c.getNumTel());
        l.getAllStyles().setFgColor(0x191970);
        ll.getAllStyles().setFgColor(0x191970);
        cc.add(ll);
        cc.add(l);

        Label ll2 = new Label("Gouvernerat :");
        Label l2 = new Label(c.getGouvernorat());
        l2.getAllStyles().setFgColor(0x191970);
                ll2.getAllStyles().setFgColor(0x191970);

        cc2.add(ll2);
        cc2.add(l2);

        
        Label ll3 = new Label("Description :");
        Label l3 = new Label(c.getDescription());
        l3.getAllStyles().setFgColor(0x191970);
                ll3.getAllStyles().setFgColor(0x191970);

        cc3.add(ll3);
        cc3.add(l3);

        idClub = c.getIdclub();
        nom = c.getNom();
        num = c.getNumTel();
        desc = c.getDescription();
        gouve = c.getGouvernorat();
        ClubEntity NvClub;
        NvClub = new ClubEntity();
        NvClub.setDescription(desc);
        NvClub.setGouvernorat(gouve);
        NvClub.setIdclub(idClub);
        NvClub.setNom(nom);
        NvClub.setNumTel(num);

        modifier = new Button("Modifier");
        modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                modClub C = new modClub(NvClub);
                C.getF().show();
            }
        });

        supprimer = new Button("Supprimer");
        supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ClubService s = new ClubService();
                s.supprimerC(NvClub);

                Dialog.show("Clubs", "Club supprimer !", "ok", null);

                listerC l = new listerC();
                l.getF().show();

            }
        });

        emp = new Button("Afficher l'emplacement");
        emp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                    map m = new map();
                try {
                    m.lister(NvClub.getGouvernorat(), NvClub.getNom());
                } catch (IOException ex) {
                }
               

            }
        });

        c1.add(cc);
        c1.add(cc2);
        c1.add(cc3);
        c1.add(modifier);
        c1.add(supprimer);
        c1.add(emp);
        f.add(c1);

        //--Ajout des menu (command) a la fenetre
        f.getToolbar().addCommandToSideMenu("Home", null, (ActionListener) (ActionEvent evt) -> {
            HomeForm hm = new HomeForm();
            hm.getF().show();
            System.out.println("Home Confirme");
        });
        f.getToolbar().addCommandToSideMenu("Ajouter Club", null, (ActionListener) (ActionEvent evt) -> {
            ajoutClub ajoutC = new ajoutClub();
            ajoutC.getF().show();
        });
        f.getToolbar().addCommandToSideMenu("Lister Club", null, (ActionListener) (ActionEvent evt) -> {
            listerC list = new listerC();
            list.getF().show();
        });

        f.getToolbar().addCommandToSideMenu("Envoyer Reclamation", null, (ActionListener) (ActionEvent evt) -> {
            envoiRec r = new envoiRec();
            r.getF().show();

        });

        f.getToolbar().addCommandToSideMenu("Quitter l'application", null, (ActionListener) (ActionEvent evt) -> {
            Display.getInstance().exitApplication();
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
//                    list = new listerC();
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

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
