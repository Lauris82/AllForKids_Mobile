///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.myapp;
// 
//import com.codename1.charts.ChartComponent;
//import com.codename1.charts.models.CategorySeries;
//import com.codename1.charts.renderers.DefaultRenderer;
//import com.codename1.charts.renderers.SimpleSeriesRenderer;
//import com.codename1.charts.util.ColorUtil;
//import com.codename1.charts.views.PieChart;
//import com.codename1.io.CharArrayReader;
//import com.codename1.io.ConnectionRequest;
//import com.codename1.io.JSONParser;
//import com.codename1.io.NetworkEvent;
//import com.codename1.io.NetworkManager;
//import com.codename1.ui.Form;
//import com.codename1.ui.List;
//import com.codename1.ui.events.ActionListener;
//import com.codename1.ui.layouts.BorderLayout;
//import entities.produitjson;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Map;
///**
// *
// * @author amine
// */
//public class graphe {
//  
//
//
//
//      /**
//     * Creates a renderer for the specified colors.
//     */
//    private ArrayList<produitjson> p =new ArrayList<>();
//    private float sum;
//   
//    public ArrayList<produitjson> getListprod(String json) {
//
//        ArrayList<produitjson> listp = new ArrayList<>();
//
//        try {
//          
//            JSONParser j = new JSONParser();
////json  bch y mapihom
//            Map<String, Object> prod = j.parseJSON(new CharArrayReader(json.toCharArray()));
//           
//            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) prod.get("root");
//
//            for (Map<String, Object> obj : list) {
//                produitjson p=new produitjson();
//
//                // System.out.println(obj.get("id"));
//                
//                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
//               // e.setEtat(obj.get("state").toString());
//                p.setNomProduit(obj.get("NomProduit").toString());
//                p.setTypeProduit(obj.get("TypeProduit").toString());
//                float quant = Float.parseFloat(obj.get("quantity").toString());
//                
//              //  p.setQuantité((int) quant);
//               //  b.setNombout(obj.get("nom_bout").toString());
////                b.setAdresse(obj.get("adresse").toString());
////                b.setNumtel(obj.get("numtel").toString());
////                b.setImage(obj.get("image").toString());
//                //e.setNom(obj.get("name").toString());
//                //System.out.println(e);
//                listp.add(p);
//
//            }
//
//        } catch (IOException ex) {
//        }
//        
//        return listp;
//
//    }
//      
//    
//    public ArrayList<produitjson> getproduits(){  
//     ConnectionRequest con = new ConnectionRequest();
//        con.setUrl("http://localhost/VERSION%20FINAAL/PIDEV2/web/app_dev.php/piee");  
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//              
//                p = getListprod(new String(con.getResponseData()));
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//        return p;
//      }
//      public int getsum(){  
//     ConnectionRequest con = new ConnectionRequest();
//        con.setUrl("http://localhost/VERSION%20FINAAL/PIDEV2/web/app_dev.php/piesum");  
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                try {
//                    
//                JSONParser j = new JSONParser();
//                String json = new String(con.getResponseData());
//         
//                Map<String, Object> prod = j.parseJSON(new CharArrayReader(json.toCharArray()));
//                sum = Float.parseFloat(prod.get("1").toString());
//                } catch (Exception e) {
//                }
//                
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//          
//        return (int)sum;
//      }
//      //form de pie chart parametre
//    private DefaultRenderer buildCategoryRenderer(int[] colors) {
//        DefaultRenderer renderer = new DefaultRenderer();
//        renderer.setLabelsTextSize(20);
//        renderer.setLabelsColor(ColorUtil.BLACK);
//        renderer.setLegendTextSize(20);
//    //    renderer.setLegendTextFont(renderer.getTextTypeface());
//        renderer.setMargins(new int[]{20, 30, 15, 0});
//        for (int color : colors) {
//            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
//            r.setColor(color);
//            renderer.addSeriesRenderer(r);
//        }
//        return renderer;
//    }
//
//    /**
//     * Builds a category series using the provided values.
//     *
//     * @param titles the series titles
//     * @param values the values
//     * @return the category series
//     */
//    //pour recuperer les valeur
//    protected CategorySeries buildCategoryDataset(String title, ArrayList<produit> values) {
//        CategorySeries series = new CategorySeries(title);
//        int k = 0;
////        for (produit value : values) {
////            try {
////            double v = value.getQuantité();System.out.println("v value : "+v);
////            int s = getsum();System.out.println("s value : "+s);
////            double quant = (v/s)*100;
//// 
////                System.out.println(quant);
////            series.add("" + value.getTitre(),(int) quant);
////            } catch (ArithmeticException e) {
////            }
////            
////        }
////
////        return series;
////    }
//////creation de pie chart
////    public Form createPieChartForm() {
////
////        // Generate the values
////       ArrayList<produit> values = new ArrayList<produit>();
////       values = getproduits();
////              
////        // Set up the renderer
////        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
////        DefaultRenderer renderer = buildCategoryRenderer(colors);
////        renderer.setZoomButtonsVisible(true);
////        renderer.setZoomEnabled(true);
////        renderer.setChartTitleTextSize(20);
////        renderer.setDisplayValues(true);
////        renderer.setShowLabels(true);
////        
////        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
////        r.setGradientEnabled(true);
////        r.setGradientStart(0, ColorUtil.BLUE);
////        r.setGradientStop(0, ColorUtil.GREEN);
////        r.setHighlighted(true);
////
////        // Create the chart ... pass the values and renderer to the chart object.
////        PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);
////
////        // Wrap the chart in a Component so we can add it to a form
////        ChartComponent c = new ChartComponent(chart);
////
////        // Create a form and show it.
////        Form f = new Form("pie chart");
////        f.setLayout(new BorderLayout());
////        f.addComponent(BorderLayout.CENTER, c);
////        return f;
////    }
////}
//
//}}}
