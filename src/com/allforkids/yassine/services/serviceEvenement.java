/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yassine.services;

import com.allforkids.yassine.entities.evenement;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

/**
 *
 * @author ASUS
 */
public class serviceEvenement {
            public void ajoutEvenement(evenement e) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://192.168.204.1/3a18/testMobilee.php?nom="+e.getNom()+"&desc="+e.getDescription()+"&user="+e.getEvenement_user() ;
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
