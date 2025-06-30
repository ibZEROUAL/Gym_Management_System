package ma.enset.gym_management.web;


import jakarta.validation.Valid;
import ma.enset.gym_management.dto.RepastDto;
import ma.enset.gym_management.dto.RepastResponseDto;
import ma.enset.gym_management.enums.*;
import ma.enset.gym_management.exceptions.*;
import ma.enset.gym_management.services.RepastService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/repast")
public class RepastRestController {
    private final RepastService repastService;
    public RepastRestController(RepastService repastService) {
        this.repastService = repastService;
    }

    @GetMapping
    public ResponseEntity<List<RepastResponseDto>> allRepasts() throws RepastsNotFoundException {
        return ResponseEntity.ok(repastService.allRepasts());
    }
    @GetMapping(path = "/{repastId}")
    public ResponseEntity<RepastResponseDto> getRepastById(@PathVariable Long repastId) throws RepastIdNotFoundException {
        return ResponseEntity.ok(repastService.getRepastById(repastId));
    }
    @GetMapping( path = "/searchByType")
    public ResponseEntity<List<RepastResponseDto>> getRepastByType(@RequestParam RepastType type) throws RepastByTypeNotFoundException {
        return ResponseEntity.ok(repastService.getRepastByType(type));
    }
    @GetMapping(path = "/searchByObjective")
    public ResponseEntity<List<RepastResponseDto>> getRepastByObjective(@RequestParam RepastObjective objective) throws RepastByObjectiveNotFoundException {
        return ResponseEntity.ok(repastService.getRepastByObjective(objective));
    }
    @GetMapping(path = "/searchByTypeAndObjective")
    public ResponseEntity<List<RepastResponseDto>> getRepastByTypeAndObjective(@RequestParam RepastType type, @RequestParam RepastObjective objective) throws RepastByTypeAndObjectiveNotFoundException {
        return ResponseEntity.ok(repastService.getRepastByTypeAndObjective(type,objective));
    }

    @GetMapping("/listRepastOfProgram/{program_Id}")
    public ResponseEntity<List<RepastResponseDto>> listRepastsOfProgram(@PathVariable Long program_Id) throws ProgramIdNotFoundException {
        return ResponseEntity.ok(repastService.listRepastsOfProgram(program_Id)) ;
    }
    @PostMapping(path = "/createRepast")
    public ResponseEntity<RepastResponseDto> createRepast(@Valid @RequestBody RepastDto repastDto) throws RepastAlreadyExistsException {
        return ResponseEntity.ok(repastService.createRepast(repastDto));
    }

    @PutMapping(path = "/updateRepast/{repastId}")
    public ResponseEntity<RepastResponseDto> updateRepast(@PathVariable Long repastId,@Valid @RequestBody RepastDto repastDto) throws ExerciseIdNotFoundException, RepastIdNotFoundException {
        return ResponseEntity.ok(repastService.updateRepast(repastId,repastDto));
    }
    @DeleteMapping(path = "/deleterepast/{repastId}")
    public ResponseEntity<String> deleteRepast(@PathVariable Long repastId) throws RepastIdNotFoundException {
        repastService.deleteRepast(repastId);
        return ResponseEntity.ok("le repas est supprimé");
    }


}
