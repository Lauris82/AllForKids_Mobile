/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yasmine.services;

import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;

/**
 *
 * @author DELL
 */
public class validation {
    
    
    
    public static boolean isTextFieldNotEmpty(TextField tf)
    {
        String espace=" ";
       boolean b =false;
       if(tf.getText().length() != 0 || ! tf.getText().isEmpty())
//     if(! tf.getText().isEmpty() || tf.getText().length() != 0 || tf.getText()!=espace )
         

           b=true;
       return b;
    
    }
    
    
     public static boolean isTextFieldNotEmpty(TextField tf , Label lb , String errorMessage )
    {
       boolean b =true;
       
       String msg=null;
       if(! isTextFieldNotEmpty(tf))
       {
           msg=errorMessage;
           b=true;
       }
       lb.setText(msg);
       return b;
    
    }
     
      public static boolean isTextFAreaNotEmpty(TextArea tx)
    {
       boolean b1=false;
       if(tx.getText().length() != 0 || ! tx.getText().isEmpty())
           b1=true;
       return b1;
    
    }
      
      public static boolean isTextFAreaNotEmpty(TextArea tx , Label lb , String errorMessage )
    {
       boolean b1 =true;
       
       String msg1=null;
       if(! isTextFAreaNotEmpty(tx))
       {
           msg1=errorMessage;
           b1=true;
       }
       lb.setText(msg1);
       return b1;
    
    }
//      
//      public static boolean isNumber(TextField tff)
//      {
//         return (tff.getText().matches("-?([1-9][0-9]*)?"));
//      
//      }
//     
//      public static boolean isEmail (TextField text)
//      {
//      return (text.getText().matches("^(.+)@(.+)$"));
//      
//      
//      }
    
    
    
    
    
    
}
