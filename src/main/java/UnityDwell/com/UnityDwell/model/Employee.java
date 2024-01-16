package UnityDwell.com.UnityDwell.model;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Employee {
    private UUID id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private char gender;
    private double salary;
    private LocalDate dateOfEmployment;
    private LocalDate dateOfEndOfEmployment;
    private HousingAssociation housingAssociation;
    private Address address;
}
