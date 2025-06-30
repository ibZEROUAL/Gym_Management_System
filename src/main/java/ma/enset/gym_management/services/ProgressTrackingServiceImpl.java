package ma.enset.gym_management.services;

import lombok.AllArgsConstructor;
import ma.enset.gym_management.dto.ProgressTrackingDto;
import ma.enset.gym_management.dto.ProgressTrackingResponseDto;
import ma.enset.gym_management.entities.Adherent;
import ma.enset.gym_management.entities.ProgressTracking;
import ma.enset.gym_management.exceptions.AdherentIdNotFoundException;
import ma.enset.gym_management.exceptions.ProgressTrackingIdNotFoundException;
import ma.enset.gym_management.mappers.ProgramMapperImpl;
import ma.enset.gym_management.repositories.AdherentRepository;
import ma.enset.gym_management.repositories.ProgressTrackingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class ProgressTrackingServiceImpl implements ProgressTrackingService {

    private final ProgressTrackingRepository trackingRepository;
    private final AdherentRepository adherentRepository;
    private final ProgramMapperImpl dtoMapper;


    @Override
    public ProgressTrackingResponseDto save(ProgressTrackingDto trackingDto) throws AdherentIdNotFoundException {
        Adherent adherent = adherentRepository.findById(trackingDto.getAdherentId())
                .orElseThrow(() -> new AdherentIdNotFoundException("Adhérent introuvable"));

        ProgressTracking tracking = ProgressTracking.builder()
                .date(trackingDto.getDate())
                .poids(trackingDto.getPoids())
                .notes(trackingDto.getNotes())
                .adherent(adherent)
                .build();
        ProgressTracking newTracking = trackingRepository.save(tracking);
        return  dtoMapper.mapToTrackingResp(newTracking);
    }

    @Override
    public List<ProgressTrackingResponseDto> findByAdherentId(Long adherentId) {
        return trackingRepository.findByAdherentId(adherentId).stream()
                .map(dtoMapper::mapToTrackingResp)
                .toList();
    }

    @Override
    public ProgressTrackingResponseDto getById(Long id) throws ProgressTrackingIdNotFoundException {
        return trackingRepository.findById(id)
                .map(dtoMapper::mapToTrackingResp)
                .orElseThrow(() -> new ProgressTrackingIdNotFoundException("Suivi non trouvé"));
    }

    @Override
    public void delete(Long id) {
        trackingRepository.deleteById(id);
    }
}
