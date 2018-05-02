/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yassine.services;

import com.allforkids.yassine.entities.evenement;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class serviceReservation {
     Boolean existe=false ;
         String str;
 public boolean estReserver(int idu , int ide) {
            
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/AllForKids/web/app_dev.php/estReserver?idu="+idu+"&ide="+ide;
con.setUrl(Url);
        con.addResponseListener((ee) -> {
             str = new String(con.getResponseData());
        });
     NetworkManager.getInstance().addToQueueAndWait(con);
existe=Boolean.valueOf(str);
        return existe ;
}
  public void Reserver(int idu , int ide) {
            
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/AllForKids/web/app_dev.php/ReserverEvenementM?idu="+idu+"&ide="+ide;
con.setUrl(Url);
        con.addResponseListener((ee) -> {
             str = new String(con.getResponseData());
        });
     NetworkManager.getInstance().addToQueueAndWait(con);

}
    public void dereserver(int idu , int ide) {
            
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/AllForKids/web/app_dev.php/deeserverEvenementM?idu="+idu+"&ide="+ide;
con.setUrl(Url);
        con.addResponseListener((ee) -> {
             str = new String(con.getResponseData());
        });
     NetworkManager.getInstance().addToQueueAndWait(con);

}
              public ArrayList<evenement> getListReservation(String json) {

        ArrayList<evenement> listReservation = new ArrayList<>();

        try {
           // System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> e = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) e.get("root");
Map<String, Object> list2  ;
Map<String, Object> list3 ;
            for (Map<String, Object> obj : list) {
                evenement ev=new evenement();
                list2= (Map<String, Object>) obj.get("user");
                list3=(Map<String, Object>) obj.get("dateReservation");
                
              ev.setNom(list2.get("nom").toString());
              ev.setDescription(list2.get("prenom").toString());
              ev.setImage(list2.get("image").toString());
            //  ev.setEvenement_user((int)Float.parseFloat(list2.get("id").toString()));
                  SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy ");
               long batch_date = (long)Float.parseFloat(list3.get("timestamp").toString()); 
    Date dt = new Date (batch_date * 1000); 
    ev.setDate_debut(sfd.format(dt));
    


                listReservation.add(ev);

            }

        } catch (IOException ex) {
        }

        return listReservation;

    }
    
    
    ArrayList<evenement> listReservation = new ArrayList<>();
    
    public ArrayList<evenement> getListReservation2(int ide){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/AllForKids/web/app_dev.php/membresEvenementM?ide="+ide);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                serviceReservation ser = new serviceReservation();
                listReservation = ser.getListReservation(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        return listReservation;
    }
    
     public int nbrReservation(int ide) throws IOException {
          
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/AllForKids/web/app_dev.php/nbrReservationM?ide="+ide;
con.setUrl(Url);
        con.addResponseListener((ee) -> {
             str = new String(con.getResponseData());
        });
     NetworkManager.getInstance().addToQueueAndWait(con);
      JSONParser j = new JSONParser();
      Map<String, Object> e=j.parseJSON(new CharArrayReader(str.toCharArray()));
     
    
return Integer.valueOf(e.get("1").toString());
}
}
