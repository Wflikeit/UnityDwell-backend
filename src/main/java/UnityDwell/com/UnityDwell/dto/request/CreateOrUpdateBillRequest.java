package UnityDwell.com.UnityDwell.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CreateOrUpdateBillRequest {
    @NotNull(message = "Amount is requested")
    @DecimalMin(value = "0.01", message = "amount must be greater or equal to 0.01")
    private double amount;
    @NotNull(message = "Date of publishing is required")
    private LocalDate dateOfPublishing;
}
