/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author MSI
 */
public class Role {
    private int id_role;
    private String nom;  
    
    public Role(){
        
    }
     public Role(int id_role, String nom){
        this.id_role=id_role;
        this.nom=nom;
 
    }
    
    public Role(String nom){
        this.nom=nom;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Role{" + "id_role=" + id_role + ", nom=" + nom + '}';
    }
    
    
    
}
