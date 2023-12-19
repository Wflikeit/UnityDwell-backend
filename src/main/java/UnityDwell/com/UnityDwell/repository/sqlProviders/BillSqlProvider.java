package UnityDwell.com.UnityDwell.repository.sqlProviders;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class BillSqlProvider implements  ProviderMethodResolver{
    public static String getAllBills() {
        return new SQL()
                .SELECT("r.ID_RACHUNKU", "r.DATA_WYSTAWIENIA", "r.KWOTA", "r.ID_TYTULU_RACHUNKU", "r.ID_SPOLDZIELNI", "r.NR_MIESZKANCA")
                .INNER_JOIN("C##MACIEK.TYTULY_RACHUNKU t ON r.ID_TYTULU_RACHUNKU = t.ID_TYTULU_RACHUNKU")
                .FROM("C##MACIEK.RACHUNKI r")
                .toString();
    }
    public String getAllOwnersBills(UUID id) {
        return new SQL()
                .SELECT("r.ID_RACHUNKU, r.DATA_WYSTAWIENIA, r.KWOTA, r.ID_TYTULU_RACHUNKU, r.ID_SPOLDZIELNI, R.NR_MIESZKANCA")
                .INNER_JOIN("C##MACIEK.TYTULY_RACHUNKU t ON r.ID_RACHUNKU = t.ID_TYTULU_RACHUNKU")
                .FROM("C##MACIEK.RACHUNKI r")
                .WHERE("r.ID_RACHUNKU = #{id}")
                .toString();
    }

}
