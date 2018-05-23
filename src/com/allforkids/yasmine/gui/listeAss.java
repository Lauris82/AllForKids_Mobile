/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yasmine.gui;

import com.allforkids.Ettien.forms.HomeForm;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.allforkids.yasmine.entities.AssociationEntity;
import java.util.ArrayList;
import java.util.List;
import com.allforkids.yasmine.services.AssService;
import com.codename1.ui.Toolbar;

/**
 *
 * @author DELL
 */
class listeAss {

    Form f;
    int i;
    List l;

    private Command Home;
    private Command quit;
    private Command listeAss;
    private Command ajouterAss;
    private Command rec;

    public listeAss() {

        f = new Form("List Association", new FlowLayout(Component.CENTER, Component.CENTER));
        f.setUIID("LoginForm");
        Toolbar tb = f.getToolbar();
        tb.setUIID("ToolBarFont");

        Home = new Command("Home");
        listeAss = new Command("Liste des Association");
        quit = new Command("Quitter l'application");
        ajouterAss = new Command("Ajouter Association");
        rec = new Command("Envoyer Reclamation");

        AssService s = new AssService();
        ArrayList<AssociationEntity> list = s.getList2();
        Container c = new Container(BoxLayout.y());
        for (i = 0; i < list.size(); i++) {
            Container c1 = new Container(BoxLayout.x());

            AssociationEntity ass = new AssociationEntity();
            ass.setId_aasociation(list.get(i).getId_aasociation());
            ass.setNom(list.get(i).getNom());
            ass.setDescription(list.get(i).getDescription());
            ass.setNum_tel(list.get(i).getNum_tel());
            ass.setGouvernorat(list.get(i).getGouvernorat());

            Label l = new Label();
            l.setText(list.get(i).getNom());
            l.getAllStyles().setFgColor(0xf44336);
            c1.add(l);
            c1.getStyle().setBgColor(0x99CCCC);
            c1.getAllStyles().setBorder(Border.getDefaultBorder());
            c1.getAllStyles().setAlignment(CENTER);
            c1.getAllStyles().setPadding(10, CENTER, 10, 10);

            c.add(c1);
            l.addPointerPressedListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    detailAss d;
                    d = new detailAss(ass);
                    d.getF().show();
                }
            });

        }
        c.getStyle().setBgColor(0x99CCCC);
        f.add(c);

        //--Ajout des menu (command) a la fenetre
        f.getToolbar().addCommandToSideMenu("Home", null,(ActionListener) (ActionEvent evt) -> {
            HomeForm hm = new HomeForm();
            hm.getF().show();
            System.out.println("Home Confirme");
        });
        f.getToolbar().addCommandToSideMenu("Ajouter Association", null,(ActionListener) (ActionEvent evt) -> {
            ajoutAss ajoutA = new ajoutAss();
            ajoutA.getF().show();
        });
        f.getToolbar().addCommandToSideMenu("Lister Association", null,(ActionListener) (ActionEvent evt) -> {
            listeAss lists = new listeAss();
            lists.getF().show();
        });
        
          f.getToolbar().addCommandToSideMenu("Envoyer Reclamation", null,(ActionListener) (ActionEvent evt) -> {
            envoiRec r=new envoiRec();
            r.getF().show();
            
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
//        f.show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
