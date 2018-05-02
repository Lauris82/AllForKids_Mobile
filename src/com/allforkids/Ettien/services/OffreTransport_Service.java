/**
* @Project: AllForKids_Mobile
* @Classe: OffreTransport_Service
* @Date: 1 mai 2018
* @Time: 12:04:52
*
* @author Lauris
*/


package com.allforkids.Ettien.services;

import com.allforkids.Ettien.entities.OffreTransport;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class OffreTransport_Service {
    
    String chaine = new String();
    ArrayList<OffreTransport> listTasks = new ArrayList<>();
    
    
    public ArrayList<OffreTransport> getListTask(String json) {
        ArrayList<OffreTransport> listOffres = new ArrayList<>();
        try {
//            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> offers = j.parseJSON(new CharArrayReader(json.toCharArray()));
//            System.out.println(offers);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) offers.get("OffreT");

            for (Map<String, Object> obj : list) {
                OffreTransport offre = new OffreTransport();
                
                offre.setId(Integer.parseInt(obj.get("id").toString()));
                offre.setUser(Integer.parseInt(obj.get("user").toString()));
                offre.setDescription(obj.get("description").toString());
                offre.setDestination(obj.get("destination").toString());
                offre.setDate_debut(obj.get("date_debut").toString());
                offre.setDate_fin(obj.get("date_fin").toString());
                offre.setNombre_place(Integer.parseInt(obj.get("nombre_place").toString()));
                offre.setPlace_restant(Integer.parseInt(obj.get("place_restant").toString()));
                offre.setPrix(Double.parseDouble(obj.get("prix").toString()));
                
                listOffres.add(offre);
            }
        } catch (IOException ex) {
        }
//        System.out.println(listOffres);
        return listOffres;
    }

    
    public ArrayList<OffreTransport> getAllOffers(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/codename/AllForKids/ListOffreTransport.php");  
        con.addResponseListener((NetworkEvent evt) -> {
            OffreTransport_Service ots = new OffreTransport_Service();
            listTasks = ots.getListTask(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    
    public OffreTransport getOffreTransport(String json) throws IOException {
        JSONParser j = new JSONParser();

        Map<String, Object> offres = j.parseJSON(new CharArrayReader(json.toCharArray()));
        OffreTransport offre = new OffreTransport();

        Map<String, Object> list;
        list = (Map<String, Object>) offres.get("OffreT");
       
        if (list != null) {
            for (Map.Entry mapentry : list.entrySet()) {
                System.out.println("clé: " + mapentry.getKey()
                        + " | valeur: " + mapentry.getValue());
            }
            offre.setId(Integer.parseInt(list.get("id").toString()));
            offre.setUser(Integer.parseInt(list.get("user").toString()));
            offre.setDescription(list.get("description").toString());
            offre.setDestination(list.get("destination").toString());
            offre.setDate_debut(list.get("date_debut").toString());
            offre.setDate_fin(list.get("date_fin").toString());
            offre.setNombre_place(Integer.parseInt(list.get("nombre_place").toString()));
            offre.setPlace_restant(Integer.parseInt(list.get("place_restant").toString()));
            offre.setPrix(Double.parseDouble(list.get("prix").toString()));

            System.out.println(offre);
        } else {
            offre = null;
            System.err.println("Aucun User");
        }
        return offre;
    }
    
    
    
    public String getOffreById(int id){ 
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/codename/AllForKids/selectOffreById.php?id="+id);  
        con.addResponseListener((NetworkEvent evt) -> {
            byte [] data = (byte[]) evt.getMetaData();
            chaine = new String(data);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        return chaine;
    }

}



/**
*@Lau82 © 2018
*/
