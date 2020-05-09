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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.MatrielMag;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.myapp.utils.Statics;
import java.util.Date;

/**
 *
 * @author Ibrahim
 */
public class ServiceMatr {
     public static ServiceMatr instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ArrayList<MatrielMag> matriels;

    private ServiceMatr() {
         req = new ConnectionRequest();
    }

    public static ServiceMatr getInstance() {
        if (instance == null) {
            instance = new ServiceMatr();
        }
        return instance;
    }
    public ArrayList<MatrielMag> parseTasks(String jsonText){
        try {
            matriels=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                MatrielMag f=new MatrielMag();
          
                
                int id =(int) Float.parseFloat(obj.get("IDM").toString());
                f.setType(obj.get("type").toString());
                f.setReference(obj.get("reference").toString());
                f.setPhoto(obj.get("photo").toString());
               
                f.setId((int)id);
                
                matriels.add(f);

              
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return matriels;
    }
     public ArrayList<MatrielMag> getMatriels(){
         String url = Statics.BASE_URL+"/api/matmag";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                matriels = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return matriels;
    }
      public ArrayList<MatrielMag> getmat(int id){
         String url = Statics.BASE_URL+"/api/matmag/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                matriels = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return matriels;
    }
      public ArrayList<MatrielMag> getMatrielByType(String type){
         String url = Statics.BASE_URL+"/api/matmag/find/"+type;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                matriels = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return matriels;
    }
    
}
