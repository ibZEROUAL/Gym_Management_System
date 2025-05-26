package ma.enset.gym_management.mappers;

import ma.enset.gym_management.dto.ExerciseDto;
import ma.enset.gym_management.dto.ProgramDto;
import ma.enset.gym_management.dto.RepastDto;
import ma.enset.gym_management.entities.Exercise;
import ma.enset.gym_management.entities.Program;
import ma.enset.gym_management.entities.Repast;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ProgramMapperImpl {


    public Program mapToProgram(ProgramDto programDto){
        Program program = new Program();
        BeanUtils.copyProperties(programDto,program);
        return program;
    }
    public ProgramDto mapToProgramDto(Program program){
        ProgramDto programDto = new ProgramDto();
        BeanUtils.copyProperties(program,programDto);
        programDto.setExerciseDto(program.getExercises().stream().map(this::mapToExerciseDto).collect(Collectors.toList()));
        return programDto;
    }
    public ExerciseDto mapToExerciseDto(Exercise exercise){
        ExerciseDto exerciseDto= new ExerciseDto();
        BeanUtils.copyProperties(exercise, exerciseDto);
        return exerciseDto;
    }
    public Exercise mapToExercise(ExerciseDto exerciseDto){
        Exercise exercise= new Exercise();
        BeanUtils.copyProperties(exerciseDto, exercise);
        return exercise;
    }

    public RepastDto mapToRepastDto(Repast repast){
        RepastDto repastDto = new RepastDto();
        BeanUtils.copyProperties(repast, repastDto);
        return repastDto;
    }
    public Repast mapToRepast(RepastDto repastDto){
        Repast repast = new Repast();
        BeanUtils.copyProperties(repastDto, repast);
        return repast;
    }
}