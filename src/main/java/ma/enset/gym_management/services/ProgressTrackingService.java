package ma.enset.gym_management.services;

import ma.enset.gym_management.dto.ProgressTrackingDto;
import ma.enset.gym_management.dto.ProgressTrackingResponseDto;
import ma.enset.gym_management.exceptions.AdherentIdNotFoundException;
import ma.enset.gym_management.exceptions.ProgressTrackingIdNotFoundException;

import java.util.List;

public interface ProgressTrackingService {
    ProgressTrackingResponseDto save(ProgressTrackingDto progressTrackingDto) throws AdherentIdNotFoundException;
    List<ProgressTrackingResponseDto> findByAdherentId(Long adherentId);
    ProgressTrackingResponseDto getById(Long id) throws ProgressTrackingIdNotFoundException;
    void delete(Long id);
}
