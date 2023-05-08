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
public class Ligne {
        
    
      private int id_ligne;
   private String nom_ligne;
   private  String type_ligne;

    public Ligne() {
    }

    public Ligne(int id_ligne, String nom_ligne, String type_ligne) {
        this.id_ligne = id_ligne;
        this.nom_ligne = nom_ligne;
        this.type_ligne = type_ligne;
    }

    public Ligne(String nom_ligne, String type_ligne) {
        this.nom_ligne = nom_ligne;
        this.type_ligne = type_ligne;
    }

    public Ligne(String text) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   

    public int getId_ligne() {
        return id_ligne;
    }

    public void setId_ligne(int id_ligne) {
        this.id_ligne = id_ligne;
    }

    public String getNom_ligne() {
        return nom_ligne;
    }

    public void setNom_ligne(String nom_ligne) {
        this.nom_ligne = nom_ligne;
    }

    public String getType_ligne() {
        return type_ligne;
    }

    public void setType_ligne(String type_ligne) {
        this.type_ligne = type_ligne;
    }

    @Override
    public String toString() {
        return "Ligne{" + "id_ligne=" + id_ligne + ", nom_ligne=" + nom_ligne + ", type_ligne=" + type_ligne + '}';
    }
    
    
}
