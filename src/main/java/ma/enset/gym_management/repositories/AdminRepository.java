package ma.enset.gym_management.repositories;

import ma.enset.gym_management.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
