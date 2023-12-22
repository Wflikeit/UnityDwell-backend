package UnityDwell.com.UnityDwell.repository.sqlProvider;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class BuildingsSqlProvider implements ProviderMethodResolver {
    public static String getBuildingsInHousingAssociation(UUID id) {
        return new SQL()
                .SELECT("b.ID_BUDYNKU", "b.DATA_MODERNIZACJI_TERMICZNEJ", "b.DATA_ODDANIA_DO_UZYTKU",
                        "b.LICZBA_PIETER", "b.DATA_REMONTU_OGOLNEGO", "b.CZY_ZDATNY_DO_MIESZKANIA",
                        "b.ID_SPOLDZIELNI", "b.ID_ADRESU")
                .FROM("C##MACIEK.BUDYNKI b")
                .INNER_JOIN("C##MACIEK.ADRESY a ON b.ID_ADRESU = a.ID_ADRESU")
                .INNER_JOIN("C##MACIEK.SPOLDZIELNIE s ON b.ID_SPOLDZIELNI = s.ID_SPOLDZIELNI")
                .WHERE("b.ID_BUDYNKU = '" + id + "'")
                .toString();
    }

}
