package ma.enset.gym_management.repositories;

import ma.enset.gym_management.entities.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdherentRepository extends JpaRepository<Adherent, Long> {
    Adherent findByUsername(String username);
}
