package ma.enset.gym_management.repositories;

import ma.enset.gym_management.entities.Subscription;
import ma.enset.gym_management.enums.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByType(SubscriptionType type);
}
