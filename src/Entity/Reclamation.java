/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


import java.util.Date;

/**
 *
 * @author hanin
 */
public class Reclamation {
    
    
    private int id_reclamation;
    private int idUser;
    private String message_rec;
    private String  objet;
    private String statut;
    private String date_rec;

    public Reclamation(String message_rec, String objet) {
        this.message_rec = message_rec;
        this.objet = objet;
    }

    public Reclamation(int id_reclamation, String message_rec, String objet) {
        this.id_reclamation = id_reclamation;
        this.message_rec = message_rec;
        this.objet = objet;
    }

    public Reclamation(int id_reclamation, String message_rec, String objet, String statut, String date_rec) {
        this.id_reclamation = id_reclamation;
        this.message_rec = message_rec;
        this.objet = objet;
        this.statut = statut;
        this.date_rec = date_rec;
    }

    public Reclamation(int id_reclamation, String message_rec, String objet, String statut) {
        this.id_reclamation = id_reclamation;
        this.message_rec = message_rec;
        this.objet = objet;
        this.statut = statut;
    }

    public Reclamation(String message_rec, String objet, String statut) {
        this.message_rec = message_rec;
        this.objet = objet;
        this.statut = statut;
    }

    public Reclamation(String message_rec, String objet, String statut, String date_rec) {
        this.message_rec = message_rec;
        this.objet = objet;
        this.statut = statut;
        this.date_rec = date_rec;
    }

    /*public Reclamation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    public Reclamation() {
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getMessage_rec() {
        return message_rec;
    }

    public void setMessage_rec(String message_rec) {
        this.message_rec = message_rec;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDate_rec() {
        return date_rec;
    }

    public void setDate_rec(String date_rec) {
        this.date_rec = date_rec;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_reclamation=" + id_reclamation + ", idUser=" + idUser + ", message_rec=" + message_rec + ", objet=" + objet + ", statut=" + statut + ", date_rec=" + date_rec + '}';
    }
    
    
    
}
