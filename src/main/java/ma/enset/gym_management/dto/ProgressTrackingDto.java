package ma.enset.gym_management.dto;

import lombok.*;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProgressTrackingDto {
    private LocalDate date;
    private double poids;
    private String notes;
    private Long adherentId;
}
