package ma.enset.gym_management.services;

import lombok.extern.slf4j.Slf4j;
import ma.enset.gym_management.dto.ProgramResponseDto;
import ma.enset.gym_management.dto.RepastDto;
import ma.enset.gym_management.dto.RepastResponseDto;
import ma.enset.gym_management.entities.Program;
import ma.enset.gym_management.entities.Repast;
import ma.enset.gym_management.enums.RepastObjective;
import ma.enset.gym_management.enums.RepastType;
import ma.enset.gym_management.exceptions.*;
import ma.enset.gym_management.mappers.ProgramMapperImpl;
import ma.enset.gym_management.repositories.ProgramRepository;
import ma.enset.gym_management.repositories.RepastRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class RepastServiceImpl implements RepastService {
    private final RepastRepository repastRepository;
    private final ProgramRepository programRepository;
    private final ProgramMapperImpl dtoMapper;

    public RepastServiceImpl(RepastRepository repastRepository, ProgramMapperImpl dtoMapper, ProgramRepository programRepository) {
        this.repastRepository = repastRepository;
        this.dtoMapper = dtoMapper;
        this.programRepository = programRepository;
    }

    @Override
    public List<RepastResponseDto> allRepasts() throws RepastsNotFoundException {
        log.info("Recherche de tous les repas ");

        List<RepastResponseDto> repastResponseDto = repastRepository.findAll()
                .stream()
                .map(dtoMapper::mapToRepastRespDto)
                .toList();

        if (repastResponseDto.isEmpty()){
            log.warn("Aucun repas trouvé");
            throw new RepastsNotFoundException("Aucun repas disponible dans la base de données.");
        }
        return repastResponseDto;
    }
    @Override
    public RepastResponseDto getRepastById(Long id) throws RepastIdNotFoundException {
        log.info("Recherche de repas avec le Id ={} ",id);
        Repast repast = repastRepository.findById(id).orElseThrow(()->
                new RepastIdNotFoundException("Repast avec le ID '"+id+"' non trouvé"));
        return dtoMapper.mapToRepastRespDto(repast);
    }
    @Override
    public List<RepastResponseDto> getRepastByType(RepastType type) throws RepastByTypeNotFoundException {
        log.info("Recherche de raps avec le Type ={} ",type);
        List<RepastResponseDto> repastResponseDto = repastRepository.findByType(type)
                .stream()
                .map(dtoMapper::mapToRepastRespDto)
                .collect(Collectors.toList());

        if (repastResponseDto.isEmpty()) {
            log.warn("Aucun repas trouvé avec le Type ={} ",type);
            throw new RepastByTypeNotFoundException("Repas avec le type '"+type+"' non trouvé");
        }
        return repastResponseDto;
    }
    @Override
    public List<RepastResponseDto> getRepastByObjective(RepastObjective objective) throws RepastByObjectiveNotFoundException {
        log.info("Recherche des repas avec l'objectif ={} ",objective);
        List<RepastResponseDto> repastResponseDto = repastRepository.findByObjective(objective)
                .stream()
                .map(dtoMapper::mapToRepastRespDto)
                .collect(Collectors.toList());

        if (repastResponseDto.isEmpty()) {
            log.warn("Aucun repas trouvé avec l'objictive ={} ",objective);
            throw new RepastByObjectiveNotFoundException("Repas avec l'objictive '"+objective+"' non trouvé");
        }
        return repastResponseDto;
    }
    @Override
    public List<RepastResponseDto> getRepastByTypeAndObjective(RepastType type , RepastObjective objictive) throws RepastByTypeAndObjectiveNotFoundException {
        log.info("Recherche de rapas avec l'objictive ={} est le type ={} ",objictive,type);
        List<RepastResponseDto> repastResponseDto = repastRepository.findByTypeAndObjective(type, objictive)
                .stream()
                .map(dtoMapper::mapToRepastRespDto)
                .collect(Collectors.toList());

        if (repastResponseDto.isEmpty()) {
            log.warn("Aucun repas trouvé avec l'objictive ={} est le type ={} ",objictive,type);
            throw new RepastByTypeAndObjectiveNotFoundException("Repas avec l'objictive '"+objictive+"' est le type '"+type+"' non trouvé");
        }
        return repastResponseDto;
    }

    @Override
    public List<RepastResponseDto> listRepastsOfProgram(Long programId) throws ProgramIdNotFoundException {
        log.info("lister les repas de programme avec l'ID = {}", programId);
        Program program = programRepository.findById(programId).orElseThrow(()->
                new ProgramIdNotFoundException("Programme avec le ID '"+programId+"' non trouvé"));

        List<RepastResponseDto> repastOfProgram = repastRepository.findByPrograms(program)
                .stream()
                .map(dtoMapper::mapToRepastRespDto)
                .toList();

        return repastOfProgram;
    }

    @Override
    public RepastResponseDto createRepast(RepastDto repastDto) throws RepastAlreadyExistsException {
        log.info("Création d'un nouveau repas avec le nom = {}", repastDto.getNom());

        Repast repast =repastRepository.findByNom(repastDto.getNom());
        if (repast != null){
            log.warn("Le repas avec le nom '{}' est existe déjà! ", repast.getNom());
            throw new RepastAlreadyExistsException(
                    "Le repas avec le nom '"+ repast.getNom() +"' est existe déjà!"
            );
        }

        return dtoMapper.mapToRepastRespDto(repastRepository.save(dtoMapper.mapDtoToRepast(repastDto)));
    }
    @Override
    public RepastResponseDto updateRepast(Long repastId, RepastDto repastDto) throws RepastIdNotFoundException {
        log.info("Mise à jour le repast avec le ID = {}", repastId);
        Repast repast = dtoMapper.mapRespToRepast(getRepastById(repastId));
        BeanUtils.copyProperties(repastDto,repast);
        return dtoMapper.mapToRepastRespDto(repastRepository.save (repast));
    }
    @Override
    public void deleteRepast(Long repastId) throws RepastIdNotFoundException {
        log.info("supprimé le rapas avec le id= {}",repastId);
        repastRepository.deleteById(repastId);
    }

}
