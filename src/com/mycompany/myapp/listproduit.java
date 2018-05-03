/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import controller.ServicePost;
import controller.ServiceProduit;
import entities.Post;
import entities.produitjson;
import entities.produitjson;
import java.util.ArrayList;

/**
 *
 * @author amine
 */
public class listproduit extends BaseForm {

    Form f;
    SpanLabel lb;
    SpanLabel lb1;
    SpanLabel lb2;
    SpanLabel lb3;
    SpanLabel lb4;

    SpanLabel con;
    private ImageViewer img;
    produitjson produit;
    Container c;

    public listproduit() {

    }

    public void listproduit(Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Produis", "Title")
                )
        );

        f = new Form("Produit", new BoxLayout(BoxLayout.Y_AXIS));
        ServiceProduit sp = new ServiceProduit();
        ArrayList<produitjson> list = sp.getList2();
        for (int i = 0; i < list.size(); i++) {
            Container c = new Container(BoxLayout.y());

            produitjson e = new produitjson();
            e.setIdProduit(list.get(i).getIdProduit());
            e.setNomProduit(list.get(i).getNomProduit());
            e.setTypeProduit(list.get(i).getTypeProduit());
            e.setPrixProduit(list.get(i).getPrixProduit());
            e.setDescription(list.get(i).getDescription());
            e.setPrixPromo(list.get(i).getPrixPromo());
            e.setImage(list.get(i).getImage());
            Label l = new Label();
            Label l2 = new Label();
            Label l3 = new Label();
            Label l4 = new Label();
            Label l5 = new Label();
            l.setText(list.get(i).getNomProduit());
            l.getAllStyles().setFgColor(0xf5bf0a);
            l2.setText("Prix :" + String.valueOf(list.get(i).getPrixProduit()) + "DT");
            l3.setText(list.get(i).getDescription());
            l4.setText("Type :" + list.get(i).getTypeProduit());
            l5.setText("Prix en Promotion :" + String.valueOf(list.get(i).getPrixProduit()) + "DT");
            Image placeholder = Image.createImage(500, 170);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            URLImage imgUrl = URLImage.createToStorage(encImage, "http://localhost/AllForKids/web/image_produit/" + e.getImage(), "http://localhost/AllForKids/web/image_produit/" + e.getImage());
            ImageViewer img1 = new ImageViewer(imgUrl);

            l.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Ajoutcommande sp = new Ajoutcommande(resourceObjectInstance, e);

                    //sp.Ajoutcommande(resourceObjectInstance, e.getIdProduit());
                }
            });

            Button b = new Button("Commander");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Ajoutcommande sp = new Ajoutcommande(resourceObjectInstance,e);
                    sp.getF().show();
                }
            });

            c.add(l);
            c.add(l2);
            c.add(l3);
            c.add(l4);

            c.add(img1);
            c.add(l5);
            c.add(b);

            f.add(c);
            f.show();
        }

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
        setLayout(new BorderLayout());
        installSidemenu(resourceObjectInstance);
        getToolbar().addCommandToRightBar("", null, e -> {
        });
    }
}
