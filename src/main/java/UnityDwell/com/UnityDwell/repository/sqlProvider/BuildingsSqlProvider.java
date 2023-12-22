package UnityDwell.com.UnityDwell.repository.sqlProvider;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class BuildingsSqlProvider implements ProviderMethodResolver {
    public static String getBuildingsInHousingAssociation(UUID id) {
        return new SQL()
                .SELECT("b.ID_BUDYNKU", "b.DATA_MODERNIZACJI", "s.DATA_ZALOZENIA", "s.NIP", "s.ID_ADRESU")
                .INNER_JOIN("C##MACIEK.ADRESY a ON s.ID_ADRESU = a.ID_ADRESU")
                .FROM("C##MACIEK.SPOLDZIELNIE b")
                .WHERE("s.ID_SPOLDZIELNI = '" + id + "'")
                .toString();
    }
}
