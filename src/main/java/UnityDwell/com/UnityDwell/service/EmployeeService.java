package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateEmployeeRequest;
import UnityDwell.com.UnityDwell.dto.response.EmployeeResponse;
import UnityDwell.com.UnityDwell.dto.mapper.EmployeeDTOMapper;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.model.users.Employee;
import UnityDwell.com.UnityDwell.model.users.User;
import UnityDwell.com.UnityDwell.repository.AddressRepository;
import UnityDwell.com.UnityDwell.repository.EmployeeRepository;
import UnityDwell.com.UnityDwell.repository.HousingAssociationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeDTOMapper employeeDTOMapper;
    private final HousingAssociationRepository housingAssociationRepository;
    private final AddressRepository addressRepository;

    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeById(UUID id) {
        Employee employee = employeeRepository.findEmployeeById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee with id %s not found", id)));

        return employeeDTOMapper.mapTo(employee);
    }


    @Transactional
    public EmployeeResponse addNewEmployee(CreateOrUpdateEmployeeRequest request) {
        HousingAssociation housingAssociation = housingAssociationRepository.findHousingAssociationById(request.getHousingAssociationId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Housing association with id %s not found", request.getHousingAssociationId())));
        Address address = Address.builder().build();
        if (request.getAddressId() != null) {
            address = addressRepository.findAddressById(request.getAddressId()).orElseThrow(() -> new ResourceNotFoundException(String
                    .format("Address with id %s not found", request.getAddressId())));
        } else {
            address.setId(UUID.randomUUID());
            address.setNumberOfBuilding(request.getNumberOfBuilding());
            address.setCity(request.getCity());
            address.setStreet(request.getStreet());
            address.setPostalCode(request.getPostalCode());
            addressRepository.save(address);
        }
        Employee employee = employeeDTOMapper.map(request, housingAssociation, address);
        employeeRepository.save(employee);
        return employeeDTOMapper.mapTo(employee);
    }

    @Transactional
    public EmployeeResponse updateEmployee(CreateOrUpdateEmployeeRequest request, UUID employeeId) {
        Employee employee = employeeRepository.findEmployeeById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee with id %s not found", employeeId)));
        HousingAssociation housingAssociation = housingAssociationRepository.findHousingAssociationById(request.getHousingAssociationId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Housing association with id %s not found", request.getHousingAssociationId())));
        Address address = Address.builder().build();
        if (request.getAddressId() != null) {
            address = addressRepository.findAddressById(request.getAddressId()).orElseThrow(() -> new ResourceNotFoundException(String
                    .format("Address with id %s not found", request.getAddressId())));
        } else {
            address.setId(UUID.randomUUID());
            address.setNumberOfBuilding(request.getNumberOfBuilding());
            address.setCity(request.getCity());
            address.setStreet(request.getStreet());
            address.setPostalCode(request.getPostalCode());
            addressRepository.save(address);
        }
        employee.setAddress(address);
        employee.setHousingAssociation(housingAssociation);
        employee.setName(request.getName());
        employee.setSurname(request.getSurname());
        employee.setSalary(request.getSalary());
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setGender(request.getGender());
        employee.setDateOfEmployment(request.getDateOfEmployment());
        employee.setDateOfEndOfEmployment(request.getDateOfEndOfEmployment());
        employee.setEmail(request.getEmail());
        employeeRepository.update(employee);
        return employeeDTOMapper.mapTo(employee);
    }

    @Transactional
    public void deleteEmployee(UUID employeeId) {
        employeeRepository.findEmployeeById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee with id %s not found", employeeId)));
        employeeRepository.delete(employeeId);
    }

    @Transactional(readOnly = true)
    @Override
    public User loadUserByUsername(String email) {
        return employeeRepository.findEmployeeByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException
                        (String.format("User with user email %s not found", email)));
    }
}
