package UnityDwell.com.UnityDwell.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class CreateOrUpdateBillRequest {
    @DecimalMin(value = "0.01", message = "amount must be greater or equal to 0.01")
    @NotNull(message = "Amount is required")
    private double amount;
    @NotNull(message = "Date of publishing is required")
    private LocalDate dateOfPublishing;
    @NotNull(message = "Housing association id is required")
    private UUID housingAssociationId;
    @NotNull(message = "Flat owner id is required")
    private String flatOwnerPhoneNumber;
    @NotNull(message = "Bill title is required")
    private String title;
}
