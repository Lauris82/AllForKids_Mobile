/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yasmine.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.allforkids.yasmine.entities.ClubEntity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class ClubService {

    String str;

    public ArrayList<ClubEntity> getList2() {
        ArrayList<ClubEntity> listClubs = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/AllForKids/web/app_dev.php/Web_S_Lister_Club");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    //listTasks = getListTask(new String(con.getResponseData()));
                    JSONParser jsonp = new JSONParser();

                    Map<String, Object> clubs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(clubs);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) clubs.get("root");
                    for (Map<String, Object> obj : list) {
                        ClubEntity e = new ClubEntity();

                        // System.out.println(obj.get("id"));
                        float id;
                        id = Float.parseFloat(obj.get("idclub").toString());
                        System.out.println(id);
                        e.setIdclub((int) id);
                        //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
//                        
//                           float idUser;
//                        idUser = Float.parseFloat(obj.get("club_user").toString());
//                        
//                e.setClub_user((int) idUser);
                        e.setNom(obj.get("nom").toString());
                        e.setDescription(obj.get("description").toString());
                        e.setNumTel(obj.get("numTel").toString());
                        e.setGouvernorat(obj.get("gouvernorat").toString());

                        listClubs.add(e);

                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        // NetworkManager.getInstance().addToQueue(con); ==> ye5dem toul affichage mayestanech resultat tjih heka 3leh tji ferr8a 
        return listClubs;
    }

    public void ajoutClub(ClubEntity ta) {

        ConnectionRequest con = new ConnectionRequest();

        String Url = "http://localhost/AllForKids/web/app_dev.php/Web_S_ajout?nom=" + ta.getNom() + "&description=" + ta.getDescription()
                + "&numTel=" + ta.getNumTel() + "&gov=" + ta.getGouvernorat() + "";
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

    public void modifierC(ClubEntity ta) {
        ConnectionRequest con = new ConnectionRequest();

        String Url = "http://localhost/AllForKids/web/app_dev.php/Web_S_modif?idc=" + ta.getIdclub() + "&n=" + ta.getNom() + "&d=" + ta.getDescription()
                + "&nu=" + ta.getNumTel() + "&g=" + ta.getGouvernorat();
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
