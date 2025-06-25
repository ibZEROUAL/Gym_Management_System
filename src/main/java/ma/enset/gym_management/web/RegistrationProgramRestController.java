package ma.enset.gym_management.web;


import ma.enset.gym_management.dto.RegistrationProgramResponseDto;
import ma.enset.gym_management.exceptions.*;
import ma.enset.gym_management.services.RegistrationProgramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/registrationProgram")
public class RegistrationProgramRestController {
    RegistrationProgramService registrationProgramService;

    public RegistrationProgramRestController(RegistrationProgramService registrationProgramService) {
        this.registrationProgramService = registrationProgramService;

    }

    @GetMapping
    public ResponseEntity<List<RegistrationProgramResponseDto>> allRegistrationProgram(){
        return ResponseEntity.ok(registrationProgramService.AllRegistrations());
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<RegistrationProgramResponseDto> getRegistrationById(@PathVariable Long id) throws RegistrationProgramIdNotFoundException {
        return ResponseEntity.ok(registrationProgramService.getRegistrationsById(id));
    }
    @GetMapping(path = "/registrationDate")
    public ResponseEntity<RegistrationProgramResponseDto> getRegistrationByDate(@RequestParam LocalDateTime date) throws RegistrationProgramDateNotFoundException {
        return ResponseEntity.ok(registrationProgramService.getRegistrationByRegisteredAt(date));
    }
    @GetMapping(path = "/registrationOfAdherent/{adherentId}")
    public ResponseEntity<List<RegistrationProgramResponseDto>> getRegistrationOfAdherent(@PathVariable Long adherentId) throws AdherentIdNotFoundException, RegistrationOfAdherentNotFoundException {
        return ResponseEntity.ok(registrationProgramService.getRegistrationByAdherent(adherentId));
    }
    @GetMapping(path = "/registrationOfProgram/{programId}")
    public ResponseEntity<List<RegistrationProgramResponseDto>> getRegistrationOfProgram(@PathVariable Long programId) throws ProgramIdNotFoundException, RegistrationOfProgramtNotFoundException {
        return ResponseEntity.ok(registrationProgramService.getRegistrationByProgram(programId));
    }
    @PostMapping(path = "/registration")
    public ResponseEntity<RegistrationProgramResponseDto> registrationInProgram(@RequestBody  LocalDateTime registeredAt, @RequestParam String adherentDtoUserName, @RequestParam String programDtoName) throws ProgramNameNotFoundException, AdherentUserNameNotFoundException {

        return ResponseEntity.ok(registrationProgramService.registrationInProgram(registeredAt,adherentDtoUserName,programDtoName));
    }
    @DeleteMapping(path = "/deleteRegistraion")
    public ResponseEntity<String> deleteRegistration(long id){
        registrationProgramService.deleteRegistration(id);
        return ResponseEntity.ok("l'inscription est supprimé");
    }


}
