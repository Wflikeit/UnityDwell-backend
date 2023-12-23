package UnityDwell.com.UnityDwell.dto.mapper;

import UnityDwell.com.UnityDwell.dto.EmployeeResponse;
import UnityDwell.com.UnityDwell.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDTOMapper {
    public EmployeeResponse mapTo(Employee employee) {
        return EmployeeResponse.builder().id(employee.getId()).name(employee.getName())
                .surname(employee.getSurname()).phoneNumber(employee.getPhoneNumber())
                .email(employee.getEmail()).gender(employee.getGender()).salary(employee.getSalary())
                .dateOfEmployment(employee.getDateOfEmployment())
                .dateOfEndOfEmployment(employee.getDateOfEndOfEmployment())
                .idOfHousingAssociation(employee.getHousingAssociation().getId()).build();
    }
}
