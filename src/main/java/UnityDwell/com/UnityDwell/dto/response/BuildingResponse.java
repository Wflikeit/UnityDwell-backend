package UnityDwell.com.UnityDwell.dto.response;

import UnityDwell.com.UnityDwell.model.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class BuildingResponse {
    private UUID id;
    private LocalDate dateOfThermalModernization;
    private LocalDate dateOfCommissioning;
    private LocalDate dateOfMajorRenovation;
    private int numberOfFloors;
    private char intendedForLiving;
    private Address address;
    private UUID idOfHousingAssociation;

}
