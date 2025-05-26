package ma.enset.gym_management.repositories;

import ma.enset.gym_management.entities.Program;
import ma.enset.gym_management.enums.ProgramLevel;
import ma.enset.gym_management.enums.ProgramType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program,Long> {

    Program findByNom (String nom);
    List<Program> findByNiveau(ProgramLevel level);
    List<Program> findByVisibilite(boolean visible);
    List<Program> findByType(ProgramType Type);
    List<Program> findByTypeAndNiveau(ProgramType type, ProgramLevel level);

}
