/**
* @Project: AllForKids_Mobile
* @Classe: ListeOffreTransportForm
* @Date: 26 avr. 2018
* @Time: 09:31:52
*
* @author Lauris
*/


package com.allforkids.Ettien.forms;

import com.allforkids.Ettien.entities.OffreTransport;
import com.allforkids.Ettien.entities.User;
import com.allforkids.Ettien.services.OffreTransport_Service;
import com.allforkids.Ettien.services.UserService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
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

public class ListeOffreTransportForm {

    Form f;
    Toolbar toolbar;
    Image default_Img = null;
    
    OffreTransport_Service ots = new OffreTransport_Service();
    UserService uss = new UserService();
    ArrayList<OffreTransport> listOffres = new ArrayList<>();
    public static OffreTransport offre_selected;
    public static User offre_selected_user;
    public User user_Connected;
    LoginForm log = new LoginForm();

    public ListeOffreTransportForm() {
        f = new Form("Liste offre Transport", BoxLayout.y());
        f.setUIID("OffreTransport_Background");
        toolbar = f.getToolbar();
        toolbar.setUIID("ToolBarFont");
        
        user_Connected = log.getUser();
        
        listOffres = ots.getAllOffers();
        
        for(OffreTransport o : listOffres){
            try {
                f.add(addOffre(o));
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

        f.setScrollableY(true);
        f.setScrollVisible(false);
        
        f.getToolbar().addCommandToLeftBar("Back", default_Img, e -> {
            HomeForm home = new HomeForm();
            home.getF().showBack();
        });
        
        f.getToolbar().addCommandToOverflowMenu("Ms reservations", default_Img, ev -> {
            ListeReservation listRes = new ListeReservation();
            listRes.getF().show();
        });
    }

    public ListeOffreTransportForm(String sms) {}
    
    
    public final Container addOffre(OffreTransport o) throws IOException{
        Container c = new Container(BoxLayout.y());
        c.setUIID("OffreContainer");
        
        String result = uss.getInfoUserById(o.getUser());
        User user = uss.getUser(result);
        
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(120, 120), true);
        URLImage profilPic = URLImage.createToStorage(placeholder, user.getImage(),
                "http://localhost//AllForKids/web/image_user/"+user.getImage());
        profilPic.fetch();
        Container c_img = BorderLayout.centerAbsolute(new Label(setImageCircle(c, profilPic)));
        Container c_info = BorderLayout.centerAbsolute(new Label("Offre de "+user.getNom()+" "+user.getPrenom()));
        c.add(c_img);
        c.add(c_info);
        
        Label hide =new Label("  ");
        c.add(hide);
        
        Label descriptionL = new Label("Description:");
        Label description = new Label(o.getDescription(), "OffreFont");
        Container c_description = new Container(BoxLayout.x());
        c_description.addAll(descriptionL, description);
        Container container_c_descrip = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        container_c_descrip.add(BorderLayout.CENTER, c_description);
        c.add(container_c_descrip);
        
        Label destinationL = new Label("Destination:");
        Label destination = new Label(o.getDestination(), "OffreFont");
        Container c_destination = new Container(BoxLayout.x());
        c_destination.addAll(destinationL, destination);
        Container container_c_desti = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        container_c_desti.add(BorderLayout.CENTER, c_destination);
        c.add(container_c_desti);
        
        Label dateDL = new Label("Date debut:");
        Label dateD = new Label(o.getDate_debut(), "OffreFont");
        Container c_dateD = new Container(BoxLayout.x());
        c_dateD.addAll(dateDL, dateD);
        Container container_c_datD = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        container_c_datD.add(BorderLayout.CENTER, c_dateD);
        c.add(container_c_datD);

        
        Label detail = new Label("", "TextField");
        detail.getAllStyles().setFgColor(0xf5bf0a);
        FontImage.setMaterialIcon(detail, FontImage.MATERIAL_DETAILS, 3);
        Button showDetailButton =new Button("Details");
        if(user_Connected.getId() == o.getUser()){
            showDetailButton.setText("Ceci est votre offre");
        }
        showDetailButton.setUIID("RegisterButton");
        showDetailButton.getAllStyles().setFgColor(0xffffff);
        
        Container c_fcb_Button = new Container(BoxLayout.x());
        c_fcb_Button.addAll(detail, showDetailButton);
        Container container_fcb = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        container_fcb.add(BorderLayout.CENTER, c_fcb_Button);
        c.add(container_fcb);
        
        
        showDetailButton.addActionListener((ActionListener) (ActionEvent evt) -> {
            setOffre_selected(o);
            setOffre_selected_user(user);
            DetailOffreForm detailOffre = new DetailOffreForm();
            detailOffre.getF().show();
        });
        
        return c;
    }
    
   
    public Image setImageCircle(Container ca, Image originalImage){
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

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public OffreTransport getOffre_selected() {
        return offre_selected;
    }

    public void setOffre_selected(OffreTransport offre_selected) {
        ListeOffreTransportForm.offre_selected = offre_selected;
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
