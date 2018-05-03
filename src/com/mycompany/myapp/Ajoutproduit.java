/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.capture.Capture;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import static com.codename1.io.Log.p;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import static com.codename1.ui.Display.GALLERY_IMAGE;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import controller.ServiceProduit;
import entities.produitjson;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import static jdk.nashorn.internal.objects.NativeJava.type;


/**
 *
 * @author amine
 */
public class Ajoutproduit {
    Form f;
    String imgnamee;
    TextField Nomprod;
    TextField type;
    TextField prixprod;
    TextField desc;
    TextField prixpromo;
    Resources resourceObjectInstance=UIManager.initFirstTheme("/theme");
    
    public Ajoutproduit() throws IOException {
    
    
     f = new Form("Ajouter Produit", BoxLayout.y());
    
     f.getToolbar().addCommandToOverflowMenu("Back", null, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new com.allforkids.Ettien.forms.HomeForm().getF().show();
                }
            });
    
        
        
        
         Nomprod = new TextField("", "Nom produit");
         type = new TextField("", "Type produit");
        prixprod = new TextField("", "Prix produit");
        desc = new TextField("", "Description");
        prixpromo = new TextField("", "Prix promo");
        
//        ComboBox<String> region = new ComboBox<>("Ariana", "Béja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba",
//                "Kairouan", "Kasserine", "Kebili", "La Manouba", "Le Kef", "Mahdia", "Médenine", "Monastir",
//                "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan");
//        ComboBox<String> cat = new ComboBox<>();

        


Button avatar = new Button("");
        avatar.setUIID("InputAvatar");
        Image defaultAvatar = FontImage.createMaterial(FontImage.MATERIAL_CAMERA, "InputAvatarImage", 8);
        Image circleMaskImage = Image.createImage(250, 200);
        defaultAvatar = defaultAvatar.scaled(circleMaskImage.getWidth(), circleMaskImage.getHeight());
        defaultAvatar = ((FontImage) defaultAvatar).toEncodedImage();
        Object circleMask = circleMaskImage.createMask();
        defaultAvatar = defaultAvatar.applyMask(circleMask);
        avatar.setIcon(defaultAvatar);

        avatar.addActionListener(e -> {
            if (Dialog.show("Camera or Gallery", "Would you like to use the camera or the gallery for the picture?", "Camera", "")) {
                String pic = Capture.capturePhoto();
                if (pic != null) {
                    try {
                        Image img = Image.createImage(pic).fill(circleMaskImage.getWidth(), circleMaskImage.getHeight());
                        avatar.setIcon(img.applyMask(circleMask));
                        Random randomGenerator = new Random();

                        int randomInt = randomGenerator.nextInt(19999999);
                        String devisnamee = String.valueOf(randomInt) + ".jpg";
                        imgnamee = devisnamee;
                        System.out.println(imgnamee);
                        System.out.println(FileSystemStorage.getInstance().getAppHomePath());
                        String imageFile = "file://C:/wamp/www/AllForKids/web/image_produit/" + devisnamee;
                        try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile);) {
                            ImageIO.getImageIO().save(img, os, ImageIO.FORMAT_PNG, 1);
                        } catch (IOException err) {
                            Log.e(err);
                        }
                    } catch (IOException err) {
                        ToastBar.showErrorMessage("An error occured while loading the image: " + err);
                        Log.e(err);
                    }
                }
            }
              
        });
        Button btn = new Button("Valider");

        f.add(Nomprod);

        f.add(type);
       
        f.add(prixprod);
         f.add(desc);
        f.add(prixpromo);

        f.add(avatar);
        f.add(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
               // if(ValidInputs()){
                
                produitjson d = new produitjson();
                d.setNomProduit(Nomprod.getText());
                d.setTypeProduit(type.getText());
                d.setPrixProduit(Double.parseDouble(prixprod.getText()));
                d.setDescription(desc.getText());
                
                //   d.setCat(cat.getSelectedItem());
                d.setPrixPromo(Double.parseDouble(prixpromo.getText()));
                 System.out.println(imgnamee);
                d.setImage(imgnamee);
                //d.setCreateurId(ServiceUser.usertemp);

                //                    Date date = formatter.parse(datef.getText());
//                    d.setDatefin(date);
                
                 ServiceProduit s = new ServiceProduit();         
                 s.ajoutTask(d);
                System.out.println("success");
               // }
            }


        });

        f.show();
    }
    
//    private boolean ValidInputs() {
//             
//         if ( (Nomprod.getText().isEmpty()) || (type.getText().isEmpty()) || (prixprod.getText().isEmpty()) || (desc.getText().isEmpty()) || (prixpromo.getText().isEmpty())  ) {
//           
//            Dialog.show("Erreur","Vous devez remplir tout les champs et choisir l'enseigne et le type", "OK", null);
//            return false;
//        }
//        else if (isNotInteger(prixprod.getText())) {
//             Dialog.show("Erreur","Numéro de téléphone non valide", "OK", null);
//             return false;
//        }
//        else if (isNotInteger(prixpromo.getText())) {
//             Dialog.show("Erreur","Numéro de téléphone non valide", "OK", null);
//             return false;
//        }
//             
//       return true;
//    }
    
    public static boolean isNotInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return true;
        }

        return false;
    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }


}

    

