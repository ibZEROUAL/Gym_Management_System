package ma.enset.gym_management.services;

import ma.enset.gym_management.dto.ExerciseDto;
import ma.enset.gym_management.dto.ProgramDto;
import ma.enset.gym_management.dto.RepastDto;
import ma.enset.gym_management.enums.*;
import ma.enset.gym_management.exceptions.*;

import java.util.List;

public interface ProgramService {
    List<ProgramDto> allPrograms() throws ProgramsNotFoundException;
    ProgramDto getProgramByID(Long id) throws ProgramIdNotFoundException;

    ProgramDto getProgramByNom(String nom) throws ProgramNameNotFoundException;
    List<ProgramDto> getProgramByLevel(ProgramLevel level) throws ProgramsOfLevelNotFoundException;
    List<ProgramDto> getProgramByVisibility(boolean visible) throws ProgramsByVisibilityNotFoundException;
    List<ProgramDto> getProgramByType(ProgramType type) throws ProgramsByTypeNotFoundException;
    List<ProgramDto> getProgramByTypeAndLevel(ProgramType type, ProgramLevel niveau) throws ProgramsByTypeAndLevelNotFoundException;

    List<ExerciseDto> listExercisesOfProgram(Long program_id) throws ProgramIdNotFoundException;

    List<RepastDto> listRepastsOfProgram(Long programId) throws ProgramIdNotFoundException;

    ProgramDto createProgram(ProgramDto programDto) throws ProgramAlreadyExistsException;

    ProgramDto updateProgram(Long programId, ProgramDto programDto) throws ProgramIdNotFoundException;

    void deleteProgram(Long programId) throws ProgramIdNotFoundException;
    ProgramDto addExerciseToProgram(Long programId, Long exerciseId) throws ProgramIdNotFoundException, ExerciseIdNotFoundException, ExerciseAlreadyAssignedToProgramException, ListExercisesOfProgramNotFoundException;


    ProgramDto addRepastToProgram(Long programId, Long repastId) throws ProgramIdNotFoundException, RepastIdNotFoundException, RepastAlreadyAssignedToProgramException;
}
