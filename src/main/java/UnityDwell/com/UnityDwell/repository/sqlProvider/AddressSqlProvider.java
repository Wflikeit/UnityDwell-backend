package UnityDwell.com.UnityDwell.repository.sqlProvider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class AddressSqlProvider implements ProviderMethodResolver {
    public static String findAddressById(@Param("id") UUID id) {
        return new SQL()
                .SELECT("a.ID_ADRESU", "a.MIASTO", "a.ULICA", "a.NR_BUDYNKU", "a.KOD_POCZTOWY")
                .FROM("C##MACIEK.ADRESY a")
                .WHERE("a.ID_ADRESU = '" + id + "'")
                .toString();
    }


}
