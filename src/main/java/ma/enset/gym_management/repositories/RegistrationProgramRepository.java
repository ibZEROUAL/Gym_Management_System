package ma.enset.gym_management.repositories;


import ma.enset.gym_management.entities.RegistrationProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface RegistrationProgramRepository extends JpaRepository<RegistrationProgram,Long> {
    List<RegistrationProgram>findByAdherent_Id(Long adherentId);
    List<RegistrationProgram>findByProgram_Id(Long programId);
    RegistrationProgram findByRegisteredAt(LocalDateTime date);
}
