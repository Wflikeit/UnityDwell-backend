package UnityDwell.com.UnityDwell.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class CreateOrUpdateEmployeeRequest {
    @NotNull(message = "Name is required")
    @Length(min = 2, max = 20, message = "Name must be between 2 and 20 letters")
    private String name;
    @NotNull(message = "Surname is required")
    @Length(min = 2, max = 20, message = "Surname must be between 2 and 20 letters")
    private String surname;
    @NotNull(message = "Phone number is required")
    @Length(min = 11, max = 11, message = "Please enter a valid phone number")
    private String phoneNumber;
    @NotNull(message = "Gender is required")
    @Pattern(regexp = "[MK]", message = "Gender must be either M or K")
    private String gender;
    @NotNull(message = "Salary is required")
    @DecimalMin(value = "100.00", message = "Salary must be greater or equal to 100.00")
    private double salary;
    @NotNull(message = "Date of employment is required")
    @PastOrPresent(message = "Date of employment must be from past or present")
    private LocalDate dateOfEmployment;
    @NotNull(message = "Date of end of employment is required")
    @FutureOrPresent(message = "Date of the end of employment must be from future or present")
    private LocalDate dateOfEndOfEmployment;
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
