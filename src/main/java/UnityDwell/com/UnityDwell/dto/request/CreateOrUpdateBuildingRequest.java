package UnityDwell.com.UnityDwell.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class CreateOrUpdateBuildingRequest {
    @NotNull(message = "Date of thermal modernization is required")
    private LocalDate dateOfThermalModernization;

    @NotNull(message = "Date of commissioning is required")
    private LocalDate dateOfCommissioning;

    @NotNull(message = "Date of major renovation is required")
    private LocalDate dateOfMajorRenovation;

    @NotNull(message = "Number of floors is required")
    @Min(3)
    @Max(50)
    private int numberOfFloors;

    @NotNull(message = "Intended for living is required")
    private boolean intendedForLiving;

    @NotNull(message = "Housing association id is required")
    private UUID housingAssociationId;

    @NotNull(message = "Address id is required")
    private UUID addressId;
}
