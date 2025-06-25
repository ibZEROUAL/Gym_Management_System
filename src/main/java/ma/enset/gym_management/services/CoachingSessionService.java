package ma.enset.gym_management.services;

import ma.enset.gym_management.dto.AdherentDto;
import ma.enset.gym_management.dto.CoachDto;
import ma.enset.gym_management.dto.CoachingSessionResponseDto;
import ma.enset.gym_management.entities.Coach;
import ma.enset.gym_management.entities.CoachingSession;
import ma.enset.gym_management.exceptions.*;

import java.time.LocalDateTime;
import java.util.List;

public interface CoachingSessionService {
    public List<CoachingSessionResponseDto> allSession();
    public CoachingSessionResponseDto getCoachingSessionById(Long id) throws CoachingSessionIdNoteFoundException;
    public CoachingSessionResponseDto getCoachingSessionByDate(LocalDateTime dateTime) throws CoachingSessionDateNoteFoundException;
    public List<CoachingSessionResponseDto> getCoachingSessionByStatute(String statute) throws CoachingSessionStatuteNoteFoundException;
    public CoachingSessionResponseDto reservationCoachingSession(LocalDateTime dateTime, String coachUserName,String adherentUserName) throws AdherentUserNameNotFoundException, CoachUserNameNotFoundException;
}
