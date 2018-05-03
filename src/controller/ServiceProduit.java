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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import entities.Post;
import entities.produitjson;
import entities.produitjson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author amine
 */
public class ServiceProduit {
    public void ajoutTask(produitjson p) {
        System.out.println(p.toString());
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/AllForKids/web/app_dev.php/ajoutproduitjson" + "?NomProduit="+p.getNomProduit() + "&TypeProduit=" + p.getTypeProduit()+
                "&PrixProduit="+ p.getPrixProduit()+ "&Description=" +p.getDescription()+ "&PrixPromo=" +p.getPrixPromo()+ "&image=" +p.getImage();
        con.setUrl(Url);
        
        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            Dialog.show("Ajout Produit", "Ajout avec Success", "OK",null);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    
    public ArrayList<produitjson> getListProduit(String json) {

        ArrayList<produitjson> listProduits = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> produits = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(produits);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) produits.get("root");

            for (Map<String, Object> obj : list) {
                produitjson e = new produitjson();

                // System.out.println(obj.get("id"));
                float IdProduit = Float.parseFloat(obj.get("IdProduit").toString());
                System.out.println(IdProduit);
                e.setIdProduit((int) IdProduit);
                
                
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setNomProduit(obj.get("nomProduit").toString());
                e.setTypeProduit(obj.get("typeProduit").toString());
                e.setPrixProduit(Double.parseDouble(obj.get("prixProduit").toString()));
                e.setDescription(obj.get("description").toString());
                e.setPrixPromo(Double.parseDouble(obj.get("prixPromo").toString()));
                e.setImage(obj.get("image").toString());
                //e.setRating(Integer.parseInt(obj.get("rating").toString().trim()));
               
             
          
                
                System.out.println(e);
                listProduits.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listProduits);
        return listProduits;

    }
    
    
    
    
    ArrayList<produitjson> listProduits = new ArrayList<>();
    
    public ArrayList<produitjson> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/AllForKids/web/app_dev.php/listproduitjson");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProduit ser = new ServiceProduit();
                listProduits = ser.getListProduit(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduits;
    }
}
