/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import entity.paiement;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import com.codename1.ui.events.ActionListener;
/**
 *
 * @author bazinfo
 */
public class PaiementService {
     public ArrayList<paiement> paiements;
   public paiement paiement;
   
     public static PaiementService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
     public PaiementService() {
        req = new ConnectionRequest();
    }

    public static PaiementService getInstance() {
        if (instance == null) {
            instance = new PaiementService();
        }
        return instance;
    }
    

     
      public boolean addpaiement(paiement e) {
        String url = Statics.BASE_URL + "/api/paiement/newpaiement?reference=" + e.getReference()
                + "&clientName=" + e.getClientName()+ "&paiementType=" + e.getPaiementType()+ "&rib=" + e.getRib()
                + "&numCheque=" + e.getNumCheque();
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
    public boolean editPaiement(int id ,String nameclient,String reference,String rib ,String num,String type) {
        String url = Statics.BASE_URL + "/api/paiement/editP/"+id + "?reference=" + reference
                + "&num_cheque=" +num;
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
   public boolean deletePaiement(int id) {
        String url = Statics.BASE_URL + "/api/paiement/deleteP/"+ id;
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
   
    public ArrayList<paiement> parsepaiement(String jsonText){
        try {
           paiements=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                paiement t = new paiement();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setReference(obj.get("reference").toString());
                
                t.setClientName(obj.get("clientName").toString());
                t.setPaiementType(obj.get("paiementType").toString());
                t.setRib(obj.get("rib").toString());
              
                t.setNumCheque(obj.get("numCheque").toString());
                
          
              
                paiements.add(t);
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return paiements;
    } 
       public ArrayList<paiement> GetAllpaiement() {
        String url = Statics.BASE_URL + "/api/paiement" ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                paiements = parsepaiement(new String(req.getResponseData()));
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return paiements;
    }
}
