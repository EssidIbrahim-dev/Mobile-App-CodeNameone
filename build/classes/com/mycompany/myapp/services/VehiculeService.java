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
import com.mycompany.myapp.entities.Vehicule;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FIRAS
 */
public class VehiculeService {
    public static VehiculeService instance;
    private ConnectionRequest req;
    public ArrayList<Vehicule>vehicules;
    public boolean resultOk;

    
    public VehiculeService(){
        req=new ConnectionRequest();
    }
    
    public static VehiculeService  getInstance(){
        if(instance==null)
            instance=new VehiculeService();
        return instance;
    }
    public boolean addVehicule(Vehicule v)
    {
        //maticule=a&weight=1&etat=a&marque=a&description=a
        //http://localhost/pidev_moi/pidev/web/app_dev.php/vehicule/new?matricule=a&weight=b&etat=c&marque=d&description=d
        String url="http://localhost/pidev/web/app_dev.php/vehicule/new?matricule="+v.getMatricule()+"&weight="+v.getWeight()+"&etat="+v.getEtat()+"&marque="+v.getMarque()+"&description="+v.getDescription();
       //String url="Http://127.0.0.1:3306/pidev"; 
         //req=new ConnectionRequest(url);
         req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                resultOk=req.getResponseCode()==200;
                req.removeResponseListener(this);
            }
            
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
    
    public ArrayList<Vehicule>parseVehicules(String jsonText)
    {
       
        try {
             vehicules=new ArrayList<>();
        JSONParser j=new JSONParser();
        
           Map<String,Object>   VehiculeListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List<Map<String,Object>> list =(List<Map<String,Object>>) VehiculeListJson.get("root");
           for(Map<String,Object> obj : list)
           {
               Vehicule v=new Vehicule();
               float id = Float.parseFloat(obj.get("id").toString());
               v.setId_vehicule((int)id);
               v.setMatricule(obj.get("matricule").toString());
               v.setWeight(Float.parseFloat(obj.get("weight").toString()));
               v.setEtat(obj.get("etat").toString());
               v.setMarque(obj.get("marque").toString());
               v.setDescription(obj.get("description").toString());
               vehicules.add(v);
               
           }
          
           

        } catch (IOException ex) {
        }
        
        
        return vehicules;
    }
    
    public ArrayList<Vehicule>getAllVehicule()
    {
       // String url="http://127.0.0.1:8000/vehicule/all";
        String url="http://localhost/pidev/web/app_dev.php/vehicule/allVehicule";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                vehicules=parseVehicules(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return vehicules;
    }
}
