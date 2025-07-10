package ma.enset.gym_management.services;

import ma.enset.gym_management.dto.AdherentDto;
import ma.enset.gym_management.dto.AdherentResponseDTO;
import ma.enset.gym_management.entities.Adherent;
import ma.enset.gym_management.entities.Subscription;
import ma.enset.gym_management.exceptions.AdherentAlreadyExistsException;
import ma.enset.gym_management.exceptions.AdherentIdNotFoundException;
import ma.enset.gym_management.exceptions.AdherentEmailNotFoundException;
import ma.enset.gym_management.exceptions.SubscriptionIdNotFoundException;

import java.util.List;

public interface AdherentService {
    public List<AdherentResponseDTO> AllAdherent();
    public AdherentResponseDTO getAdherentById(Long id) throws AdherentIdNotFoundException;

    public AdherentResponseDTO getAdherentByEmail(String email) throws AdherentEmailNotFoundException;

    public AdherentResponseDTO addAdherent(AdherentDto adherentDto) throws AdherentAlreadyExistsException;
    public AdherentResponseDTO updateAdherent(long adherentId, AdherentDto adherentDto) throws AdherentIdNotFoundException;
    public void deleteAdherent(Long adherentDtoId);
    public AdherentResponseDTO addSubscriptionToAdherent(Long adherentId, Long subscriptionId) throws AdherentIdNotFoundException, SubscriptionIdNotFoundException;

}
