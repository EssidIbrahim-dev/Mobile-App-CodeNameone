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
import com.mycompany.myapp.entities.Fournisseur;
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
public class ServiceFournisseur {
     public static ServiceFournisseur instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ArrayList<Fournisseur> fournisseurs;

    private ServiceFournisseur() {
         req = new ConnectionRequest();
    }

    public static ServiceFournisseur getInstance() {
        if (instance == null) {
            instance = new ServiceFournisseur();
        }
        return instance;
    }
    public ArrayList<Fournisseur> parseTasks(String jsonText){
        try {
            fournisseurs=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Fournisseur f=new Fournisseur();
          
                
                float id = Float.parseFloat(obj.get("id").toString());
                f.setFirstname(obj.get("firstname").toString());
                f.setLastname(obj.get("lastname").toString());
                f.setAddress(obj.get("address").toString());
                f.setEmail(obj.get("email").toString());
                f.setPhoneNumber(obj.get("phoneNumber").toString());
                f.setId((int)id);
                
                fournisseurs.add(f);

              
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return fournisseurs;
    }
     public ArrayList<Fournisseur> getFournisseurs(){
         String url = Statics.BASE_URL+"/api/four";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                fournisseurs = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return fournisseurs;
    }
      public ArrayList<Fournisseur> getFournisseur(int id){
         String url = Statics.BASE_URL+"/api/four/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                fournisseurs = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return fournisseurs;
    }
      public ArrayList<Fournisseur> getFournisseurByName(String name){
         String url = Statics.BASE_URL+"/api/four/find/"+name;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                fournisseurs = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return fournisseurs;
    }
    
}
