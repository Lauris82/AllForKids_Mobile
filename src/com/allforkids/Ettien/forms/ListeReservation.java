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
import com.allforkids.Ettien.entities.Reservation_Offre;
import com.allforkids.Ettien.entities.User;
import com.allforkids.Ettien.services.OffreTransport_Service;
import com.allforkids.Ettien.services.ReservationOffre_Service;
import com.allforkids.Ettien.services.UserService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;


public class ListeReservation {

    Form f;
    Toolbar toolbar;
    Image default_Img = null;
    
    OffreTransport_Service ots = new OffreTransport_Service();
    ReservationOffre_Service rots = new ReservationOffre_Service();
    LoginForm log = new LoginForm();
    UserService uss = new UserService();
    ArrayList<Reservation_Offre> listResv = new ArrayList<>();
    public static OffreTransport offre_selected;
    public static User offre_selected_user;
    public User user_Connected;

    public ListeReservation() {
        user_Connected = log.getUser();
        f = new Form("Mes reservations", BoxLayout.y());
        f.setUIID("OffreTransport_Background");
        Toolbar tb = f.getToolbar();
        tb.setUIID("ToolBarFont");
        
        listResv = rots.getMyReservations(user_Connected.getId());
        
        for(Reservation_Offre r : listResv){
            try {
                f.add(addOffre(r));
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        
        f.getToolbar().addCommandToLeftBar("Back", default_Img, e -> {
            if(listResv.isEmpty()){
                ListeOffreTransportForm listOf = new ListeOffreTransportForm("Aucune Reservation");
                listOf.getF().showBack();
            }else{
                ListeOffreTransportForm listO = new ListeOffreTransportForm();
                listO.getF().showBack();
            }
        });
    }

    
    
    
    public final Container addOffre(Reservation_Offre res) throws IOException{
        Container c = new Container(BoxLayout.y());
        c.setUIID("OffreContainer");
        
        String result = ots.getOffreById(res.getOffreTransport());
        offre_selected = ots.getOffreTransport(result);
        
        String resultU = uss.getInfoUserById(offre_selected.getUser());
        User user = uss.getUser(resultU);
        
        Label descriptionL = new Label("Date de reservation:");
        Label description = new Label(res.getDate_reservation(), "OffreFont");
        Container c_description = new Container(BoxLayout.x());
        c_description.addAll(descriptionL, description);
        Container container_c_descrip = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        container_c_descrip.add(BorderLayout.CENTER, c_description);
        c.add(container_c_descrip);
        
        Label hide =new Label("  ");
        c.add(hide);
        
        Label destinationL = new Label("Destination:");
        Label destination = new Label(offre_selected.getDestination(), "OffreFont");
        Container c_destination = new Container(BoxLayout.x());
        c_destination.addAll(destinationL, destination);
        Container container_c_desti = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        container_c_desti.add(BorderLayout.CENTER, c_destination);
        c.add(container_c_desti);
        
        Label dateDL = new Label("Date debut:");
        Label dateD = new Label(offre_selected.getDate_debut(), "OffreFont");
        Container c_dateD = new Container(BoxLayout.x());
        c_dateD.addAll(dateDL, dateD);
        Container container_c_datD = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        container_c_datD.add(BorderLayout.CENTER, c_dateD);
        c.add(container_c_datD);

        
        Label detail = new Label("", "TextField");
        detail.getAllStyles().setFgColor(0xf5bf0a);
        FontImage.setMaterialIcon(detail, FontImage.MATERIAL_DETAILS, 3);
        Button showDetailButton =new Button("Details");
        showDetailButton.setUIID("RegisterButton");
        showDetailButton.getAllStyles().setFgColor(0xffffff);
        
        Container c_fcb_Button = new Container(BoxLayout.x());
        c_fcb_Button.addAll(detail, showDetailButton);
        Container container_fcb = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        container_fcb.add(BorderLayout.CENTER, c_fcb_Button);
        c.add(container_fcb);
        
        showDetailButton.addActionListener((ActionListener) (ActionEvent evt) -> {
            setOffre_selected(offre_selected);
            setOffre_selected_user(user);
            DetailOffreForm detailOffre = new DetailOffreForm();
            detailOffre.getF().show();
        });
        
        return c;
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public static OffreTransport getOffre_selected() {
        return offre_selected;
    }

    public static void setOffre_selected(OffreTransport offre_selected) {
        ListeReservation.offre_selected = offre_selected;
    }
    
    public User getOffre_selected_user() {
        return offre_selected_user;
    }

    public void setOffre_selected_user(User offre_selected_user) {
        ListeOffreTransportForm.offre_selected_user = offre_selected_user;
    }
    

}



/**
*@Lau82 © 2018
*/
