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
    private Address address;
    private HousingAssociation housingAssociation;
    private LocalDate dateOfThermalModernization;
    private LocalDate dateOfCommissioning;
    private LocalDate dateOfMajorRenovation;
    private int numberOfFloors;
    private boolean intendedForLiving;
}
