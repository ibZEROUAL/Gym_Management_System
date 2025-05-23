package ma.enset.gym_management.services;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import ma.enset.gym_management.dto.ExerciseDto;
import ma.enset.gym_management.dto.ProgramDto;
import ma.enset.gym_management.entities.Exercise;
import ma.enset.gym_management.enums.ProgramLevel;
import ma.enset.gym_management.enums.ProgramType;
import ma.enset.gym_management.entities.Program;
import ma.enset.gym_management.exceptions.*;
import ma.enset.gym_management.repositories.ExerciseRepository;
import ma.enset.gym_management.repositories.ProgramRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;
    private final ExerciseRepository exerciseRepository;

    //Logger log= LoggerFactory.getLogger(this.getClass().getName());


    public ProgramServiceImpl(ProgramRepository programRepository, ExerciseRepository exerciseRepository) {
        this.programRepository = programRepository;
        this.exerciseRepository = exerciseRepository;
    }
    @Override
    public List<ProgramDto> allPrograms() throws ProgramsNotFoundException {
        List<ProgramDto> programs = programRepository.findAll()
                .stream()
                .map(this::mapToProgramDto)
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
        return mapToProgramDto(program);
    }
    @Override
    public ProgramDto getProgramByNom(String nom) throws ProgramNameNotFoundException {
        Program program= programRepository.findByNom(nom);
        if (program==null) {
            throw new ProgramNameNotFoundException("Programme avec le nom '"+nom+"' non trouvé");
        }
        return mapToProgramDto(program);
    }
    @Override
    public List<ProgramDto> getProgramByLevel(ProgramLevel level) throws ProgramsOfLevelNotFoundException {
        List<ProgramDto> program= programRepository.findByNiveau(level)
                .stream()
                .map(this::mapToProgramDto)
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
                .map(this::mapToProgramDto)
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
                .map(this::mapToProgramDto)
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
                    .map(this::mapToProgramDto)
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
    public List<ExerciseDto> listExercisesOfProgram(Long programId) throws ProgramIdNotFoundException, ListExercisesOfProgramNotFoundException {
        log.info("Recherche des exercices pour le programme avec l'ID = {}", programId);
        ProgramDto programDto = getProgramByID(programId);

        List<ExerciseDto> exercisesOfProgram = exerciseRepository.findByPrograms(mapToProgram(programDto))
                .stream()
                .map(this::mapToExerciseDto)
                .collect(Collectors.toList());

        if (exercisesOfProgram.isEmpty()){
            log.warn("Aucun exercice trouvé pour le programme '{}'", programDto.getNom());
            throw new ListExercisesOfProgramNotFoundException(
                    "La liste des exercices du programme '" +programDto.getNom()+ "' est vide."
            );
        }
        return exercisesOfProgram;
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
        Program newProgram= programRepository.save(mapToProgram(programDto));
        return mapToProgramDto(newProgram);
    }
    @Override
    public ProgramDto updateProgram(Long programId) {
        return null;
    }
    @Override
    public ProgramDto deleteProgram(Long programId) {
        return null;
    }
    @Override
    public ProgramDto addExerciseToProgram(Long programId, Long exerciseId) throws ProgramIdNotFoundException, ExerciseIdNotFoundException, ExerciseAlreadyAssignedToProgramException, ListExercisesOfProgramNotFoundException {
        Program program = mapToProgram(getProgramByID(programId));

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(()-> new ExerciseIdNotFoundException("Exercice avec le ID "+exerciseId+" non trouvé"));

        List<Exercise> exercisesOfProgram = listExercisesOfProgram(programId)
                .stream()
                .map(this::mapToExercise)
                .toList();

        if(exercisesOfProgram.contains(exercise)){
            throw new ExerciseAlreadyAssignedToProgramException("L'exercice avec le nom "+exercise.getNom()+" est déjà associé à ce programme "+program.getNom()+".");
        }

         program.getExercises().add(exercise);
         exercise.getPrograms().add(program);

         exerciseRepository.save(exercise);
         Program savedProgram= programRepository.save(program);

        return mapToProgramDto(savedProgram);
    }



    private Program mapToProgram(ProgramDto programDto){
        Program program = new Program();
        BeanUtils.copyProperties(programDto,program);
        return program;
    }
    private ProgramDto mapToProgramDto(Program program){
        ProgramDto programDto = new ProgramDto();
        BeanUtils.copyProperties(program,programDto);
        programDto.setExerciseDto(program.getExercises().stream().map(this::mapToExerciseDto).collect(Collectors.toList()));
        return programDto;
    }
    private ExerciseDto mapToExerciseDto(Exercise exercise){
        ExerciseDto exerciseDto= new ExerciseDto();
        BeanUtils.copyProperties(exercise, exerciseDto);
        return exerciseDto;
    }
    private Exercise mapToExercise(ExerciseDto exerciseDto){
        Exercise exercise= new Exercise();
        BeanUtils.copyProperties(exerciseDto, exercise);
        return exercise;
    }
}
