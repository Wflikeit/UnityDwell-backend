package UnityDwell.com.UnityDwell.dto.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class CreateOrUpdateAddressRequest {
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
