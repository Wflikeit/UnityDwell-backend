package UnityDwell.com.UnityDwell.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class CreateOrUpdateFlatRequest {
    @NotNull(message = "Number of flat is required")
    @Min(1)
    private int numberOfFlat;
    @NotNull(message = "Space is required")
    @DecimalMin(value = "13.00", message = "Value must be greater than 13.00")
    private double space;
    @NotNull(message = "Number of rooms is required")
    @Min(value = 1, message = "Number of rooms must be between 1 and 5")
    @Max(value = 5, message = "Number of rooms must be between 1 and 5")
    private int numberOfRooms;
    @NotNull(message = "Date of last gas control is required")
    @PastOrPresent(message = "Date of last gas control must be from past or present")
    private LocalDate dateOfLastGasControl;
    @NotNull(message = "Building id is required")
    private UUID buildingId;
}
