package UnityDwell.com.UnityDwell.controller;

import UnityDwell.com.UnityDwell.dto.response.EmployeeResponse;
import UnityDwell.com.UnityDwell.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @GetMapping(value = "/{id}")
    public EmployeeResponse getEmployee(@PathVariable("id") UUID employeeId){
        return employeeService.getEmployeeById(employeeId);
    }
}
