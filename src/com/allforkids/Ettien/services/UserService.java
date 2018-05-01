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
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
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
        con.addResponseListener((NetworkEvent evt) -> {
            UserService uss = new UserService();
            listTasks = uss.getListTask(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    
    public User getUser(String json) throws IOException {
        JSONParser j = new JSONParser();

        Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
        User us = new User();

        Map<String, Object> list;
        list = (Map<String, Object>) users.get("User");
       
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
            us.setContact(Long.parseLong(list.get("contact").toString()));
            us.setImage(String.valueOf(list.get("image")));
            
        } else {
            us = null;
            System.err.println("Aucun User");
        }
        return us;
    }
    
    
    String chaine = new String();
    public String getInfoUser(String email, String password){ 
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/codename/AllForKids/selectUser.php?email="+email+"&pass="+password);  
        con.addResponseListener((NetworkEvent evt) -> {
            byte [] data = (byte[]) evt.getMetaData();
            chaine = new String(data);
        });
        
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        con.setDisposeOnCompletion(dlg);
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        return chaine;
    }
    
    
    public String getInfoUserByEmail(String email){ 
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/codename/AllForKids/selectUserByEmail.php?email="+email);  
        con.addResponseListener((NetworkEvent evt) -> {
            byte [] data = (byte[]) evt.getMetaData();
            chaine = new String(data);
        });
        
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        con.setDisposeOnCompletion(dlg);
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        return chaine;
    }
    
    
    public String addUser(String email, String username, String password, String role, String nom, String prenom, String dateN, String adresse, long contact, String image){ 
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/codename/AllForKids/insertUser.php?email=" +email+ "&username=" +username+"&pass=" +password+
                "&role=" +role+ "&nom=" +nom+ "&prenom=" +prenom+ "&dateb=" +dateN+ "&adresse=" +adresse+
                "&contact=" +contact+ "&image=" +image);  
        
        con.addResponseListener((NetworkEvent evt) -> {
            byte [] data = (byte[]) evt.getMetaData();
            chaine = new String(data);
        });
        
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        con.setDisposeOnCompletion(dlg);
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        return chaine;
    }
    
    
    public String updatePassword(String email, String password){ 
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/codename/AllForKids/updatePassword.php?email=" +email+ "&pass=" +password);  
        
        con.addResponseListener((NetworkEvent evt) -> {
            byte [] data = (byte[]) evt.getMetaData();
            chaine = new String(data);
        });
        
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        con.setDisposeOnCompletion(dlg);
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        return chaine;
    }
    
    
    public static String replaceAll(String source, String pattern, String replace) {
        StringBuilder sb = new StringBuilder();
        String lowSource = source.toLowerCase();
        pattern = pattern.toLowerCase();
        int idx = 0;
        String workingSource = source;
        idx = workingSource.indexOf(pattern);
        if(idx == -1){
            return source;
        }
       
        while (idx != -1) {
            sb.append(workingSource.substring(0, idx));
            sb.append(replace);
            workingSource = workingSource.substring(idx + pattern.length());
            lowSource = lowSource.substring(idx + pattern.length());
            idx = lowSource.indexOf(pattern);            
        }
        sb.append(workingSource);

        return sb.toString();
    }
    
}



/**
*@Lau82 © 2018
*/
