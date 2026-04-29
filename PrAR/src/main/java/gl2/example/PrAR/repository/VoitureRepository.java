package gl2.example.PrAR.repository;
import gl2.example.PrAR.model.User;
import gl2.example.PrAR.model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VoitureRepository extends JpaRepository<Voiture, String>{
}
