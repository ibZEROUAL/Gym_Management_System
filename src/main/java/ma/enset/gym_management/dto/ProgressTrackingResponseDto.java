package ma.enset.gym_management.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor @NoArgsConstructor
@Builder
@Getter @Setter
public class ProgressTrackingResponseDto {
    private Long id;
    private LocalDate date;
    private double poids;
    private String notes;
    private Long adherentId;
    private String adherentEmail;
}
