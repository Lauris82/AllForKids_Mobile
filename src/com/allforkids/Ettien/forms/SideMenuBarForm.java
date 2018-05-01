/**
* @Project: AllForKids_Mobile
* @Classe: SideMenuBarForm
* @Date: 25 avr. 2018
* @Time: 23:27:17
*
* @author Lauris
*/


package com.allforkids.Ettien.forms;

import com.allforkids.Ettien.entities.User;
import com.allforkids.Ettien.services.UserService;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;


public final class SideMenuBarForm{

    private final Resources theme;
    Container c_img;
    URLImage profilPic;
    Image default_Img = null;
    
    public static User user_Connect;
    public UserService uss;

    public SideMenuBarForm(Form f) {
        theme = UIManager.initFirstTheme("/theme");
        LoginForm logF = new LoginForm(theme);
        
        user_Connect = logF.getUser();
        
        System.out.println(user_Connect.getImage());
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(120, 120), true);
        profilPic = URLImage.createToStorage(placeholder, user_Connect.getImage(),
                "http://localhost//AllForKids/web/image_user/"+user_Connect.getImage());
        profilPic.fetch();
        
        c_img = BorderLayout.centerAbsolute(new Label(setImageCircle(f, profilPic)));
        c_img.add(BorderLayout.SOUTH, new Label("  "+user_Connect.getNom()+" "+user_Connect.getPrenom(), "SideMenuTagline"));
        f.getToolbar().addComponentToSideMenu(c_img);
        
        
        f.getToolbar().addCommandToSideMenu("Garderie", theme.getImage("garderie.png"), (ActionListener) (ActionEvent evt) -> {
            
            
        });
        
        
        f.getToolbar().addCommandToSideMenu("Club et Association", theme.getImage("meeting.png"), (ActionListener) (ActionEvent evt) -> {
            
            
        });
        
        
        f.getToolbar().addCommandToSideMenu("Evènements", theme.getImage("event.png"), (ActionListener) (ActionEvent evt) -> {
            
            
        });
        
        
        f.getToolbar().addCommandToSideMenu("Offre Transport", theme.getImage("bus.png"), (ActionListener) (ActionEvent evt) -> {
            ListeOffreTransportForm lotf = new ListeOffreTransportForm();
            lotf.getToolbar().addCommandToLeftBar("Back", default_Img, (ActionListener) (ActionEvent evt1) -> {
                f.showBack();
            });
            lotf.getF().show();
        });
        
        
        f.getToolbar().addCommandToSideMenu("Produits", theme.getImage("brick.png"), (ActionListener) (ActionEvent evt) -> {
           
            
        });
    }
   
    
    public Image setImageCircle(Form f, Image originalImage){
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
    
}



/**
*@Lau82 © 2018
*/
