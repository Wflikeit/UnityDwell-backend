package UnityDwell.com.UnityDwell.repository.sqlProvider;

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
