/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;



/**
 *
 * @author aymen
 */
public class paiement {
    
   private int id;
   private String reference ;
   private String clientName;
   private String paiementType;
   private String rib;
   private String numCheque;

    public paiement() {
    }

    public paiement(int id, String reference, String clientName, String paiementType, String rib, String numCheque) {
        this.id = id;
        this.reference = reference;
        this.clientName = clientName;
        this.paiementType = paiementType;
        this.rib = rib;
        this.numCheque = numCheque;
    }

    public paiement(String reference, String clientName, String paiementType, String rib, String numCheque) {
        this.reference = reference;
        this.clientName = clientName;
        this.paiementType = paiementType;
        this.rib = rib;
        this.numCheque = numCheque;
    }

    @Override
    public String toString() {
        return "paiement{" + "id=" + id + ", reference=" + reference + ", clientName=" + clientName + ", paiementType=" + paiementType + ", rib=" + rib + ", numCheque=" + numCheque + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setPaiementType(String paiementType) {
        this.paiementType = paiementType;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public void setNumCheque(String numCheque) {
        this.numCheque = numCheque;
    }

    public int getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public String getClientName() {
        return clientName;
    }

    public String getPaiementType() {
        return paiementType;
    }

    public String getRib() {
        return rib;
    }

    public String getNumCheque() {
        return numCheque;
    }

  
  
   
   
}
