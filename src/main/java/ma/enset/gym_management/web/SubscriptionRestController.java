package ma.enset.gym_management.web;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.enset.gym_management.dto.SubscriptionDto;
import ma.enset.gym_management.dto.SubscriptionResponseDto;
import ma.enset.gym_management.enums.SubscriptionType;
import ma.enset.gym_management.exceptions.SubscriptionIdNotFoundException;
import ma.enset.gym_management.exceptions.SubscriptionTypeNotFoundException;
import ma.enset.gym_management.services.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor

public class SubscriptionRestController {

        private final SubscriptionService subscriptionService;

        @PostMapping(path = "createSubscription")
        public ResponseEntity<SubscriptionResponseDto> createSubscription(@Valid @RequestBody SubscriptionDto subscriptionDto) {
            return ResponseEntity.ok(subscriptionService.save(subscriptionDto));
        }

        @PutMapping(path = "/updateSubscription/{id}")
        public ResponseEntity<SubscriptionResponseDto> updateSubscription(@PathVariable Long id,@Valid @RequestBody SubscriptionDto subscriptionDto) throws SubscriptionIdNotFoundException {
            return ResponseEntity.ok(subscriptionService.update(id, subscriptionDto));
        }

        @GetMapping("/{id}")
        public ResponseEntity<SubscriptionResponseDto> searchById(@PathVariable Long id) throws SubscriptionIdNotFoundException {
            return ResponseEntity.ok(subscriptionService.get(id));
        }
        @GetMapping("/{type}")
        public ResponseEntity<List<SubscriptionResponseDto>>searchByTyp(@PathVariable SubscriptionType type) throws SubscriptionTypeNotFoundException {
        return ResponseEntity.ok(subscriptionService.getByType(type));
         }

        @GetMapping
        public ResponseEntity<List<SubscriptionResponseDto>> AllSubscription() {
            return ResponseEntity.ok(subscriptionService.getAll());
        }

        @DeleteMapping(path = "/deleteSubscription/{id}")
        public ResponseEntity<String> deleteSubscription(@PathVariable Long id) {
            subscriptionService.delete(id);
            return ResponseEntity.ok("l'abonnement est supprimé");
        }

}
