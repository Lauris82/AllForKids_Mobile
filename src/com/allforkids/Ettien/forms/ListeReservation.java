/**
* @Project: AllForKids_Mobile
* @Classe: ListeReservation
* @Date: 2 mai 2018
* @Time: 05:09:41
*
* @author Lauris
*/


package com.allforkids.Ettien.forms;

import com.allforkids.Ettien.entities.OffreTransport;
import com.allforkids.Ettien.entities.User;
import com.allforkids.Ettien.services.OffreTransport_Service;
import com.allforkids.Ettien.services.UserService;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;


public class ListeReservation {

    Form f;
    Toolbar toolbar;
    Image default_Img = null;
    
    OffreTransport_Service ots = new OffreTransport_Service();
    UserService uss = new UserService();
    ArrayList<OffreTransport> listOffres = new ArrayList<>();
    public static OffreTransport offre_selected;
    public static User offre_selected_user;

    public ListeReservation() {
        
        f = new Form("Mes reservations", BoxLayout.y());
        f.setUIID("OffreTransport_Background");
        
        
        
        
        
        
        f.getToolbar().addCommandToLeftBar("Back", default_Img, e -> {
            ListeOffreTransportForm listO = new ListeOffreTransportForm();
            listO.getF().showBack();
        });
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
