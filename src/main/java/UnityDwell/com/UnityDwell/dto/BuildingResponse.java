package UnityDwell.com.UnityDwell.dto;

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
    private boolean intendedForLiving;
    private Address address;
    private String city;
    private String Street;
    private String PostalCode;
    private UUID idOfHousingAssociation;

}
