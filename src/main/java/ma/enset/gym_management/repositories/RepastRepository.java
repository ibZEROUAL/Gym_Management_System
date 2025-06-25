package ma.enset.gym_management.repositories;

import ma.enset.gym_management.entities.Program;
import ma.enset.gym_management.entities.Repast;
import ma.enset.gym_management.enums.RepastObjective;
import ma.enset.gym_management.enums.RepastType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepastRepository extends JpaRepository<Repast,Long> {
     List<Repast> findByType(RepastType type);
     Repast findByNom(String nom);
     List<Repast> findByObjective(RepastObjective objective);
     List<Repast> findByTypeAndObjective(RepastType type, RepastObjective objective);
     List<Repast> findByPrograms(Program program);



}
