package UnityDwell.com.UnityDwell.model.users;

import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Employee extends User {
    private String name;
    private String surname;
    private String phoneNumber;
    private char gender;
    private double salary;
    private LocalDate dateOfEmployment;
    private LocalDate dateOfEndOfEmployment;
    private HousingAssociation housingAssociation;
    private Address address;
}
