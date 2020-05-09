/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Achat;
import com.mycompany.myapp.entities.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author Ibrahim
 */
public class ServiceProduct {
    public ArrayList<Product> products;

      
    public static ServiceProduct instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceProduct() {
         req = new ConnectionRequest();
    }

    public static ServiceProduct getInstance() {
        if (instance == null) {
            instance = new ServiceProduct();
        }
        return instance;
    }
    public ArrayList<Product> parseTasks(String jsonText){
        try {
            products=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Product t = new Product();
          
                float priceHT = Float.parseFloat(obj.get("priceHT").toString());
                float priceTTC = Float.parseFloat(obj.get("priceTTC").toString());
                float tva = Float.parseFloat(obj.get("tVA").toString());
                float weight = Float.parseFloat(obj.get("weight").toString());
                t.setProduct_name(obj.get("productName").toString());
                t.setProduct_type(obj.get("productType").toString());
                float id = Float.parseFloat(obj.get("id").toString());

                t.setId_product((int)id);
                t.setPhoto(obj.get("photo").toString());
                t.setReference(obj.get("reference").toString());
                t.setMarque(obj.get("marque").toString());
                t.setPriceTTC(priceTTC);
                t.setPriceHT(priceHT);
                t.setWeight(weight);
               t.setTVA(tva);
                products.add(t);
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return products;
    }
     public ArrayList<Product> getAllProducts(){
         String url = Statics.BASE_URL+"/api/products";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return products;
    }
      public ArrayList<Product> findProducts(String name){
         String url = Statics.BASE_URL+"/api/products/"+name;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return products;
    }
      public ArrayList<Product> getProducts(int id){
         String url = Statics.BASE_URL+"/api/product/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return products;
    }
    
}
