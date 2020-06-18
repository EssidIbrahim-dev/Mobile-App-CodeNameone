/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author aymen
 */
public class Facture {
     private int id;	
    private String reference;	
    private int  achat_id;
    private String clientName;
    private String clientType;
    private String type_facture;
    private String statutFacture;
    private float totalHT;
    private float totalTTC;
    private String echeance;
   
    private String dateFact;

    public Facture() {
    }

    public Facture(String reference, String clientName, String clientType, String type_facture, String statutFacture, String echeance, String dateFact) {
        this.reference = reference;
        this.clientName = clientName;
        this.clientType = clientType;
        this.type_facture = type_facture;
        this.statutFacture = statutFacture;
        this.echeance = echeance;
        this.dateFact = dateFact;
    }

    public Facture(String reference, String clientName, String clientType, String type_facture, String statutFacture, float totalHT, float totalTTC, String echeance, String dateFact) {
        this.reference = reference;
        this.clientName = clientName;
        this.clientType = clientType;
        this.type_facture = type_facture;
        this.statutFacture = statutFacture;
        this.totalHT = totalHT;
        this.totalTTC = totalTTC;
        this.echeance = echeance;
        this.dateFact = dateFact;
    }

    public Facture(String reference, int achat_id, String clientName, String clientType, String type_facture, String statutFacture, float totalHT, float totalTTC, String echeance, String dateFact) {
        this.reference = reference;
        this.achat_id = achat_id;
        this.clientName = clientName;
        this.clientType = clientType;
        this.type_facture = type_facture;
        this.statutFacture = statutFacture;
        this.totalHT = totalHT;
        this.totalTTC = totalTTC;
        this.echeance = echeance;
        this.dateFact = dateFact;
    }

    public Facture(int id, String reference, int achat_id, String clientName, String clientType, String type_facture, String statutFacture, float totalHT, float totalTTC, String echeance, String dateFact) {
        this.id = id;
        this.reference = reference;
        this.achat_id = achat_id;
        this.clientName = clientName;
        this.clientType = clientType;
        this.type_facture = type_facture;
        this.statutFacture = statutFacture;
        this.totalHT = totalHT;
        this.totalTTC = totalTTC;
        this.echeance = echeance;
        this.dateFact = dateFact;
    }

    @Override
    public String toString() {
        return "Facture{" + "id=" + id + ", reference=" + reference + ", achat_id=" + achat_id + ", clientName=" + clientName + ", clientType=" + clientType + ", type_facture=" + type_facture + ", statutFacture=" + statutFacture + ", totalHT=" + totalHT + ", totalTTC=" + totalTTC + ", echeance=" + echeance + ", dateFact=" + dateFact + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setAchat_id(int achat_id) {
        this.achat_id = achat_id;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public void setType_facture(String type_facture) {
        this.type_facture = type_facture;
    }

    public void setStatut_facture(String statutFacture) {
        this.statutFacture = statutFacture;
    }

    public void setTotalHT(float totalHT) {
        this.totalHT = totalHT;
    }

    public void setTotalTTC(float totalTTC) {
        this.totalTTC = totalTTC;
    }

    public void setEcheance(String echeance) {
        this.echeance = echeance;
    }

    public void setDateFact(String dateFact) {
        this.dateFact = dateFact;
    }

    public int getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public int getAchat_id() {
        return achat_id;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientType() {
        return clientType;
    }

    public String getType_facture() {
        return type_facture;
    }

    public String getStatutFacture() {
        return statutFacture;
    }

    public float getTotalHT() {
        return totalHT;
    }

    public float getTotalTTC() {
        return totalTTC;
    }

    public String getEcheance() {
        return echeance;
    }

    public String getDateFact() {
        return dateFact;
    }
    
    
    

}