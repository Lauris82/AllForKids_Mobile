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
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
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
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.io.IOException;


public class RegisterForm {

    private Resources theme;
    Form f;
    String fileName ;
    
    TextField email, username, password, rpassword, adresse, nom, prenom, contact;
    Label filePathLabel;
    Button retourButton, suivantButton, chooseImageButton;
    
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
        
        ComboBox roles = new ComboBox("Parent", "Responsable Garderie", "Responsable Club");
        Label rolesIcon = new Label("", "TextField");
        rolesIcon.getAllStyles().setFgColor(0xf5bf0a);
        FontImage.setMaterialIcon(rolesIcon, FontImage.MATERIAL_ASSIGNMENT, 3);
        Container c_roles = new Container(BoxLayout.x());
        c_roles.addAll(rolesIcon, roles);
        f.add(c_roles);
        
        Picker birthday = new Picker();
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
                    Container c_image = new Container(BoxLayout.x());
                    c_image.addAll(chooseImageButton, filePathLabel);
                    page2.add(c_image);
                    
//                    
//                    chooseImageButton.addActionListener((ActionListener) (ActionEvent evt1) -> {
//                        chooseImageAction();
//                    });
                    
                    
                    page2.show();
                }else{
                    Dialog.show("Erreur", "Veuillez remplir tous les champs", "Ok", "Cancel");
                }
            }
        });
        
        
        
    }

    
    
    
    
    
    
    public void chooseImageAction(){
        String i = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
        if(i != null){
            try {
                Image img = Image.createImage(i);
                filePathLabel.setText(i);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
    
    
    
    public boolean checkFirstPage(TextField em, TextField usern, TextField pass, TextField rpass, TextField adresse){
        return !em.getText().isEmpty() && !usern.getText().isEmpty() && !pass.getText().isEmpty() && !rpass.getText().isEmpty() && 
                !adresse.getText().isEmpty();
    }
    
    public boolean checkSecondPage(TextField name, TextField fname, TextField cont){
        return !name.getText().isEmpty() && !fname.getText().isEmpty() && !cont.getText().isEmpty();
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
