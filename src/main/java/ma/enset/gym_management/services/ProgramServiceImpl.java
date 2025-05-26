package ma.enset.gym_management.services;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import ma.enset.gym_management.dto.ExerciseDto;
import ma.enset.gym_management.dto.ProgramDto;
import ma.enset.gym_management.dto.RepastDto;
import ma.enset.gym_management.entities.Exercise;
import ma.enset.gym_management.entities.Repast;
import ma.enset.gym_management.enums.*;
import ma.enset.gym_management.entities.Program;
import ma.enset.gym_management.exceptions.*;
import ma.enset.gym_management.mappers.ProgramMapperImpl;
import ma.enset.gym_management.repositories.ExerciseRepository;
import ma.enset.gym_management.repositories.ProgramRepository;
import ma.enset.gym_management.repositories.RepastRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;
    private final ExerciseRepository exerciseRepository;
    private final ProgramMapperImpl dtoMapper;
    private final RepastRepository repastRepository;
    private final ExerciseService exerciseService;
    private final RepastService repastService;


    //Logger log= LoggerFactory.getLogger(this.getClass().getName());


    public ProgramServiceImpl(ProgramRepository programRepository, ExerciseRepository exerciseRepository, ProgramMapperImpl dtoMapper, RepastRepository repastRepository, ExerciseService exerciseService, RepastService repastService) {
        this.programRepository = programRepository;
        this.exerciseRepository = exerciseRepository;
        this.dtoMapper = dtoMapper;
        this.repastRepository = repastRepository;
        this.exerciseService = exerciseService;
        this.repastService = repastService;
    }
    @Override
    public List<ProgramDto> allPrograms() throws ProgramsNotFoundException {
        List<ProgramDto> programs = programRepository.findAll()
                .stream()
                .map(dtoMapper::mapToProgramDto)
                .toList();
        if (programs.isEmpty()){
            throw new ProgramsNotFoundException("Programmes non trouvé");
        }
        return programs;
    }
    @Override
    public ProgramDto getProgramByID(Long id) throws ProgramIdNotFoundException {
        Program program = programRepository.findById(id).orElseThrow(()->
                new ProgramIdNotFoundException("Programme avec le ID '"+id+"' non trouvé"));
        return dtoMapper.mapToProgramDto(program);
    }
    @Override
    public ProgramDto getProgramByNom(String nom) throws ProgramNameNotFoundException {
        Program program= programRepository.findByNom(nom);
        if (program==null) {
            throw new ProgramNameNotFoundException("Programme avec le nom '"+nom+"' non trouvé");
        }
        return dtoMapper.mapToProgramDto(program);
    }
    @Override
    public List<ProgramDto> getProgramByLevel(ProgramLevel level) throws ProgramsOfLevelNotFoundException {
        List<ProgramDto> program= programRepository.findByNiveau(level)
                .stream()
                .map(dtoMapper::mapToProgramDto)
                .collect(Collectors.toList());
        if (program.isEmpty()) {
            throw new ProgramsOfLevelNotFoundException(
                    "Programmes avec le niveau '"+level+"' non trouvé"
            );
        }
        return program;
    }

    @Override
    public List<ProgramDto> getProgramByVisibility(boolean visible) throws ProgramsByVisibilityNotFoundException {
        log.info("Recherche des programmes avec visibilité = {}", visible);
        List<ProgramDto> programs= programRepository.findByVisibilite(visible)
                .stream()
                .map(dtoMapper::mapToProgramDto)
                .collect(Collectors.toList());
        if (programs.isEmpty()) {
            log.warn("Aucun programme trouvé avec visibilité = {}", visible);
            throw new ProgramsByVisibilityNotFoundException(
                    "Aucun programme trouvé avec la visibilité : " + visible
            );
        }
        return programs;
    }

    @Override
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
        return programs;
    }
    @Override
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
    }
    @Override
    public List<ExerciseDto> listExercisesOfProgram(Long programId) throws ProgramIdNotFoundException {
        log.info("lister les exercices de programme avec l'ID = {}", programId);
        ProgramDto programDto = getProgramByID(programId);

        List<ExerciseDto> exercisesOfProgram = exerciseRepository.findByPrograms(dtoMapper.mapToProgram(programDto))
                .stream()
                .map(dtoMapper::mapToExerciseDto)
                .collect(Collectors.toList());

        /*if (exercisesOfProgram.isEmpty()){
            log.warn("Aucun exercice trouvé pour le programme '{}'", programDto.getNom());
            throw new ListExercisesOfProgramNotFoundException(
                    "La liste des exercices du programme '" +programDto.getNom()+ "' est vide."
            );
        }*/
        return exercisesOfProgram;
    }

    @Override
    public List<RepastDto> listRepastsOfProgram(Long programId) throws ProgramIdNotFoundException {
        log.info("lister les repas de programme avec l'ID = {}", programId);
        ProgramDto programDto = getProgramByID(programId);

        List<RepastDto> repastOfProgram = repastRepository.findByPrograms(dtoMapper.mapToProgram(programDto))
                .stream()
                .map(dtoMapper::mapToRepastDto)
                .toList();

        return repastOfProgram;
    }

    @Override
    public ProgramDto createProgram(ProgramDto programDto) throws ProgramAlreadyExistsException {
        log.info("Saving new program");
        Program program =programRepository.findByNom(programDto.getNom());
        if (program != null) {
            throw new ProgramAlreadyExistsException(
                    "Le '"+ programDto.getNom() +"' est existe déjà!"
            );
        }
        Program newProgram= programRepository.save(dtoMapper.mapToProgram(programDto));
        return dtoMapper.mapToProgramDto(newProgram);
    }
    @Override
    public ProgramDto updateProgram(Long programId, ProgramDto programDto) throws ProgramIdNotFoundException {
        log.info("Mise à jour du programme avec le ID = {}", programId);
        getProgramByID(programId);
        Program program= programRepository.save(dtoMapper.mapToProgram(programDto));
        return dtoMapper.mapToProgramDto(program);
    }
    @Override
    public void deleteProgram(Long programId) throws ProgramIdNotFoundException {
        getProgramByID(programId);
        programRepository.deleteById(programId);
    }
    @Override
    public ProgramDto addExerciseToProgram(Long programId, Long exerciseId) throws ProgramIdNotFoundException, ExerciseIdNotFoundException, ExerciseAlreadyAssignedToProgramException, ListExercisesOfProgramNotFoundException {
        log.info("Ajout d'un repas (ID = {}) au programme (ID = {})", exerciseId, programId);
        Program program =dtoMapper.mapToProgram(getProgramByID(programId));

        Exercise exercise =dtoMapper.mapToExercise(exerciseService.getExerciseByID(exerciseId)) ;
        List<Exercise> exercisesOfProgram = listExercisesOfProgram(programId)
                .stream()
                .map(dtoMapper::mapToExercise)
                .toList();

        if(exercisesOfProgram.contains(exercise)){
            throw new ExerciseAlreadyAssignedToProgramException(
                    "L'exercice avec le nom "+exercise.getNom()+" est déjà associé à ce programme "+program.getNom()+"."
            );
        }

         program.getExercises().add(exercise);
         exercise.getPrograms().add(program);

         exerciseRepository.save(exercise);
         Program savedProgram = programRepository.save(program);

        log.info("Exercise '{}' ajouté au programme '{}'", exercise.getNom(), savedProgram.getNom());
        return dtoMapper.mapToProgramDto(savedProgram);
    }

    @Override
    public ProgramDto addRepastToProgram(Long programId, Long repastId) throws ProgramIdNotFoundException, RepastIdNotFoundException, RepastAlreadyAssignedToProgramException {
        log.info("Ajout d'un repas (ID = {}) au programme (ID = {})", repastId, programId);
        Program program =dtoMapper.mapToProgram(getProgramByID(programId));

        Repast repast =dtoMapper.mapToRepast(repastService.getRepastById(repastId));
        List<Repast> repastsOfProgram = listRepastsOfProgram(programId)
                .stream()
                .map(dtoMapper::mapToRepast)
                .toList();

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
        return dtoMapper.mapToProgramDto(savedProgram);
    }

}
