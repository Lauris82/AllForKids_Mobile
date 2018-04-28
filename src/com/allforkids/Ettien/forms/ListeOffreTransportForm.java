/**
* @Project: AllForKids_Mobile
* @Classe: ListeOffreTransportForm
* @Date: 26 avr. 2018
* @Time: 09:31:52
*
* @author Lauris
*/


package com.allforkids.Ettien.forms;

import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;


public class ListeOffreTransportForm {

    Form f;
    Toolbar toolbar;

    public ListeOffreTransportForm() {
        
        f = new Form("Liste offre Transport", BoxLayout.y());
        f.setUIID("LoginForm");
        toolbar = f.getToolbar();

        
        f.setScrollableY(true);
        f.setScrollVisible(false);
    }

    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }
    
    
}



/**
*@Lau82 © 2018
*/
