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
import entities.Commentaire;
import entities.Post;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author amine
 */
public class SinglePostController {

    public ArrayList<Post> getListTask(String json) {

        ArrayList<Post> listPosts = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("post");

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

    Post listPosts = new Post();

    public Post getList2(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/AllForKids/web/app_dev.php/post/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listPosts = getListTask(new String(con.getResponseData())).get(0);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPosts;
    }

    public ArrayList<Commentaire> getListcom(String json) throws IOException {
        JSONParser j = new JSONParser();
        ArrayList<Commentaire> listcoms = new ArrayList<>();
        Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
        System.out.println(etudiants);

        List<Map<String, Object>> listcom = (List<Map<String, Object>>) etudiants.get("coms");
      
        
        for (Map<String, Object> obj : listcom) {
            Commentaire c = new Commentaire();

            c.setContenu(obj.get("contenu").toString());
            Map<String, Object> ss = (Map<String, Object>) obj.get("createdAt");
            float pp = Float.parseFloat(ss.get("timestamp").toString());
            c.setDate(new Date((long) pp * 1000));
            listcoms.add(c);

        }
        return listcoms;
    }
    ArrayList<Commentaire> sss = new ArrayList<>();

    public ArrayList<Commentaire> getListcoms2(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/AllForKids/web/app_dev.php/listcommentjson/" +id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    sss = getListcom(new String(con.getResponseData()));
                } catch (IOException ex) {
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return sss;
    }
}
