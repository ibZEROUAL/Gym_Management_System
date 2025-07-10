package ma.enset.gym_management.repositories;

import ma.enset.gym_management.entities.ProgressTracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgressTrackingRepository extends JpaRepository<ProgressTracking, Long> {
    List<ProgressTracking> findByAdherentId(Long adherentId);

}
