/**
* @Project: AllForKids_Mobile
* @Classe: ReservationOffre_Service
* @Date: 1 mai 2018
* @Time: 16:23:01
*
* @author Lauris
*/


package com.allforkids.Ettien.services;



import com.allforkids.Ettien.entities.Reservation_Offre;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ReservationOffre_Service {
    
    Reservation_Offre res_Offre = new Reservation_Offre();
    String chaine = new String();
    ArrayList<Reservation_Offre> listTasks = new ArrayList<>();
    
    
    public ArrayList<Reservation_Offre> getListTask(String json) {
        ArrayList<Reservation_Offre> listRes = new ArrayList<>();
        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> res_O = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(res_O);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) res_O.get("ReservationO");

            for (Map<String, Object> obj : list) {
                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                res_Offre.setId((int) id);
                
                float idU = Float.parseFloat(obj.get("user").toString());
                res_Offre.setUser((int) idU);
                
                float idNE = Float.parseFloat(obj.get("nombreEnfants").toString());
                res_Offre.setUser((int) idNE);
                
                res_Offre.setDate_reservation((java.util.Date) obj.get("date_reservation"));
                
                float offre = Float.parseFloat(obj.get("offreTransport").toString());
                res_Offre.setOffreTransport((int) offre);
                float place_R = Float.parseFloat(obj.get("etat").toString());
                res_Offre.setEtat((int) place_R);
                
                System.out.println(res_Offre);
                listRes.add(res_Offre);
            }
        } catch (IOException ex) {
        }
        System.out.println(listRes);
        return listRes;
    }

    
    public ArrayList<Reservation_Offre> getAllOffers(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/codename/AllForKids/ListReservationOffre.php");  
        con.addResponseListener((NetworkEvent evt) -> {
            ReservationOffre_Service rots = new ReservationOffre_Service();
            listTasks = rots.getListTask(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

}



/**
*@Lau82 © 2018
*/
