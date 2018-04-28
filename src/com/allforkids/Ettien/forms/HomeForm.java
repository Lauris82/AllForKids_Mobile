/**
* @Project: AllForKids_Mobile
* @Classe: HomeForm
* @Date: 19 avr. 2018
* @Time: 23:17:41
*
* @author Lauris
*/


package com.allforkids.Ettien.forms;


import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class HomeForm{

    Form f;
    private Resources theme;

    public HomeForm(){
        
        f = new Form("AllForkids", BoxLayout.y());
        f.setUIID("LoginForm");

        SideMenuBarForm side_bar = new SideMenuBarForm(f);
        
        f.setScrollableY(true);
        f.setScrollVisible(false);
    }
    
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}



/**
*@Lau82 © 2018
*/
