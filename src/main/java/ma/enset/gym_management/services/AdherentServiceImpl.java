package ma.enset.gym_management.services;

import lombok.extern.slf4j.Slf4j;
import ma.enset.gym_management.dto.AdherentDto;
import ma.enset.gym_management.dto.AdherentResponseDTO;
import ma.enset.gym_management.entities.Adherent;
import ma.enset.gym_management.exceptions.AdherentAlreadyExistsException;
import ma.enset.gym_management.exceptions.AdherentIdNotFoundException;
import ma.enset.gym_management.exceptions.AdherentUserNameNotFoundException;
import ma.enset.gym_management.mappers.ProgramMapperImpl;
import ma.enset.gym_management.repositories.AdherentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AdherentServiceImpl implements AdherentService {
    private final AdherentRepository adherentRepository;
    private final ProgramMapperImpl dtoMapper;

    public AdherentServiceImpl(AdherentRepository adherentRepository ,ProgramMapperImpl dtoMapper) {
        this.adherentRepository = adherentRepository;
        this.dtoMapper = dtoMapper;
    }

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
    public AdherentResponseDTO getAdherentByUserName(String userName) throws AdherentUserNameNotFoundException {
        log.info("Recherche un adherents avec username = {}",userName);
        Adherent adherent = adherentRepository.findByUsername(userName);

        if (adherent==null){
            log.warn("Adherent avec userName '{}' non trouvé",userName);
            throw new AdherentUserNameNotFoundException("Adherent avec userName '"+userName+"' non trouvé ");
        }

        return dtoMapper.mapToAdherentResponseDTO(adherent);
    }

    @Override
    public AdherentResponseDTO addAdherent(AdherentDto adherentDto) throws AdherentAlreadyExistsException {
        log.info("Ajout d’un nouvel adhérent : {}", adherentDto.getUsername());
        Adherent adherent =adherentRepository.findByUsername(adherentDto.getUsername());

        if (adherent != null){
            log.warn("l'adhérent avec UserName '{}' est exisite d'éja",adherent.getUsername());
            throw new AdherentAlreadyExistsException("l'adhérenet '"+adherent.getUsername()+"' est exisite d'éja!");
        }

       Adherent newAdherent= adherentRepository.save(dtoMapper.mapAdherentDtoToAdherent(adherentDto));
        return dtoMapper.mapToAdherentResponseDTO(newAdherent);
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
