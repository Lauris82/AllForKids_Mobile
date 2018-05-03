/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;


public class BaseForm extends Form {
    public void installSidemenu(Resources res) {
        Image selection = res.getImage("");
        
        Image homeImage = res.getImage("");
        if(isCurrentHome()) homeImage = selection;

        Image annonceImage = res.getImage("");
        if(isCurrentAnnonce()) annonceImage = selection;
        
        Image offreImage = res.getImage("");
        if(isCurrentOffre()) offreImage = selection;
        
        Image favorisImage = res.getImage("");
        if(isCurrentFavoris()) favorisImage = selection;
        
        Image reservImage = res.getImage("");
        if(isCurrentReser()) reservImage = selection;
        
        Image expImage = res.getImage("");
        if(isCurrentExperience()) expImage = selection;
        
        Image forumImage = res.getImage("");
        if(isCurrentForum()) forumImage = selection;
        
        Image decoImage = res.getImage("");
//        Font fnt = Font.createTrueTypeFont("", "").derive(28, Font.SIZE_LARGE);
        Container logoGAH = new Container(new BorderLayout());
        Label imglogo = new Label(res.getImage(""), "Container");
        imglogo.getAllStyles().setMarginLeft(15);
        Label guest = new Label("All");
//        guest.getAllStyles().setFont(fnt);
        
        
//        guest.setUIID("logotext1");
        Label and = new Label("For");
//        and.getAllStyles().setFont(fnt);
//        and.setUIID("logotext1");
        Label host = new Label("Kids");
//        host.getAllStyles().setFont(fnt);
//        host.setUIID("logotext1");
        Container logotextc =new Container(new BoxLayout(BoxLayout.X_AXIS));
//        logotextc.setUIID("logotextc");
        logotextc.add(guest);
        guest.getAllStyles().setFgColor(0xffffff);
        host.getAllStyles().setFgColor(0xffffff);
        and.getAllStyles().setFgColor(0xe35d59);
        logotextc.add(and);
        logotextc.add(host);
      
//        logoGAH.setUIID("LogoContainer");
        imglogo.getAllStyles().setAlignment(CENTER);
        
        logoGAH.add(BorderLayout.CENTER,imglogo);
        logoGAH.add(BorderLayout.SOUTH,logotextc);
        getToolbar().addComponentToSideMenu(logoGAH);
        
        getToolbar().addCommandToSideMenu("  Accueil", homeImage, e -> new HomeForm().show());
        getToolbar().addCommandToSideMenu("  Mes Postes", annonceImage, e ->{ listblog lb=new listblog();lb.listblog(res);});
        getToolbar().addCommandToSideMenu("  Mes Produits", offreImage, e -> {listproduit lb1 = new listproduit(); lb1.listproduit(res);});
        getToolbar().addCommandToSideMenu("  Ajout Produit", favorisImage, e -> {try {
            Ajoutproduit lb2 = new Ajoutproduit();
            } catch (IOException ex) {
            }
});
        getToolbar().addCommandToSideMenu("  Mes Réservations", reservImage, e -> {});
        getToolbar().addCommandToSideMenu("  Se déconnecter", decoImage, e -> {});
        
        // spacer
        
    }

        
    protected boolean isCurrentHome() {
        return false;
    }
    
    protected boolean isCurrentAnnonce() {
        return false;
    }

    protected boolean isCurrentOffre() {
        return false;
    }

    protected boolean isCurrentFavoris() {
        return false;
    }
    
    protected boolean isCurrentReser() {
        return false;
    }
    protected boolean isCurrentForum() {
        return false;
    }
    protected boolean isCurrentExperience() {
        return false;
    }
}
