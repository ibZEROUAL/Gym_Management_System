package ma.enset.gym_management.services;

import lombok.extern.slf4j.Slf4j;

import ma.enset.gym_management.dto.*;
import ma.enset.gym_management.entities.Coach;
import ma.enset.gym_management.exceptions.*;
import ma.enset.gym_management.mappers.ProgramMapperImpl;
import ma.enset.gym_management.repositories.CoachRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@Transactional
@Slf4j
public class CoachServiceImpl implements CoachService {
    private final CoachRepository coachRepository;
    private final ProgramMapperImpl dtoMapper;

    public CoachServiceImpl(CoachRepository coachRepository, ProgramMapperImpl dtoMapper) {
        this.coachRepository = coachRepository;
        this.dtoMapper = dtoMapper;
    }
    @Override
    public List<CoachResponseDto> AllCoach() throws CoachsNotFoundException {
        log.info("Recherche tous les coaches ");

        List<CoachResponseDto> coachResponseDto = coachRepository.findAll()
                .stream()
                .map(dtoMapper::mapToCoachRespDto)
                .toList();

        if (coachResponseDto.isEmpty()){
            log.warn("Aucun coach trouvé");
            throw new CoachsNotFoundException("Aucun Coach disponible dans la base de données.");
        }

        return coachResponseDto ;
    }

    @Override
    public CoachResponseDto getCoachByID(Long id) throws CoachIdNotFoundException {
        log.info("Recherche de coach avec le Id ={} ", id);

        Coach coach = coachRepository.findById(id).orElseThrow(()->
                new CoachIdNotFoundException("Coach avec le ID '" + id + "' non trouvé."));

        return dtoMapper.mapToCoachRespDto(coach);
    }
    @Override
    public CoachResponseDto getCoachByUserName(String userName) throws CoachNameNotFoundException {
        log.info("Recherche des Coaches avec le nom d'utilisateur = {}", userName);
        Coach coach = coachRepository.findByUsername(userName);
        if (coach==null) {
            log.warn("Aucun coach trouvé avec le userName = {}", userName);
            throw new CoachNameNotFoundException("Coach avec le userName '"+userName+"' non trouvé");
        }
        return dtoMapper.mapToCoachRespDto(coach);
    }

    @Override
    public List<CoachResponseDto> getCoachBySpeciality(String speciality) throws CoachSpecialityNotFoundException {
        log.info("Recherche des coachs avec la specialite ={} ",speciality);
        List<CoachResponseDto> coachResponseDto= coachRepository.findBySpecialite(speciality)
                .stream()
                .map(dtoMapper::mapToCoachRespDto)
                .toList();

        if (coachResponseDto.isEmpty()){
            log.warn("Aucun coach trouvé avec la specialite ={} ",speciality);
            throw new CoachSpecialityNotFoundException("Coach avec la specialite '"+speciality+"' non trouvé");
        }

        return coachResponseDto;
    }
    @Override
    public CoachResponseDto addCoach(CoachDto coachDto) throws CoachAlreadyExistsException {
        log.info("Ajout d’un nouvel Coach : {}", coachDto.getUsername());
        Coach coach = coachRepository.findByUsername(coachDto.getUsername());

        if (coach != null){
            log.warn("le coach avec UserName '{}' est exisite d'éja",coach.getUsername());
            throw new CoachAlreadyExistsException("le coach '"+coach.getUsername()+"' est exisite d'éja!");
        }

        Coach newCoach= coachRepository.save(dtoMapper.mapDtoToCoach(coachDto));
        return dtoMapper.mapToCoachRespDto(newCoach);
    }
    @Override
    public CoachResponseDto updateCoach(long coachId, CoachDto coachDto) throws CoachIdNotFoundException {
        log.info("Mise à jour le coach avec le ID = {}", coachId);
        Coach existingAdCoach =coachRepository.findById(coachId).orElseThrow(()->
                new CoachIdNotFoundException("Coach avec le ID '"+coachId+"' non trouvé "));
        BeanUtils.copyProperties(coachDto,existingAdCoach);

        Coach coach = coachRepository.save(existingAdCoach);
        return dtoMapper.mapToCoachRespDto(coach);
    }

    @Override
    public void deleteCoch(Long coachId) {
        log.info("Supprimé le coach avec le ID = {}", coachId);

        coachRepository.deleteById(coachId);
    }


}
