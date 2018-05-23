/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yasmine.services;

import allforkids.yasmine.entities.ReclamationEntity;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

/**
 *
 * @author DELL
 */
public class RecService {
   
        String str;

    
    
         public void envoiRec(ReclamationEntity ta) {

        ConnectionRequest con = new ConnectionRequest();

        String Url = "http://localhost/AllForKids/web/app_dev.php/Web_S_EnvoiR?objet=" + ta.getObjetRec() + "&mail=" + ta.getMail()
                + "&contenu=" + ta.getContenuRec();
        System.out.println(Url);
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            str = new String(con.getResponseData());
//            System.out.println(str);

            System.out.println("tessssssssssssssst");

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println(str + " tst ");

    }
    
    
}
