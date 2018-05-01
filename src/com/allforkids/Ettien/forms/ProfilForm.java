/**
* @Project: AllForKids_Mobile
* @Classe: ProfilForm
* @Date: 30 avr. 2018
* @Time: 22:25:19
*
* @author Lauris
*/


package com.allforkids.Ettien.forms;

import com.allforkids.Ettien.entities.User;
import static com.allforkids.Ettien.forms.SideMenuBarForm.user_Connect;
import com.allforkids.Ettien.services.UserService;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;


public class ProfilForm {
    
    private final Resources theme;
    LoginForm logF = new LoginForm();
    Form f;
    
    public static User user_Connect;
    public UserService uss;

    public ProfilForm() {
        theme = UIManager.initFirstTheme("/theme");
        user_Connect = logF.getUser();
        Container c_img = new Container();
        Dimension d = new Dimension(50, 50);
        c_img.setSize(d);
        
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(f.getWidth(), f.getWidth()), true);
        URLImage profilPic = URLImage.createToStorage(placeholder, user_Connect.getImage(),
            "http://localhost//AllForKids/web/image_user/"+user_Connect.getImage());
        profilPic.fetch();
    }
    
    

}



/**
*@Lau82 © 2018
*/
