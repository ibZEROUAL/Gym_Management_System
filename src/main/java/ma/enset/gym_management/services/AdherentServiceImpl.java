package ma.enset.gym_management.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.gym_management.dto.AdherentDto;
import ma.enset.gym_management.dto.AdherentResponseDTO;
import ma.enset.gym_management.entities.Adherent;
import ma.enset.gym_management.entities.Subscription;
import ma.enset.gym_management.exceptions.AdherentAlreadyExistsException;
import ma.enset.gym_management.exceptions.AdherentIdNotFoundException;
import ma.enset.gym_management.exceptions.AdherentEmailNotFoundException;
import ma.enset.gym_management.exceptions.SubscriptionIdNotFoundException;
import ma.enset.gym_management.mappers.ProgramMapperImpl;
import ma.enset.gym_management.repositories.AdherentRepository;
import ma.enset.gym_management.repositories.SubscriptionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AdherentServiceImpl implements AdherentService {
    private final AdherentRepository adherentRepository;
    private final ProgramMapperImpl dtoMapper;
    private final PasswordEncoder passwordEncoder;
    private final SubscriptionRepository subscriptionRepository;



    @Override
    public List<AdherentResponseDTO> AllAdherent() {
        log.info("Recherche des adherents");

        return adherentRepository.findAll()
                .stream()
                .map(dtoMapper::mapToAdherentResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdherentResponseDTO getAdherentById(Long id) throws AdherentIdNotFoundException {
        log.info("Recherche un adherents avec le Id ={}",id);
        Adherent adherent = adherentRepository.findById(id).orElseThrow(()->
                new AdherentIdNotFoundException("Adherent avec le ID '"+id+"' non trouvé "));

        return dtoMapper.mapToAdherentResponseDTO(adherent);
    }

    @Override
    public AdherentResponseDTO getAdherentByEmail(String email) throws AdherentEmailNotFoundException {
        log.info("Recherche un adherents avec email = {}",email);
        Adherent adherent = adherentRepository.findByEmail(email);

        if (adherent==null){
            log.warn("Adherent avec email '{}' non trouvé",email);
            throw new AdherentEmailNotFoundException("Adherent avec userName '"+email+"' non trouvé ");
        }

        return dtoMapper.mapToAdherentResponseDTO(adherent);
    }

    @Override
    public AdherentResponseDTO addAdherent(AdherentDto adherentDto) throws AdherentAlreadyExistsException {
        log.info("Ajout d’un nouvel adhérent : {}", adherentDto.getEmail());

        Adherent adherent =adherentRepository.findByEmail(adherentDto.getEmail());

        if (adherent != null){
            log.warn("l'adhérent avec Email '{}' est exisite d'éja",adherent.getEmail());
            throw new AdherentAlreadyExistsException("l'adhérenet '"+adherent.getEmail()+"' est exisite d'éja!");
        }

        Adherent adherentAdd = dtoMapper.mapAdherentDtoToAdherent(adherentDto);
        adherentAdd.setPassword(passwordEncoder.encode(adherentDto.getPassword()));
        Adherent newAdherent= adherentRepository.save(adherentAdd);
        return dtoMapper.mapToAdherentResponseDTO(newAdherent);
    }
    @Override
    public AdherentResponseDTO addSubscriptionToAdherent(Long adherentId, Long subscriptionId) throws AdherentIdNotFoundException, SubscriptionIdNotFoundException {
        log.info("Ajout un nouvel abonnement ");

        Adherent adherent =adherentRepository.findById(adherentId).orElseThrow(()->
                new AdherentIdNotFoundException("Adherent avec le ID '"+adherentId+"' non trouvé "));

        Subscription subscription =subscriptionRepository.findById(subscriptionId).orElseThrow(()->
                new SubscriptionIdNotFoundException("subscription avec le ID '"+subscriptionId+"' non trouvé "));

        adherent.setSubscription(subscription);
        Adherent addSubscription = adherentRepository.save(adherent);
        return dtoMapper.mapToAdherentResponseDTO(addSubscription);
    }


    @Override
    public AdherentResponseDTO updateAdherent(long adherentId ,AdherentDto adherentDto) throws AdherentIdNotFoundException {
        log.info("Mise à jour l'adherent avec le ID = {}", adherentId);
        Adherent existingAdherent =adherentRepository.findById(adherentId).orElseThrow(()->
                new AdherentIdNotFoundException("Adherent avec le ID '"+adherentId+"' non trouvé "));
        BeanUtils.copyProperties(adherentDto,existingAdherent);

        Adherent adherent = adherentRepository.save(existingAdherent);
        return dtoMapper.mapToAdherentResponseDTO(adherent);
    }

    @Override
    public void deleteAdherent(Long adherentDtoId) {
        log.info("Supprimé l'adherent avec le ID = {}", adherentDtoId);

        adherentRepository.deleteById(adherentDtoId);
    }
}
