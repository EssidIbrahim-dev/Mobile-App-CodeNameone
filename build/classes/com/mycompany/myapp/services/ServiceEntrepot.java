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
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceEntrepot {

    public ArrayList<Entrepot> entrepots;
    
    public static ServiceEntrepot instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEntrepot() {
         req = new ConnectionRequest();
    }

    public static ServiceEntrepot getInstance() {
        if (instance == null) {
            instance = new ServiceEntrepot();
        }
        return instance;
    }

    public boolean addEntrepot(Entrepot e) {
        
        String url = Statics.BASE_URL + "/entrepot/new?address=" +e.getAddress()+"&nbrRangs="+e.getNbrRangs()+"&phone="
                +e.getPhone()+"&phoneBis="+e.getPhone_bis()+"&latitude="+e.getLatitude()+"&longitude="+e.getLongitude();
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
    
     public boolean editEntrepot(Entrepot e) {
        String url = Statics.BASE_URL + "/entrepot/edit?id=" + e.getId() + "&address=" + e.getAddress()
                + "&nbrRangs=" + e.getNbrRangs() + "&phone=" + e.getPhone() + "&phoneBis=" + e.getPhone_bis()
                + "&latitude=" + e.getLatitude() + "&longitude=" + e.getLongitude() ;
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

    public boolean deleteEntrepot(int id) {
        String url = Statics.BASE_URL + "/entrepot/delete?id=" + id;
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


         public ArrayList<Entrepot> parseEntrepots(String jsonText){
        try {
            entrepots=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> entrepotsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)entrepotsListJson.get("root");
            for(Map<String,Object> obj : list){
                Entrepot t = new Entrepot();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                String address = (String) obj.get("address").toString();
                String latitude = (String) obj.get("latitude").toString();
                String longitude = (String) obj.get("longitude").toString();
                String phone = (String) obj.get("phone").toString();
                String phonebis = (String)obj.get("phoneBis").toString() ;
                float nbr =Float.parseFloat(obj.get("nbrRangs").toString()) ;
               
                t.setAddress(address);
                t.setLatitude(latitude);
                t.setLongitude(longitude);
                t.setPhone(phone);
                t.setPhone_bis(phonebis);
                t.setNbrRangs((int)nbr);
                entrepots.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return entrepots;
    }
    
      public ArrayList<Entrepot> getAllEntrepot(){
        String url = Statics.BASE_URL+"/entrepot/all";
        req.setUrl(url);
        req.setPost(false);
        Entrepot t = new Entrepot();
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                entrepots = parseEntrepots(new String(req.getResponseData()));
                req.removeResponseListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return entrepots;
    }
}
