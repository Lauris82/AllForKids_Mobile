/**
* @Project: AllForKids_Mobile
* @Classe: UserService
* @Date: 19 avr. 2018
* @Time: 23:29:01
*
* @author Lauris
*/


package com.allforkids.Ettien.services;

import com.allforkids.Ettien.entities.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UserService {
       
    User user = new User();


    public ArrayList<User> getListTask(String json) {

        ArrayList<User> listUsers = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(users);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("root");

            for (Map<String, Object> obj : list) {
//                User user = new User();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                user.setId((int) id);
                user.setUsername(obj.get("username").toString());
                user.setEmail(obj.get("email").toString());
                user.setNom(obj.get("nom").toString());
                user.setPrenom(obj.get("prenom").toString());
                user.setAdresse(obj.get("adresse").toString());
                System.out.println(user);
                listUsers.add(user);
            }

        } catch (IOException ex) {
        }
        System.out.println(listUsers);
        return listUsers;
    }
    
    ArrayList<User> listTasks = new ArrayList<>();
    
    public ArrayList<User> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Allforkids/web/app_dev.php/api/users/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                UserService uss = new UserService();
                listTasks = uss.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    
    public User getUser(String json) throws IOException {
        System.out.println("\n" + json);
        JSONParser j = new JSONParser();

        Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
        User us = new User();

        Map<String, Object> list;
        list = (Map<String, Object>) users.get("root");
        Map<String , Object> m = null  ;
        String s = null;
        System.out.println("**************************"+list);
       
        if (list != null) {
            for (Map.Entry mapentry : list.entrySet()) {
                System.out.println("clé: " + mapentry.getKey()
                        + " | valeur: " + mapentry.getValue());
            }
            us.setId(Integer.parseInt(list.get("id").toString()));
            us.setNom(String.valueOf(list.get("nom")));
            us.setPrenom(String.valueOf(list.get("prenom")));
            us.setEmail(String.valueOf(list.get("email")));
            us.setUsername(String.valueOf(list.get("username")));
            
            System.out.println("***********" + us.getNom());
        } else {
            us = null;
            System.err.println("Aucun User");
        }
        return us;
    }


    public User getUser2(String json) {

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(users);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("root");

            for (Map<String, Object> obj : list) {

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                user.setId((int) id);
                user.setUsername(obj.get("username").toString());
                user.setEmail(obj.get("email").toString());
                user.setNom(obj.get("nom").toString());
                user.setPrenom(obj.get("prenom").toString());
                user.setAdresse(obj.get("adresse").toString());
                System.out.println(user);
            }

        } catch (IOException ex) {
        }
        return user;
        
    }
    
}



/**
*@Lau82 © 2018
*/
