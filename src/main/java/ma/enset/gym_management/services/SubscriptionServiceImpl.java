package ma.enset.gym_management.services;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import ma.enset.gym_management.dto.SubscriptionDto;
import ma.enset.gym_management.dto.SubscriptionResponseDto;
import ma.enset.gym_management.entities.Adherent;
import ma.enset.gym_management.entities.Coach;
import ma.enset.gym_management.entities.CoachingSession;
import ma.enset.gym_management.entities.Subscription;
import ma.enset.gym_management.enums.SubscriptionType;
import ma.enset.gym_management.exceptions.*;
import ma.enset.gym_management.mappers.ProgramMapperImpl;
import ma.enset.gym_management.repositories.AdherentRepository;
import ma.enset.gym_management.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@Builder
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    ProgramMapperImpl dtoMapper;
    SubscriptionRepository subscriptionRepository;
    @Override
    public SubscriptionResponseDto save(SubscriptionDto subscriptionDto) {
        log.info("ajouter un abonnements ");

        Subscription subscription = new Subscription();
        subscription.setType(subscriptionDto.getType());
        subscription.setPrix(subscriptionDto.getPrix());
        subscription.setDateDebut(subscriptionDto.getDateDebut());
        subscription.setDateFin(subscriptionDto.getDateFin());

        return dtoMapper.mapSubsToSubsResDTO(subscriptionRepository.save(subscription));

    }

    @Override
    public SubscriptionResponseDto update(Long id, SubscriptionDto subscriptionDto) throws SubscriptionIdNotFoundException {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new SubscriptionIdNotFoundException("Subscription introuvable"));
        subscription.setType(subscriptionDto.getType());
        subscription.setPrix(subscriptionDto.getPrix());
        subscription.setDateDebut(subscriptionDto.getDateDebut());
        subscription.setDateFin(subscriptionDto.getDateFin());
        return dtoMapper.mapSubsToSubsResDTO(subscriptionRepository.save(subscription));
    }

    @Override
    public void delete(Long id) {
        subscriptionRepository.deleteById(id);
    }

    @Override
    public SubscriptionResponseDto get(Long id) throws SubscriptionIdNotFoundException {
        log.info("Recherche un abonnement avec le Id ={}",id);
        return subscriptionRepository.findById(id)
                .map(dtoMapper::mapSubsToSubsResDTO)
                .orElseThrow(() -> new SubscriptionIdNotFoundException("Subscription introuvable"));
    }

    @Override
    public List<SubscriptionResponseDto> getAll() {
        log.info("Recherche tout les abonnements ");

        return subscriptionRepository.findAll().stream().map(dtoMapper::mapSubsToSubsResDTO).toList();

    }

    @Override
    public List<SubscriptionResponseDto> getByType(SubscriptionType type) throws SubscriptionTypeNotFoundException {
        log.info("");
        List<Subscription> subscription = subscriptionRepository.findByType(type);
        if (subscription.isEmpty()){
            log.warn("les abonnements  non trouvé");
            throw new SubscriptionTypeNotFoundException(" les abonnements non trouvé ");
        }
        return subscription.stream().map(dtoMapper::mapSubsToSubsResDTO).collect(Collectors.toList());
    }
}
