package ma.enset.gym_management.services;

import ma.enset.gym_management.dto.RepastDto;
import ma.enset.gym_management.enums.RepastObjictive;
import ma.enset.gym_management.enums.RepastType;
import ma.enset.gym_management.exceptions.*;

import java.util.List;

public interface RepastService {
    RepastDto updateRepast(Long repastId, RepastDto repastDto) throws RepastIdNotFoundException;

    List<RepastDto> getRepastByType(RepastType type) throws RepastByTypeNotFoundException;

    void deleterepast(Long repastId) throws RepastIdNotFoundException;

    List<RepastDto> getRepastByObjective(RepastObjictive objictive) throws RepastByObjictiveNotFoundException;

    List<RepastDto> getRepastByTypeAndObjective(RepastType type, RepastObjictive objictive) throws RepastByTypeAndObjictiveNotFoundException;

    RepastDto createRepast(RepastDto repastDto) throws RepastAlreadyExistsException;
    List<RepastDto> allRepasts() throws RepastsNotFoundException;
    RepastDto getRepastById(Long id) throws RepastIdNotFoundException;
}
