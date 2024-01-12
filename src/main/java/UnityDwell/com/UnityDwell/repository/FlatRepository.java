package UnityDwell.com.UnityDwell.repository;

import UnityDwell.com.UnityDwell.model.Building;
import UnityDwell.com.UnityDwell.model.Flat;
import UnityDwell.com.UnityDwell.model.OwnerOfFlat;
import UnityDwell.com.UnityDwell.repository.sqlProvider.FlatSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface FlatRepository {
    @SelectProvider(FlatSqlProvider.class)
    @Results(id = "FlatsMap", value = {
            @Result(property = "id", column = "ID_MIESZKANIA"),
            @Result(property = "numberOfFlat", column = "NR_MIESZKANIA"),
            @Result(property = "space", column = "POWIERZCHNIA"),
            @Result(property = "numberOfRooms", column = "LICZBA_POKOI"),
            @Result(property = "dateOfLastGasControl", column = "DATA_KONTROLI_GAZOWEJ"),
            @Result(property = "building",
                    javaType = Building.class,
                    column = "ID_BUDYNKU",
                    one = @One(select = "UnityDwell.com.UnityDwell.repository." +
                            "BuildingsRepository.getBuildingById")
            ),
            @Result(property = "flatOwners",
                    javaType = OwnerOfFlat.class,
                    column = "NR_MIESZKANCA",
                    many = @Many(select = "UnityDwell.com.UnityDwell.repository." +
                            "OwnerOfFlatRepository.findAllFlatsOfOwner"))
    })
    List<Flat> getAllFlatsInBuilding(UUID buildingId);

    @SelectProvider(FlatSqlProvider.class)
    @ResultMap("FlatsMap")
    Optional<Flat> findFlatById(UUID flatId);

    @SelectProvider(FlatSqlProvider.class)
    @ResultMap("FlatsMap")
    List<Flat> findAllFlatsOfOwner(UUID flatOwnerId);

    @InsertProvider(FlatSqlProvider.class)
    void save(Flat flat);

    @DeleteProvider(FlatSqlProvider.class)
    void delete(UUID flatId);

    @UpdateProvider(FlatSqlProvider.class)
    void update(Flat flat);
}
