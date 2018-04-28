/**
* @Project: AllForKids_Mobile
* @Classe: SideMenuBarForm
* @Date: 25 avr. 2018
* @Time: 23:27:17
*
* @author Lauris
*/


package com.allforkids.Ettien.forms;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;


public class SideMenuBarForm{

    private Resources theme;

    public SideMenuBarForm(Form f) {
        
        f.getToolbar().addMaterialCommandToSideMenu("Offre Transport", FontImage.MATERIAL_TRAFFIC, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ListeOffreTransportForm lotf = new ListeOffreTransportForm();
                lotf.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_BACKSPACE, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        f.showBack();
                    }
                });
                lotf.getF().show();
            }
        });
    }
   
    
    
}



/**
*@Lau82 © 2018
*/
