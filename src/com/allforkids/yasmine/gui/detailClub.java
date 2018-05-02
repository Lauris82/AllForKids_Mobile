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
import com.codename1.ui.Toolbar;

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
    Button modifier;
    Button supprimer;

    int idClub;
    String num;
    String nom;
    String desc;
    String gouve;
    listerC list;
    public detailClub(ClubEntity c) {

        f = new Form("Club " + c.getNom() + " : ", new FlowLayout(Component.CENTER, Component.CENTER));
        f.setUIID("LoginForm");
        Toolbar tb = f.getToolbar();
        tb.setUIID("ToolBarFont");

        System.out.println("test id :" + c.getIdclub());

        Home = new Command("Home");
        listeClub = new Command("Liste des clubs");
        quit = new Command("Quitter l'application");
        ajouterClub = new Command("Ajouter Club");

        Container c1 = new Container(BoxLayout.y());

        Label l = new Label(c.getNumTel());
        l.getAllStyles().setFgColor(0xeae4c4);
        Label l2 = new Label(c.getGouvernorat());

        l2.getAllStyles().setFgColor(0xeae4a4);
        Label l3 = new Label(c.getDescription());
        l3.getAllStyles().setFgColor(0xeae4e5);

    

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

        supprimer=new Button("Supprimer");
        supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                


            }
        });
        
        
        c1.add(l);
        c1.add(l2);
        c1.add(l3);
        c1.add(modifier);
        f.add(c1);

        //--Ajout des menu (command) a la fenetre
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
