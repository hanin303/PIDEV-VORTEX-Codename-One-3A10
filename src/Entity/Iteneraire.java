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
public class Iteneraire {
    private int id;
    private String pts_depart;
    private String pts_arrive;

    public Iteneraire() {
    }

    public Iteneraire(int id, String pts_depart, String pts_arrive) {
        this.id = id;
        this.pts_depart = pts_depart;
        this.pts_arrive = pts_arrive;
    }

    public Iteneraire(String pts_depart, String pts_arrive) {
        this.pts_depart = pts_depart;
        this.pts_arrive = pts_arrive;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Iteneraire{" + "id=" + id + ", pts_depart=" + pts_depart + ", pts_arrive=" + pts_arrive + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPts_depart() {
        return pts_depart;
    }

    public void setPts_depart(String pts_depart) {
        this.pts_depart = pts_depart;
    }

    public String getPts_arrive() {
        return pts_arrive;
    }

    public void setPts_arrive(String pts_arrive) {
        this.pts_arrive = pts_arrive;
    }
    
}
