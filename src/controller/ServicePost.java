/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener; 
import entities.Post;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.concurrent.Task;

/**
 *
 * @author amine
 */
public class ServicePost {
    
     public ArrayList<Post> getListTask(String json) {

        ArrayList<Post> listPosts = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Post e = new Post();

                // System.out.println(obj.get("id"));
                int id_post = Integer.parseInt(obj.get("idPost").toString().substring(0, 1));
                System.out.println(id_post);
                e.setId_post((int) id_post);
//                
//                 int post_user = Integer.parseInt(obj.get("post_user").toString());
//                System.out.println(post_user);
//                e.setId_post((int) post_user);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setTitre(obj.get("titre").toString());
                e.setContenu(obj.get("contenu").toString());
                e.setImage(obj.get("image").toString());
                System.out.println(e);
                listPosts.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listPosts);
        return listPosts;

    }
    
    
    ArrayList<Post> listPosts = new ArrayList<>();
    
    public ArrayList<Post> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/AllForKids/web/app_dev.php/a");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServicePost ser = new ServicePost();
                listPosts = ser.getListTask(new String(con.getResponseData()));
            }
        });
        
         
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 NetworkManager.getInstance().addToQueueAndWait(con);
        return listPosts;
    }

    
}
