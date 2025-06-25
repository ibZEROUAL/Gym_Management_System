package ma.enset.gym_management.services;

import ma.enset.gym_management.dto.RepastDto;
import ma.enset.gym_management.dto.RepastResponseDto;
import ma.enset.gym_management.enums.RepastObjective;
import ma.enset.gym_management.enums.RepastType;
import ma.enset.gym_management.exceptions.*;

import java.util.List;

public interface RepastService {

    List<RepastResponseDto> allRepasts() throws RepastsNotFoundException;

    RepastResponseDto getRepastById(Long id) throws RepastIdNotFoundException;

    List<RepastResponseDto> getRepastByType(RepastType type) throws RepastByTypeNotFoundException;

    List<RepastResponseDto> getRepastByObjective(RepastObjective objictive) throws RepastByObjectiveNotFoundException;

    List<RepastResponseDto> getRepastByTypeAndObjective(RepastType type, RepastObjective objective) throws RepastByTypeAndObjectiveNotFoundException;

    List<RepastResponseDto> listRepastsOfProgram(Long programId) throws ProgramIdNotFoundException;

    RepastResponseDto createRepast(RepastDto repastDto) throws RepastAlreadyExistsException;

    RepastResponseDto updateRepast(Long repastId, RepastDto repastDto) throws RepastIdNotFoundException;

    void deleteRepast(Long repastId) throws RepastIdNotFoundException;

}
