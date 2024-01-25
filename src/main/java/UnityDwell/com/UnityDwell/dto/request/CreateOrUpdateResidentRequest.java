package UnityDwell.com.UnityDwell.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class CreateOrUpdateResidentRequest {
    @NotNull(message = "Name is required")
    @Length(min = 5, max = 30, message = "Field must be between {min} and {max} characters long.")
    String name;
    @NotNull(message = "Surname is required")
    @Length(min = 5, max = 30, message = "Field must be between {min} and {max} characters long.")
    String surname;
}
