package UnityDwell.com.UnityDwell.repository;

import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.repository.sqlProvider.HousingAssociationSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface HousingAssociationRepository {
    @SelectProvider(HousingAssociationSqlProvider.class)
    @Results(id = "HousingAssociationMap", value = {
            @Result(property = "id", column = "ID_SPOLDZIELNI"),
            @Result(property = "name", column = "NAZWA"),
            @Result(property = "dateOfEstablishment", column = "DATA_ZALOZENIA"),
            @Result(property = "nip", column = "NIP"),
            @Result(property = "address",
                    javaType = Address.class,
                    column = "ID_ADRESU",
                    one = @One(select = "UnityDwell.com.UnityDwell.repository." +
                            "AddressRepository.findAddressById")
            )
    })
    Optional<HousingAssociation> getHousingAssociation(UUID id);

}
