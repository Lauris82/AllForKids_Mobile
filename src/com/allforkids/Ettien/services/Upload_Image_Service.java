/**
* @Project: AllForKids_Mobile
* @Classe: Upload_Image_Service
* @Date: 29 avr. 2018
* @Time: 13:42:11
*
* @author Lauris
*/


package com.allforkids.Ettien.services;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.ImageIO;
import com.codename1.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class Upload_Image_Service {
    
    public void chooseImage(ImageViewer imgV){
        Display.getInstance().openGallery((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (ev != null && ev.getSource() != null) {
                    try {
                        String filePath = (String) ev.getSource();
                        int fileNameIndex = filePath.lastIndexOf("/") + 1;
                        String fileName = filePath.substring(fileNameIndex);
                        
                        Image imge = null;
                        imge = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath));
                        
                        imgV.setImage(imge);
                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
                }
            }
        }, Display.GALLERY_IMAGE);
    }
    
    
    public String encodeImageToBase64(Image img, String imgEncoded) throws IOException{
        ImageIO imgIO = ImageIO.getImageIO();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            imgIO.save(img, out, ImageIO.FORMAT_JPEG, 1);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        byte[] ba = out.toByteArray();
        imgEncoded = Base64.encode(ba);
        
        return imgEncoded;
    }
    
    
    String chaine;
    public String testI(String imgEncode, String imgName) throws IOException{
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(true);
        con.setHttpMethod("POST");
        con.addArgument("Image", imgEncode);
        con.addArgument("Name", imgName);
        con.setUrl("http://localhost:80/Upload_image.php");
        
        con.addResponseListener((NetworkEvent evt) -> {
            byte[] data = con.getResponseData();
            chaine = new String(data);
            System.out.println(chaine);
        });
        NetworkManager.getInstance().addToQueue(con);
        return chaine;
    }

}



/**
*@Lau82 © 2018
*/
