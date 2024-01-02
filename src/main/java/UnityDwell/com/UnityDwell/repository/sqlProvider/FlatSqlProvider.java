package UnityDwell.com.UnityDwell.repository.sqlProvider;

import UnityDwell.com.UnityDwell.model.Flat;
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

    public static String findFlatById(UUID flatId) {
        return new SQL()
                .SELECT("m.ID_MIESZKANIA", "m.NR_MIESZKANIA", "m.POWIERZCHNIA",
                        "m.LICZBA_POKOI", "m.DATA_KONTROLI_GAZOWEJ", "m.ID_BUDYNKU")
                .FROM("C##MACIEK.MIESZKANIA m")
                .WHERE("m.ID_MIESZKANIA = #{flatId}")
                .toString();
    }

    public static String findAllFlatsOfOwner(UUID flatOwnerId) {
        return new SQL()
                .SELECT("m.ID_MIESZKANIA", "m.NR_MIESZKANIA", "m.POWIERZCHNIA",
                        "m.LICZBA_POKOI", "m.DATA_KONTROLI_GAZOWEJ", "m.ID_BUDYNKU")
                .FROM("C##MACIEK.MIESZKANIA m")
                .INNER_JOIN("C##MACIEK.MIESZKANIA_WLASCICIELI mieszkaniaw ON mieszkaniaw.NR_MIESZKANCA = #{flatOwnerId}")
                .toString();
    }

    public static String save(Flat flat) {
        return new SQL()
                .INSERT_INTO("C##MACIEK.MIESZKANIA")
                .VALUES("ID_MIESZKANIA", "#{id}")
                .VALUES("NR_MIESZKANIA", "#{numberOfFlat}")
                .VALUES("POWIERZCHNIA", "#{space}")
                .VALUES("LICZBA_POKOI", "numberOfRooms")
                .VALUES("DATA_KONTROLI_GAZOWEJ", "dateOfLastGasControl")
                .VALUES("ID_BUDYNKU", "#{building.id}")
                .VALUES("ID_ADRESU", "#{address.id}")
                .toString();
    }

    public static String delete(UUID flatId) {
        return new SQL()
                .DELETE_FROM("C##MACIEK.MIESZKANIA")
                .WHERE("ID_MIESZKANIA = #{flatId}")
                .toString();
    }

    public static String update(Flat flat) {
        return new SQL()
                .UPDATE("C##MACIEK.MIESZKANIA")
                .SET("NR_MIESZKANIA = #{numberOfFlat")
                .SET("POWIERZCHNIA = #{space")
                .SET("LICZBA_POKOI = #{numberOfRooms")
                .SET("DATA_KONTROLI_GAZOWEJ = #{dateOfLastGasControl")
                .SET("ID_BUDYNKU = #{building.id}")
                .toString();

    }
}
