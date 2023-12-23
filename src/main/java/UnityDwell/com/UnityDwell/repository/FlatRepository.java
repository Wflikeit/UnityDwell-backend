package UnityDwell.com.UnityDwell.repository;

import UnityDwell.com.UnityDwell.model.*;
import UnityDwell.com.UnityDwell.repository.sqlProvider.EmployeeSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface FlatRepository {
    @SelectProvider(EmployeeSqlProvider.class)
    @Results(id = "EmployeeMap", value = {
            @Result(property = "id", column = "ID_MIESZKANIA"),
            @Result(property = "numberOfFlat", column = "NR_MIESZKANIA"),
            @Result(property = "space", column = "POWIERZCHNIA"),
            @Result(property = "amountOfRooms", column = "LICZBA_POKOI"),
            @Result(property = "dateOfLastGasControl", column = "DATA_KONTROLI_GAZOWEJ"),
            @Result(property = "building", column = "ID_BUDYNKU"),
            @Result(property = "building",
                    javaType = Building.class,
                    column = "ID_BUDYNKU",
                    one = @One(select = "UnityDwell.com.UnityDwell.repository." +
                            "BuildingRepository.getBuildingById")
            )
    })
    List<Flat> getAllFlatsInBuilding(UUID id);
}
