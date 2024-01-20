package UnityDwell.com.UnityDwell.repository.sqlProvider;

import UnityDwell.com.UnityDwell.model.Employee;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class EmployeeSqlProvider implements ProviderMethodResolver {
    public static String findEmployeeById(UUID employeeId) {
        return new SQL()
                .SELECT("p.ID_PRACOWNIKA", "p.IMIE"
                        , "p.NAZWISKO", "p.NR_TELEFONU", "p.DATA_ZATRUDNIENIA"
                            , "p.DATA_KONCA_UMOWY", "p.PLEC", "p.PLACA", "p.EMAIL", "p.ID_SPOLDZIELNI", "p.ID_ADRESU")
                .FROM("C##MACIEK.PRACOWNICY p")
                .WHERE("p.ID_PRACOWNIKA = #{employeeId}")
                .toString();
    }

    public static String delete(UUID employeeId) {
        return new SQL()
                .DELETE_FROM("C##MACIEK.PRACOWNICY")
                .WHERE("ID_PRACOWNIKA = #{employeeId}")
                .toString();
    }

    public static String save(Employee employee) {
        return new SQL()
                .INSERT_INTO("C##MACIEK.PRACOWNICY")
                .VALUES("ID_PRACOWNIKA",  "#{id}")
                .VALUES("IMIE", "#{name}")
                .VALUES("NAZWISKO", "#{surname}")
                .VALUES("NR_TELEFONU", "#{phoneNumber}")
                .VALUES("PLEC", "#{gender}")
                .VALUES("PLACA", "{salary}")
                .VALUES("DATA_ZATRUDNIENIA", "#{dateOfEmployment}")
                .VALUES("DATA_KONCA_UMOWY", "#{dateOfEndOfEmployment}")
                .VALUES("ID_SPOLDZIELNI", "#{housingAssociation.id}")
                .VALUES("ID_ADRESU", "#{address.id}")
                .toString();
    }

    public static String update(Employee employee) {
        return new SQL()
                .UPDATE("C##MACIEK.PRACOWNICY")
                .SET("IMIE = #{name}")
                .SET("NAZWISKO = #{surname}")
                .SET("NR_TELEFONU = #{phoneNumber}")
                .SET("PLEC = #{gender}")
                .SET("PLACA = #{salary}")
                .SET("DATA_ZATRUDNIENIA = #{dateOfEmployment}")
                .SET("DATA_KONCA_UMOWY = #{dateOfEndOfEmployment}")
                .SET("ID_SPOLDZIELNI = #{address.id}")
                .toString();
    }

    public static String getEmployeesOfHA(UUID housingAssociationId) {
        return new SQL()
                .SELECT("p.ID_PRACOWNIKA", "p.IMIE"
                        , "p.NAZWISKO", "p.NR_TELEFONU", "p.DATA_ZATRUDNIENIA"
                        , "p.DATA_KONCA_UMOWY", "p.PLEC", "p.PLACA", "p.EMAIL", "p.ID_SPOLDZIELNI", "p.ID_ADRESU")
                .FROM("C##MACIEK.PRACOWNICY p")
                .WHERE("p.ID_SPOLDZIELNI = #{housingAssociationId}")
                .toString();

    }
    public static String findEmployeeByEmail(String email){
        return new SQL()
                .SELECT("p.ID_PRACOWNIKA", "p.IMIE"
                        , "p.NAZWISKO", "p.NR_TELEFONU", "p.DATA_ZATRUDNIENIA"
                        , "p.DATA_KONCA_UMOWY", "p.PLEC", "p.PLACA", "p.EMAIL", "p.ID_SPOLDZIELNI", "p.ID_ADRESU")
                .FROM("C##MACIEK.PRACOWNICY p")
                .WHERE("p.EMAIL = #{email}")
                .toString();
    }
}
