/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yassine.services;
import com.allforkids.yassine.entities.evenement;
import com.allforkids.yassine.gui.partage;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.facebook.User;
import com.codename1.io.Storage;
import com.codename1.social.FacebookConnect;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
/**
 *
 * @author ASUS
 */
public class fbConnect extends com.codename1.ui.Form{
    public fbConnect() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public fbConnect(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        showFormElements();
    }
    
     private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setInlineStylesTheme(resourceObjectInstance);
        setTitle("connexion ");
        setName("connexion");
    }// </editor-fold>
     
      private void showFormElements() {
        this.setScrollable(false);
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        showData(this);
    }

    private void showData(fbConnect form) {
         facebookLogin(form);
         

    }

    private void showIfNotLoggedIn(fbConnect form) {
        form.getContentPane().removeAll(); //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
        Storage.getInstance().writeObject("token", "");
        ScaleImageLabel myPic = new ScaleImageLabel();
        Dimension d = new Dimension(50, 50);
        myPic.setPreferredSize(d);
        form.add(myPic);
        form.add(new Label("User non connected"));
        Button buttonLogin = new Button("Login");
        buttonLogin.addActionListener((e) -> {
            facebookLogin(form);
        });
        form.add(buttonLogin);
        form.revalidate();
        //form.show();
    }

    private void showIfLoggedIn(fbConnect form) {
        String token = (String) Storage.getInstance().readObject("token");
        FaceBookAccess.setToken(token);
            final User me = new User();
            try {
                FaceBookAccess.getInstance().getUser("me", me, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        String miNombre = me.getName();
                        
                        form.getContentPane().removeAll();
                        
                        form.add(new Label(miNombre));
                        
                        Button buttonLogout = new Button("Logout");
                        buttonLogout.addActionListener((e) -> {
                            facebookLogout(form);
                            showIfNotLoggedIn(form);
                        });

                        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
                        URLImage background = URLImage.createToStorage(placeholder, "fbuser.jpg",
                                "https://graph.facebook.com/v2.11/me/picture?access_token=" + token);
                        background.fetch();
                        ScaleImageLabel myPic = new ScaleImageLabel();
                        myPic.setIcon(background);
                        
                        form.add(myPic);
                        form.add(buttonLogout);
                        
                        form.revalidate();
                        //form.show();
                    }

                    
                });
            } catch (IOException ex) {
                ex.printStackTrace();
                showIfNotLoggedIn(form);
            }
    }

    private void facebookLogout(fbConnect form) {
        String clientId = "349160732241016";
        String redirectURI = "https://www.google.fr/"; //Una URI cualquiera. Si la pones en tu equipo debes crear un Servidor Web. Yo usé XAMPP
        String clientSecret = "db068ddd2fc20481f4bdb6775f581a71";
        FacebookConnect fb = FacebookConnect.getInstance();
        fb.setClientId(clientId);
        fb.setRedirectURI(redirectURI);
        fb.setClientSecret(clientSecret);

        //trigger the login if not already logged in
        fb.doLogout();
        Storage.getInstance().writeObject("token", "EAAE9j1FqLHgBABP2A2dHmqjzdFVJSuETicWLCfg4ttOeExWRziH5uDklgctz1nCT4TH2d0ibW1ATbIWCgdVzXdYRNo7okZB90yF75LsW4QSQc0Yr4yqtiwFR3Ki9J8z3s48Uvh1XKLt9dBPPDGpEIBJ7ZCOttuwGnBq0uXAQZDZD");
        showIfNotLoggedIn(form);
    }
    
    public void facebookLogin(fbConnect form) {
         String clientId = "349160732241016";
        String redirectURI = "https://www.google.fr/"; //Una URI cualquiera. Si la pones en tu equipo debes crear un Servidor Web. Yo usé XAMPP
        String clientSecret = "db068ddd2fc20481f4bdb6775f581a71";
        FacebookConnect fb = FacebookConnect.getInstance();
        fb.setClientId(clientId);
        fb.setRedirectURI(redirectURI);
        fb.setClientSecret(clientSecret);
        //Sets a LoginCallback listener
        fb.setCallback(new LoginCallback() {
            @Override
            public void loginFailed(String errorMessage) {
                System.out.println("Falló el login");
                Storage.getInstance().writeObject("token", "EAAE9j1FqLHgBABP2A2dHmqjzdFVJSuETicWLCfg4ttOeExWRziH5uDklgctz1nCT4TH2d0ibW1ATbIWCgdVzXdYRNo7okZB90yF75LsW4QSQc0Yr4yqtiwFR3Ki9J8z3s48Uvh1XKLt9dBPPDGpEIBJ7ZCOttuwGnBq0uXAQZDZD");
                showIfNotLoggedIn(form);
                System.out.println("erreuuuuur");
            }

            @Override
            public void loginSuccessful() {
                System.out.println("Funcionó el login");
                String token = fb.getAccessToken().getToken();
                Storage.getInstance().writeObject("token", token);
            
                partage p=new partage();
                p.getF().show();
                
            }
            
        });
        if(!fb.isUserLoggedIn()){
            fb.doLogin();
        }else{
            String token = fb.getAccessToken().getToken();
            Storage.getInstance().writeObject("token", token);
            showIfLoggedIn(form);
        }
    }

    public void setInlineStylesTheme(Resources resourceObjectInstance) {
    }
}
