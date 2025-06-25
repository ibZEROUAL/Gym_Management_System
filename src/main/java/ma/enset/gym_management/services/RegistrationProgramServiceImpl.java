package ma.enset.gym_management.services;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import ma.enset.gym_management.dto.*;
import ma.enset.gym_management.entities.Adherent;
import ma.enset.gym_management.entities.Program;
import ma.enset.gym_management.entities.RegistrationProgram;
import ma.enset.gym_management.exceptions.*;
import ma.enset.gym_management.mappers.ProgramMapperImpl;
import ma.enset.gym_management.repositories.AdherentRepository;
import ma.enset.gym_management.repositories.ProgramRepository;
import ma.enset.gym_management.repositories.RegistrationProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Builder
@Slf4j
public class RegistrationProgramServiceImpl implements RegistrationProgramService {
    private final RegistrationProgramRepository registrationProgramRepository;
    private final ProgramMapperImpl dtoMapper;
    private final AdherentRepository adherentRepository;
    private final ProgramRepository programRepository;

    public RegistrationProgramServiceImpl(RegistrationProgramRepository registrationProgramRepository, ProgramMapperImpl dtoMapper,
                                          AdherentRepository adherentRepository, ProgramRepository programRepository) {
        this.registrationProgramRepository = registrationProgramRepository;
        this.dtoMapper = dtoMapper;
        this.adherentRepository = adherentRepository;
        this.programRepository = programRepository;
    }

    @Override
    public List<RegistrationProgramResponseDto> AllRegistrations() {
        log.info("Recherche tous les inscriptions ");
        return registrationProgramRepository.findAll()
                .stream()
                .map(dtoMapper::mapRegisToRegisRespDto)
                .collect(Collectors.toList());
    }

    @Override
    public RegistrationProgramResponseDto getRegistrationsById(Long id) throws RegistrationProgramIdNotFoundException {
        log.info("Recherche un inscription avec le id= {}", id);
        RegistrationProgram registrationProgram= registrationProgramRepository.findById(id).orElseThrow(
                ()->new RegistrationProgramIdNotFoundException("Registration avec le ID '"+id+"' non trouvé")
        );
        return dtoMapper.mapRegisToRegisRespDto(registrationProgram);
    }
    @Override
    public RegistrationProgramResponseDto getRegistrationByRegisteredAt(LocalDateTime date) throws RegistrationProgramDateNotFoundException {
        log.info("Recherche des inscriptions avec la date = {}", date);
        RegistrationProgram registrationProgram = registrationProgramRepository.findByRegisteredAt(date);
        if(registrationProgram == null){
            log.warn("La list des inscriptions pour la date '{}' est vide ",date);
            throw new RegistrationProgramDateNotFoundException("Aucun inscription pour la date'"+date+"' trouvé ");
        }

        return dtoMapper.mapRegisToRegisRespDto(registrationProgram);    }

    @Override
    public List<RegistrationProgramResponseDto> getRegistrationByAdherent(Long adherentId) throws AdherentIdNotFoundException, RegistrationOfAdherentNotFoundException {
        log.info("Recherche des inscription de l'adherent avec le Id= {}:",adherentId);

        Adherent adherent = adherentRepository.findById(adherentId).orElseThrow(()->
                new AdherentIdNotFoundException("Adherent avec le ID '"+adherentId+"' non trouvé "));

        List<RegistrationProgramResponseDto> registrationProgram = registrationProgramRepository.findByAdherent_Id(adherentId)
                .stream()
                .map(dtoMapper::mapRegisToRegisRespDto)
                .toList();

        if(registrationProgram.isEmpty()){
            log.warn("Aucun inscription pour le membre {} trouvé.",adherent.getUsername());
            throw new RegistrationOfAdherentNotFoundException("Aucun inscription pour le membre '"+adherent.getUsername()+"' trouvé.");
        }
        return registrationProgram;
    }
    @Override
    public List<RegistrationProgramResponseDto> getRegistrationByProgram(Long programID) throws ProgramIdNotFoundException, RegistrationOfProgramtNotFoundException {
        log.info("Recherche des inscription pour le programme avec le Id= {}:",programID);
        Program program = programRepository.findById(programID).orElseThrow(()->
                new ProgramIdNotFoundException("Programme avec le ID '"+programID+"' non trouvé"));

        List<RegistrationProgramResponseDto> registrationProgram = registrationProgramRepository.findByProgram_Id(programID)
                .stream()
                .map(dtoMapper::mapRegisToRegisRespDto)
                .collect(Collectors.toList());
        if(registrationProgram.isEmpty()){
            log.warn("Aucun inscription pour le programme {} trouvé.",program.getNom());
            throw new RegistrationOfProgramtNotFoundException("Aucun inscription pour le programme '"+program.getNom()+"' trouvé.");
        }
        return registrationProgram;
    }

    @Override
    public RegistrationProgramResponseDto registrationInProgram(LocalDateTime registeredAt, String adherentDtoUserName, String programDtoName) throws AdherentUserNameNotFoundException, ProgramNameNotFoundException {
        log.info("inscription  de l'adherent '{}' done le programme '{}'",adherentDtoUserName,programDtoName);

        Adherent adherent = adherentRepository.findByUsername(adherentDtoUserName);
        if (adherent==null){
            log.warn("Adherent avec userName '{}' non trouvé",adherentDtoUserName);
            throw new AdherentUserNameNotFoundException("Adherent avec userName '"+adherentDtoUserName+"' non trouvé ");
        }

        Program program =programRepository.findByNom(programDtoName);
        if (program==null) {
            log.warn("Aucun programme trouvé avec le nom = {}", programDtoName);
            throw new ProgramNameNotFoundException("Programme avec le nom '"+programDtoName+"' non trouvé");
        }

        RegistrationProgram registrationProgram = new RegistrationProgram();
        registrationProgram.setRegisteredAt(registeredAt);
        registrationProgram.setAdherent(adherent);
        registrationProgram.setProgram(program);
        registrationProgramRepository.save(registrationProgram);
        return dtoMapper.mapRegisToRegisRespDto(registrationProgram);
    }
    @Override
    public void deleteRegistration(Long id){
        log.info("supprimé l'inscription avec le id= {}",id);
        registrationProgramRepository.deleteById(id);
    }



}
