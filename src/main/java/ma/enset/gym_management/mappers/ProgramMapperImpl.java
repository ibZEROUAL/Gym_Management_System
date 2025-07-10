package ma.enset.gym_management.mappers;

import ma.enset.gym_management.dto.*;
import ma.enset.gym_management.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;



import java.util.stream.Collectors;

@Service
public class ProgramMapperImpl {


    public Program mapDtoToProgram(ProgramDto programDto) {
        Program program = new Program();
        BeanUtils.copyProperties(programDto, program);
        return program;
    }
    /*
    public ProgramResponseDto mapDtoToProgramRespDto(ProgramDto programDto) {
        ProgramResponseDto programResponseDto = new ProgramResponseDto();
        BeanUtils.copyProperties(programDto, programResponseDto);
        programResponseDto.setExerciseResponseDto(programDto.getExerciseDto().stream().map(this::mapToExerciseDto).collect(Collectors.toList()));
        programResponseDto.setRepastResponseDto(programDto.getRepastsDto().stream().map(this::mapToRepastDto).collect(Collectors.toList()));

        return programResponseDto;
    }*/
    public ProgramDto mapToProgramDto(Program program) {
        ProgramDto programDto = new ProgramDto();
        BeanUtils.copyProperties(program, programDto);
        return programDto;
    }
    public ProgramResponseDto mapToProgramRespDto(Program program) {
        ProgramResponseDto programResponseDto = new ProgramResponseDto();
        BeanUtils.copyProperties(program, programResponseDto);
        programResponseDto.setExerciseResponseDto(program.getExercises().stream().map(this::mapToExerciseRespDto).collect(Collectors.toList()));
        programResponseDto.setRepastResponseDto(program.getRepasts().stream().map(this::mapToRepastRespDto).collect(Collectors.toList()));

        return programResponseDto;
    }

    public Program mapRespToProgram(ProgramResponseDto programResponseDto) {
        Program program = new Program();
        BeanUtils.copyProperties(programResponseDto,program);
        program.setExercises(programResponseDto.getExerciseResponseDto().stream().map(this::mapRespToExercise).collect(Collectors.toList()));
        program.setRepasts(programResponseDto.getRepastResponseDto().stream().map(this::mapRespToRepast).collect(Collectors.toList()));

        return program;
    }
  /*  public ProgramDto mapRespToProgramDto(ProgramResponseDto programResponseDto) {
        ProgramDto programDto = new ProgramDto();
        BeanUtils.copyProperties(programResponseDto,programDto);
        programDto.setExerciseDto(programResponseDto.getExerciseResponseDto().stream().map(this::mapToExerciseDto).collect(Collectors.toList()));
        programDto.setRepastsDto(programResponseDto.getRepastResponseDto().stream().map(this::mapToRepastDto).collect(Collectors.toList()));

        return programDto;
    }*/



    public ExerciseDto mapToExerciseDto(Exercise exercise) {
        ExerciseDto exerciseDto = new ExerciseDto();
        BeanUtils.copyProperties(exercise, exerciseDto);
        return exerciseDto;
    }
    public ExerciseResponseDto mapToExerciseRespDto(Exercise exercise) {
        ExerciseResponseDto exerciseResponseDto = new ExerciseResponseDto();
        BeanUtils.copyProperties(exercise,exerciseResponseDto);
        return exerciseResponseDto;
    }
    public Exercise mapRespToExercise(ExerciseResponseDto exerciseResponseDto) {
        Exercise exercise = new Exercise();
        BeanUtils.copyProperties(exerciseResponseDto,exercise);
        return exercise;
    }
    public Exercise mapDtoToExercise(ExerciseDto exerciseDto) {
        Exercise exercise = new Exercise();
        BeanUtils.copyProperties(exerciseDto, exercise);
        return exercise;
    }



    public RepastDto mapToRepastDto(Repast repast) {
        RepastDto repastDto = new RepastDto();
        BeanUtils.copyProperties(repast, repastDto);
        return repastDto;
    }
    public RepastResponseDto mapToRepastRespDto(Repast repast) {
        RepastResponseDto repastResponseDto = new RepastResponseDto();
        BeanUtils.copyProperties(repast, repastResponseDto);
        repastResponseDto.setAlimentResponseDto(repast.getAliments().stream().map(this::mapToAlimentRespDto).collect(Collectors.toList()));
        return repastResponseDto;
    }
    public Repast mapRespToRepast(RepastResponseDto repastResponseDto) {
        Repast repast = new Repast();
        BeanUtils.copyProperties(repastResponseDto, repast);
        repast.setAliments(repastResponseDto.getAlimentResponseDto().stream().map(this::mapRespToAliment).collect(Collectors.toList()));
        return repast;
    }
    public Repast mapDtoToRepast(RepastDto repastDto) {
        Repast repast = new Repast();
        BeanUtils.copyProperties(repastDto, repast);
        return repast;
    }


    public CoachDto mapToCoachDto(Coach coach) {
        CoachDto coachDto = new CoachDto();
        BeanUtils.copyProperties(coach, coachDto);
        return coachDto;
    }
    public CoachResponseDto mapToCoachRespDto(Coach coach) {
        CoachResponseDto coachResponseDto = new CoachResponseDto();
        BeanUtils.copyProperties(coach, coachResponseDto);
        return coachResponseDto;
    }
    public Coach mapRespToCoach(CoachResponseDto coachResponseDto) {
        Coach coach = new Coach();
        BeanUtils.copyProperties(coachResponseDto,coach);
        return coach;
    }
    public Coach mapDtoToCoach(CoachDto coachDto) {
        Coach coach = new Coach();
        BeanUtils.copyProperties(coachDto, coach);
        return coach;
    }



    public AlimentDto mapToAlimentDto(Aliment aliment) {
        AlimentDto alimentDto = new AlimentDto();
        BeanUtils.copyProperties(aliment, alimentDto);
        return alimentDto;
    }
    public AlimentResponseDto mapToAlimentRespDto(Aliment aliment) {
        AlimentResponseDto alimentResponseDto = new AlimentResponseDto();
        BeanUtils.copyProperties(aliment, alimentResponseDto);
        return alimentResponseDto;
    }
    public Aliment mapDtoToAliment(AlimentDto alimentDto) {
        Aliment aliment = new Aliment();
        BeanUtils.copyProperties(alimentDto, aliment);
        return aliment;
    }
    public Aliment mapRespToAliment(AlimentResponseDto alimentResponseDto) {
        Aliment aliment = new Aliment();
        BeanUtils.copyProperties(alimentResponseDto, aliment);
        return aliment;
    }


    public AdherentResponseDTO mapToAdherentResponseDTO(Adherent adherent) {
        AdherentResponseDTO adherentResponseDTO = new AdherentResponseDTO();
        BeanUtils.copyProperties(adherent, adherentResponseDTO);
        return adherentResponseDTO;
    }

    public Adherent mapResponseDtoToAdherent(AdherentResponseDTO adherentResponseDTO) {
        Adherent adherent = new Adherent();
        BeanUtils.copyProperties(adherentResponseDTO, adherent);
        return adherent;
    }

    public AdherentDto mapResponseDtoToAdherentdto(AdherentResponseDTO adherentResponseDTO) {
        AdherentDto adherentDto = new AdherentDto();
        BeanUtils.copyProperties(adherentResponseDTO, adherentDto);
        return adherentDto;
    }

    public Adherent mapAdherentDtoToAdherent(AdherentDto adherentDto) {
        Adherent adherent = new Adherent();
        BeanUtils.copyProperties(adherentDto, adherent);
        return adherent;
    }
    public AdherentDto mapToAdherentDto(Adherent adherent) {
        AdherentDto adherentDto = new AdherentDto();
        BeanUtils.copyProperties(adherent, adherentDto);
        return adherentDto;
    }



    public RegistrationProgramResponseDto mapRegisDtoToRegisRespDto(RegistrationProgramDto registrationProgramDto) {
        RegistrationProgramResponseDto registrationResDto = new RegistrationProgramResponseDto();
        BeanUtils.copyProperties(registrationProgramDto, registrationResDto);
        return registrationResDto;
    }
    public RegistrationProgram mapRegisDtoToRegis(RegistrationProgramDto registrationProgramDto) {
        RegistrationProgram registrationProgram = new RegistrationProgram();
        BeanUtils.copyProperties(registrationProgramDto, registrationProgram);
        return registrationProgram;
    }
    public RegistrationProgramDto mapRegisRespDtoToRegisDto(RegistrationProgramResponseDto registrationProgramResponseDto) {
        RegistrationProgramDto registrationProgramDto = new RegistrationProgramDto();
        BeanUtils.copyProperties(registrationProgramResponseDto, registrationProgramDto);
        return registrationProgramDto;
    }
    public RegistrationProgram mapRegisRespDtoToRegist(RegistrationProgramResponseDto registrationProgramResponseDto) {
        RegistrationProgram registrationProgram = new RegistrationProgram();
        BeanUtils.copyProperties(registrationProgramResponseDto, registrationProgram);
        return registrationProgram;
    }
    public RegistrationProgramResponseDto mapRegisToRegisRespDto(RegistrationProgram registrationProgram) {
        RegistrationProgramResponseDto registrationProgramResponseDto = new RegistrationProgramResponseDto();
        BeanUtils.copyProperties(registrationProgram, registrationProgramResponseDto);
        registrationProgramResponseDto.setAdherent(mapToAdherentDto(registrationProgram.getAdherent()));
        registrationProgramResponseDto.setPrograms(mapToProgramDto(registrationProgram.getProgram()));
        return registrationProgramResponseDto;
    }
    public RegistrationProgramDto mapRegisToRegisDto(RegistrationProgram registrationProgram) {
        RegistrationProgramDto registrationProgramDto = new RegistrationProgramDto();
        BeanUtils.copyProperties(registrationProgram, registrationProgramDto);
        return registrationProgramDto;
    }

    public CoachingSessionResponseDto  mapCoachingToCoachingRespDto(CoachingSession coachingSession) {
        CoachingSessionResponseDto coachingSessionResponseDto = new CoachingSessionResponseDto();
        BeanUtils.copyProperties(coachingSession, coachingSessionResponseDto);
        return coachingSessionResponseDto;
    }

    public SubscriptionResponseDto mapSubsToSubsResDTO(Subscription subscription) {
        SubscriptionResponseDto subscriptionResponseDto = new SubscriptionResponseDto();
        BeanUtils.copyProperties(subscription, subscriptionResponseDto);
        subscriptionResponseDto.setAdherents(subscription.getAdherents().stream().map(this::mapToAdherentResponseDTO).collect(Collectors.toList()));

        return subscriptionResponseDto;
    }

    public ProgressTrackingResponseDto mapToTrackingResp(ProgressTracking tracking) {
        ProgressTrackingResponseDto trackingResponseDto = new ProgressTrackingResponseDto();
        BeanUtils.copyProperties(tracking, trackingResponseDto);
        trackingResponseDto.setAdherentId(tracking.getAdherent().getId());
        trackingResponseDto.setAdherentEmail(tracking.getAdherent().getEmail());
        return trackingResponseDto;
    }


}