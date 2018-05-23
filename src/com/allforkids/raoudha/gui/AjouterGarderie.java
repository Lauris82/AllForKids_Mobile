/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.raoudha.gui;

import com.allforkids.raoudha.myapp.MyApplication;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author user
 */
public class AjouterGarderie 
{
    Form f;
    TextField txt1,txt2,txt3,txt4,txt5;
    Button ajouter;
    public AjouterGarderie()
    {
    f =new Form("Ajout Garderie",BoxLayout.y());
    TextField txt1=new TextField("","Nom");
    TextField txt2=new TextField("","Emplacement");
    TextField txt3=new TextField("","Description");
    TextField txt4=new TextField("","Capacite");
    TextField txt5=new TextField("","Numéro Téléphone");
    f.add(txt1);
    f.add(txt2);
    f.add(txt3);
    f.add(txt4);
    f.add(txt5);
    ajouter =new Button("Ajouter");
    f.add(ajouter);
    ajouter.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
              ConnectionRequest req = new ConnectionRequest();
   req.setUrl("http://localhost/ScriptMobile/ajoutGard.php?nom=" + txt1.getText() + "&emplacement=" + txt2.getText() + "&description=" + txt3.getText()+ "&capacite=" + txt4.getText()+ "&num_tel="+txt5.getText() + "");          
            req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(req);       
            
            
        }
    })
    ;
    
//    f.getToolbar().addCommandToRightBar("back", null, (ev)->{Home h=new Home();
//          h.getF().show();
//          });
    f.show();
 
 f.getToolbar().addCommandToLeftBar("Back", null, e -> {
     MyApplication hm = new MyApplication();
     hm.getF().show();
 });
    
    
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
}
