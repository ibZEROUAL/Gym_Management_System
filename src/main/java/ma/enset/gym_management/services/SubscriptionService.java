package ma.enset.gym_management.services;

import ma.enset.gym_management.dto.SubscriptionDto;
import ma.enset.gym_management.dto.SubscriptionResponseDto;
import ma.enset.gym_management.enums.SubscriptionType;
import ma.enset.gym_management.exceptions.AdherentIdNotFoundException;
import ma.enset.gym_management.exceptions.SubscriptionIdNotFoundException;
import ma.enset.gym_management.exceptions.SubscriptionTypeNotFoundException;

import java.util.List;

public interface SubscriptionService {
    SubscriptionResponseDto save(SubscriptionDto subscriptionDto);
    SubscriptionResponseDto update(Long id, SubscriptionDto dto) throws SubscriptionIdNotFoundException;
    void delete(Long id);
    SubscriptionResponseDto get(Long id) throws SubscriptionIdNotFoundException;
    List<SubscriptionResponseDto> getAll();
    List<SubscriptionResponseDto> getByType(SubscriptionType type) throws SubscriptionTypeNotFoundException;
}
