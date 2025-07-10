package ma.enset.gym_management.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.gym_management.dto.ExerciseDto;
import ma.enset.gym_management.dto.ExerciseResponseDto;
import ma.enset.gym_management.entities.Exercise;
import ma.enset.gym_management.entities.Program;
import ma.enset.gym_management.enums.ExerciseCategorie;
import ma.enset.gym_management.exceptions.*;
import ma.enset.gym_management.mappers.ProgramMapperImpl;
import ma.enset.gym_management.repositories.ExerciseRepository;
import ma.enset.gym_management.repositories.ProgramRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ProgramMapperImpl dtoMapper;
    private final ProgramRepository programRepository;


    @Override
    public List<ExerciseResponseDto> allExercises() throws ExercisesNotFoundException {
        log.info("Recherche de tous les exercices ");
        List<ExerciseResponseDto> exerciseResponseDto = exerciseRepository.findAll()
                .stream()
                .map(dtoMapper:: mapToExerciseRespDto)
                .toList();
        if (exerciseResponseDto.isEmpty()){
            log.warn("Aucun exercice trouvé");
            throw new ExercisesNotFoundException("Exercices non trouvé");
        }
        return exerciseResponseDto;
    }
    @Override
    public ExerciseResponseDto getExerciseByID(Long id) throws ExerciseIdNotFoundException {
        log.info("Recherche d'exercice avec le Id ={} ",id);
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(()->
                new ExerciseIdNotFoundException("Exercise avec le ID '"+id+"' non trouvé"));
        return dtoMapper.mapToExerciseRespDto(exercise);
    }
    @Override
    public ExerciseResponseDto getExerciseByNom(String nom) throws ExerciseNameNotFoundException {
        log.info("Recherche d'exercice avec le nom ={} ",nom);
        Exercise exercise= exerciseRepository.findByNom(nom);
        if (exercise==null) {
            log.warn("Aucun exercice trouvé avec le nom ={} ",nom);
            throw new ExerciseNameNotFoundException("Exercice avec le nom '"+nom+"' non trouvé");
        }
        return dtoMapper.mapToExerciseRespDto(exercise);
    }
    @Override
    public List<ExerciseResponseDto> getExercisesByCategory(ExerciseCategorie category) throws ExercisesByCategorieNotFoundException {
        log.info("Recherche d'exercice avec le categorie ={} ",category);
        List<ExerciseResponseDto> exerciseResponseDto = exerciseRepository.findByCategorie(category)
                .stream()
                .map(dtoMapper::mapToExerciseRespDto)
                .collect(Collectors.toList());
        if (exerciseResponseDto.isEmpty()) {
            log.warn("Aucun exercice trouvé de set categorie '{}'", category);
            throw new ExercisesByCategorieNotFoundException(
                    "Aucun exercice trouvé de ste categorie :" +category
            );
        }
        return exerciseResponseDto;
    }

    @Override
    public List<ExerciseResponseDto> listExercisesOfProgram(Long programId) throws ProgramIdNotFoundException, ListExercisesOfProgramNotFoundException {
        log.info("lister les exercices de programme avec l'ID = {}", programId);

        Program program = programRepository.findById(programId).orElseThrow(
                ()->new ProgramIdNotFoundException("Programme avec le ID '"+programId+"' non trouvé"));

        List<Exercise> exercisesOfProgram = exerciseRepository.findByPrograms_Id(programId);

        if (exercisesOfProgram.isEmpty()){
            log.warn("Aucun exercice trouvé pour le programme '{}'", program.getNom());
            throw new ListExercisesOfProgramNotFoundException(
                    "La liste des exercices du programme '" +program.getNom()+ "' est vide."
            );
        }

        return exercisesOfProgram.stream().map(dtoMapper::mapToExerciseRespDto).collect(Collectors.toList());
    }

    @Override
    public ExerciseResponseDto createExercise(ExerciseDto exerciseDto) throws ExerciseAlreadyExistsException {
        log.info("creé un exercice avec le nom ={} ", exerciseDto.getNom());

        Exercise exercise =exerciseRepository.findByNom(exerciseDto.getNom());
        if (exercise != null){
            log.warn("L'exercice avec le nom '{}' est existe déjà! ", exerciseDto.getNom());
            throw new ExerciseAlreadyExistsException(
                    "L'exercice '"+ exerciseDto.getNom() +"' est existe déjà!"
            );
        }

        return dtoMapper.mapToExerciseRespDto(exerciseRepository.save(dtoMapper.mapDtoToExercise(exerciseDto)));
    }
    @Override
    public ExerciseResponseDto updateExercise(Long exerciseId, ExerciseDto exerciseDto) throws ExerciseIdNotFoundException {
        log.info("Mise à jour d'exercice avec le ID = {}", exerciseId);
        Exercise exercise = dtoMapper.mapRespToExercise(getExerciseByID(exerciseId));
        BeanUtils.copyProperties(exerciseDto,exercise);

        return dtoMapper.mapToExerciseRespDto(exerciseRepository.save(exercise));
    }
    @Override
    public void deleteExercise(Long exerciseId) throws ExerciseIdNotFoundException {
        log.info("supprimé l'exercice avec le id ={}",exerciseId);
        exerciseRepository.deleteById(exerciseId);
    }

}