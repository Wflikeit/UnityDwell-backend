package UnityDwell.com.UnityDwell.dto.mapper;

import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateEmployeeRequest;
import UnityDwell.com.UnityDwell.dto.response.EmployeeResponse;
import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.model.users.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class EmployeeDTOMapper {
    public EmployeeResponse mapTo(Employee employee) {
        return EmployeeResponse.builder().id(employee.getId()).name(employee.getName())
                .surname(employee.getSurname()).phoneNumber(employee.getPhoneNumber())
                    .gender(employee.getGender()).salary(employee.getSalary())
                        .dateOfEmployment(employee.getDateOfEmployment())
                            .dateOfEndOfEmployment(employee.getDateOfEndOfEmployment())
                                .idOfHousingAssociation(employee.getHousingAssociation().getId()).build();
    }

    public Employee map(CreateOrUpdateEmployeeRequest request,
                        HousingAssociation housingAssociation, Address address) {
        return Employee.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .salary(request.getSalary())
                .phoneNumber(request.getPhoneNumber())
                .gender(request.getGender())
                .dateOfEmployment(request.getDateOfEmployment())
                .dateOfEndOfEmployment(request.getDateOfEndOfEmployment())
                .housingAssociation(housingAssociation)
                .address(address)
                .build();
    }

    public List<EmployeeResponse> mapToEmployeeList(List<Employee> employees) {
        return employees == null ? new ArrayList<>() : employees.stream().filter(Objects::nonNull)
                .map(this::mapTo)
                .toList();
    }

}
