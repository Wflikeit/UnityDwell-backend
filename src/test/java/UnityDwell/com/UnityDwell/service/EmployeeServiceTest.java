package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateEmployeeRequest;
import UnityDwell.com.UnityDwell.dto.response.EmployeeResponse;
import UnityDwell.com.UnityDwell.dto.mapper.EmployeeDTOMapper;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.model.Employee;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.repository.AddressRepository;
import UnityDwell.com.UnityDwell.repository.EmployeeRepository;
import UnityDwell.com.UnityDwell.repository.HousingAssociationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    HousingAssociationRepository housingAssociationRepository;
    @Mock
    AddressRepository addressRepository;
    @Mock
    EmployeeDTOMapper employeeDTOMapper;
    @InjectMocks
    EmployeeService employeeService;

    @Test
    public void getEmployeeById_WhenOneExists() {
        //Arrange
        UUID id = UUID.randomUUID();
        Employee employee = Employee.builder().build();
        EmployeeResponse employeeResponse = EmployeeResponse.builder().build();
        when(employeeRepository.findEmployeeById(id)).thenReturn(Optional.of(employee));
        when(employeeDTOMapper.mapTo(employee)).thenReturn(employeeResponse);
        //Act & Assert
        assertEquals(employeeResponse, employeeService.getEmployeeById(id));
    }

    @Test
    public void getEmployeeById_WhenOneNotExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(employeeRepository.findEmployeeById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> employeeService.getEmployeeById(id));
    }

    @Test
    public void deleteEmployeeWhenOneExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        Employee employee = Employee.builder().build();
        when(employeeRepository.findEmployeeById(id)).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).delete(id);

        // Act
        employeeService.deleteEmployee(id);

        // Assert
        verify(employeeRepository, times(1)).findEmployeeById(id);
        verify(employeeRepository, times(1)).delete(id);
    }

    @Test
    public void deleteEmployeeWhenOneNotExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(employeeRepository.findEmployeeById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class,
                () -> employeeService.deleteEmployee(id));
    }

    @Test
    public void addNewEmployeeWhenOneNotExists() {
        // Arrange
        CreateOrUpdateEmployeeRequest request = CreateOrUpdateEmployeeRequest.builder().build();
        Employee employee = Employee.builder().build();
        HousingAssociation housingAssociation = HousingAssociation.builder().build();
        UUID housingAssociationId = UUID.randomUUID();
        UUID addressId = UUID.randomUUID();
        request.setAddressId(addressId);
        request.setHousingAssociationId(housingAssociationId);
        Address address = Address.builder().build();
        EmployeeResponse expectedResponse = EmployeeResponse.builder().build();

        when(housingAssociationRepository.findHousingAssociationById(housingAssociationId)).thenReturn(Optional.of(housingAssociation));
        when(addressRepository.findAddressById(addressId)).thenReturn(Optional.of(address));
        when(employeeDTOMapper.map(request, housingAssociation, address)).thenReturn(employee);
        when(employeeDTOMapper.mapTo(employee)).thenReturn(expectedResponse);

        // Act
        EmployeeResponse actualResponse = employeeService.addNewEmployee(request);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);

        verify(employeeRepository, times(1)).save(employee);
    }
}
