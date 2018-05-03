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
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import controller.ServicePost;
import entities.Post;
import java.util.ArrayList;

/**
 *
 * @author amine
 */
public class listblog extends BaseForm {

    Form f;
    SpanLabel lb;
    SpanLabel con;
    private ImageViewer img;
    Post post;
    Container c;

    public listblog() {


    }

    public void listblog(Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Posts", "Title")
                )
        );

        
       
        
        
        f = new Form("BLOG");
        ServicePost sp = new ServicePost();
        ArrayList<Post> list = sp.getList2();
        for (int i = 0; i < list.size(); i++) {
              Container c = new Container(BoxLayout.y());
            Post e = new Post();
            e.setId_post(list.get(i).getId_post());
            e.setTitre(list.get(i).getTitre());
            e.setContenu(list.get(i).getContenu());
            e.setImage(list.get(i).getImage());
            Label l = new Label();
            l.setText(list.get(i).getTitre());
            l.getAllStyles().setFgColor(0xf5bf0a);
            Image placeholder = Image.createImage(500, 170);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            URLImage imgUrl = URLImage.createToStorage(encImage, "http://localhost/AllForKids/web/image_post/" + e.getImage(), "http://localhost/AllForKids/web/image_post/" + e.getImage());
            ImageViewer img1 = new ImageViewer(imgUrl);
            l.addPointerPressedListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent evt) {
                 SinglePostForm sp=new SinglePostForm();
                 sp.SinglePostForm(resourceObjectInstance, e.getId_post());
                  }
              });
            c.add(l);
            c.add(img1);
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
        // setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setLayout(new BorderLayout());
        installSidemenu(resourceObjectInstance);
        getToolbar().addCommandToRightBar("", null, e -> {
        });

    }

}
