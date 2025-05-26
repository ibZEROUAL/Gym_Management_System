package ma.enset.gym_management.services;

import ma.enset.gym_management.dto.ExerciseDto;
import ma.enset.gym_management.enums.ExerciseCategorie;
import ma.enset.gym_management.exceptions.*;

import java.util.List;

public interface ExerciseService {
    List<ExerciseDto> allExercises() throws ExercisesNotFoundException;


    ExerciseDto getExerciseByNom(String nom) throws ExerciseNameNotFoundException;


    ExerciseDto getExerciseByID(Long id) throws ExerciseIdNotFoundException;

    List<ExerciseDto> getExercisesByCategory(ExerciseCategorie category)throws ExercisesByCategorieNotFoundException;

    ExerciseDto createExercise(ExerciseDto exerciseDto) throws ExerciseAlreadyExistsException;

    ExerciseDto updateExercise(Long exerciseId, ExerciseDto exerciseDto) throws ExerciseIdNotFoundException;

    void deleteExercise(Long exerciseId) throws ExerciseIdNotFoundException;
}
