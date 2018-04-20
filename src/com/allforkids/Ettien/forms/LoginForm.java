/**
* @Project: AllForKids_Mobile
* @Classe: LoginForm
* @Date: 19 avr. 2018
* @Time: 21:33:27
*
* @author Lauris
*/


package com.allforkids.Ettien.forms;

import com.allforkids.Ettien.entities.User;
import com.allforkids.Ettien.services.UserService;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Map;


public class LoginForm{

    public static User user;
    Form f;
    
    public LoginForm(Resources theme) {
        f = new Form("Welcome To AllForKids", BoxLayout.y());
        f.setUIID("LoginForm");
        f.getToolbar().setHidden(true);
        
        Image img = theme.getImage("logo.png");
        Label imgLabel = new Label(img, "First Image");
        Container c = new Container(new FlowLayout(Component.CENTER, Component.CENTER));
        c.add(imgLabel);
        f.add(c);
        
        TextField username = new TextField("", "Username");
        Label usernameIcon = new Label("", "TextField");
        usernameIcon.getAllStyles().setFgColor(0xf5bf0a);
        FontImage.setMaterialIcon(usernameIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        Container c_username = new Container(BoxLayout.x());
        c_username.addAll(usernameIcon, username);
        f.add(c_username);
        
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        Label passwordIcon = new Label("", "TextField");
        passwordIcon.getAllStyles().setFgColor(0xff000);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        Container c_password = new Container(BoxLayout.x());
        c_password.addAll(passwordIcon, password);
        f.add(c_password);
        
        Button loginButton = new Button("Login");
        loginButton.setUIID("LoginButton");
        loginButton.getAllStyles().setFgColor(0xff000);
        Container c_loginButton = new Container(BoxLayout.x());
        c_loginButton.addAll(loginButton);
        f.add(c_loginButton);
        
        
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                String url = "http://localhost/codename/AllForKids/select.php?username="+username.getText()+"&pass="+password.getText();
                ConnectionRequest con = new ConnectionRequest(url);
                
                con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        UserService uss = new UserService();
                        System.out.println(uss.getList2().toString());
                    }
                });
                NetworkManager.getInstance().addToQueue(con);
            }
        });
        
    }

    
    public Form getForm() {
        return f;
    }

    public void setForm(Form form) {
        this.f = form;
    }
}



/**
*@Lau82 © 2018
*/
