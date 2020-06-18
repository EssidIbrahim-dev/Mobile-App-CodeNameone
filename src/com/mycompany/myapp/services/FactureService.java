/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Facture;

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
public class FactureService {
   public ArrayList<Facture> factures;
   public Facture facture;
   
     public static FactureService instance;
    public boolean resultOK;
    private ConnectionRequest req;
     public FactureService() {
        req = new ConnectionRequest();
    }

    public static FactureService getInstance() {
        if (instance == null) {
            instance = new FactureService();
        }
        return instance;
    }
    
    /*public boolean GetAllfacture() {
        String url = "http://localhost/pidev/web/app_dev.php/api/factures" ;
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
    }*/
     public boolean addFacture(Facture e) {
        String url = Statics.BASE_URL + "/api/factures/newfact?reference=" +e.getReference()+ "&type_facture=" + e.getType_facture()
                +"&echeance=" + e.getEcheance()+  "&dateFact=" + e.getDateFact()+ "&clientName=" + e.getClientName()+ "&clientType=" + e.getClientType()
                + "&statutFacture="+ e.getStatutFacture();
        req.setUrl(url);
        System.out.println("Succes connexion");
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
     public boolean deletefacture(int id) {
        String url = Statics.BASE_URL + "/api/factures/deleted/" + id  ;
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
     
      public boolean editFacture(int id,String ref,int id_achat,String clname,String cltype,String typefact,String statfact,float totht,float totttc,String ech) {
        String url = Statics.BASE_URL + "/api/factures/edited/"+ id+ "&reference=" + ref
                + "&achat_id=" + id_achat+ "&clientName=" + clname+  "&clientType=" + cltype+"&type_facture=" + typefact+ 
                 "&statutFacture="+ statfact+ "&totalHT=" + totht+ "&totalTTC=" + totttc
                + "&echeance="+ ech;
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
    public ArrayList<Facture> parseFacture(String jsonText){
        try {
            factures=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Facture t = new Facture();
                System.out.print(1);
                t.setId((int)(Float.parseFloat(obj.get("id").toString())));
                t.setReference(obj.get("reference").toString());
                 float id2 = Float.parseFloat(obj.get("achat").toString());
                t.setAchat_id((int)id2);
                System.out.print(2);
                t.setClientName(obj.get("clientName").toString());
                t.setClientType(obj.get("clientType").toString());
                t.setType_facture(obj.get("typeFacture").toString());
                t.setStatut_facture(obj.get("statutFacture").toString());
                 float totht = Float.parseFloat(obj.get("totalHT").toString());
                t.setTotalHT((float)totht);
                 float totttc = Float.parseFloat(obj.get("totalTTC").toString());
                t.setTotalTTC((float)totttc);
                 t.setEcheance(obj.get("echeance").toString());
               System.out.print(3);
                 
               // t.setDateFact(obj.get("dateFact").toString());
                
          
              
                factures.add(t);
                 System.out.print(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return factures;
    } 
    
      public ArrayList<Facture> GetAllfactures() {
        String url = Statics.BASE_URL + "/api/factures" ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                factures = parseFacture(new String(req.getResponseData()));
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return factures;
    }
}
