/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yassine.gui;

import com.allforkids.yassine.entities.evenement;
import com.codename1.components.ShareButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.share.FacebookShare;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.util.ImageIO;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author ASUS
 */
public class partage {
    Form f ;
    public partage(){
        
     evenement e=   detailEvenement.ev ;
       f=new Form("Partage");
        f.setUIID("LoginForm");
        Toolbar tb = f.getToolbar();
        tb.setUIID("ToolBarFont");
        ShareButton sb = new ShareButton();
        sb.setText("Publier l'èvenement");
        sb.setTextToShare("Nom de l'èvenement : "+e.getNom()+" Description : "+e.getDescription()+" à :"+e.getEmplacement()+" commence le :"+e.getDate_debut()+" juasqu'a : "+e.getDate_fin());
         f.getToolbar().addCommandToLeftBar("back", null, (ev)->{detailEvenement l;
         l=new detailEvenement(e);
         l.getF().show();
        
          });
        f.add(sb);
       



        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
