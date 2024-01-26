package UnityDwell.com.UnityDwell.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class EmployeeResponse {
    private UUID id;
    private String name;
    private String surname;
    private String phoneNumber;
    private char gender;
    private double salary;
    private LocalDate dateOfEmployment;
    private LocalDate dateOfEndOfEmployment;
    private UUID idOfHousingAssociation;
}
