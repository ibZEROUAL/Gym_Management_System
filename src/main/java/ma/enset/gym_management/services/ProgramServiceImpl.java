package ma.enset.gym_management.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.gym_management.dto.*;
import ma.enset.gym_management.entities.Coach;
import ma.enset.gym_management.entities.Exercise;
import ma.enset.gym_management.entities.Repast;
import ma.enset.gym_management.enums.*;
import ma.enset.gym_management.entities.Program;
import ma.enset.gym_management.exceptions.*;
import ma.enset.gym_management.mappers.ProgramMapperImpl;
import ma.enset.gym_management.repositories.CoachRepository;
import ma.enset.gym_management.repositories.ExerciseRepository;
import ma.enset.gym_management.repositories.ProgramRepository;
import ma.enset.gym_management.repositories.RepastRepository;
import org.springframework.beans.BeanUtils;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;
    private final ExerciseRepository exerciseRepository;
    private final RepastRepository repastRepository;
    private final CoachRepository coachRepository;
    private final ProgramMapperImpl dtoMapper;



    //Logger log= LoggerFactory.getLogger(this.getClass().getName());


    @Override
    public List<ProgramResponseDto> allPrograms() throws ProgramsNotFoundException {
        log.info("Recherche des programmes ");

        List<ProgramResponseDto> programResponseDto = programRepository.findAll()
                .stream()
                .map(dtoMapper::mapToProgramRespDto)
                .toList();
        if (programResponseDto.isEmpty()){
            log.warn("Aucun programme trouvé");
            throw new ProgramsNotFoundException("Programmes non trouvé");
        }
        return programResponseDto;
    }
    @Override
    public ProgramResponseDto getProgramByID(Long id) throws ProgramIdNotFoundException {
        log.info("Recherche des programmes avec le Id = {}", id);

        Program program = programRepository.findById(id).orElseThrow(()->
                new ProgramIdNotFoundException("Programme avec le ID '"+id+"' non trouvé"));
        return dtoMapper.mapToProgramRespDto(program);
    }
    @Override
    public ProgramResponseDto getProgramByNom(String nom) throws ProgramNameNotFoundException {
        log.info("Recherche des programmes avec le nom = {}", nom);
        Program program= programRepository.findByNom(nom);
        if (program==null) {
            log.warn("Aucun programme trouvé avec le nom = {}", nom);
            throw new ProgramNameNotFoundException("Programme avec le nom '"+nom+"' non trouvé");
        }
        return dtoMapper.mapToProgramRespDto(program);
    }
    @Override
    public List<ProgramResponseDto> getProgramByLevel(ProgramLevel level) throws ProgramsOfLevelNotFoundException {
        log.info("Recherche des programmes avec level = {}", level);
        List<ProgramResponseDto> programResponseDto= programRepository.findByNiveau(level)
                .stream()
                .map(dtoMapper::mapToProgramRespDto)
                .collect(Collectors.toList());
        if (programResponseDto.isEmpty()) {
            log.warn("Aucun programme trouvé avec level = {}", level);
            throw new ProgramsOfLevelNotFoundException(
                    "Programmes avec le niveau '"+level+"' non trouvé"
            );
        }
        return programResponseDto;
    }

    @Override
    public List<ProgramResponseDto> getProgramByVisibility(boolean visible) throws ProgramsByVisibilityNotFoundException {
        log.info("Recherche des programmes avec visibilité = {}", visible);
        List<ProgramResponseDto> programResponseDto= programRepository.findByVisibilite(visible)
                .stream()
                .map(dtoMapper::mapToProgramRespDto)
                .collect(Collectors.toList());
        if (programResponseDto.isEmpty()) {
            log.warn("Aucun programme trouvé avec visibilité = {}", visible);
            throw new ProgramsByVisibilityNotFoundException(
                    "Aucun programme trouvé avec la visibilité : " + visible
            );
        }
        return programResponseDto;
    }

    /* @Override
     public List<ProgramDto> getProgramByType(ProgramType type) throws ProgramsByTypeNotFoundException {
         log.info("Recherche des programmes avec le type = {}", type);
         List<ProgramDto> programs= programRepository.findByType(type)
                 .stream()
                 .map(dtoMapper::mapToProgramDto)
                 .collect(Collectors.toList());
         if (programs.isEmpty()) {
             log.warn("Aucun programme trouvé avec le type = {}", type);
             throw new ProgramsByTypeNotFoundException(
                     "Aucun programme trouvé avec le type : " + type
             );
         }
         return program
     }
     /* @Override
     public List<ProgramDto> getProgramByTypeAndLevel(ProgramType type, ProgramLevel level) throws ProgramsByTypeAndLevelNotFoundException {
             log.info("Recherche des programmes avec le type = {} et le niveau = {}", type, level);

             List<ProgramDto> programs = programRepository.findByTypeAndNiveau(type, level)
                     .stream()
                     .map(dtoMapper::mapToProgramDto)
                     .collect(Collectors.toList());

             if (programs.isEmpty()) {
                 log.warn("Aucun programme trouvé avec le type = {} et le niveau = {}", type, level);
                 throw new ProgramsByTypeAndLevelNotFoundException(
                         "Aucun programme trouvé avec le type : " + type + " et le niveau : " + level
                 );
             }
             return programs;
     }*/


    @Override
    public List<ProgramResponseDto> getListProgramsOfCoach(Long coachId) throws CoachIdNotFoundException, ListProgramsOfCoachNotFoundException {
        log.info("lister les programmes de coach avec l'ID = {}", coachId);

        Coach coach =coachRepository.findById(coachId).orElseThrow(()->
                new CoachIdNotFoundException("Coach avec le ID '" + coachId + "' non trouvé."));

        List<ProgramResponseDto> programResponseDto = programRepository.findByCoach(coach)
                .stream()
                .map(dtoMapper::mapToProgramRespDto)
                .toList();

        if (programResponseDto.isEmpty()){
            log.warn("Aucun programmes trouvé pour le coach '{}' ", coach.getNom());
            throw new ListProgramsOfCoachNotFoundException("La liste des programmes du coach '"+coach.getNom()+"' est vide.");
        }
        return programResponseDto;
    }


    @Override
    public ProgramResponseDto createProgram(ProgramDto programDto) throws ProgramAlreadyExistsException {
        log.info("Saving new program");
        Program program =programRepository.findByNom(programDto.getNom());
        if (program != null) {
            throw new ProgramAlreadyExistsException(
                    "Le '"+ programDto.getNom() +"' est existe déjà!"
            );
        }
        Program newProgram= programRepository.save(dtoMapper.mapDtoToProgram(programDto));
        return dtoMapper.mapToProgramRespDto(newProgram);
    }
    @Override
    public ProgramResponseDto updateProgram(Long programId, ProgramDto programDto) throws ProgramIdNotFoundException {
        log.info("Mise à jour du programme avec le ID = {}", programId);
        Program program = dtoMapper.mapRespToProgram(getProgramByID(programId));
        BeanUtils.copyProperties(programDto ,program);
        Program programUpdate= programRepository.save(program);
        return dtoMapper.mapToProgramRespDto(programUpdate);
    }
    @Override
    public void deleteProgram(Long programId) throws ProgramIdNotFoundException {
        log.info("Supprimé le programme avec le ID = {}", programId);
        programRepository.deleteById(programId);
    }
    @Override
    public ProgramResponseDto addExerciseToProgram(Long programId, Long exerciseId) throws ProgramIdNotFoundException, ExerciseIdNotFoundException, ExerciseAlreadyAssignedToProgramException, ListExercisesOfProgramNotFoundException {
        log.info("Ajouter d'un repas (ID = {}) au programme (ID = {})", exerciseId, programId);
        Program program =dtoMapper.mapRespToProgram(getProgramByID(programId));

        Exercise exercise =exerciseRepository.findById(exerciseId).orElseThrow(()->
                new ExerciseIdNotFoundException("Exercise avec le ID '"+exerciseId+"' non trouvé"));
        /*  List<Exercise> exercisesOfProgram = exerciseService.listExercisesOfProgram(programId)
                .stream()
                .map(dtoMapper::mapRespToExercise)
                .toList();
        */
        List<Exercise> exercisesOfProgram =program.getExercises().stream().toList();
        if(exercisesOfProgram.contains(exercise)){
            log.warn("L'exercice avec le nom '{}' est déjà associé à ce programme '{}'.",exercise.getNom(),program.getNom());
            throw new ExerciseAlreadyAssignedToProgramException(
                    "L'exercice avec le nom "+exercise.getNom()+" est déjà associé à ce programme "+program.getNom()+"."
            );
        }

         program.getExercises().add(exercise);
         exercise.getPrograms().add(program);

         exerciseRepository.save(exercise);
         Program savedProgram = programRepository.save(program);

        log.info("Exercise '{}' ajouté au programme '{}'", exercise.getNom(), savedProgram.getNom());
        return dtoMapper.mapToProgramRespDto(savedProgram);
    }

    @Override
    public ProgramResponseDto addRepastToProgram(Long programId, Long repastId) throws ProgramIdNotFoundException, RepastIdNotFoundException, RepastAlreadyAssignedToProgramException {
        log.info("Ajout d'un repas (ID = {}) au programme (ID = {})", repastId, programId);
        Program program =dtoMapper.mapRespToProgram(getProgramByID(programId));

        Repast repast =repastRepository.findById(repastId).orElseThrow(()->
                new RepastIdNotFoundException("Repast avec le ID '"+repastId+"' non trouvé"));

        List<Repast> repastsOfProgram = program.getRepasts().stream().toList();
        if(repastsOfProgram.contains(repast)){
            log.warn("Le repas '{}' est déjà associé au programme '{}'",repast.getNom(),program.getNom());
            throw new RepastAlreadyAssignedToProgramException(
                    "Le repas '"+repast.getNom()+"' est déjà associé au programme '"+program.getNom()+"'."
            );
        }

        program.getRepasts().add(repast);
        repast.getPrograms().add(program);

        repastRepository.save(repast);
        Program savedProgram = programRepository.save(program);

        log.info("Repas '{}' ajouté au programme '{}'", repast.getNom(), savedProgram.getNom());
        return dtoMapper.mapToProgramRespDto(savedProgram);
    }

}
