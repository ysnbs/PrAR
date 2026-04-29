package gl2.example.PrAR.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    private String ncin;

    private String nom;
    private String prenom;
    private String role;
    private String numeroTelephone;
    private String password;

    public User() {
    }

    public User(String ncin, String nom, String prenom, String role, String numeroTelephone, String password) {
        this.ncin = ncin;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.numeroTelephone = numeroTelephone;
        this.password = password;
    }


    public String getNcin() {
        return ncin;
    }

    public void setNcin(String ncin) {
        this.ncin = ncin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}