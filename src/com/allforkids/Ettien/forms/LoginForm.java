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
import com.allforkids.Ettien.services.FacebookService;
import com.allforkids.Ettien.services.UserService;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
    
    boolean is_connect;
    public static User user;
    public UserService uss;
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
        
        Label forget_password = new Label("Forget Password");
        forget_password.getAllStyles().setFgColor(0xf5bf0a);
        Button facebookButton = new Button("Facebook");
        facebookButton.setUIID("FacebookButton");
        facebookButton.getAllStyles().setFgColor(0xffffff);
        Container c_fcb_Button = new Container(BoxLayout.x());
        c_fcb_Button.addAll(facebookButton, forget_password);
        Container container_fcb = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        container_fcb.add(BorderLayout.CENTER, c_fcb_Button);
        f.add(container_fcb);
        
        
        
        loginButton.addActionListener((ActionListener) (ActionEvent evt) -> {
            uss = new UserService();
            String res = uss.getInfoUser(email.getText(), password.getText());
//            System.out.println(res);
            
            try {
                user = uss.getUser(res);
//                System.out.println(user);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
            
            if(res.contains(email.getText()) && res.contains(password.getText())){
                HomeForm home = new HomeForm();
                home.getF().show();
                System.out.println("Ok verifé");
            }else{
                Dialog.show("Erreur", "Les informations saisies ne sont pas correctes", "Ok", "Cancel");
            }
        });
        
        
        registerButton.addActionListener((ActionListener) (ActionEvent evt) -> {
            RegisterForm regsF = new RegisterForm();
            regsF.getF().show();
        });
        
        
        facebookButton.addActionListener((ActionListener) (ActionEvent evt) -> {
            FacebookService fb_service = new FacebookService();
            is_connect = fb_service.loginWithFacebook();
        });
        if(is_connect == true){
            HomeForm home = new HomeForm();
            home.getF().show();
        }
        
        forget_password.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            ForgetPasswordForm fpf = new ForgetPasswordForm();
            fpf.getF().show();
        });
        
    }

    public LoginForm() {}
    
    
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
