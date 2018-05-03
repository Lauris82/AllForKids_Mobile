/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import controller.ServiceCommande;
import entities.Commande;
import entities.produitjson;

/**
 *
 * @author amine
 */
public class Ajoutcommande {
Form f;
    public Ajoutcommande(Resources resourceObjectInstance,produitjson p) {
        
        
        System.out.println("hedh    p kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" + p);
        f = new Form("Ajouter Commande", BoxLayout.y());
    
     f.getToolbar().addCommandToOverflowMenu("Back", null, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new com.allforkids.Ettien.forms.HomeForm().getF().show();
                }
            });
    
        TextField titre = new TextField("", "Adresse");
        TextField desc = new TextField("", "Quantite");
        
        GenarateQR g = new GenarateQR();
        f.add(g.GenarateQR(p.getIdProduit()));
        Button b = new Button("passe Commande");
        
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            
                ServiceCommande xxxx = new ServiceCommande();
                
                Commande cm = new Commande( titre.getText(), Integer.parseInt(desc.getText()), 4, p.getIdProduit());
                System.out.println("hedha howa ======================== "+ cm.toString());
                xxxx.ajoutTask(cm);
                listproduit lpp = new listproduit();
                lpp.listproduit(resourceObjectInstance);
                lpp.getF().show();
            }
            
        });
        
        f.show();
        
        f.add(titre);
        f.add(desc);
        f.add(b);
    }
    
   public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    } 
}
