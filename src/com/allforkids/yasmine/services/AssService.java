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
import com.allforkids.yasmine.entities.AssociationEntity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class AssService {
    
    String str;
    
    
     public ArrayList<AssociationEntity> getList2() {
        ArrayList<AssociationEntity> listass = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/AllForKids/web/app_dev.php/Web_S_Lister_Ass");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser jsonp = new JSONParser();
//con.response resultat de l'url caster sur string
//json parssir convertie e result en map pour parcourir le resultat
                    Map<String, Object> ass = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(ass);
                    // recuperer le map get(root)
                    List<Map<String, Object>> list = (List<Map<String, Object>>) ass.get("root");
                    for (Map<String, Object> obj : list) {
                        AssociationEntity e = new AssociationEntity();

                        // System.out.println(obj.get("id"));
                        float id;
                        id = Float.parseFloat(obj.get("id_aasociation").toString());
                        System.out.println(id);
                        e.setId_aasociation((int) id);
                        //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
//                        
//                           float idUser;
//                        idUser = Float.parseFloat(obj.get("club_user").toString());
//                        
//                e.setClub_user((int) idUser);
                        e.setNom(obj.get("nom").toString());
                        e.setDescription(obj.get("description").toString());
                        e.setNum_tel((int) Float.parseFloat(obj.get("numTel").toString()));
                        e.setGouvernorat(obj.get("gouvernorat").toString());

                        listass.add(e);

                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        // NetworkManager.getInstance().addToQueue(con); 
        return listass;
    }
    
    
        public void modifierA(AssociationEntity ta) {
        ConnectionRequest con = new ConnectionRequest();
       

        String Url = "http://localhost/AllForKids/web/app_dev.php/Web_S_modifA?idc=" + ta.getId_aasociation()+ "&n=" + ta.getNom() + "&d=" + ta.getDescription()
                + "&nu=" + ta.getNum_tel()+ "&g=" + ta.getGouvernorat();
        System.out.println(Url);
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            str = new String(con.getResponseData());
//            System.out.println(str);

            System.out.println("tessssssssssssssst");

        });
        //executer la requete
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println(str + " tst ");

    }
     
     
      public void ajoutAss(AssociationEntity ta) {

        ConnectionRequest con = new ConnectionRequest();

        String Url = "http://localhost/AllForKids/web/app_dev.php/Web_S_ajoutA?nom=" + ta.getNom() + "&description=" + ta.getDescription()
                + "&numTel=" + ta.getNum_tel()+ "&gov=" + ta.getGouvernorat() + "";
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
        
      
        public void supprimerA(AssociationEntity ta){
    
    
              ConnectionRequest con = new ConnectionRequest();

        String Url = "http://localhost/AllForKids/web/app_dev.php/Web_S_suppA?idc=" +ta.getId_aasociation();
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
