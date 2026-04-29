package gl2.example.PrAR.repository;
import java.util.List;
import gl2.example.PrAR.model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VoitureRepository extends JpaRepository<Voiture, String>{
    List<Voiture> findByEtat(Voiture.Etat etat);
}
