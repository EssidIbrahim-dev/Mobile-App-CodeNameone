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
public class ServiceStatStaff {
    
     public ArrayList<MyData> mydatas;
     public Achat achat;


      
    public static ServiceStatStaff instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceStatStaff() {
         req = new ConnectionRequest();
    }

    public static ServiceStatStaff getInstance() {
        if (instance == null) {
            instance = new ServiceStatStaff();
        }
        return instance;
    }
   
    public ArrayList<MyData> parseMyStaffstat(String jsonText){
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
        public ArrayList<MyData> getStatStaff(){
         String url = Statics.BASE_URL+"/api/stats/staff1";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                mydatas = parseMyStaffstat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return mydatas;
    }
}
