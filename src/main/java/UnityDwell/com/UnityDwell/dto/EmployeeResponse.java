package UnityDwell.com.UnityDwell.dto;

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
    private String email;
    private String gender;
    private double salary;
    private LocalDate dateOfEmployment;
    private LocalDate dateOfEndOfEmployment;
    private UUID idOfHousingAssociation;
}
