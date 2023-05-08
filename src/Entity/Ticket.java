/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author hanin
 */
public class Ticket {
    private int id_t ;
    private String nom_ticket;
    private String prix;
    private String status;
    private String id_reservation ;

    public Ticket() {
    }

    public Ticket(String nom_ticket, String prix, String status) {
        this.nom_ticket = nom_ticket;
        this.prix = prix;
        this.status = status;
    }

   

    public Ticket(int id_t, String nom_ticket, String prix, String status, String id_reservation) {
        this.id_t = id_t;
        this.nom_ticket = nom_ticket;
        this.prix = prix;
        this.status = status;
        this.id_reservation = id_reservation;
    }

    public Ticket(String nom_ticket, String prix, String status, String id_reservation) {
        this.nom_ticket = nom_ticket;
        this.prix = prix;
        this.status = status;
        this.id_reservation = id_reservation;
    }

    public int getId_t() {
        return id_t;
    }

    public String getNom_ticket() {
        return nom_ticket;
    }

    public String getPrix() {
        return prix;
    }

    public String getStatus() {
        return status;
    }

    public String getId_reservation() {
        return id_reservation;
    }

    public void setId_t(int id_t) {
        this.id_t = id_t;
    }

    public void setNom_ticket(String nom_ticket) {
        this.nom_ticket = nom_ticket;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId_reservation(String id_reservation) {
        this.id_reservation = id_reservation;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id_t=" + id_t + ", nom_ticket=" + nom_ticket + ", prix=" + prix + ", status=" + status + ", id_reservation=" + id_reservation + '}';
    }
    
 
}
