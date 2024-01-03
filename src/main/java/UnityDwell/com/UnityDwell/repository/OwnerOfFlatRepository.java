package UnityDwell.com.UnityDwell.repository;

import UnityDwell.com.UnityDwell.model.Flat;
import UnityDwell.com.UnityDwell.model.Resident;
import UnityDwell.com.UnityDwell.model.users.OwnerOfFlat;
import UnityDwell.com.UnityDwell.repository.sqlProvider.OwnerOfFlatSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface OwnerOfFlatRepository {
    @SelectProvider(OwnerOfFlatSqlProvider.class)
    @Results(id = "OwnerOfFlatMap", value = {
            @Result(property = "id", column = "NR_MIESZKANCA"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "nip", column = "NIP_FIRMY"),
            @Result(property = "pesel", column = "PESEL"),
            @Result(property = "phoneNumber", column = "NR_TELEFONU"),
            @Result(property = "resident",
                    javaType = Resident.class,
                    column = "NR_MIESZKANCA",
                    one = @One(select = "UnityDwell.com.UnityDwell.repository." +
                            "ResidentRepository.findResidentById")),
            @Result(property = "flats",
                    javaType = Flat.class,
                    column = "ID_MIESZKANIA",
                    many = @Many(select = "UnityDwell.com.UnityDwell.repository." +
                            "FlatRepository.findAllFlatsOfOwner"))
    })
    OwnerOfFlat findOwnerById(UUID flatOwnerId);


    @SelectProvider(OwnerOfFlatSqlProvider.class)
    @ResultMap("OwnerOfFlatMap")
    List<OwnerOfFlat> findAllOwnersOfFlat(UUID flatId);
    @SelectProvider(OwnerOfFlatSqlProvider.class)
    @ResultMap("OwnerOfFlatMap")
    Optional<OwnerOfFlat> findOwnerOfFlatByEmail(String email);

}
