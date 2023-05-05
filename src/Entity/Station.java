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
public class Station {
        private int id;
        private String long_alt;

    public Station(int id, String long_alt) {
        this.id = id;
        this.long_alt = long_alt;
    }

    public Station(String long_alt) {
        this.long_alt = long_alt;
    }

    public Station() {
    }

    public int getId() {
        return id;
    }

    public String getLong_alt() {
        return long_alt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLong_alt(String long_alt) {
        this.long_alt = long_alt;
    }
        
}
