package ma.enset.gym_management.services;

import ma.enset.gym_management.dto.AdherentDto;
import ma.enset.gym_management.dto.AdherentResponseDTO;
import ma.enset.gym_management.exceptions.AdherentAlreadyExistsException;
import ma.enset.gym_management.exceptions.AdherentIdNotFoundException;
import ma.enset.gym_management.exceptions.AdherentUserNameNotFoundException;

import java.util.List;

public interface AdherentService {
    public List<AdherentResponseDTO> AllAdherent();
    public AdherentResponseDTO getAdherentById(Long id) throws AdherentIdNotFoundException;

    public AdherentResponseDTO getAdherentByUserName(String userName) throws AdherentUserNameNotFoundException;

    public AdherentResponseDTO addAdherent(AdherentDto adherentDto) throws AdherentAlreadyExistsException;
    public AdherentResponseDTO updateAdherent(long adherentId, AdherentDto adherentDto) throws AdherentIdNotFoundException;
    public void deleteAdherent(Long adherentDtoId);

}
