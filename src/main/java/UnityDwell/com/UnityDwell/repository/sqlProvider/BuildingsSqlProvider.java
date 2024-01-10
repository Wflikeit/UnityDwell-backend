package UnityDwell.com.UnityDwell.repository.sqlProvider;

import UnityDwell.com.UnityDwell.model.Building;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class BuildingsSqlProvider implements ProviderMethodResolver {
    public static String getBuildingsInHousingAssociation(UUID houseAssociationId) {
        return new SQL()
                .SELECT("b.ID_BUDYNKU", "b.DATA_MODERNIZACJI_TERMICZNEJ", "b.DATA_ODDANIA_DO_UZYTKU",
                        "b.LICZBA_PIETER", "b.DATA_REMONTU_OGOLNEGO", "b.CZY_ZDATNY_DO_MIESZKANIA",
                        "b.ID_SPOLDZIELNI", "b.ID_ADRESU")
                .FROM("C##MACIEK.BUDYNKI b")
                .INNER_JOIN("C##MACIEK.ADRESY a ON b.ID_ADRESU = a.ID_ADRESU")
                .INNER_JOIN("C##MACIEK.SPOLDZIELNIE s ON b.ID_SPOLDZIELNI = s.ID_SPOLDZIELNI")
                .WHERE("b.ID_SPOLDZIELNI = #{houseAssociationId}")
                .toString();
    }

    public static String getBuildingById(UUID buildingId) {
        return new SQL()
                .SELECT("b.ID_BUDYNKU", "b.DATA_MODERNIZACJI_TERMICZNEJ", "b.DATA_ODDANIA_DO_UZYTKU",
                        "b.LICZBA_PIETER", "b.DATA_REMONTU_OGOLNEGO", "b.CZY_ZDATNY_DO_MIESZKANIA",
                        "b.ID_SPOLDZIELNI", "b.ID_ADRESU")
                .FROM("C##MACIEK.BUDYNKI b")
                .INNER_JOIN("C##MACIEK.ADRESY a ON b.ID_ADRESU = a.ID_ADRESU")
                .INNER_JOIN("C##MACIEK.SPOLDZIELNIE s ON b.ID_SPOLDZIELNI = s.ID_SPOLDZIELNI")
                .WHERE("b.ID_BUDYNKU = #{buildingId}")
                .toString();
    }

    public static String save(Building building) {
        return new SQL()
                .INSERT_INTO("C##MACIEK.BUDYNKI")
                .VALUES("ID_BUDYNKU", "#{id}")
                .VALUES("DATA_MODERNIZACJI_TERMICZNEJ", "#{dateOfThermalModernization}")
                .VALUES("DATA_ODDANIA_DO_UZYTKU", "#{dateOfCommissioning}")
                .VALUES("LICZBA_PIETER", "#{numberOfFloors}")
                .VALUES("DATA_REMONTU_OGOLNEGO", "#{dateOfMajorRenovation}")
                .VALUES("CZY_ZDATNY_DO_MIESZKANIA", "#{intendedForLiving}")
                .VALUES("ID_SPOLDZIELNI", "#{housingAssociation.id}")
                .VALUES("ID_ADRESU", "#{address.id}")
                .toString();
    }

    public static String update(Building building) {
        return new SQL()
                .UPDATE("C##MACIEK.BUDYNKI")
                .SET("DATA_MODERNIZACJI_TERMICZNEJ = #{dateOfThermalModernization}")
                .SET("LICZBA_PIETER = #{numberOfFloors}")
                .SET("DATA_REMONTU_OGOLNEGO = #{dateOfMajorRenovation}")
                .SET("CZY_ZDATNY_DO_MIESZKANIA = #{intendedForLiving}")
                .SET("DATA_ODDANIA_DO_UZYTKU = #{dateOfCommissioning}")
                .SET("ID_SPOLDZIELNI = #{housingAssociation.id}")
                .SET("ID_ADRESU = #{address.id}")
                .WHERE("ID_BUDYNKU = #{id}")
                .toString();
    }

    public static String delete(UUID buildingId) {
        return new SQL()
                .DELETE_FROM("C##MACIEK.BUDYNKI")
                .WHERE("ID_BUDYNKU = #{buildingId}")
                .toString();
    }
}
