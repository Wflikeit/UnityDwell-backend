package UnityDwell.com.UnityDwell.model;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Building {
    private UUID id;
    private LocalDate dateOfThermalModernization;
    private LocalDate dateOfCommissioning;
    private LocalDate dateOfMajorRenovation;
    private int numberOfFloors;
    private boolean intendedForLiving;
    private Address address;
}
