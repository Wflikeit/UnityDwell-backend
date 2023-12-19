package UnityDwell.com.UnityDwell.repository.sqlProvider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class HousingAssociationSqlProvider implements ProviderMethodResolver {
    public static String getHousingAssociation(@Param("id") UUID id) {
        return new SQL()
                .SELECT("s.ID_SPOLDZIELNI", "s.NAZWA", "s.DATA_ZALOZENIA", "s.NIP", "s.ID_ADRESU")
                .INNER_JOIN("C##MACIEK.ADRESY a ON s.ID_ADRESU = a.ID_ADRESU")
                .FROM("C##MACIEK.SPOLDZIELNIE s")
                .WHERE("s.ID_SPOLDZIELNI = '" + id + "'")

                .toString();
    }

}

