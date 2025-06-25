package ma.enset.gym_management.web;

import jakarta.validation.Valid;
import ma.enset.gym_management.dto.*;
import ma.enset.gym_management.exceptions.*;
import ma.enset.gym_management.services.CoachService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/Coach")
public class CoachRestController {
    CoachService coachService;
    public CoachRestController(CoachService coachService) {
        this.coachService = coachService;
    }


    @GetMapping
    public ResponseEntity<List<CoachResponseDto>> allCoach() throws CoachsNotFoundException {

        return ResponseEntity.ok(coachService.AllCoach());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CoachResponseDto> getProgramByID(@PathVariable Long id) throws CoachIdNotFoundException {
        return ResponseEntity.ok(coachService.getCoachByID(id));
    }


    @GetMapping(path = "/searchBySpeciality")
    public ResponseEntity<List<CoachResponseDto>> getProgramBySpeciality(@RequestParam("Speciality") String speciality) throws CoachSpecialityNotFoundException {
        return ResponseEntity.ok(coachService.getCoachBySpeciality(speciality));
    }

    @PostMapping("/addNewCoach")
    public ResponseEntity<CoachResponseDto> addNewCoach(@RequestBody @Valid CoachDto coachDto) throws CoachAlreadyExistsException {
        CoachResponseDto newCoach = coachService.addCoach(coachDto);
        return ResponseEntity.ok(newCoach);
    }


    @PutMapping(path = "/updateCoach/{id}")
    public ResponseEntity<CoachResponseDto> updateCoach(@PathVariable Long id, @Valid @RequestBody CoachDto coachDto) throws AdherentIdNotFoundException, CoachIdNotFoundException {
        CoachResponseDto coachResponseDto = coachService.updateCoach(id,coachDto);
        return ResponseEntity.ok(coachResponseDto);
    }

    @DeleteMapping(path = "/deleteCoach/{id}")
    public ResponseEntity<String> deleteAdherent(@PathVariable Long id){
        coachService.deleteCoch(id);
        return ResponseEntity.ok("le coach est supprimé");
    }

}
