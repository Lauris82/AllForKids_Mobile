/**
* @Project: AllForKids_Mobile
* @Classe: FacebookService
* @Date: 25 avr. 2018
* @Time: 18:21:21
*
* @author Lauris
*/


package com.allforkids.Ettien.services;

import com.allforkids.Ettien.forms.HomeForm;
import com.allforkids.Ettien.forms.LoginForm;
import com.codename1.io.Storage;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Dialog;


public class FacebookService {
    boolean is_connect;
        
    public boolean loginWithFacebook(){
        
        String clientId = "2044145842464338";
        String redirectURI = "https://github.com/Lauris82";
        String clientSecret = "30ec51a3282ef3136654ee91b4d5f4e1";
        
        Login fb = FacebookConnect.getInstance();
        fb.setClientId(clientId);
        fb.setRedirectURI(redirectURI);
        fb.setClientSecret(clientSecret);
        
        //Sets a LoginCallback listener
        
        fb.setCallback(new LoginCallback() {
            @Override
            public void loginFailed(String errorMessage) {
                is_connect = false;
                System.out.println("Connection failed");
                Dialog.show("Erreur", "Les informations saisies ne sont pas correctes", "Ok", "Cancel");
                Storage.getInstance().writeObject("token", "");
            }

            @Override
            public void loginSuccessful() {
                is_connect = true;
//                LoginForm log = new LoginForm();
//                log.getForm().show();
                
                System.out.println("Connection success");
                String token = fb.getAccessToken().getToken();
                Storage.getInstance().writeObject("token", token);
            }
            
        });
        
        //trigger the login if not already logged in
        if(!fb.isUserLoggedIn()){
            fb.doLogin();
        }else{
            //get the token and now you can query the facebook API
            String token = fb.getAccessToken().getToken();
            Storage.getInstance().writeObject("token", token);
        }
        
        return is_connect;
    }

}



/**
*@Lau82 © 2018
*/
