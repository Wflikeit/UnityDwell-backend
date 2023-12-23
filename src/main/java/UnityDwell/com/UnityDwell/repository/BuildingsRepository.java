package UnityDwell.com.UnityDwell.repository;

import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.model.Building;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.repository.sqlProvider.BuildingsSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Mapper
public interface BuildingsRepository {
    @SelectProvider(BuildingsSqlProvider.class)
    @Results(id = "BuildingMap", value = {
            @Result(property = "id", column = "ID_BUDYNKU"),
            @Result(property = "address",
                    javaType = Address.class,
                    column = "ID_ADRESU",
                    one = @One(select = "UnityDwell.com.UnityDwell.repository." +
                            "AddressRepository.findAddressById")),
            @Result(property = "housingAssociation",
                    javaType = HousingAssociation.class,
                    column = "ID_SPOLDZIELNI",
                    one = @One(select = "UnityDwell.com.UnityDwell.repository." +
                            "HousingAssociationRepository.findHousingAssociationById")),
            @Result(property = "dateOfThermalModernization", column = "DATA_MODERNIZACJI_TERMICZNEJ"),
            @Result(property = "dateOfCommissioning", column = "DATA_ODDANIA_DO_UZYTKU"),
            @Result(property = "dateOfMajorRenovation", column = "DATA_REMONTU_OGOLNEGO"),
            @Result(property = "numberOfFloors", column = "LICZBA_PIETER"),
            @Result(property = "intendedForLiving", column = "CZY_ZDATNY_DO_MIESZKANIA"),
    })
    List<Building> getBuildingsInHousingAssociation(UUID id);
    @SelectProvider(type = BuildingsSqlProvider.class, method = "getBuildingInHousingAssociationById")
    @ResultMap("BuildingMap")
    Building getBuildingInHousingAssociationById(UUID id);


}
