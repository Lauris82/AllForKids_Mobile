/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yassine.services;

import com.allforkids.yassine.entities.evenement;
import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.File;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.ImageIO;
import com.codename1.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class serviceEvenement {
            public void ajoutEvenement(evenement e) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/AllForKids/web/app_dev.php/creerEvenementMobile?user="+e.getEvenement_user()+"&nom="+e.getNom()+"&desc="+e.getDescription()+"&emp="+e.getEmplacement()+"&dateD="+e.getDate_debut()+"&dateF="+e.getDate_fin()+"&img="+e.getImage()+"&nbrP="+e.getNbr_place() ;
        con.setUrl(Url);
        con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());
            //tem.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
             public ArrayList<evenement> getListEvenement(String json) {

        ArrayList<evenement> listEvenement = new ArrayList<>();

        try {
           // System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> e = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) e.get("root");
Map<String, Object> list2  ;
Map<String, Object> list3 ;
Map<String, Object> list4 ;
            for (Map<String, Object> obj : list) {
                evenement ev=new evenement();
                list2= (Map<String, Object>) obj.get("user");
                list3=(Map<String, Object>) obj.get("dateDebut");
                list4=(Map<String, Object>) obj.get("dateFin");
                float id = Float.parseFloat(obj.get("id_evenement").toString());
                ev.setId_evenement((int) id);
              ev.setNom(obj.get("nom").toString());
              ev.setDescription(obj.get("description").toString());
              ev.setEmplacement(obj.get("emplacement").toString());
              ev.setImage(obj.get("image").toString());
              float id2 = Float.parseFloat(obj.get("nbrPlace").toString());
              ev.setNbr_place((int)id2);
              ev.setEvenement_user((int)Float.parseFloat(list2.get("id").toString()));
                  SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy ");
               long batch_date = (long)Float.parseFloat(list3.get("timestamp").toString()); 
    Date dt = new Date (batch_date * 1000); 
    ev.setDate_debut(sfd.format(dt));
     long batch_date2 = (long)Float.parseFloat(list4.get("timestamp").toString()); 
    Date dt2 = new Date (batch_date2 * 1000); 
              ev.setDate_fin(sfd.format(dt2));


                listEvenement.add(ev);

            }

        } catch (IOException ex) {
        }

        return listEvenement;

    }
    
    
    ArrayList<evenement> listEvenement = new ArrayList<>();
    
    public ArrayList<evenement> getListEvenement2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/AllForKids/web/app_dev.php/listEvenementMobile");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                serviceEvenement ser = new serviceEvenement();
                listEvenement = ser.getListEvenement(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvenement;
    }
          public void modifierEvenement(evenement e) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/AllForKids/web/app_dev.php/modifierEvenementMobile?user="+e.getEvenement_user()+"&nom="+e.getNom()+"&desc="+e.getDescription()+"&emp="+e.getEmplacement()+"&dateD="+e.getDate_debut()+"&dateF="+e.getDate_fin()+"&img="+e.getImage()+"&nbrP="+e.getNbr_place()+"&id="+e.getId_evenement() ;
        con.setUrl(Url);
        con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());
        //    System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
           public void supprimerEvenement(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/AllForKids/web/app_dev.php/supprimerEvenementMobile?id="+id;
        con.setUrl(Url);
        con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());
        //    System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
            public void setImage(String filePath, ImageViewer iv) {
           try {
               //creation d'image apartir du filepath
               Image i1 = Image.createImage(filePath).scaled(400,400);
               iv.setImage(i1);
               if (i1 != null) {
                   //FileSystemStorage  
                   //trodek tnajm testoki l image en binaire
                   ImageIO imgIO = ImageIO.getImageIO();
                   //stocker l'inage dans le flux
                   ByteArrayOutputStream out = new ByteArrayOutputStream();
                   imgIO.save(i1, out, File.separator, 1);
                   //recuperer l image du flux dans un tab binaire
                   byte[] ba = out.toByteArray();
                   //cryptage de l image binaire
                   String Imagecode = Base64.encode(ba);
                   ConnectionRequest request = new ConnectionRequest();
                   request.addResponseListener((NetworkEvent evt) -> {
                       byte[] data = (byte[]) evt.getMetaData();
                       String imageName = new String(data);
                       System.out.println("metadata "+imageName);
                       iv.getImage().setImageName(imageName);
                   });
                   request.setPost(true);
                   request.setHttpMethod("POST");
                  // imagecode sequence binaire de l image coder
                   request.addArgument("Image", Imagecode);
                   request.setUrl("http://localhost:80/Upload.php");
                   NetworkManager.getInstance().addToQueueAndWait(request);
               } else {
                   System.out.println("Unable to upload");
               }
               iv.getParent().revalidate();

           } catch (Exception ex) {
            
           }
       
    }
        
 public void browseImage(ImageViewer im){
     //open gallery
     Display.getInstance().openGallery((ActionListener) (ActionEvent ev) -> {
         
         if (ev != null && ev.getSource() != null) {
             String filePath = (String) ev.getSource();
                // retenue de nom d'image
//             int fileNameIndex = filePath.lastIndexOf("/") + 1;
//             String fileName = filePath.substring(fileNameIndex);
             // Do something
             
             setImage(filePath,im);
         }
     }, Display.GALLERY_IMAGE);
    
 }    

}
