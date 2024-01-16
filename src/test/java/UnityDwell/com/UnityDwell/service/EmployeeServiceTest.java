package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.response.EmployeeResponse;
import UnityDwell.com.UnityDwell.dto.mapper.EmployeeDTOMapper;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.users.Employee;
import UnityDwell.com.UnityDwell.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository;
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
}
