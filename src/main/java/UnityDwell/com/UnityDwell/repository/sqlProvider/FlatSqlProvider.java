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

    public static String findFlatById(UUID flatId){
        return new SQL()
                .SELECT("m.ID_MIESZKANIA", "m.NR_MIESZKANIA", "m.POWIERZCHNIA",
                        "m.LICZBA_POKOI", "m.DATA_KONTROLI_GAZOWEJ", "m.ID_BUDYNKU")
                .FROM("C##MACIEK.MIESZKANIA m")
                .WHERE("m.ID_MIESZKANIA = #{flatId}")
                .toString();
    }

    public static String findAllFlatsOfOwner(UUID flatOwnerId){
        return new SQL()
                .SELECT("m.ID_MIESZKANIA", "m.NR_MIESZKANIA", "m.POWIERZCHNIA",
                        "m.LICZBA_POKOI", "m.DATA_KONTROLI_GAZOWEJ", "m.ID_BUDYNKU")
                .FROM("C##MACIEK.MIESZKANIA m")
                .INNER_JOIN("C##MACIEK.MIESZKANIA_WLASCICIELI mieszkaniaw ON mieszkaniaw.NR_MIESZKANCA = #{flatOwnerId}")
                .toString();
    }
}
