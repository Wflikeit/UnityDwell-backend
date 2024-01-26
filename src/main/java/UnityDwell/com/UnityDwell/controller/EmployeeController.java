package UnityDwell.com.UnityDwell.controller;

import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateEmployeeRequest;
import UnityDwell.com.UnityDwell.dto.response.EmployeeResponse;
import UnityDwell.com.UnityDwell.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping(value = "/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    public EmployeeResponse getEmployee(@PathVariable("id") UUID employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse addEmployee(@Validated @RequestBody CreateOrUpdateEmployeeRequest request) {
        return employeeService.addNewEmployee(request);
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@Validated @RequestBody CreateOrUpdateEmployeeRequest request,
                                           @PathVariable("id") UUID id) {
        return employeeService.updateEmployee(request, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") UUID id) {
        employeeService.deleteEmployee(id);
    }
}
