package ma.enset.gym_management.services;

import ma.enset.gym_management.dto.CoachDto;
import ma.enset.gym_management.dto.CoachResponseDto;
import ma.enset.gym_management.dto.ProgramDto;
import ma.enset.gym_management.dto.ProgramResponseDto;
import ma.enset.gym_management.exceptions.*;

import java.util.List;


public interface CoachService {
    List<CoachResponseDto> AllCoach() throws CoachsNotFoundException;
    CoachResponseDto getCoachByID(Long id) throws CoachIdNotFoundException;

    CoachResponseDto getCoachByUserName(String userName) throws CoachNameNotFoundException;

    List<CoachResponseDto> getCoachBySpeciality(String speciality) throws CoachSpecialityNotFoundException;

    CoachResponseDto addCoach(CoachDto coachDto) throws CoachAlreadyExistsException;

    CoachResponseDto updateCoach(long coachId, CoachDto coachDto) throws CoachIdNotFoundException;

    void deleteCoch(Long coachId);
}
