package UnityDwell.com.UnityDwell.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
    @DecimalMin(value = "0.01", message = "Value must be greater than 0.01")
    private double space;
    @NotNull(message = "Number of rooms is required")
    @Min(1)
    private int numberOfRooms;
    @NotNull(message = "Date of last gas control is required")
    @PastOrPresent(message = "Date of last gas control must be from past or present")
    private LocalDate dateOfLastGasControl;
    @NotNull(message = "Building id is required")
    private UUID buildingId;
}
