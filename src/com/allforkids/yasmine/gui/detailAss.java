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
import com.allforkids.yasmine.entities.AssociationEntity;
import com.allforkids.yasmine.entities.ClubEntity;
import com.allforkids.yasmine.services.AssService;
import com.codename1.ui.Dialog;
import com.codename1.ui.Toolbar;

/**
 *
 * @author DELL
 */
class detailAss {

    Form f;
    ClubEntity e;

    private Command Home;
    private Command quit;
    private Command listeAss;
    private Command ajouterAss;
    private Command rec;

    Button modifier;
    Button supprimer;

    int idAss;
    int num;
    String nom;
    String desc;
    String gouve;
    listerC list;

    public detailAss(AssociationEntity c) {

        f = new Form( c.getNom() + " : ", new FlowLayout(Component.CENTER, Component.CENTER));
        f.setUIID("LoginForm");
        Toolbar tb = f.getToolbar();
        tb.setUIID("ToolBarFont");

        System.out.println("test id :" + c.getId_aasociation());

        Home = new Command("Home");
        listeAss = new Command("Liste des Associations");
        quit = new Command("Quitter l'application");
        ajouterAss = new Command("Ajouter Association");
        rec = new Command("Envoyer Reclamation");

        Container c1 = new Container(BoxLayout.y());
        Container cc2 = new Container(BoxLayout.x());
        Container cc3 = new Container(BoxLayout.x());
        Container cc = new Container(BoxLayout.x());

        Label ll = new Label("Num Tel :");
        Label l = new Label(String.valueOf(c.getNum_tel()), "OffreFont");
        ll.getAllStyles().setFgColor(0x191970);
        l.getAllStyles().setFgColor(0x191970);

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

        idAss = c.getId_aasociation();
        nom = c.getNom();
        num = c.getNum_tel();
        desc = c.getDescription();
        gouve = c.getGouvernorat();
        AssociationEntity NvAss;
        NvAss = new AssociationEntity();
        NvAss.setDescription(desc);
        NvAss.setGouvernorat(gouve);
        NvAss.setId_aasociation(idAss);
        NvAss.setNom(nom);
        NvAss.setNum_tel(num);

        modifier = new Button("Modifier");
        modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                modAss C = new modAss(NvAss);
                C.getF().show();
            }
        });

        supprimer = new Button("Supprimer");
        supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                AssService s = new AssService();
                s.supprimerA(NvAss);

                Dialog.show("Associations", "Association supprimer !", "ok", null);

                listerC l = new listerC();
                l.getF().show();

            }
        });

        c1.add(cc);
        c1.add(cc2);
        c1.add(cc3);
        c1.add(modifier);
        c1.add(supprimer);

        f.add(c1);

        //--Ajout des menu (command) a la fenetre
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

//        f.addCommandListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//
//             Command cmd = evt.getCommand();
//                if (cmd == Home) {
//                    homeAss h;
//                    h = new homeAss();
//
//                } else if (cmd == quit) {
//
//                    Display.getInstance().exitApplication();
//
//                } else if (cmd == listeAss) {
//
//                    listeAss list = new listeAss();
//                    list.getF().show();
//
//                } else if (cmd == ajouterAss) {
//
//                    ajoutAss ajoutA = new ajoutAss();
//                    ajoutA.getF().show();
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
