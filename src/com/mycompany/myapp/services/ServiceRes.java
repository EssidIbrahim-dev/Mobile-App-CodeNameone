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
import com.mycompany.myapp.entities.Reserver;
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
public class ServiceRes {
     public static ServiceRes instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ArrayList<Reserver> reservations;

    private ServiceRes() {
         req = new ConnectionRequest();
    }

    public static ServiceRes getInstance() {
        if (instance == null) {
            instance = new ServiceRes();
        }
        return instance;
    }
    public ArrayList<Reserver> parseTasks(String jsonText){
        try {
            reservations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                Map<String,Object> list1=(Map<String,Object>)obj.get("staff");
                Map<String,Object> list2=(Map<String,Object>)obj.get("matriel");
                System.out.println(""+list1.get("firstname"));
                Reserver f=new Reserver();
                int id =(int) Float.parseFloat(obj.get("id").toString());
                f.setId(id);       
                f.setStaff(list1.get("firstname").toString());
                f.setMat(list2.get("type").toString());
                    System.out.println("htgrfedhtgfred"+obj.get("reservedAt"));
                    f.setDateres(obj.get("reservedAt").toString());                
                    f.setDateret(obj.get("recuperedAt").toString());
                
                
                
                reservations.add(f);

              
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return reservations;
    }
     public ArrayList<Reserver> getReservs(){
         String url = Statics.BASE_URL+"/api/reservation";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservations = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservations;
    }
      public ArrayList<Reserver> getRes(int id){
         String url = Statics.BASE_URL+"/api/reservation/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservations= parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservations;
    }
         public boolean deleteRes(int id) {
        String url = Statics.BASE_URL + "/api/reservation/delete/"+id;
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
