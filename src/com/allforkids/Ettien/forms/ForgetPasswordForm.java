/**
* @Project: AllForKids_Mobile
* @Classe: ForgetPasswordForm
* @Date: 26 avr. 2018
* @Time: 10:15:23
*
* @author Lauris
*/


package com.allforkids.Ettien.forms;

import com.allforkids.Ettien.entities.User;
import com.allforkids.Ettien.services.SMS_Service;
import com.allforkids.Ettien.services.UserService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Random;


public final class ForgetPasswordForm {
    public static User user;
    public UserService uss;
    private String verifCode;
    private long userNum;
    
    Form f;
    private final Resources theme;
    TextField email, code, password, rpassword;
    Button cancelButton, saveButton, sendCode_Button ;
    Label number, text;

    public ForgetPasswordForm() {
        theme = UIManager.initFirstTheme("/theme");
        
        f = new Form("Recuperer Mot de Passe", BoxLayout.y());
        f.setUIID("LoginForm");
        Toolbar tb = f.getToolbar();
        tb.setUIID("ToolBarFont");
        
        email = new TextField("", "Adresse mail", 20, TextField.EMAILADDR);
        Button verifEmail_Button = new Button("Verifier email");
        Container c_email = new Container(BoxLayout.x());
        c_email.addAll(verifEmail_Button ,email);
        f.add(c_email);
        
        
        verifEmail_Button.addActionListener((ActionListener) (ActionEvent evt) -> {
            
            uss = new UserService();
            String res = uss.getInfoUserByEmail(email.getText());
            System.out.println(res);
            
            if(res.contains(email.getText())){
                try {
                    user = uss.getUser(res);
                    userNum = user.getContact();
                    System.out.println(userNum);
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
                setUpFirstStep();
                f.refreshTheme();
            }else{
                Dialog.show("Erreur", "Adresse Mail incorrecte", "Ok", "Cancel");
            }
        });
    }
    

    private void envoyerCodeAction(){
        sendCode_Button.addActionListener((ActionListener) (ActionEvent evt) -> {
            SMS_Service sms = new SMS_Service();
            verifCode = makeVerifCode();
            String verifMessage = "\nMessage de AllForKids\nCode de vérification: " + verifCode + "\nMerci.";
            System.out.println(userNum);
            System.out.println(verifCode);
            sms.createSMS(userNum, verifMessage);
            
        });
            setUpSecondStep();
    }
    
    
    private void updatePass(){
        saveButton.addActionListener((ActionListener) (ActionEvent evt) -> {
            
            if(!password.getText().isEmpty() && !rpassword.getText().isEmpty() && 
                    password.getText().equals(rpassword.getText())){
                
                String result = uss.updatePassword(email.getText(), rpassword.getText());
                
                if(result.contains("success")){
                    
                    Dialog.show("Success", "Votre Mot de pass a été mis à jour", "Ok", "Cancel");
                    LoginForm log = new LoginForm(theme);
                    log.getForm().show();
                }
                
            }else{
                Dialog.show("Erreur", "Mot de pass non mis à jour\nVerifiez les données saisies", "Ok", "Cancel");
            }
        });
    }
    
    
    public void setUpFirstStep(){
        String num = Long.toString(userNum);
        number = new Label("+216****"+ num.substring(4, 8));
        sendCode_Button = new Button("Send code");
        Container c_number = new Container(BoxLayout.x());
        c_number.addAll(sendCode_Button ,number);
        f.add(c_number);
        
        envoyerCodeAction();
    }

    
    public void setUpSecondStep(){
        
        text = new Label("Le code a été envoyé a votre numero");
        f.add(text);
        
        Label hide =new Label("  ");
        f.add(hide);
        
        code = new TextField("Enter the code");
        
        Container c_code = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        c_code.add(BorderLayout.CENTER, code);
        f.add(c_code);
        f.refreshTheme();
        
        code.addDataChangedListener((int type, int index) -> {
            
            System.out.println("ok");
            if(code.getText().equals(verifCode)){
                System.out.println("Debug");
                setUpThirdStep();
                code.setEditable(false);
                System.out.println("Debug ++");
            }
            
        }); 
    }
    
    
    public void setUpThirdStep(){
        System.out.println("Debug -----");
        
        password = new TextField("", "Enter new Password", 20, TextField.PASSWORD);
        Container c_password = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        c_password.add(BorderLayout.CENTER, password);
        f.add(c_password);
        
        rpassword = new TextField("", "Repeat Password", 20, TextField.PASSWORD);
        Container c_rpassword = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        c_rpassword.add(BorderLayout.CENTER, rpassword);
        f.add(c_rpassword);
        
        Label hide1 =new Label("  ");
        f.add(hide1);
        
        cancelButton = new Button("Cancel");
        cancelButton.setUIID("LoginButton");
        cancelButton.getAllStyles().setFgColor(0xffffff);

        saveButton =new Button("Save");
        saveButton.setUIID("RegisterButton");
        saveButton.getAllStyles().setFgColor(0xffffff);

        Container c_Button = new Container(BoxLayout.x());
        c_Button.addAll(cancelButton, saveButton);
        Container container_b = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        container_b.add(BorderLayout.CENTER, c_Button);
        f.add(container_b);
        
        f.refreshTheme();
        updatePass();
    }
    
    
    
    

    public String makeVerifCode() {
        String randomString = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();
        verifCode = "";
        for (int i = 0; i < 8; i++) {
            int n = rand.nextInt(randomString.length());
            verifCode += randomString.charAt(n);
        }
        return verifCode;
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
