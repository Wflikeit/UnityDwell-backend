package UnityDwell.com.UnityDwell.repository.sqlProvider;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class FlatSqlProvider implements ProviderMethodResolver {
    public static String getAllFlatsInBuilding(UUID buildingId) {
        return new SQL()
                .SELECT("m.ID_MIESZKANIA", "m.NR_MIESZKANIA", "m.POWIERZCHNIA",
                        "m.LICZBA_POKOI", "m.DATA_KONTROLI_GAZOWEJ", "m.ID_BUDYNKU")
                .FROM("C##MACIEK.MIESZKANIA m")
                .WHERE("m.ID_BUDYNKU = #{buildingId}")
                .toString();
    }


}
