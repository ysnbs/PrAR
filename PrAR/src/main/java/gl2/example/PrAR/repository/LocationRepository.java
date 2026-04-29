package gl2.example.PrAR.repository;

import gl2.example.PrAR.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByUser(int iduser);
    List<Location> findBymatricule(String matricule);

}