package ma.enset.gym_management.services;

import ma.enset.gym_management.dto.ExerciseDto;
import ma.enset.gym_management.dto.ProgramDto;
import ma.enset.gym_management.dto.ProgramResponseDto;
import ma.enset.gym_management.dto.RepastDto;
import ma.enset.gym_management.enums.*;
import ma.enset.gym_management.exceptions.*;

import java.util.List;

public interface ProgramService {
    List<ProgramResponseDto> allPrograms() throws ProgramsNotFoundException;
    ProgramResponseDto getProgramByID(Long id) throws ProgramIdNotFoundException;

    ProgramResponseDto getProgramByNom(String nom) throws ProgramNameNotFoundException;
    List<ProgramResponseDto> getProgramByLevel(ProgramLevel level) throws ProgramsOfLevelNotFoundException;
    List<ProgramResponseDto> getProgramByVisibility(boolean visible) throws ProgramsByVisibilityNotFoundException;
    //List<ProgramDto> getProgramByType(ProgramType type) throws ProgramsByTypeNotFoundException;
    //List<ProgramDto> getProgramByTypeAndLevel(ProgramType type, ProgramLevel niveau) throws ProgramsByTypeAndLevelNotFoundException;

    List<ProgramResponseDto> getListProgramsOfCoach(Long coachId) throws CoachIdNotFoundException, ListProgramsOfCoachNotFoundException;


    ProgramResponseDto createProgram(ProgramDto programDto) throws ProgramAlreadyExistsException;

    ProgramResponseDto updateProgram(Long programId, ProgramDto programDto) throws ProgramIdNotFoundException;

    void deleteProgram(Long programId) throws ProgramIdNotFoundException;
    ProgramResponseDto addExerciseToProgram(Long programId, Long exerciseId) throws ProgramIdNotFoundException, ExerciseIdNotFoundException, ExerciseAlreadyAssignedToProgramException, ListExercisesOfProgramNotFoundException;

    ProgramResponseDto addRepastToProgram(Long programId, Long repastId) throws ProgramIdNotFoundException, RepastIdNotFoundException, RepastAlreadyAssignedToProgramException;
}
