package ma.enset.gym_management.services;

import lombok.extern.slf4j.Slf4j;
import ma.enset.gym_management.dto.ExerciseDto;
import ma.enset.gym_management.entities.Exercise;
import ma.enset.gym_management.enums.ExerciseCategorie;
import ma.enset.gym_management.exceptions.*;
import ma.enset.gym_management.mappers.ProgramMapperImpl;
import ma.enset.gym_management.repositories.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ProgramMapperImpl dtoMapper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ProgramMapperImpl dtoMapper) {
        this.exerciseRepository = exerciseRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<ExerciseDto> allExercises() throws ExercisesNotFoundException {
        log.info("Recherche de tous les exercices ");
        List<ExerciseDto> exercises = exerciseRepository.findAll()
                .stream()
                .map(dtoMapper:: mapToExerciseDto)
                .toList();
        if (exercises.isEmpty()){
            log.warn("Aucun exercice trouvé");
            throw new ExercisesNotFoundException("Exercices non trouvé");
        }
        return exercises;
    }
    @Override
    public ExerciseDto getExerciseByID(Long id) throws ExerciseIdNotFoundException {
        log.info("Recherche d'exercice avec le Id ={} ",id);
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(()->
                new ExerciseIdNotFoundException("Exercise avec le ID '"+id+"' non trouvé"));
        return dtoMapper.mapToExerciseDto(exercise);
    }
    @Override
    public ExerciseDto getExerciseByNom(String nom) throws ExerciseNameNotFoundException {
        log.info("Recherche d'exercice avec le nom ={} ",nom);
        Exercise exercise= exerciseRepository.findByNom(nom);
        if (exercise==null) {
            log.warn("Aucun exercice trouvé avec le nom ={} ",nom);
            throw new ExerciseNameNotFoundException("Exercice avec le nom '"+nom+"' non trouvé");
        }
        return dtoMapper.mapToExerciseDto(exercise);
    }
    @Override
    public List<ExerciseDto> getExercisesByCategory(ExerciseCategorie category) throws ExercisesByCategorieNotFoundException {
        log.info("Recherche d'exercice avec le categorie ={} ",category);
        List<ExerciseDto> exercise = exerciseRepository.findByCategorie(category)
                .stream()
                .map(dtoMapper::mapToExerciseDto)
                .collect(Collectors.toList());
        if (exercise.isEmpty()) {
            log.warn("Aucun exercice trouvé de set categorie '{}'", category);
            throw new ExercisesByCategorieNotFoundException(
                    "Aucun exercice trouvé de ste categorie :" +category
            );
        }
        return exercise;
    }
    @Override
    public ExerciseDto createExercise(ExerciseDto exerciseDto) throws ExerciseAlreadyExistsException {
        log.info("Recherche a un exercice avec le nom ={} ", exerciseDto.getNom());

        Exercise exercise =exerciseRepository.findByNom(exerciseDto.getNom());
        if (exercise != null){
            log.warn("L'exercice avec le nom '{}' est existe déjà! ", exerciseDto.getNom());
            throw new ExerciseAlreadyExistsException(
                    "L'exercice '"+ exerciseDto.getNom() +"' est existe déjà!"
            );
        }

        return dtoMapper.mapToExerciseDto(exerciseRepository.save(dtoMapper.mapToExercise(exerciseDto)));
    }
    @Override
    public ExerciseDto updateExercise(Long exerciseId, ExerciseDto exerciseDto) throws ExerciseIdNotFoundException {
        log.info("Mise à jour d'exercice avec le ID = {}", exerciseId);
        getExerciseByID(exerciseId);
        return dtoMapper.mapToExerciseDto(exerciseRepository.save(dtoMapper.mapToExercise(exerciseDto)));
    }
    @Override
    public void deleteExercise(Long exerciseId) throws ExerciseIdNotFoundException {
        getExerciseByID(exerciseId);
        exerciseRepository.deleteById(exerciseId);
    }

}