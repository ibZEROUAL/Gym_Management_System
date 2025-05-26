package ma.enset.gym_management.web;

import ma.enset.gym_management.dto.ExerciseDto;
import ma.enset.gym_management.enums.ExerciseCategorie;
import ma.enset.gym_management.exceptions.*;
import ma.enset.gym_management.services.ExerciseService;
import ma.enset.gym_management.services.ProgramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseRestController {
    private final ExerciseService exerciseService;
    public ExerciseRestController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public ResponseEntity<List<ExerciseDto>> allExercises() throws ExercisesNotFoundException {
        return ResponseEntity.ok(exerciseService.allExercises());
    }
    @GetMapping(path = "/{exerciseId}")
    public ResponseEntity<ExerciseDto> getExerciseById(@PathVariable Long exerciseId) throws ExerciseIdNotFoundException {
        return ResponseEntity.ok(exerciseService.getExerciseByID(exerciseId));
    }
    @GetMapping( path = "/searchByName")
    public ResponseEntity<ExerciseDto> getExerciseByNom(String nom) throws ExerciseNameNotFoundException {
        return ResponseEntity.ok(exerciseService.getExerciseByNom(nom));
    }
    @GetMapping(path = "/searchByCategory")
    public ResponseEntity<List<ExerciseDto>> getExercisesByCategory(ExerciseCategorie category) throws ExercisesByCategorieNotFoundException{
        return ResponseEntity.ok(exerciseService.getExercisesByCategory(category));
    }
    @PostMapping(path = "/createExercise")
    public ResponseEntity<ExerciseDto> createExercise(@RequestBody ExerciseDto exerciseDto) throws ExerciseAlreadyExistsException {
        return ResponseEntity.ok(exerciseService.createExercise(exerciseDto));
    }

    @PutMapping(path = "/updateExercise/{exerciseId}")
    public ResponseEntity<ExerciseDto> updateExercise(@PathVariable Long exerciseId, @RequestBody ExerciseDto exerciseDto) throws ExerciseIdNotFoundException {
        exerciseDto.setId(exerciseId);
        return ResponseEntity.ok(exerciseService.updateExercise(exerciseId,exerciseDto));
    }
    @DeleteMapping(path = "/deleteExercise/{exerciseId}")
    public ResponseEntity<String> deleteExercise(@PathVariable Long exerciseId) throws ExerciseIdNotFoundException {
        exerciseService.deleteExercise(exerciseId);
        return ResponseEntity.ok("l'exercice est supprimé");
    }

}
