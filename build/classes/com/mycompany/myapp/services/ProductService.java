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
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.entities.Product1;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ProductService {

    public ArrayList<Product1> tasks;

    public static ProductService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ProductService() {
        req = new ConnectionRequest();
    }

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    public boolean addTask(Product1 p) {
        String url = Statics.BASE_URL + "/product/new?productName=" + p.getProductName()
                + "&productType=" + p.getProductType() + "&marque=" + p.getMarque() + "&priceHT=" + p.getPriceHT()
                + "&priceTTC=" + p.getPriceTTC() + "&tVA=" + p.getTva() + "&reference=" + p.getReference()
                + "&weight=" + p.getWeight() + "&photo=" + p.getPhoto();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean Tweeter(String message) {
        String url = Statics.BASE_URL2 +"?message="+ message;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean editProduct(Product1 p) {
        String url = Statics.BASE_URL + "/product/edit?id=" + p.getId() + "&productName=" + p.getProductName()
                + "&productType=" + p.getProductType() + "&marque=" + p.getMarque() + "&priceHT=" + p.getPriceHT()
                + "&priceTTC=" + p.getPriceTTC() + "&tVA=" + p.getTva() + "&reference=" + p.getReference()
                + "&weight=" + p.getWeight() + "&photo=" + p.getPhoto();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean deleteProduct(int id) {
        String url = Statics.BASE_URL + "/product/delete?id=" + id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Product1> parseTasks(String jsonText) {
        try {
            tasks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Product1 t = new Product1();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                String productName = (String) obj.get("productName").toString();
                String productType = (String) obj.get("productType").toString();
                String reference = (String) obj.get("reference").toString();
                String marque = (String) obj.get("marque").toString();
                float priceht = Float.parseFloat(obj.get("priceHT").toString());
                float pricettc = Float.parseFloat(obj.get("priceTTC").toString());
                float tva = Float.parseFloat(obj.get("tVA").toString());
                float weight = Float.parseFloat(obj.get("weight").toString());
                String photo = (String) obj.get("photo");
                t.setProductName(productName);
                t.setProductType(productType);
                t.setReference(reference);
                t.setMarque(marque);
                t.setPriceHT(priceht);
                t.setPriceTTC(pricettc);
                t.setTva(tva);
                t.setWeight(weight);
                t.setPhoto(photo);
                tasks.add(t);
            }






        } catch (IOException ex) {

        }
        return tasks;
    }

    public ArrayList<Product1> getAllTasks() {
        String url = Statics.BASE_URL + "/products/all";
        req.setUrl(url);
        req.setPost(false);
        Product1 t = new Product1();
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }

}
