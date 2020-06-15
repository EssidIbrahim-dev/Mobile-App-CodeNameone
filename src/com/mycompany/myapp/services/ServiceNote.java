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
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ibrahim
 */
public class ServiceNote {
        public static ServiceNote instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ArrayList<Note> notes;

    private ServiceNote() {
         req = new ConnectionRequest();
    }

    public static ServiceNote getInstance() {
        if (instance == null) {
            instance = new ServiceNote();
        }
        return instance;
    }
     public ArrayList<Note> parseTasks(String jsonText){
        try {
            notes=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Note f=new Note();
          
                
                float product = Float.parseFloat(obj.get("product").toString());
                float valeur = Float.parseFloat(obj.get("valeur").toString());

                f.setClient(obj.get("client").toString());
                f.setProduct((int)product);
                f.setValeur((int)valeur);
               
                
                
                notes.add(f);

              
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return notes;
    }
      public ArrayList<Note> getNote(String username,int p){
         String url = Statics.BASE_URL+"/api/note/show/"+username+"/"+p;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                notes = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return notes;
    }
       public boolean addNote(String username,int valeur,int product) {
        String url = Statics.BASE_URL + "/api/note/add/" + username + "/" + valeur+"/"+product;
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
