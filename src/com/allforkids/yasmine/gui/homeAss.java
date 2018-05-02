/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yasmine.gui;

import com.allforkids.Ettien.forms.HomeForm;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author DELL
 */
public class homeAss {
    
    Form f;

    private Command Home;
    private Command quit;
    private Command listeAss;
    private Command ajouterAss;

    public homeAss() {
    
     //creatin d'une interface ou fenetre--
        f = new Form("Association");
        f.setUIID("LoginForm");
        Toolbar tb = f.getToolbar();
        tb.setUIID("ToolBarFont");
        //creation d'un layout vertical pour disposer les elements dans la fenetre

        BoxLayout mainLayout = new BoxLayout(BoxLayout.Y_AXIS);
        f.setLayout(mainLayout);
        Label texte = new Label("Aide aux parents");
        Label texte2 = new Label("        des enfants en difficultés");
        texte.getAllStyles().setAlignment(CENTER);

        Home = new Command("Home");
        listeAss = new Command("Liste des Association");
        quit = new Command("Quitter l'application");
        ajouterAss = new Command("Ajouter Club");

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
            listeAss list = new listeAss();
            list.getF().show();
        });
        f.getToolbar().addCommandToSideMenu("Quitter l'application", null,(ActionListener) (ActionEvent evt) -> {
            Display.getInstance().exitApplication();
        });
        //ajouter les element creer à la fenetre de l'interface
        f.addComponent(texte);
        f.addComponent(texte2);
        //affichage de la fenetre
//        f.show();
//        f.addCommandListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//
//               Command cmd = evt.getCommand();
//                if (cmd == Home) {
//                    homeAss h = new homeAss();
//                    h.getF();
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
