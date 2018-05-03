/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import entities.Commande;
import entities.produitjson;

/**
 *
 * @author amine
 */
public class ServiceCommande {
  
    public void ajoutTask(Commande p) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/AllForKids/web/app_dev.php/commandeproduit" + "?id_user="+p.getUser() + "&nom=" + p.getNom()+
                "&qte="+ p.getQuantité()+ "&id_produit=" +p.getIdProduit();
        con.setUrl(Url);
        
        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            Dialog.show("Ajout Commande", "Ajout avec Success", "OK",null);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
}
