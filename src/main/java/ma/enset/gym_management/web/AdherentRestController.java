package ma.enset.gym_management.web;
import jakarta.validation.Valid;


import ma.enset.gym_management.dto.AdherentDto;
import ma.enset.gym_management.dto.AdherentResponseDTO;

import ma.enset.gym_management.exceptions.AdherentAlreadyExistsException;
import ma.enset.gym_management.exceptions.AdherentIdNotFoundException;
import ma.enset.gym_management.exceptions.AdherentEmailNotFoundException;
import ma.enset.gym_management.exceptions.SubscriptionIdNotFoundException;
import ma.enset.gym_management.services.AdherentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@Validated
@RestController
@RequestMapping(path = "/api/Adherent")
public class AdherentRestController {

    public AdherentRestController(AdherentService adherentService) {
        this.adherentService = adherentService;
    }
    private final AdherentService adherentService;

    @GetMapping
    public ResponseEntity<List<AdherentResponseDTO>> AllAdherent(){
        List<AdherentResponseDTO> adherents = adherentService.AllAdherent();
        return ResponseEntity.ok(adherents);
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<AdherentResponseDTO> getAdherentById(@PathVariable Long id) throws AdherentIdNotFoundException {
        AdherentResponseDTO adherent = adherentService.getAdherentById(id);
        return ResponseEntity.ok(adherent);
    }
    @GetMapping(path = "/searchByEmail")
    public ResponseEntity<AdherentResponseDTO> getAdherentByUserName(@Valid @RequestParam String username) throws AdherentEmailNotFoundException {
        AdherentResponseDTO adherent = adherentService.getAdherentByEmail(username);
        return ResponseEntity.ok(adherent);
    }


    @PostMapping("/addNewAdherent")
    public ResponseEntity<AdherentResponseDTO> addNewAdherent(
            @RequestBody @Valid AdherentDto adherentDto) throws AdherentAlreadyExistsException {
        System.out.println("=== REÇU ===> " + adherentDto);
        AdherentResponseDTO newAdherent = adherentService.addAdherent(adherentDto);
        return ResponseEntity.ok(newAdherent);
    }


    @PutMapping(path = "/updateAdherent/{id}")
    public ResponseEntity<AdherentResponseDTO> updateAdherent(@PathVariable Long id, @Valid @RequestBody AdherentDto adherentDto) throws AdherentIdNotFoundException {
      AdherentResponseDTO adherentDtoA = adherentService.updateAdherent(id,adherentDto);
      return ResponseEntity.ok(adherentDtoA);
    }

    @PostMapping("/addSubscription/{adherentId}/{subscriptionId}")
    public ResponseEntity<AdherentResponseDTO> addSubscriptionToAdherent(@PathVariable Long adherentId,@PathVariable Long subscriptionId) throws AdherentIdNotFoundException, SubscriptionIdNotFoundException {
        AdherentResponseDTO addSubscription = adherentService.addSubscriptionToAdherent(adherentId, subscriptionId);
        return ResponseEntity.ok(addSubscription);
    }

    @DeleteMapping(path = "/deleteAdherent/{id}")
    public ResponseEntity<String> deleteAdherent(@PathVariable Long id){
        adherentService.deleteAdherent(id);
        return ResponseEntity.ok("l'adherent est supprimé");
    }
}
