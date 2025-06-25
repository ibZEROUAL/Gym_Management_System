package ma.enset.gym_management.web;

import ma.enset.gym_management.dto.ProgramDto;
import ma.enset.gym_management.dto.ProgramResponseDto;
import ma.enset.gym_management.enums.ProgramLevel;
import ma.enset.gym_management.exceptions.*;
import ma.enset.gym_management.services.ProgramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/program")
public class ProgramRestController {
    private final ProgramService programService;

    public ProgramRestController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    public ResponseEntity<List<ProgramResponseDto>> allPrograms() throws ProgramsNotFoundException {

        return ResponseEntity.ok(programService.allPrograms());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProgramResponseDto> getProgramByID(@PathVariable Long id) throws ProgramIdNotFoundException {
        return ResponseEntity.ok(programService.getProgramByID(id));
    }
    @GetMapping(path = "/searchByName")
    public ResponseEntity<ProgramResponseDto>getProgramByNom(@RequestParam("name") String nom) throws ProgramNameNotFoundException {
        return ResponseEntity.ok(programService.getProgramByNom(nom));
    }

    @GetMapping(path = "/searchByLevel")
    public ResponseEntity<List<ProgramResponseDto>> getProgramByLevel(@RequestParam("level") ProgramLevel level) throws ProgramsOfLevelNotFoundException {
        return ResponseEntity.ok(programService.getProgramByLevel(level));
    }
    @GetMapping(path = "/searchByVisibility")
    public ResponseEntity<List<ProgramResponseDto>> getProgrammeByVisibility(@RequestParam boolean visible) throws ProgramsByVisibilityNotFoundException {
        return ResponseEntity.ok(programService.getProgramByVisibility(visible));
    }
   /* @GetMapping(path = "/searchByType")
    public ResponseEntity<List<ProgramDto>> getProgramByType(@RequestParam ProgramType type) throws ProgramsByTypeNotFoundException {
        return ResponseEntity.ok(programService.getProgramByType(type));
    }

    @GetMapping(path = "/searchBytypeAndnivau")
    public ResponseEntity<List<ProgramDto>> getProgramByTypeAndLevel(@RequestParam ProgramType type, @RequestParam ProgramLevel level) throws ProgramsByTypeAndLevelNotFoundException {
        return ResponseEntity.ok(programService.getProgramByTypeAndLevel(type,level));
    }

    */


    @PostMapping(path = "/create")
    public ResponseEntity<ProgramResponseDto> createProgram(@RequestBody ProgramDto programDto) throws ProgramAlreadyExistsException {
        return ResponseEntity.ok(programService.createProgram(programDto)) ;
    }
    @PostMapping(path ="/{programId}/exercises/{exerciseId}")
    public ResponseEntity<ProgramResponseDto> addExerciseToProgram (@PathVariable Long programId,@PathVariable Long exerciseId) throws ProgramIdNotFoundException, ExerciseIdNotFoundException, ExerciseAlreadyAssignedToProgramException, ListExercisesOfProgramNotFoundException {
        return ResponseEntity.ok(programService.addExerciseToProgram(programId,exerciseId));
    }

    @PutMapping(path = "/updateProgram/{programId}")
    public ResponseEntity<ProgramResponseDto> updateProgram(@PathVariable Long programId, @RequestBody ProgramDto programDto) throws ProgramIdNotFoundException {
        return ResponseEntity.ok(programService.updateProgram(programId,programDto));
    }
    @DeleteMapping(path = "/deleteProgram/{programId}")
    public ResponseEntity<String> deleteProgram(@PathVariable Long programId) throws ProgramIdNotFoundException {
        programService.deleteProgram(programId);
        return ResponseEntity.ok("le programme est supprimé");
    }
    @PostMapping(path ="/{programId}/repast/{repastId}")
    public ResponseEntity<ProgramResponseDto> addRepastToProgram (@PathVariable Long programId, @PathVariable Long repastId) throws ProgramIdNotFoundException, RepastIdNotFoundException, RepastAlreadyAssignedToProgramException {
        return ResponseEntity.ok(programService.addRepastToProgram(programId,repastId));
    }

}
