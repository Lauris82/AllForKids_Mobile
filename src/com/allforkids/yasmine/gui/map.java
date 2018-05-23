/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.yasmine.gui;

import com.allforkids.Ettien.forms.HomeForm;
import com.allforkids.yasmine.entities.ClubEntity;
import com.allforkids.yasmine.services.ClubService;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.maps.MapListener;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class map {

    private static final String MAPS_KEY = "AIzaSyCvHb9kp9zK7OdVQZEmzhHwyIiT7xwHL74";
    private Form current;
    private Resources theme;
    ArrayList<ClubEntity> list = new ArrayList<>();

    public void lister(String adresse, String nom) throws IOException {

        ClubService rs = new ClubService();
        list = rs.getList2();
        theme = UIManager.initFirstTheme("/theme");
        Form hi = new Form("Maps");
        hi.setUIID("LoginForm");
        Toolbar tb = hi.getToolbar();
        tb.setUIID("ToolBarFont");
        hi.getToolbar().addCommandToLeftBar("Back", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                HomeForm hm = new HomeForm();
                hm.getF().show();
            }
        });
        Label lbl = new Label();
        hi.setLayout(new BorderLayout());
        final MapContainer cnt = new MapContainer();
        Coord lastLocation = new Coord(36.899527163883356, 10.18983787368006);
        cnt.zoom(lastLocation, 6);
        cnt.addMapListener(new MapListener() {
            public void mapPositionUpdated(Component source, int zoom, Coord center) {
                lbl.setText("0 lon: " + cnt.getCoordAtPosition(0, 0).getLongitude() + " w lon " + cnt.getCoordAtPosition(Display.getInstance().getDisplayWidth(), 0).getLongitude());
            }
        });

        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);

        cnt.addMarker(
                                    EncodedImage.createFromImage(theme.getImage("maps-pin.png"), false),
//                EncodedImage.create("maps-pin.png"),
                new Coord(getCoords(adresse).getLatitude(), getCoords(adresse).getLongitude()),
                "Hi marker",
                "Optional long description",
                evt -> {
                    ToastBar.showMessage(nom + "\n" + adresse + "", FontImage.MATERIAL_PLACE);

                }
        );

        Container root = LayeredLayout.encloseIn(
                BorderLayout.center(cnt),
                BorderLayout.south(
                        FlowLayout.encloseBottom()
                )
        );

        hi.add(BorderLayout.CENTER, root);

        hi.show();
    }

    public static Coord getCoords(String address) {
        Coord ret = null;
        try {
            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/geocode/json", false);
            request.addArgument("key", MAPS_KEY);
            request.addArgument("address", address);

            NetworkManager.getInstance().addToQueueAndWait(request);
            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            if (response.get("results") != null) {
                ArrayList results = (ArrayList) response.get("results");
                if (results.size() > 0) {
                    LinkedHashMap location = (LinkedHashMap) ((LinkedHashMap) ((LinkedHashMap) results.get(0)).get("geometry")).get("location");
                    ret = new Coord((double) location.get("lat"), (double) location.get("lng"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

}
