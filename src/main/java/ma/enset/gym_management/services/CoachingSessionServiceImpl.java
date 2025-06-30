package ma.enset.gym_management.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import ma.enset.gym_management.dto.CoachingSessionResponseDto;
import ma.enset.gym_management.entities.Adherent;
import ma.enset.gym_management.entities.Coach;
import ma.enset.gym_management.entities.CoachingSession;
import ma.enset.gym_management.exceptions.*;
import ma.enset.gym_management.mappers.ProgramMapperImpl;
import ma.enset.gym_management.repositories.AdherentRepository;
import ma.enset.gym_management.repositories.CoachRepository;
import ma.enset.gym_management.repositories.CoachingSessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CoachingSessionServiceImpl implements CoachingSessionService {
    ProgramMapperImpl dtoMapper;
    AdherentRepository adherentRepository;
    CoachRepository coachRepository;
    CoachingSessionRepository coachingSessionRepository;
    @Override
    public List<CoachingSessionResponseDto> allSession() {
        log.info("Recherche des toutes séances de coaching" );
        return coachingSessionRepository.findAll()
                .stream()
                .map(dtoMapper::mapCoachingToCoachingRespDto)
                .toList();
    }

    @Override
    public CoachingSessionResponseDto getCoachingSessionById(Long id) throws CoachingSessionIdNoteFoundException {
        log.info("Recherche de séance de coaching avec le id= {}",id);
        CoachingSession coachingSession  = coachingSessionRepository.findById(id).orElseThrow(()->
                new CoachingSessionIdNoteFoundException("la séance de coaching avec le id= '"+id+"' non trouvé")
        );
        return dtoMapper.mapCoachingToCoachingRespDto(coachingSession);
    }

    @Override
    public CoachingSessionResponseDto getCoachingSessionByDate(LocalDateTime dateTime) throws CoachingSessionDateNoteFoundException {
        log.info("Recherche de séance de coaching avec la date= {}", dateTime);
        CoachingSession coachingSession = coachingSessionRepository.findByDateInscriptionSession(dateTime);
        if (coachingSession == null){
            log.warn("Aucune séance d'entraînement trouvée pour la  '{}'",dateTime);
            throw new CoachingSessionDateNoteFoundException("Aucune séance d'entraînement trouvée pour la "+dateTime);
        }
        return dtoMapper.mapCoachingToCoachingRespDto(coachingSession);
    }

    @Override
    public List<CoachingSessionResponseDto> getCoachingSessionByStatute(boolean statute) throws CoachingSessionStatuteNoteFoundException {
        log.info("Recherche de séance de coaching avec le statue= {}", statute);
        List<CoachingSession> coachingSession = coachingSessionRepository.findByStatute(statute);
        if (coachingSession.isEmpty()) {
            log.warn("Aucune séance d'entraînement trouvée avec le statue '{}' ",statute);
            throw new CoachingSessionStatuteNoteFoundException("Aucune séance d'entraînement trouvée avec le statue "+statute);
        }
        return coachingSession
                .stream()
                .map(dtoMapper::mapCoachingToCoachingRespDto)
                .collect(Collectors.toList());
    }
    @Override
    public CoachingSessionResponseDto reservationCoachingSession(LocalDateTime dateTime, String coachUserName, String adherentUserName) throws AdherentEmailNotFoundException, CoachEmailNotFoundException {

        log.info("reservation de l'adherent {} des séance d'entrainement",adherentUserName);

        Adherent adherent = adherentRepository.findByEmail(adherentUserName);
        if (adherent == null){
            log.warn("Aucune séance d'entraînement trouvée avec le userName {}: ",adherentUserName);
            throw new AdherentEmailNotFoundException("Aucune séance d'entraînement trouvée avec le userName: "+adherentUserName);
        }

        Coach coach = coachRepository.findByEmail(coachUserName);
        if (coach == null){
            log.warn("Aucune coache trouvée avec le userName {}: ",coachUserName);
            throw new CoachEmailNotFoundException("Aucune coach trouvée avec le userName: "+coachUserName);
        }

        CoachingSession coachingSession = new CoachingSession();
        coachingSession.setAdherent(adherent);
        coachingSession.setCoach(coach);
        coachingSession.setDateInscriptionSession(dateTime);

        CoachingSession newSession = coachingSessionRepository.save(coachingSession);

        return dtoMapper.mapCoachingToCoachingRespDto(newSession);
    }
}
