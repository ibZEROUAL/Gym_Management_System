package ma.enset.gym_management.services;

import ma.enset.gym_management.dto.ExerciseDto;
import ma.enset.gym_management.dto.ExerciseResponseDto;
import ma.enset.gym_management.enums.ExerciseCategorie;
import ma.enset.gym_management.exceptions.*;

import java.util.List;

public interface ExerciseService {
    List<ExerciseResponseDto> allExercises() throws ExercisesNotFoundException;


    ExerciseResponseDto getExerciseByNom(String nom) throws ExerciseNameNotFoundException;


    ExerciseResponseDto getExerciseByID(Long id) throws ExerciseIdNotFoundException;

    List<ExerciseResponseDto> getExercisesByCategory(ExerciseCategorie category)throws ExercisesByCategorieNotFoundException;

    List<ExerciseResponseDto> listExercisesOfProgram(Long program_id) throws ProgramIdNotFoundException, ListExercisesOfProgramNotFoundException;

    ExerciseResponseDto createExercise(ExerciseDto exerciseDto) throws ExerciseAlreadyExistsException;

    ExerciseResponseDto updateExercise(Long exerciseId, ExerciseDto exerciseDto) throws ExerciseIdNotFoundException;

    void deleteExercise(Long exerciseId) throws ExerciseIdNotFoundException;
}
