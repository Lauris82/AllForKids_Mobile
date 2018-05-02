/**
* @Project: AllForKids_Mobile
* @Classe: DetailOffreForm
* @Date: 1 mai 2018
* @Time: 23:10:14
*
* @author Lauris
*/


package com.allforkids.Ettien.forms;

import com.allforkids.Ettien.entities.OffreTransport;
import com.allforkids.Ettien.entities.User;
import com.allforkids.Ettien.services.UserService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;


public class DetailOffreForm {
    
    Form f;
    public OffreTransport offre_selected;
    public User user;
    UserService uss = new UserService();
    Image default_Img = null;

    public DetailOffreForm(){
        
        f = new Form("Detail d'offre", BoxLayout.y());
        f.setUIID("OffreTransport_Background");
        
        ListeOffreTransportForm listForm = new ListeOffreTransportForm();
        offre_selected = listForm.getOffre_selected();
        user = listForm.getOffre_selected_user();
        System.out.println(offre_selected);
        System.out.println(user);
        
        Container c = new Container(BoxLayout.y());
        c.setUIID("OffreContainer");
        
        
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(120, 120), true);
        URLImage profilPic = URLImage.createToStorage(placeholder, user.getImage(),
                "http://localhost//AllForKids/web/image_user/"+user.getImage());
        profilPic.fetch();
        System.out.println(user.getImage());
        Container c_img = BorderLayout.centerAbsolute(new Label(setImageCircle(c, profilPic)));
        Container c_info = BorderLayout.centerAbsolute(new Label("Offre de "+user.getNom()+" "+user.getPrenom()));
        c.add(c_img);
        c.add(c_info);
        
        Label descriptionL = new Label("Description:");
        Label description = new Label(offre_selected.getDescription(), "OffreFont");
        Container c_description = new Container(BoxLayout.x());
        c_description.addAll(descriptionL, description);
        Container container_c_descrip = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        container_c_descrip.add(BorderLayout.CENTER, c_description);
        c.add(container_c_descrip);
        
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
        
        Label dateFL = new Label("Date fin:");
        Label dateF = new Label(offre_selected.getDate_fin(), "OffreFont");
        Container c_dateF = new Container(BoxLayout.x());
        c_dateF.addAll(dateFL, dateF);
        Container container_c_datF = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        container_c_datF.add(BorderLayout.CENTER, c_dateF);
        c.add(container_c_datF);
        
        Label placeL = new Label("Place Restant:");
        Label place = new Label(offre_selected.getPlace_restant().toString(), "OffreFont");
        Container c_place = new Container(BoxLayout.x());
        c_place.addAll(placeL, place);
        Container container_c_place = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        container_c_place.add(BorderLayout.CENTER, c_place);
        c.add(container_c_place);
        
        Label prixL = new Label("Prix:");
        Label prix = new Label(offre_selected.getPrix().toString(), "OffreFont");
        Container c_prix = new Container(BoxLayout.x());
        c_prix.addAll(prixL, prix);
        Container container_c_prix = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        container_c_prix.add(BorderLayout.CENTER, c_prix);
        c.add(container_c_prix);

        
        Button reserverButton = new Button("Resever une place");
        reserverButton.setUIID("LoginButton");
        reserverButton.getAllStyles().setFgColor(0xffffff);
        
        
        Container c_Button = new Container(BoxLayout.x());
        c_Button.addAll(reserverButton);
        Container container = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        container.add(BorderLayout.CENTER, c_Button);
        c.add(container);
        
        f.add(c);
        f.getToolbar().addCommandToLeftBar("Back", default_Img, e -> {
            ListeOffreTransportForm listO = new ListeOffreTransportForm();
            listO.getF().showBack();
        });
    }
    
    
   
    
    public final Image setImageCircle(Container ca, Image originalImage){
        int w = originalImage.getWidth();
        int h = originalImage.getHeight();
        
        Image maskImage = Image.createImage(w, h);
        Graphics g = maskImage.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0x000000);
        g.fillRect(0, 0, w, h);
        g.setColor(0xdadbce);
        g.fillArc(0, 0, w, h, 0, 360);
        
        Object mask = maskImage.createMask();
        
        Image maskedImage = originalImage.applyMask(mask);
        
        return maskedImage;
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public OffreTransport getOffre_selected() {
        return offre_selected;
    }

    public void setOffre_selected(OffreTransport offre_selected) {
        this.offre_selected = offre_selected;
    }
    
}



/**
*@Lau82 © 2018
*/
