package UnityDwell.com.UnityDwell.repository;

import UnityDwell.com.UnityDwell.dto.response.EmployeeResponse;
import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.model.users.Employee;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.repository.sqlProvider.EmployeeSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface EmployeeRepository {
    @SelectProvider(EmployeeSqlProvider.class)
    @Results(id = "EmployeeMap", value = {
            @Result(property = "id", column = "ID_PRACOWNIKA"),
            @Result(property = "name", column = "IMIE"),
            @Result(property = "surname", column = "NAZWISKO"),
            @Result(property = "phoneNumber", column = "NR_TELEFONU"),
            @Result(property = "dateOfEmployment", column = "DATA_ZATRUDNIENIA"),
            @Result(property = "dateOfEndOfEmployment", column = "DATA_KONCA_UMOWY"),
            @Result(property = "gender", column = "PLEC"),
            @Result(property = "salary", column = "PLACA"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "address",
                    javaType = Address.class,
                    column = "ID_ADRESU",
                    one = @One(select = "UnityDwell.com.UnityDwell.repository." +
                            "AddressRepository.findAddressById")
            ),
            @Result(property = "housingAssociation",
                    javaType = HousingAssociation.class,
                    column = "ID_SPOLDZIELNI",
                    one = @One(select = "UnityDwell.com.UnityDwell.repository." +
                            "HousingAssociationRepository.findHousingAssociationById")
            )
    })
    Optional<Employee> findEmployeeById(UUID employeeId);
    @SelectProvider(EmployeeSqlProvider.class)
    @ResultMap("EmployeeMap")
    Optional<Employee> findEmployeeByEmail(String employeeEmail);
    @SelectProvider(EmployeeSqlProvider.class)
    @ResultMap("EmployeeMap")
    List<Employee> getEmployeesOfHA(UUID housingAssociationId);
    @InsertProvider(EmployeeSqlProvider.class)
    EmployeeResponse save(Employee employee);
    @UpdateProvider(EmployeeSqlProvider.class)
    EmployeeResponse update(Employee employee);
    @DeleteProvider(EmployeeSqlProvider.class)
    void delete(UUID employeeId);
}
