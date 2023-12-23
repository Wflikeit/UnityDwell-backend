package UnityDwell.com.UnityDwell.repository;

import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.repository.sqlProvider.AddressSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface AddressRepository {
    @SelectProvider(AddressSqlProvider.class)
    @Results(id = "AddressMap", value = {
            @Result(property = "id", column = "ID_ADRESU"),
            @Result(property = "city", column = "MIASTO"),
            @Result(property = "street", column = "ULICA"),
            @Result(property = "numberOfBuilding", column = "NR_BUDYNKU"),
            @Result(property = "postalCode", column = "KOD_POCZTOWY")
    })
    Optional<Address> findAddressById(UUID id);

    @InsertProvider(AddressSqlProvider.class)
    void save(Address address);
    @DeleteProvider(AddressSqlProvider.class)
    void delete(UUID addressId);
}
