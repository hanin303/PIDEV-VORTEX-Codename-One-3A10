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
public class Reservation {
    private int id_reservation ;
    private Date date_reservation ;
    private String heure_depart ;
    private String heure_arrive ;
    private String status;
    private String type_ticket; 
    private String id_client;
    private String id_moy;
    private String id_it;

    public Reservation() {
    }

    public Reservation(int id_reservation, Date date_reservation, String heure_depart, String heure_arrive, String status, String type_ticket, String id_client, String id_moy, String id_it) {
        this.id_reservation = id_reservation;
        this.date_reservation = date_reservation;
        this.heure_depart = heure_depart;
        this.heure_arrive = heure_arrive;
        this.status = status;
        this.type_ticket = type_ticket;
        this.id_client = id_client;
        this.id_moy = id_moy;
        this.id_it = id_it;
    }

    public Reservation(Date date_reservation, String heure_depart, String heure_arrive, String status, String type_ticket, String id_client, String id_moy, String id_it) {
        this.date_reservation = date_reservation;
        this.heure_depart = heure_depart;
        this.heure_arrive = heure_arrive;
        this.status = status;
        this.type_ticket = type_ticket;
        this.id_client = id_client;
        this.id_moy = id_moy;
        this.id_it = id_it;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public String getHeure_depart() {
        return heure_depart;
    }

    public String getHeure_arrive() {
        return heure_arrive;
    }

    public String getStatus() {
        return status;
    }

    public String getType_ticket() {
        return type_ticket;
    }

    public String getId_client() {
        return id_client;
    }

    public String getId_moy() {
        return id_moy;
    }

    public String getId_it() {
        return id_it;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    public void setHeure_depart(String heure_depart) {
        this.heure_depart = heure_depart;
    }

    public void setHeure_arrive(String heure_arrive) {
        this.heure_arrive = heure_arrive;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType_ticket(String type_ticket) {
        this.type_ticket = type_ticket;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public void setId_moy(String id_moy) {
        this.id_moy = id_moy;
    }

    public void setId_it(String id_it) {
        this.id_it = id_it;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_reservation=" + id_reservation + ", date_reservation=" + date_reservation + ", heure_depart=" + heure_depart + ", heure_arrive=" + heure_arrive + ", status=" + status + ", type_ticket=" + type_ticket + ", id_client=" + id_client + ", id_moy=" + id_moy + ", id_it=" + id_it + '}';
    }

}
