package gl2.example.PrAR.service;

import gl2.example.PrAR.model.Voiture;
import gl2.example.PrAR.repository.VoitureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoitureService {

    private final VoitureRepository voitureRepository;

    public VoitureService(VoitureRepository voitureRepository) {
        this.voitureRepository = voitureRepository;
    }

    // USER
    public List<Voiture> getVoituresDisponibles() {
        return voitureRepository.findByEtat(Voiture.Etat.DISPO);
    }

    public Voiture getVoitureById(String matricule) {
        Optional<Voiture> v = voitureRepository.findById(matricule);
        return v.orElse(null);
    }

    // ADMIN
    public List<Voiture> getAll() {
        return voitureRepository.findAll();
    }

    public Voiture add(Voiture v) {
        if (voitureRepository.existsById(v.getMatricule())) {
            return null;
        }
        if (v.getEtat() == null) {
            v.setEtat(Voiture.Etat.DISPO);
        }
        return voitureRepository.save(v);
    }

    public Voiture update(String matricule, Voiture v) {
        Optional<Voiture> existing = voitureRepository.findById(matricule);

        if (existing.isPresent()) {
            Voiture voiture = existing.get();

            voiture.setType(v.getType());
            voiture.setPrixParJour(v.getPrixParJour());
            voiture.setDescription(v.getDescription());
            voiture.setEtat(v.getEtat());

            return voitureRepository.save(voiture);
        }

        return null;
    }

    public void delete(String matricule) {
        voitureRepository.deleteById(matricule);
    }
}