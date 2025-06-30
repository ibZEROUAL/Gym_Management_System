package ma.enset.gym_management.web;



import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.enset.gym_management.dto.CoachingSessionResponseDto;
import ma.enset.gym_management.exceptions.*;
import ma.enset.gym_management.services.CoachingSessionService;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class CoachingSessionRestController {

    private final CoachingSessionService coachingSessionService;

    @GetMapping
    public ResponseEntity<List<CoachingSessionResponseDto>> getAllSessions() {
        return ResponseEntity.ok(coachingSessionService.allSession());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoachingSessionResponseDto> getById(@PathVariable Long id) throws CoachingSessionIdNoteFoundException {
        return ResponseEntity.ok(coachingSessionService.getCoachingSessionById(id));
    }
    @GetMapping("/searchByDate")
    public ResponseEntity<CoachingSessionResponseDto> getByDate(
            @RequestParam LocalDateTime dateTime
    ) throws CoachingSessionDateNoteFoundException {
        return ResponseEntity.ok(coachingSessionService.getCoachingSessionByDate(dateTime));
    }

    @GetMapping("/searchByStatute")
    public ResponseEntity<List<CoachingSessionResponseDto>> getByStatute(@RequestParam boolean statute)
            throws CoachingSessionStatuteNoteFoundException {
        return ResponseEntity.ok(coachingSessionService.getCoachingSessionByStatute(statute));
    }
    @PostMapping("/reservationSession")
    public ResponseEntity<CoachingSessionResponseDto> reservationCoachingSession(
            @Valid @RequestParam LocalDateTime dateTime,
            @RequestParam String coachUserName,
            @RequestParam String adherentUserName
    ) throws CoachEmailNotFoundException, AdherentEmailNotFoundException {
        return ResponseEntity.ok(coachingSessionService.reservationCoachingSession(dateTime, coachUserName, adherentUserName));
    }
}

