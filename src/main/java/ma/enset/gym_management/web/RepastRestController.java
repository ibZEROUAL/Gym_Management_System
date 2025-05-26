package ma.enset.gym_management.web;


import ma.enset.gym_management.dto.RepastDto;
import ma.enset.gym_management.enums.*;
import ma.enset.gym_management.exceptions.*;
import ma.enset.gym_management.services.ProgramService;
import ma.enset.gym_management.services.RepastService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repast")
public class RepastRestController {
    private final RepastService repastService;
    public RepastRestController(RepastService repastService) {
        this.repastService = repastService;
    }

    @GetMapping
    public ResponseEntity<List<RepastDto>> allRepasts() throws RepastsNotFoundException {
        return ResponseEntity.ok(repastService.allRepasts());
    }
    @GetMapping(path = "/{repastId}")
    public ResponseEntity<RepastDto> getRepastByid(@PathVariable Long repastId) throws RepastIdNotFoundException {
        return ResponseEntity.ok(repastService.getRepastById(repastId));
    }
    @GetMapping( path = "/searchByType")
    public ResponseEntity<List<RepastDto>> getRepastByType(RepastType type) throws RepastByTypeNotFoundException {
        return ResponseEntity.ok(repastService.getRepastByType(type));
    }
    @GetMapping(path = "/searchByObjective")
    public ResponseEntity<List<RepastDto>> getRepastByObjective(RepastObjictive objictive) throws RepastByObjictiveNotFoundException {
        return ResponseEntity.ok(repastService.getRepastByObjective(objictive));
    }
    @GetMapping(path = "/searchByTypeAndObjective")
    public ResponseEntity<List<RepastDto>> getRepastByTypeAndObjective(@RequestParam RepastType type, @RequestParam RepastObjictive objictive) throws RepastByTypeAndObjictiveNotFoundException {
        return ResponseEntity.ok(repastService.getRepastByTypeAndObjective(type,objictive));
    }

    @PostMapping(path = "/createRepast")
    public ResponseEntity<RepastDto> createRepast(@RequestBody RepastDto repastDto) throws RepastAlreadyExistsException {
        return ResponseEntity.ok(repastService.createRepast(repastDto));
    }

    @PutMapping(path = "/updateRepast/{repastId}")
    public ResponseEntity<RepastDto> updateRepast(@PathVariable Long repastId, @RequestBody RepastDto repastDto) throws ExerciseIdNotFoundException, RepastIdNotFoundException {
        repastDto.setId(repastId);
        return ResponseEntity.ok(repastService.updateRepast(repastId,repastDto));
    }
    @DeleteMapping(path = "/deleterepast/{repastId}")
    public ResponseEntity<String> deleteExercise(@PathVariable Long repastId) throws RepastIdNotFoundException {
        repastService.deleterepast(repastId);
        return ResponseEntity.ok("le repas est supprimé");
    }


}
