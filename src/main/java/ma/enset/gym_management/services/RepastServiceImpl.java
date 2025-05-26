package ma.enset.gym_management.services;

import lombok.extern.slf4j.Slf4j;
import ma.enset.gym_management.dto.RepastDto;
import ma.enset.gym_management.entities.Repast;
import ma.enset.gym_management.enums.RepastObjictive;
import ma.enset.gym_management.enums.RepastType;
import ma.enset.gym_management.exceptions.*;
import ma.enset.gym_management.mappers.ProgramMapperImpl;
import ma.enset.gym_management.repositories.RepastRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class RepastServiceImpl implements RepastService {
     RepastRepository repastRepository;
     ProgramMapperImpl dtoMapper;
    public RepastServiceImpl(RepastRepository repastRepository, ProgramMapperImpl dtoMapper) {
        this.repastRepository = repastRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<RepastDto> allRepasts() throws RepastsNotFoundException {
        log.info("Recherche de tous les repas ");

        List<RepastDto> repastDtos = repastRepository.findAll()
                .stream()
                .map(dtoMapper::mapToRepastDto)
                .toList();

        if (repastDtos.isEmpty()){
            log.warn("Aucun repas trouvé");
            throw new RepastsNotFoundException("Aucun repas disponible dans la base de données.");
        }
        return repastDtos;
    }
    @Override
    public RepastDto getRepastById(Long id) throws RepastIdNotFoundException {
        log.info("Recherche de repas avec le Id ={} ",id);
        Repast repast = repastRepository.findById(id).orElseThrow(()->
                new RepastIdNotFoundException("Repast avec le ID '"+id+"' non trouvé"));
        return dtoMapper.mapToRepastDto(repast);
    }
    @Override
    public List<RepastDto> getRepastByType(RepastType type) throws RepastByTypeNotFoundException {
        log.info("Recherche de raps avec le Type ={} ",type);
        List<RepastDto> repastDtos = repastRepository.findByType(type)
                .stream()
                .map(dtoMapper::mapToRepastDto)
                .collect(Collectors.toList());

        if (repastDtos.isEmpty()) {
            log.warn("Aucun repas trouvé avec le Type ={} ",type);
            throw new RepastByTypeNotFoundException("Repas avec le type '"+type+"' non trouvé");
        }
        return repastDtos;
    }
    @Override
    public List<RepastDto> getRepastByObjective(RepastObjictive objictive) throws RepastByObjictiveNotFoundException {
        log.info("Recherche de rapas avec l'objictive ={} ",objictive);
        List<RepastDto> repastDtos = repastRepository.findByObjective(objictive)
                .stream()
                .map(dtoMapper::mapToRepastDto)
                .collect(Collectors.toList());

        if (repastDtos.isEmpty()) {
            log.warn("Aucun repas trouvé avec l'objictive ={} ",objictive);
            throw new RepastByObjictiveNotFoundException("Repas avec l'objictive '"+objictive+"' non trouvé");
        }
        return repastDtos;
    }
    @Override
    public List<RepastDto> getRepastByTypeAndObjective(RepastType type ,RepastObjictive objictive) throws RepastByTypeAndObjictiveNotFoundException {
        log.info("Recherche de rapas avec l'objictive ={} est le type ={} ",objictive,type);
        List<RepastDto> repastDtos = repastRepository.findByTypeAndObjective(type, objictive)
                .stream()
                .map(dtoMapper::mapToRepastDto)
                .collect(Collectors.toList());

        if (repastDtos.isEmpty()) {
            log.warn("Aucun repas trouvé avec l'objictive ={} est le type ={} ",objictive,type);
            throw new RepastByTypeAndObjictiveNotFoundException("Repas avec l'objictive '"+objictive+"' est le type '"+type+"' non trouvé");
        }
        return repastDtos;
    }

    @Override
    public RepastDto createRepast(RepastDto repastDto) throws RepastAlreadyExistsException {
        log.info("Création d'un nouveau repas avec le nom = {}", repastDto.getNom());

        Repast repast =repastRepository.findByNom(repastDto.getNom());
        if (repast != null){
            log.warn("Le repas avec le nom '{}' est existe déjà! ", repast.getNom());
            throw new RepastAlreadyExistsException(
                    "Le repas avec le nom '"+ repast.getNom() +"' est existe déjà!"
            );
        }

        return dtoMapper.mapToRepastDto(repastRepository.save(dtoMapper.mapToRepast(repastDto)));
    }
    @Override
    public RepastDto updateRepast(Long repastId, RepastDto repastDto) throws RepastIdNotFoundException {
        log.info("Mise à jour le repast avec le ID = {}", repastId);
        getRepastById(repastId);
        return dtoMapper.mapToRepastDto(repastRepository.save(dtoMapper.mapToRepast(repastDto)));
    }
    @Override
    public void deleterepast(Long repastId) throws RepastIdNotFoundException {
        getRepastById(repastId);
        repastRepository.deleteById(repastId);
    }

}
