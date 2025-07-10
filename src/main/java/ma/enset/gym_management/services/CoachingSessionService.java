package ma.enset.gym_management.services;

import ma.enset.gym_management.dto.CoachingSessionResponseDto;
import ma.enset.gym_management.exceptions.*;

import java.time.LocalDateTime;
import java.util.List;

public interface CoachingSessionService {
    public List<CoachingSessionResponseDto> allSession();
    public CoachingSessionResponseDto getCoachingSessionById(Long id) throws CoachingSessionIdNoteFoundException;
    public CoachingSessionResponseDto getCoachingSessionByDate(LocalDateTime dateTime) throws CoachingSessionDateNoteFoundException;
    public List<CoachingSessionResponseDto> getCoachingSessionByStatute(boolean statute) throws CoachingSessionStatuteNoteFoundException;
    public CoachingSessionResponseDto reservationCoachingSession(LocalDateTime dateTime, String coachUserName,String adherentUserName) throws AdherentEmailNotFoundException, CoachEmailNotFoundException;
}
