package gl2.example.PrAR.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int iduser;

    private String  matricule;

    private Date dateDebut;
    private Date dateFin;

    private int nombreJour;
    private double prixTotal;

    public Location() {
    }
    public Location(Long id, int user, String voiture, Date dateDebut, Date dateFin, int nombreJour, double prixTotal) {
        this.id = id;
        this.iduser = user;
        this.matricule = voiture;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombreJour = nombreJour;
        this.prixTotal = prixTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUser() {
        return iduser;
    }

    public void setUser(int user) {
        this.iduser = user;
    }

    public String getVoiture() {
        return matricule;
    }

    public void setVoiture(String voiture) {
        this.matricule = voiture;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNombreJour() {
        return nombreJour;
    }

    public void setNombreJour(int nombreJour) {
        this.nombreJour = nombreJour;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }
}