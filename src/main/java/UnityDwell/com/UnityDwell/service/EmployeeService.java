package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.response.EmployeeResponse;
import UnityDwell.com.UnityDwell.dto.mapper.EmployeeDTOMapper;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.users.Employee;
import UnityDwell.com.UnityDwell.model.users.User;
import UnityDwell.com.UnityDwell.repository.EmployeeRepository;
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

    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeById(UUID id) {
        Employee employee = employeeRepository.findEmployeeById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee with id %s not found", id)));

        return employeeDTOMapper.mapTo(employee);
    }

    @Transactional(readOnly = true)
    @Override
    public User loadUserByUsername(String email) {
        return employeeRepository.findEmployeeByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException
                        (String.format("User with user email %s not found", email)));
    }
}
