/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

/**
 *
 * @author Imen Rajhi
 */
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

public class HomeForm extends BaseForm {


    Form f;
    private final Resources theme;
     
    

//    public HomeForm() {
//        theme = UIManager.initFirstTheme("/theme");
//        f = new Form("AllForkids", BoxLayout.y());
//        f.setUIID("LoginForm");
////        this(com.codename1.ui.util.Resources.getGlobalResources());
//    }

    @Override
    protected boolean isCurrentHome() {
        return true;
    }

    public HomeForm() {
        theme = UIManager.initFirstTheme("/theme");
        f = new Form("AllForkids", BoxLayout.y());
        f.setUIID("LoginForm");
        Toolbar tb = f.getToolbar();
        tb.setUIID("ToolBarFont");

        initGuiBuilderComponents(theme);
        
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Accueil", "Title")
                )
        );

    }

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
       // setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
//setLayout(new BorderLayout());
//        installSidemenu(resourceObjectInstance);
//        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage(""), e -> {
//        });
       
        f.getToolbar().addCommandToSideMenu("  Accueil", null, e -> new com.allforkids.Ettien.forms.HomeForm().getF().show());
        f.getToolbar().addCommandToSideMenu("  Mes Postes", null, e ->{ listblog lb=new listblog();lb.listblog(theme);});
        f.getToolbar().addCommandToSideMenu("  Mes Produits", null, e -> {listproduit lb1 = new listproduit(); lb1.listproduit(theme);});
        f.getToolbar().addCommandToSideMenu("  Ajout Produit", null, e -> {
            try {
                Ajoutproduit lb2 = new Ajoutproduit();
            } catch (IOException ex) {
            }
        });
        f.getToolbar().addCommandToSideMenu("  Mes Réservations", null, e -> {});
        f.getToolbar().addCommandToSideMenu("  Se déconnecter", null, e -> {});
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    
}
