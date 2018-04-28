/**
* @Project: AllForKids_Mobile
* @Classe: RegisterForm
* @Date: 20 avr. 2018
* @Time: 17:46:20
*
* @author Lauris
*/


package com.allforkids.Ettien.forms;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;


public class RegisterForm {

    private Resources theme;
    Form f;
    String emailU, usernameU, passwordU, roleU, nomU, prenomU, adresseU, imageU, fileName ; 
    Date dateN;
    long contactU;
    
    TextField email, username, password, rpassword, adresse, nom, prenom, contact;
    ComboBox roles;
    Picker birthday;
    Label filePathLabel;
    Button retourButton, suivantButton, chooseImageButton, registerButton, cancelButton;
    ImageViewer imageView = new ImageViewer();
    Image profilePic;
    
    public RegisterForm(){

        theme = UIManager.initFirstTheme("/theme");
        f = new Form("Register on AllForKids", BoxLayout.y());
        f.setUIID("RegisterForm");
        
        email = new TextField("", "Adresse mail", 20, TextField.EMAILADDR);
        Label emailIcon = new Label("", "TextField");
        emailIcon.getAllStyles().setFgColor(0xf5bf0a);
        FontImage.setMaterialIcon(emailIcon, FontImage.MATERIAL_EMAIL, 3);
        Container c_email = new Container(BoxLayout.x());
        c_email.addAll(emailIcon, email);
        f.add(c_email);
        
        username = new TextField("", "Username");
        Label usernameIcon = new Label("", "TextField");
        usernameIcon.getAllStyles().setFgColor(0xf5bf0a);
        FontImage.setMaterialIcon(usernameIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        Container c_username = new Container(BoxLayout.x());
        c_username.addAll(usernameIcon, username);
        f.add(c_username);
        
        password = new TextField("", "Password", 20, TextField.PASSWORD);
        Label passwordIcon = new Label("", "TextField");
        passwordIcon.getAllStyles().setFgColor(0xf5bf0a);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        Container c_password = new Container(BoxLayout.x());
        c_password.addAll(passwordIcon, password);
        f.add(c_password);
        
        rpassword = new TextField("", "Repeat Password", 20, TextField.PASSWORD);
        Label rpasswordIcon = new Label("", "TextField");
        rpasswordIcon.getAllStyles().setFgColor(0xf5bf0a);
        FontImage.setMaterialIcon(rpasswordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        Container c_rpassword = new Container(BoxLayout.x());
        c_rpassword.addAll(rpasswordIcon, rpassword);
        f.add(c_rpassword);
        
        roles = new ComboBox("PARENT", "RESPONSABLE_GARDERIE", "RESPONSABLE_CLUB");
        Label rolesIcon = new Label("", "TextField");
        rolesIcon.getAllStyles().setFgColor(0xf5bf0a);
        FontImage.setMaterialIcon(rolesIcon, FontImage.MATERIAL_ASSIGNMENT, 3);
        Container c_roles = new Container(BoxLayout.x());
        c_roles.addAll(rolesIcon, roles);
        f.add(c_roles);
        
        birthday = new Picker();
        birthday.setType(Display.PICKER_TYPE_DATE);
        Label birthdayIcon = new Label("", "TextField");
        birthdayIcon.getAllStyles().setFgColor(0xf5bf0a);
        FontImage.setMaterialIcon(birthdayIcon, FontImage.MATERIAL_DATE_RANGE, 3);
        Container c_birthday = new Container(BoxLayout.x());
        c_birthday.addAll(birthdayIcon, birthday);
        f.add(c_birthday);
        
        adresse = new TextField("", "Adresse");
        Label adresseIcon = new Label("", "TextField");
        adresseIcon.getAllStyles().setFgColor(0xf5bf0a);
        FontImage.setMaterialIcon(adresseIcon, FontImage.MATERIAL_HOME, 3);
        Container c_adresse = new Container(BoxLayout.x());
        c_adresse.addAll(adresseIcon, adresse);
        f.add(c_adresse);
        
        retourButton = new Button("Cancel");
        retourButton.setUIID("LoginButton");
        retourButton.getAllStyles().setFgColor(0xffffff);
        
        suivantButton =new Button("Next");
        suivantButton.setUIID("RegisterButton");
        suivantButton.getAllStyles().setFgColor(0xffffff);
        
        Container c_Button = new Container(BoxLayout.x());
        c_Button.addAll(retourButton, suivantButton);
        Container container = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        container.add(BorderLayout.CENTER, c_Button);
        f.add(container);
        
        
        retourButton.addActionListener((ActionListener) (ActionEvent evt) -> {
            LoginForm login = new LoginForm(theme);
            login.getForm().showBack();
        });
        
        suivantButton.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                boolean isClear = checkFirstPage(email, username, password, rpassword, adresse);
                System.out.println(isClear);
                
                if(isClear == true){
                    
                    ////////////////////get User Info first Page///////////////////////
                    String recupererRole = roles.getSelectedItem().toString();
                    roleU = "";
                    if(recupererRole.contains("PARENT")){
                        roleU =  "a:0:{}";
                    }
                    if(recupererRole.contains("RESPONSABLE_GARDERIE")){
                        roleU = "a:1:{i:0;s:25:\"ROLE_RESPONSABLE_GARDERIE\";}";
                    }
                    if(recupererRole.contains("RESPONSABLE_CLUB")){
                        roleU = "a:1:{i:0;s:21:\"ROLE_RESPONSABLE_CLUB\";}";
                    }
                    
                    emailU = email.getText();
                    usernameU = username.getText();
                    passwordU = rpassword.getText();
                    dateN = birthday.getDate();
                    System.out.println(dateN);
                    adresseU = adresse.getText();
                    
                    //////////////////////////////////////////////////////////
        
                    Form page2 = new Form("Final Step", BoxLayout.y());
                    page2.setUIID("RegisterForm");
                    
                    nom = new TextField("", "Last name");
                    Label nomIcon = new Label("", "TextField");
                    nomIcon.getAllStyles().setFgColor(0xf5bf0a);
                    FontImage.setMaterialIcon(nomIcon, FontImage.MATERIAL_PERSON_ADD, 3);
                    Container c_nom = new Container(BoxLayout.x());
                    c_nom.addAll(nomIcon, nom);
                    page2.add(c_nom);
                    
                    prenom = new TextField("", "First name");
                    Label prenomIcon = new Label("", "TextField");
                    prenomIcon.getAllStyles().setFgColor(0xf5bf0a);
                    FontImage.setMaterialIcon(prenomIcon, FontImage.MATERIAL_PERSON_ADD, 3);
                    Container c_prenom = new Container(BoxLayout.x());
                    c_prenom.addAll(prenomIcon, prenom);
                    page2.add(c_prenom);
                    
                    contact = new TextField("", "Contact", 20, TextField.NUMERIC);
                    Label contactIcon = new Label("", "TextField");
                    contactIcon.getAllStyles().setFgColor(0xf5bf0a);
                    FontImage.setMaterialIcon(contactIcon, FontImage.MATERIAL_PERSON_PIN, 3);
                    Container c_contact = new Container(BoxLayout.x());
                    c_contact.addAll(contactIcon, contact);
                    page2.add(c_contact);
                    
                    chooseImageButton =new Button("Choose Image");
                    chooseImageButton.setUIID("RegisterButton");
                    chooseImageButton.getAllStyles().setFgColor(0xffffff);
                    
                    
                    filePathLabel = new Label("", "TextField");
                    Container c_fileP = new Container(BoxLayout.x());
                    c_fileP.addAll(chooseImageButton, filePathLabel);
                    page2.add(c_fileP);
                    
                    Container c_image = new Container(BoxLayout.x());
                    c_image.add(imageView);
                    Container container = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                    container.add(BorderLayout.CENTER, c_image);
                    page2.add(container);
        
                    cancelButton = new Button("Cancel");
                    cancelButton.setUIID("LoginButton");
                    cancelButton.getAllStyles().setFgColor(0xffffff);

                    registerButton =new Button("Register");
                    registerButton.setUIID("RegisterButton");
                    registerButton.getAllStyles().setFgColor(0xffffff);

                    Container c_Button = new Container(BoxLayout.x());
                    c_Button.addAll(cancelButton, registerButton);
                    Container container_b = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                    container_b.add(BorderLayout.CENTER, c_Button);
                    page2.add(container_b);
                    
                    Label hide =new Label("  ");
                    page2.add(hide);
                    
                    page2.show();



                    chooseImageButton.addActionListener((ActionListener) (ActionEvent evt1) -> {
                        String i = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                        if(i != null){
                            try {
                                profilePic = Image.createImage(i);

                                imageView.setImage(profilePic);
                                filePathLabel.setText(i);
                                System.out.println(i);
                            } catch (IOException ex) {
                                System.err.println(ex.getMessage());
                            }
                        }
                    });
                    
                    cancelButton.addActionListener((ActionListener) (ActionEvent evt2) -> {
                        LoginForm login = new LoginForm(theme);
                        login.getForm().showBack();
                    });
                    
                    registerButton.addActionListener((ActionListener) (ActionEvent evt3) -> {
                        boolean isClear2 = checkSecondPage(nom, prenom, contact, filePathLabel);
                        if(isClear2 == true){
                            
                            if(profilePic != null){
                                ImageIO imgIO = ImageIO.getImageIO();
                                ByteArrayOutputStream out = new ByteArrayOutputStream();
                                try {
                                    imgIO.save(profilePic, out, ImageIO.FORMAT_JPEG, 1);
                                } catch (IOException ex) {
                                    System.err.println(ex.getMessage());
                                }
                                byte[] ba = out.toByteArray();
                                String Imagecode = Base64.encode(ba);
                                ConnectionRequest request = new ConnectionRequest(){
                                    protected void handleErrorResponsableCode(int code, String message){
                                        System.out.println("Code :"+code+" Msg :"+message);
                                    }
                                };
                                request.setPost(true);
                                request.setHttpMethod("POST");
                                request.addArgument("Image", Imagecode);
                                request.setUrl("http://localhost/AllForKids/web/image_user/text.txt");
                                NetworkManager.getInstance().addToQueueAndWait(request);
                                
                                Dialog.show("Info", "Image Upload with succes", "Ok", "Cancel");
                            }else{
                                Dialog.show("Erreur", "Unable to upload", "Ok", "Cancel");
                            }
                        }
                    });
                    
                }else{
                    Dialog.show("Erreur", "Veuillez remplir tous les champs", "Ok", "Cancel");
                }
            }
        });
        
        
        f.setScrollableY(true);
        f.setScrollVisible(false);
    }

    
    
    
    
    
    
    public void chooseImageAction(){
        String i = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
        if(i != null){
            try {
                
                Image img = Image.createImage(i);
                img.scaled(50, 50);
                
                imageView.setImage(img);
                filePathLabel.setText(i);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
    
    
    
    public boolean checkFirstPage(TextField em, TextField usern, TextField pass, TextField rpass, TextField adresse){
        return !em.getText().isEmpty() && !usern.getText().isEmpty() && !pass.getText().isEmpty() && !rpass.getText().isEmpty() && 
                !adresse.getText().isEmpty() && pass.getText().equals(rpass.getText());
    }
    
    public boolean checkSecondPage(TextField name, TextField fname, TextField cont, Label lab){
        return !name.getText().isEmpty() && !fname.getText().isEmpty() && !cont.getText().isEmpty() && cont.getText().length() == 8 && !lab.getText().isEmpty();
    }
    
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}



/**
*@Lau82 © 2018
*/
