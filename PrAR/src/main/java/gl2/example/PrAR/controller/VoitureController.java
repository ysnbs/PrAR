package gl2.example.PrAR.controller;

import gl2.example.PrAR.model.Voiture;
import gl2.example.PrAR.service.VoitureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/voitures")
public class VoitureController {

    @Autowired
    private VoitureService voitureService;

    @GetMapping
    public List<Voiture> getAll(@RequestParam(required = false) Boolean dispo) {
        if (dispo != null && dispo) {
            return voitureService.getVoituresDisponibles();
        }
        return voitureService.getAll();
    }

    @GetMapping("/{matricule}")
    public Voiture getById(@PathVariable String matricule) {
        return voitureService.getVoitureById(matricule);
    }

    @PostMapping
    public Voiture add(@RequestBody Voiture v) {
        return voitureService.add(v);
    }

    @PutMapping("/{matricule}")
    public Voiture update(@PathVariable String matricule, @RequestBody Voiture v) {
        return voitureService.update(matricule, v);
    }

    @DeleteMapping("/{matricule}")
    public void delete(@PathVariable String matricule) {
        voitureService.delete(matricule);
    }

    @PatchMapping("/{matricule}/prix")
    public ResponseEntity<Voiture> updatePrix(@PathVariable String matricule, @RequestBody Map<String, Double> body) {
        Double newPrice = body.get("prix");
        if (newPrice == null || newPrice <= 0) {
            return ResponseEntity.badRequest().build();
        }
        Voiture updated = voitureService.updatePrix(matricule, newPrice);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }
}