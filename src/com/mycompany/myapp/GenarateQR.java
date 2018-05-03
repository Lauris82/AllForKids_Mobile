/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author amine
 */
public class GenarateQR {

    Form f;

    public Container GenarateQR(int i) {

        f = new Form("Participation", new BorderLayout());

        System.out.println(i);

        Image imgUrl;
        Image placeholder = Image.createImage(200, 200);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        imgUrl = URLImage.createToStorage(encImage, "http://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + i, "http://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + i);
        ImageViewer img1 = new ImageViewer(imgUrl);

        f.refreshTheme();

        Container c2 = new Container(BoxLayout.y());

        c2.addComponent(new Label(""));
        c2.addComponent(new Label("        C'est le QR code  que  le Produit  "));
        c2.addComponent(new Label("        va le présenter le  moment que "));
        c2.addComponent(new Label("        vous passer une Commande "));
        c2.addComponent(new Label(""));
        c2.addComponent(new Label(""));

        c2.add(img1);

        return c2;
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
