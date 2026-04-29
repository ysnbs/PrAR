package gl2.example.PrAR.controller;

import gl2.example.PrAR.model.Location;
import gl2.example.PrAR.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/louer")
    public Location louer(@RequestBody Map<String, Object> body) {

            int iduser = (int) body.get("iduser");
            String matricule = (String) body.get("matricule");

            String debutStr = (String) body.get("debut");
            String finStr = (String) body.get("fin");

            Date debut = java.sql.Date.valueOf(debutStr);
            Date fin = java.sql.Date.valueOf(finStr);

            return locationService.louerVoiture(iduser, matricule, debut, fin);
    }
    @GetMapping("/user/{iduser}")
    public List<Location> historiqueUser(@PathVariable int iduser) {
        return locationService.getHistoriqueUser(iduser);
    }

    @GetMapping
    public List<Location> getAll() {
        return locationService.getAll();
    }
    @PutMapping("/{id}")
    public Location update(@RequestBody Map<String, Object> body,@PathVariable Long id) {

        int iduser = (int) body.get("iduser");
        String matricule = (String) body.get("matricule");

        String debutStr = (String) body.get("debut");
        String finStr = (String) body.get("fin");

        Date debut = java.sql.Date.valueOf(debutStr);
        Date fin = java.sql.Date.valueOf(finStr);

        return locationService.updateLocation(id,iduser, matricule, debut, fin);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        locationService.deleteLocation(id);
    }
}