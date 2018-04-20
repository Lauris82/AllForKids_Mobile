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
import com.codename1.io.ConnectionRequest;
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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;


public class LoginForm{

    public static User user;
    public UserService uss;
    Form f;
    
    public LoginForm(Resources theme) {
        f = new Form("Welcome To AllForKids", BoxLayout.y());
//        f = new Form("Welcome To AllForKids", new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        f.setUIID("LoginForm");
        f.getToolbar().setHidden(true);
        
        Image img = theme.getImage("logo.png");
        Label imgLabel = new Label(img, "First Image");
        Container c = new Container(new FlowLayout(Component.CENTER, Component.CENTER));
        c.add(imgLabel);
        f.add(c);
        
        TextField email = new TextField("", "Adresse mail");
        Label emailIcon = new Label("", "TextField");
        emailIcon.getAllStyles().setFgColor(0xf5bf0a);
        FontImage.setMaterialIcon(emailIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        Container c_username = new Container(BoxLayout.x());
        c_username.addAll(emailIcon, email);
        f.add(c_username);
        
        Label hide =new Label("  ");
        f.add(hide);
        
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        Label passwordIcon = new Label("", "TextField");
        passwordIcon.getAllStyles().setFgColor(0xf5bf0a);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        Container c_password = new Container(BoxLayout.x());
        c_password.addAll(passwordIcon, password);
        f.add(c_password);
        
        Label hide1 =new Label("  ");
        f.add(hide1);
        
        Button loginButton = new Button("Login");
        loginButton.setUIID("LoginButton");
        loginButton.getAllStyles().setFgColor(0xffffff);
        
        Button registerButton =new Button("Register");
        registerButton.setUIID("RegisterButton");
        registerButton.getAllStyles().setFgColor(0xffffff);
        
        Container c_Button = new Container(BoxLayout.x());
        c_Button.addAll(loginButton, registerButton);
        
        Container container = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        container.add(BorderLayout.CENTER, c_Button);
        f.add(container);
        
        Button facebookButton = new Button("Facebook");
        facebookButton.setUIID("FacebookButton");
        facebookButton.getAllStyles().setFgColor(0xffffff);
        Container c_fcb_Button = new Container(BoxLayout.x());
        c_fcb_Button.add(facebookButton);
        Container container_fcb = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        container_fcb.add(BorderLayout.CENTER, c_fcb_Button);
        f.add(container_fcb);
//        f.add(c_fcb_Button);
        
        
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                String url = "http://localhost/codename/AllForKids/selectUser.php?email="+email.getText()+"&pass="+password.getText();
                ConnectionRequest con = new ConnectionRequest(url);
                
                con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        user = uss.getUser2(new String(con.getResponseData()));
                        if (user != null) {
                            System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
//                            System.out.println(uss.getUser2(new String(con.getResponseData())));
//                                user = uss.getUser(new String(con.getResponseData()));

//                            HomeForm home = new HomeForm();
//                            home.getF().show();
                        }
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

    public User getUser() {
        return user;
    }
    
}



/**
*@Lau82 © 2018
*/
