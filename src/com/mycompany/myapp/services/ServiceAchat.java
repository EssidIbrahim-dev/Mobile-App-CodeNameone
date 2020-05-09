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
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.MyData;
import com.mycompany.myapp.entities.ProdAchat;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.entities.ProductAchat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author Ibrahim
 */
public class ServiceAchat {
     public ArrayList<Achat> achats;
     public ArrayList<Commande> commandes;
     public ArrayList<ProdAchat> prodAchats;
     public ArrayList<MyData> mydatas;
     public Achat achat;


      
    public static ServiceAchat instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceAchat() {
         req = new ConnectionRequest();
    }

    public static ServiceAchat getInstance() {
        if (instance == null) {
            instance = new ServiceAchat();
        }
        return instance;
    }
    public boolean addAchatProduct(int ida,int idp) {
        String url = Statics.BASE_URL + "/api/achat/product/" + ida + "/" + idp;
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
      public boolean deleteAchatProduct(int id,String username) {
        String url = Statics.BASE_URL + "/api/achat/prod/delete/" + id + "/" + username;
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
        public boolean editAchatProduct(int id,String username,int qte) {
        String url = Statics.BASE_URL + "/api/achat/prod/edit/" + id + "/" + username+"/"+qte;
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
      public boolean Confirmer(String username) {
        String url = Statics.BASE_URL + "/api/achat/confirm/" + username;
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
    public ArrayList<Achat> parseAchats(String jsonText){
        try {
            achats=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Achat t = new Achat();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId_achat((int)id);
                t.setClient_address(obj.get("clientAddress").toString());
                t.setClient_name(obj.get("clientName").toString());
                t.setClient_type(obj.get("clientType").toString());
                
          
              
                achats.add(t);
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return achats;
    }
    public ArrayList<MyData> parseMyClientAchat(String jsonText){
        try {
            mydatas=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.println(""+list);
            for(Map<String,Object> obj : list){
                MyData md=new MyData();
                
                md.setX(obj.get("x").toString());
                float nbr=Float.parseFloat(obj.get("nbr").toString());
                md.setY((int)nbr);
                
                
          
              
                mydatas.add(md);
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return mydatas;
    }
    public ArrayList<ProdAchat> parseProdAchats(String jsonText){
        try {
            prodAchats=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                ProdAchat prodAchat=new ProdAchat();
                float id = Float.parseFloat(obj.get("id").toString());
                float qte = Float.parseFloat(obj.get("qte").toString());
                float product = Float.parseFloat(obj.get("product").toString());
                float achat = Float.parseFloat(obj.get("achat").toString());
                prodAchat.setId((int)id);
                prodAchat.setQte((int)qte);
                prodAchat.setAchat((int)achat);
                prodAchat.setProduct((int)product);
                prodAchats.add(prodAchat);


                
                

                
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return prodAchats;
    }
    public ArrayList<Commande> parseCommandes(String jsonText){
        try {
            commandes=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Commande commande=new Commande();
                float id = Float.parseFloat(obj.get("id").toString());
                float qte = Float.parseFloat(obj.get("qte").toString());
                float product = Float.parseFloat(obj.get("product").toString());
                float achat = Float.parseFloat(obj.get("achat").toString());
                
                commande.setId((int)id);
                commande.setQte((int)qte);
                commande.setAchat((int)achat);
                commande.setProduct((int)product);
                commande.setDate(obj.get("date").toString());
                commande.setReference(obj.get("reference").toString());
                commandes.add(commande);


                
                

                
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return commandes;
    }
     public ArrayList<Achat> getAchat(String username){
         String url = Statics.BASE_URL+"/api/achat/"+username;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                achats = parseAchats(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return achats;
    }
      public ArrayList<Commande> getCommandes(String username){
         String url = Statics.BASE_URL+"/api/achat/commandes/"+username;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commandes = parseCommandes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commandes;
    }
      public ArrayList<ProdAchat> getProdAchat(String username){
         String url = Statics.BASE_URL+"/api/achat/prod/"+username;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                prodAchats = parseProdAchats(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return prodAchats;
    }
        public ArrayList<MyData> getStatsAchat(){
         String url = Statics.BASE_URL+"/api/achat/stats/Achat";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                mydatas = parseMyClientAchat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return mydatas;
    }
         public ArrayList<MyData> getStatsClient(){
         String url = Statics.BASE_URL+"/api/achat/stats/Client";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                mydatas = parseMyClientAchat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return mydatas;
    }
   
    
}
