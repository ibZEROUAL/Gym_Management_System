package ma.enset.gym_management;

import ma.enset.gym_management.entities.Exercise;
import ma.enset.gym_management.enums.ExerciseCategorie;
import ma.enset.gym_management.repositories.ExerciseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;


@SpringBootApplication
public class GymManagementSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(GymManagementSystemApplication.class, args);
    }
     @Bean
     CommandLineRunner start(ExerciseRepository exerciseRepository){
        return args -> {
          /* Stream.of("Squate","Dips","Bench bress","Développé incliné","Exer1","Exer2","Exer3").forEach(name->{
                exerciseRepository.save(new Exercise(null, name, "Renforce les jambes",new Date(), ExerciseCategorie.CardioVasculaire ,"image.JPG", null));
            });

            programmeRepository.save(Program.builder()
                            .id(null)
                            .nom( "Programmes de musculation (prise de masse / force)")
                            .niveau(LevelType.Intermédiaire)
                            .description("Full Body : tout le corps travaillé à chaque séance\n" +
                                    "\n" +
                                    "Split Routine (ou Split) : un groupe musculaire par séance (ex. : dos/lundi, pec/mardi…)\n" +
                                    "\n" +
                                    "Upper / Lower Split : haut du corps / bas du corps en alternance\n" +
                                    "\n" +
                                    "5x5 StrongLifts : programme basé sur la force\n" +
                                    "\n" +
                                    "German Volume Training (GVT) : 10 séries de 10 répétitions\n" +
                                    "\n" +
                                    "Body Recomposition : combiner prise de muscle et perte de graisse")
                            .dureeEnSemaines(4)
                            .visibilite(true)
                            .objectif("prise de masse / force")
                            .type(ProgramType.programmeSportif)

                    .build());
                    */
        };
     }

}
