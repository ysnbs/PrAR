package gl2.example.PrAR.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
public class Voiture {

    @Id
    private String matricule;

    private String type;
    private double prixParJour;

    @Enumerated(EnumType.STRING)
    private Etat etat;

    private String description;

    public enum Etat {
        DISPO, LOUEE, MAINTENANCE
    }

    public Voiture() {
    }


    public Voiture(String matricule, String type, double prixParJour, Etat etat, String description) {
        this.matricule = matricule;
        this.type = type;
        this.prixParJour = prixParJour;
        this.etat = etat;
        this.description = description;
    }


    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrixParJour() {
        return prixParJour;
    }

    public void setPrixParJour(double prixParJour) {
        this.prixParJour = prixParJour;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}