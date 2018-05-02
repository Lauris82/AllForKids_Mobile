/**
* @Project: AllForKids_Mobile
* @Classe: HomeForm
* @Date: 19 avr. 2018
* @Time: 23:17:41
*
* @author Lauris
*/


package com.allforkids.Ettien.forms;


import com.codename1.components.SpanLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

public class HomeForm{

    Form f;
    private final Resources theme;
    SpanLabel info = new SpanLabel();

    public HomeForm(){
        theme = UIManager.initFirstTheme("/theme");
        
        f = new Form("AllForkids", BoxLayout.y());
        f.setUIID("LoginForm");
        Toolbar tb = f.getToolbar();
        tb.setUIID("ToolBarFont");

        SideMenuBarForm side_bar = new SideMenuBarForm(f);
        
        Image img = theme.getImage("logo.png");
        Label imgLabel = new Label(img, "First Image");
        Container c = new Container(new FlowLayout(Component.CENTER, Component.CENTER));
        c.add(imgLabel);
        f.add(c);
        
        info = new SpanLabel("AllForKids est une Aplication dediée aux parents, Associations, Ecoles, etc..."
            + " Nous proposons des services pour vous aider dans votre tache quotidienne avec votre enfant. Garderies, Clubs, Evenements,"
            + " Offre de transport, et vente de Produits vous sont proposés");
//        **info.getAllStyles().setFgColor(0xbb43f2);
        f.add(info);

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
