/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.raoudha.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.allforkids.raoudha.entities.Garderie;

/**
 *
 * @author user
 */
public class ModifierGarderie
{
    Form f; 
 TextField txt1,txt2,txt3,txt4,txt5;
    Button Modifier;
    public ModifierGarderie(Garderie g) 
   
    {
        f =new Form("Update Garderie",BoxLayout.y());
        TextField txt1=new TextField("",g.getNom());
    TextField txt2=new TextField("",g.getEmplacement());
    TextField txt3=new TextField("",g.getDescription());
    TextField txt4=new TextField("",g.getCapacite());
    TextField txt5=new TextField("",g.getNum_tel());
    f.add(txt1);
    f.add(txt2);
    f.add(txt3);
    f.add(txt4);
    f.add(txt5);
     Modifier =new Button("Modifier");
    f.add(Modifier);
    Modifier.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt)
            { ConnectionRequest con = new ConnectionRequest();
        //    String Url = "http://localhost/AllForKids/web/app_dev.php/modifGarderieMobile?nom=" 
        //            + txt1.getText() + "&emplacement=" + txt2.getText() + "&description=" + txt3.getText()+ "&capacite=" + txt4.getText()+ "&num_tel="+txt5.getText()+"");
          //  con.setUrl(Url);

  

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
                
                
                
            }
        });
        
     f.show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
    
    
    
}
