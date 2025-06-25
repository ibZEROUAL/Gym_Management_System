package ma.enset.gym_management.web;

import ma.enset.gym_management.dto.ExerciseDto;
import ma.enset.gym_management.dto.ExerciseResponseDto;
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
    public ResponseEntity<List<ExerciseResponseDto>> allExercises() throws ExercisesNotFoundException {
        return ResponseEntity.ok(exerciseService.allExercises());
    }
    @GetMapping(path = "/{exerciseId}")
    public ResponseEntity<ExerciseResponseDto> getExerciseById(@PathVariable Long exerciseId) throws ExerciseIdNotFoundException {
        return ResponseEntity.ok(exerciseService.getExerciseByID(exerciseId));
    }
    @GetMapping( path = "/searchByName")
    public ResponseEntity<ExerciseResponseDto> getExerciseByNom(@RequestParam String nom) throws ExerciseNameNotFoundException {
        return ResponseEntity.ok(exerciseService.getExerciseByNom(nom));
    }
    @GetMapping(path = "/searchByCategory")
    public ResponseEntity<List<ExerciseResponseDto>> getExercisesByCategory(@RequestParam ExerciseCategorie category) throws ExercisesByCategorieNotFoundException{
        return ResponseEntity.ok(exerciseService.getExercisesByCategory(category));
    }
    @GetMapping("/listExeOfProgram/{program_Id}")
    public ResponseEntity<List<ExerciseResponseDto>> listExercisesOfProgram(@PathVariable Long program_Id) throws ProgramIdNotFoundException, ListExercisesOfProgramNotFoundException {
        return ResponseEntity.ok(exerciseService.listExercisesOfProgram(program_Id)) ;
    }

    @PostMapping(path = "/createExercise")
    public ResponseEntity<ExerciseResponseDto> createExercise(@RequestBody ExerciseDto exerciseDto) throws ExerciseAlreadyExistsException {
        return ResponseEntity.ok(exerciseService.createExercise(exerciseDto));
    }

    @PutMapping(path = "/updateExercise/{exerciseId}")
    public ResponseEntity<ExerciseResponseDto> updateExercise(@PathVariable Long exerciseId, @RequestBody ExerciseDto exerciseDto) throws ExerciseIdNotFoundException {
        return ResponseEntity.ok(exerciseService.updateExercise(exerciseId,exerciseDto));
    }
    @DeleteMapping(path = "/deleteExercise/{exerciseId}")
    public ResponseEntity<String> deleteExercise(@PathVariable Long exerciseId) throws ExerciseIdNotFoundException {
        exerciseService.deleteExercise(exerciseId);
        return ResponseEntity.ok("l'exercice est supprimé");
    }

}
