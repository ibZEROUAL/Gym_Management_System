package ma.enset.gym_management.services;

import ma.enset.gym_management.dto.RegistrationProgramResponseDto;
import ma.enset.gym_management.exceptions.*;

import java.time.LocalDateTime;
import java.util.List;

public interface RegistrationProgramService {
    List<RegistrationProgramResponseDto> AllRegistrations();
    RegistrationProgramResponseDto getRegistrationsById(Long id) throws RegistrationProgramIdNotFoundException;
    RegistrationProgramResponseDto getRegistrationByRegisteredAt(LocalDateTime date) throws RegistrationProgramDateNotFoundException;
    List<RegistrationProgramResponseDto> getRegistrationByAdherent(Long adherentId) throws AdherentIdNotFoundException, RegistrationOfAdherentNotFoundException;
    List<RegistrationProgramResponseDto> getRegistrationByProgram(Long programID) throws ProgramIdNotFoundException, RegistrationOfProgramtNotFoundException;

    RegistrationProgramResponseDto registrationInProgram(LocalDateTime registeredAt, String adherentDtoUserName, String programDtoName) throws AdherentEmailNotFoundException, ProgramNameNotFoundException;

    void deleteRegistration(Long id);
}

