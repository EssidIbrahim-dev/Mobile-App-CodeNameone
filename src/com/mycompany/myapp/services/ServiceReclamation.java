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
import com.mycompany.myapp.entities.Fournisseur;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ibrahim
 */
public class ServiceReclamation {
     public ArrayList<Reclamation> reclamations;

      
    public static ServiceReclamation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReclamation() {
         req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }
     public ArrayList<Reclamation> parseReclamations(String jsonText){
        try {
            reclamations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Reclamation r=new Reclamation();
          
                
                float id = Float.parseFloat(obj.get("id").toString());
               float product = Float.parseFloat(obj.get("product").toString());

                float idUser = Float.parseFloat(obj.get("idUser").toString());

                r.setId((int)id);
                r.setProduct((int)product);
                r.setContenu(obj.get("contenu").toString());
                r.setIdUser((int)idUser);
                r.setEtat(obj.get("etat").toString());
                r.setDate(obj.get("date").toString());
                r.setPriority(obj.get("priority").toString());
                r.setUsername(obj.get("username").toString());

                
                
                
                
                reclamations.add(r);

              
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return reclamations;
    }
       public ArrayList<Reclamation> getReclamation(){
         String url = Statics.BASE_URL+"/api/rec";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reclamations = parseReclamations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }
       public boolean addRec(String username,int p,String niveau,String contenu) {
        String url = Statics.BASE_URL + "/api/rec/create/" + username + "/"+ p+"/"+ niveau+"/"+contenu;
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
        public boolean deleteRec(int id) {
        String url = Statics.BASE_URL + "/api/rec/delete/" +id;
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
        public boolean editRec(int id,String niveau,String contenu) {
        String url = Statics.BASE_URL + "/api/rec/edit/" +id+"/"+niveau+"/"+contenu;
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
    
}
