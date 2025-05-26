package ma.enset.gym_management.repositories;

import ma.enset.gym_management.entities.Exercise;
import ma.enset.gym_management.entities.Program;
import ma.enset.gym_management.enums.ExerciseCategorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise,Long>{

    Exercise findByNom(String nom);
    List<Exercise> findByCategorie(ExerciseCategorie category);
    List<Exercise> findByPrograms(Program programs);
}
