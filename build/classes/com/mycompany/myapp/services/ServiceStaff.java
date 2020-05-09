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
import com.mycompany.myapp.entities.Staff;
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
public class ServiceStaff {
     public static ServiceStaff instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ArrayList<Staff> staffs;

    private ServiceStaff() {
         req = new ConnectionRequest();
    }

    public static ServiceStaff getInstance() {
        if (instance == null) {
            instance = new ServiceStaff();
        }
        return instance;
    }
    public ArrayList<Staff> parseTasks(String jsonText){
        try {
            staffs=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Staff f=new Staff();
          
                
                int id =(int) Float.parseFloat(obj.get("id").toString());
                f.setNom(obj.get("firstname").toString());
                f.setPrenom(obj.get("lastname").toString());
                f.setSalary(Float.parseFloat(obj.get("salary").toString()));
                f.setPost(obj.get("post").toString());
                f.setRib(obj.get("rib").toString());
                f.setId((int)id);
                f.setReference(obj.get("reference").toString());
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                try {
                    f.setDate(formatter.parse(obj.get("datedeb").toString()));
                } catch (ParseException ex) {
                }
                f.setNumber((int)Float.parseFloat(obj.get("phoneNumber").toString()));
                f.setNb_c((int)Float.parseFloat(obj.get("nbConj").toString()));
                f.setNb_heur((int)Float.parseFloat(obj.get("nbHeur").toString()));
                f.setStatut((int)Float.parseFloat(obj.get("statut").toString()));
                f.setPrime(Float.parseFloat(obj.get("prime").toString()));
                
                staffs.add(f);

              
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return staffs;
    }
     public ArrayList<Staff> getStaffs(){
         String url = Statics.BASE_URL+"/api/staff";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                staffs = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return staffs;
    }
      public ArrayList<Staff> getStaff(int id){
         String url = Statics.BASE_URL+"/api/staff/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                staffs = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return staffs;
    }
      public ArrayList<Staff> getStaffByName(String name){
         String url = Statics.BASE_URL+"/api/staff/find/"+name;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                staffs = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return staffs;
    }
    
}
