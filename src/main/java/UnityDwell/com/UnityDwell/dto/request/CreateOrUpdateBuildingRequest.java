package UnityDwell.com.UnityDwell.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
    private UUID addressId;
    @NotNull(message = "City is required.")
    @Length(min = 2, max = 20, message = "Field must be between {min} and {max} characters long.")
    private String city;
    @NotNull(message = "Street is required.")
    @Length(min = 2, max = 50, message = "Field must be between {min} and {max} characters long.")
    private String street;
    @NotNull(message = "Number of building is required.")
    @Length(min = 2, max = 4, message = "Field must be between {min} and {max} characters long.")
    private String numberOfBuilding;
    @NotNull(message = "Postal code is required.")
    @Pattern(regexp = "^\\d{2}-\\d{3}$", message = "Invalid postalCode code")
    @Length(min = 6, max = 6, message = "Field must be {max} characters long.")
    private String postalCode;
}
