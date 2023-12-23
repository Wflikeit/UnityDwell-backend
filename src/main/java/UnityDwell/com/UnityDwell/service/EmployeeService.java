package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.EmployeeResponse;
import UnityDwell.com.UnityDwell.dto.mapper.EmployeeDTOMapper;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Employee;
import UnityDwell.com.UnityDwell.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeDTOMapper employeeDTOMapper;

    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeById(UUID id){
        Employee employee = employeeRepository.findEmployeeById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee with id %s not found", id)));

        return employeeDTOMapper.mapTo(employee);
    }
}
