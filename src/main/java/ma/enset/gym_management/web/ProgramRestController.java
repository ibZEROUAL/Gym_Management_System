package ma.enset.gym_management.web;

import ma.enset.gym_management.dto.ExerciseDto;
import ma.enset.gym_management.dto.ProgramDto;
import ma.enset.gym_management.enums.ProgramLevel;
import ma.enset.gym_management.enums.ProgramType;
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
    public ResponseEntity<List<ProgramDto>> allPrograms() throws ProgramsNotFoundException {

        return ResponseEntity.ok(programService.allPrograms());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProgramDto> getProgramByID(@PathVariable Long id) throws ProgramIdNotFoundException {
        return ResponseEntity.ok(programService.getProgramByID(id));
    }
    @GetMapping(path = "/searchByName")
    public ResponseEntity<ProgramDto>getProgramByNom(@RequestParam("name") String nom) throws ProgramNameNotFoundException {
        return ResponseEntity.ok(programService.getProgramByNom(nom));
    }

    @GetMapping(path = "/searchByLevel")
    public ResponseEntity<List<ProgramDto>> getProgramByLevel(@RequestParam("level") ProgramLevel level) throws ProgramsOfLevelNotFoundException {
        return ResponseEntity.ok(programService.getProgramByLevel(level));
    }
    @GetMapping(path = "/searchByVisibility")
    public ResponseEntity<List<ProgramDto>> getProgrammeByVisibility(@RequestParam boolean visible) throws ProgramsByVisibilityNotFoundException {
        return ResponseEntity.ok(programService.getProgramByVisibility(visible));
    }
    @GetMapping(path = "/searchByType")
    public ResponseEntity<List<ProgramDto>> getProgramByType(@RequestParam ProgramType type) throws ProgramsByTypeNotFoundException {
        return ResponseEntity.ok(programService.getProgramByType(type));
    }

    @GetMapping(path = "/searchBytypeAndnivau")
    public ResponseEntity<List<ProgramDto>> getProgramByTypeAndLevel(@RequestParam ProgramType type, @RequestParam ProgramLevel level) throws ProgramsByTypeAndLevelNotFoundException {
        return ResponseEntity.ok(programService.getProgramByTypeAndLevel(type,level));
    }
    @GetMapping("/listExeOfProgram/{program_Id}")
        public ResponseEntity<List<ExerciseDto>> listExercisesOfProgram(@PathVariable Long program_Id) throws ProgramIdNotFoundException, ListExercisesOfProgramNotFoundException {
        return ResponseEntity.ok(programService.listExercisesOfProgram(program_Id)) ;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ProgramDto> createProgram(@RequestBody ProgramDto programDto) throws ProgramAlreadyExistsException {
        return ResponseEntity.ok(programService.createProgram(programDto)) ;
    }
    @PostMapping(path ="/{programId}/exercises/{exerciseId}")
    public ResponseEntity<ProgramDto> addExerciseToProgram (@PathVariable Long programId,@PathVariable Long exerciseId) throws ProgramIdNotFoundException, ExerciseIdNotFoundException, ListExercisesOfProgramNotFoundException, ExerciseAlreadyAssignedToProgramException {
        return ResponseEntity.ok(programService.addExerciseToProgram(programId,exerciseId));
    }


}
