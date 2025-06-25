package ma.enset.gym_management.services;

import ma.enset.gym_management.dto.AdherentDto;
import ma.enset.gym_management.dto.ProgramDto;
import ma.enset.gym_management.dto.RegistrationProgramResponseDto;
import ma.enset.gym_management.entities.Adherent;
import ma.enset.gym_management.entities.Program;
import ma.enset.gym_management.entities.RegistrationProgram;
import ma.enset.gym_management.exceptions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface RegistrationProgramService {
    List<RegistrationProgramResponseDto> AllRegistrations();
    RegistrationProgramResponseDto getRegistrationsById(Long id) throws RegistrationProgramIdNotFoundException;
    RegistrationProgramResponseDto getRegistrationByRegisteredAt(LocalDateTime date) throws RegistrationProgramDateNotFoundException;
    List<RegistrationProgramResponseDto> getRegistrationByAdherent(Long adherentId) throws AdherentIdNotFoundException, RegistrationOfAdherentNotFoundException;
    List<RegistrationProgramResponseDto> getRegistrationByProgram(Long programID) throws ProgramIdNotFoundException, RegistrationOfProgramtNotFoundException;

    RegistrationProgramResponseDto registrationInProgram(LocalDateTime registeredAt, String adherentDtoUserName, String programDtoName) throws AdherentUserNameNotFoundException, ProgramNameNotFoundException;

    void deleteRegistration(Long id);
}

