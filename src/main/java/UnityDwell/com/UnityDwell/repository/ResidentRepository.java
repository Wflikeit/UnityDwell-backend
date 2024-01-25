package UnityDwell.com.UnityDwell.repository;

import UnityDwell.com.UnityDwell.model.Flat;
import UnityDwell.com.UnityDwell.model.Resident;
import UnityDwell.com.UnityDwell.repository.sqlProvider.ResidentSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface ResidentRepository {
    @SelectProvider(ResidentSqlProvider.class)
    @Results(id = "ResidentMap", value = {
            @Result(property = "id", column = "NR_MIESZKANCA"),
            @Result(property = "name", column = "NR_MIESZKANCA"),
            @Result(property = "surname", column = "NR_MIESZKANCA"),
            @Result(property = "flat",
                    javaType = Flat.class,
                    column = "ID_MIESZKANIA",
                    one = @One(select = "UnityDwell.com.UnityDwell.repository." +
                            "FlatRepository.findFlatById"))
    })
    Optional<Resident> findResidentById(UUID id);

    @InsertProvider(ResidentSqlProvider.class)
    void save(Resident resident);
}
