/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.raoudha.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.allforkids.raoudha.entities.Garderie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author user
 */
public class serviceGarderie 

{

    public serviceGarderie() {}
    
     public ArrayList<Garderie> getListGarderie(String json) throws IOException {
        ArrayList<Garderie> listGarderies = new ArrayList<>();
       
              System.out.println(json);
            JSONParser j = new JSONParser();
            Map<String, Object> garderies = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) garderies.get("root");
            

            for (Map<String, Object> obj : list)
            {
                 Garderie g = new Garderie();
                 float id = Float.parseFloat(obj.get("id_garderie").toString());
                System.out.println(id);
                g.setId_garderie((int) id);
              
                g.setNom(obj.get("nom").toString());
                System.out.println("2");
               g.setEmplacement(obj.get("emplacement").toString());
               System.out.println("3");
                
                g.setDescription(obj.get("description").toString());
                System.out.println("4");
                g.setCapacite((int) Float.parseFloat(obj.get("capacite").toString()));
                System.out.println("5");
                
                g.setNum_tel((int) Float.parseFloat(obj.get("numTel").toString()));
                System.out.println("6");
                float user=1;
                g.setUser_garderie((int)user );
                System.out.println("7");
                g.setNomImage(obj.get("nomImage").toString());
                
                System.out.println(g);
                listGarderies.add(g);

            }


{

}


          
        return listGarderies;

    }
         ArrayList<Garderie> listgarderie = new ArrayList<>();
  
           public ArrayList<Garderie> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
              
        con.setUrl("http://localhost/AllForKids/web/app_dev.php/aff2");  
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                serviceGarderie ser = new serviceGarderie();
                 System.out.println("ici cv");
                try {
                    listgarderie = ser.getListGarderie(new String(con.getResponseData()));
                } catch (IOException ex) {
                    System.out.println(ex);                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return  listgarderie;
    }
           /*  
           
            ConnectionRequest req = new ConnectionRequest();
   req.setUrl("http://localhost/ScriptMobile/ajoutGard.php?nom=" + txt1.getText() + "&emplacement=" + txt2.getText() + "&description=" + txt3.getText()+ "&capacite=" + txt4.getText()+ "&num_tel="+txt5.getText() + "");          
            req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(req);   
           
           
           */
           
           public ArrayList<Garderie> AffichedetailGard(Garderie g)
           { ConnectionRequest con = new ConnectionRequest();
           con.setUrl("http://localhost/AllForKids/web/app_dev.php/aff3/"+g.getId_garderie());
           
               System.out.println(g.getId_garderie());
               
           con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                serviceGarderie ser = new serviceGarderie();
                 System.out.println("ici cv");
                try {
                    listgarderie = ser.getListGarderie(new String(con.getResponseData()));
                } catch (IOException ex) {
                    System.out.println(ex);                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return  listgarderie;
           } 
           
           public void supprimer(Garderie g)
           {
            ConnectionRequest con = new ConnectionRequest();
              String Url = "http://localhost/AllForKids/web/app_dev.php/suppGarderieMobile/"+g.getId_garderie();
                   con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            System.out.println("suppression effectuée");

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
            
            
           }
           
         
}
