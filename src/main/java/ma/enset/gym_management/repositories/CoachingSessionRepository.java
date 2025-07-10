package ma.enset.gym_management.repositories;

import ma.enset.gym_management.entities.CoachingSession;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDateTime;
import java.util.List;

public interface CoachingSessionRepository extends JpaRepository<CoachingSession, Long> {
    CoachingSession findByDateInscriptionSession(LocalDateTime dateInscriptionSession);
    List<CoachingSession> findByStatute(boolean statute);
    CoachingSession findByCoach_Id(Long id);
    CoachingSession findByAdherent_Id(Long id);
}
