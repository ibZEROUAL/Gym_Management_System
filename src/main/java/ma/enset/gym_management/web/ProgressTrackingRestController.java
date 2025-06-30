package ma.enset.gym_management.web;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.enset.gym_management.dto.ProgressTrackingDto;
import ma.enset.gym_management.dto.ProgressTrackingResponseDto;
import ma.enset.gym_management.exceptions.AdherentIdNotFoundException;
import ma.enset.gym_management.exceptions.ProgressTrackingIdNotFoundException;
import ma.enset.gym_management.services.ProgressTrackingService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor

public class ProgressTrackingRestController {

    private final ProgressTrackingService progressTrackingService;

    @PostMapping(path = "/progress")
    public ResponseEntity<ProgressTrackingResponseDto> save(@Valid @RequestBody ProgressTrackingDto trackingDto) throws AdherentIdNotFoundException {
        return ResponseEntity.ok(progressTrackingService.save(trackingDto));
    }

    @GetMapping("/adherent/{adherentId}")
    public ResponseEntity<List<ProgressTrackingResponseDto>> getByAdherent(@PathVariable Long adherentId) {
        return ResponseEntity.ok(progressTrackingService.findByAdherentId(adherentId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgressTrackingResponseDto> getById(@PathVariable Long id) throws ProgressTrackingIdNotFoundException {
        return ResponseEntity.ok(progressTrackingService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok("le Suivi est supprimé");
    }
}
