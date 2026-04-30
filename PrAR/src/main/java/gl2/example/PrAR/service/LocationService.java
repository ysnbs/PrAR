package gl2.example.PrAR.service;

import gl2.example.PrAR.model.Location;
import gl2.example.PrAR.model.Voiture;
import gl2.example.PrAR.repository.LocationRepository;
import gl2.example.PrAR.repository.VoitureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final VoitureRepository voitureRepository;

    public LocationService(LocationRepository locationRepository,
                           VoitureRepository voitureRepository) {
        this.locationRepository = locationRepository;
        this.voitureRepository = voitureRepository;
    }


    @Transactional
    public Location louerVoiture(int iduser,
                                 String matricule,
                                 Date debut,
                                 Date fin) {

        if (fin.before(debut)) {
            return null;
        }

        Voiture voiture = voitureRepository.findById(matricule).orElse(null);

        if (voiture == null) {
            return null;
        }

        if (voiture.getEtat() != Voiture.Etat.DISPO) {
            return null;
        }
        List<Location> locations = locationRepository.findByMatricule(matricule);

        for (Location l : locations) {
            if (debut.before(l.getDateFin()) && fin.after(l.getDateDebut())) {
                return null;
            }
        }

        long diff = fin.getTime() - debut.getTime();
        int jours = (int) (diff / (1000 * 60 * 60 * 24))+1;

        double total = jours * voiture.getPrixParJour();

        voiture.setEtat(Voiture.Etat.LOUEE);
        voitureRepository.save(voiture);
        Location location = new Location();
        location.setUser(iduser);
        location.setVoiture(matricule);
        location.setDateDebut(debut);
        location.setDateFin(fin);
        location.setNombreJour(jours);
        location.setPrixTotal(total);

        return locationRepository.save(location);
    }

    public List<Location> getHistoriqueUser(int iduser) {
        return locationRepository.findByIduser(iduser);
    }

    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    public void deleteLocation(Long id) {
        Location location = locationRepository.findById(id).orElse(null);
        if (location == null) return;

        Voiture voiture = voitureRepository.findById(location.getVoiture()).orElse(null);

        if (voiture != null) {
            voiture.setEtat(Voiture.Etat.DISPO);
            voitureRepository.save(voiture);
        }

        locationRepository.deleteById(id);
    }
    public Location updateLocation(Long id, int iduser, String matricule, Date debut, Date fin) {

        Location location = locationRepository.findById(id).orElse(null);
        if (location == null) return null;

        if (!fin.after(debut)) return null;

        Voiture voiture = voitureRepository.findById(matricule).orElse(null);
        if (voiture == null) return null;

        List<Location> locations = locationRepository.findByMatricule(matricule);

        for (Location l : locations) {
            if (!l.getId().equals(id)) {
                if (debut.before(l.getDateFin()) && fin.after(l.getDateDebut())) {
                    return null;
                }
            }
        }

        long diff = fin.getTime() - debut.getTime();
        int jours = (int) (diff / (1000 * 60 * 60 * 24)) + 1;

        double total = jours * voiture.getPrixParJour();

        location.setUser(iduser);
        location.setVoiture(matricule);
        location.setDateDebut(debut);
        location.setDateFin(fin);
        location.setNombreJour(jours);
        location.setPrixTotal(total);

        return locationRepository.save(location);
    }
}