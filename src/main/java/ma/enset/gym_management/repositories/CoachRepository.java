package ma.enset.gym_management.repositories;

import ma.enset.gym_management.dto.ProgramDto;
import ma.enset.gym_management.entities.Coach;
import ma.enset.gym_management.entities.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoachRepository extends JpaRepository<Coach ,Long> {
    List<Coach> findBySpecialite(String speciality);
    Coach findByUsername(String username);
}
