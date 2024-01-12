package UnityDwell.com.UnityDwell.repository.sqlProvider;

import UnityDwell.com.UnityDwell.model.Address;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class AddressSqlProvider implements ProviderMethodResolver {
    public static String findAddressById(UUID id) {
        return new SQL()
                .SELECT("a.ID_ADRESU", "a.MIASTO", "a.ULICA", "a.NR_BUDYNKU", "a.KOD_POCZTOWY")
                .FROM("C##MACIEK.ADRESY a")
                .WHERE("a.ID_ADRESU = #{id}")
                .toString();
    }

    public static String save(Address address) {
        return new SQL()
                .INSERT_INTO("C##MACIEK.ADRESY")
                .VALUES("ID_ADRESU", "#{id}")
                .VALUES("MIASTO", "#{city}")
                .VALUES("ULICA", "#{street}")
                .VALUES("NR_BUDYNKU", "#{numberOfBuilding}")
                .VALUES("KOD_POCZTOWY", "#{postalCode}")
                .toString();
    }

    public static String delete(UUID addressId) {
        return new SQL()
                .DELETE_FROM("C##MACIEK.ADRESY")
                .WHERE("ID_ADRESU = #{addressId}")
                .toString();
    }


}
