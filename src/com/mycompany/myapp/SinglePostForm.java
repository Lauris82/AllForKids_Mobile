/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import controller.SinglePostController;
import entities.Commentaire;
import entities.Post;
import java.util.List;

/**
 *
 * @author amine
 */
public class SinglePostForm extends BaseForm {

    Form f;
    List l;
    SpanLabel lb;
    SpanLabel con;

    public SinglePostForm() {

    }

    public void SinglePostForm(com.codename1.ui.util.Resources resourceObjectInstance, int id) {
        initGuiBuilderComponents(resourceObjectInstance);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Posts", "Title")
                )
        );

        f = new Form("BLOG");
        SinglePostController controller = new SinglePostController();
        Post list = controller.getList2(id);

        Container c = new Container(BoxLayout.y());
//            Post e = new Post();
//            e.setTitre(list.getTitre());
//            e.setContenu(list.getContenu());
//            e.setImage(list.getImage());
        Label la = new Label();
        la.setText(list.getTitre());
        la.getAllStyles().setFgColor(0xf5bf0a);
        Image placeholder = Image.createImage(500, 170);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        URLImage imgUrl = URLImage.createToStorage(encImage, "http://localhost/AllForKids/web/image_post/" + list.getImage(), "http://localhost/AllForKids/web/image_post/" + list.getImage());
        ImageViewer img1 = new ImageViewer(imgUrl);

        TextArea textArea = new TextArea(list.getContenu());
        c.add(la);
        c.add(img1);
        c.add(textArea);

        for (Commentaire co : controller.getListcoms2(id)) {
            Container ccc = new Container();
            Label cont = new Label("Contenu: " + co.getContenu());
            Label d = new Label("Date: " + co.getDate());
            ccc.add(cont);
            ccc.add(d);
            c.add(ccc);
        }
        f.add(c);
        f.show();

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        // setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setLayout(new BorderLayout());
        installSidemenu(resourceObjectInstance);
        getToolbar().addCommandToRightBar("", null, e -> {
        });

    }
}
