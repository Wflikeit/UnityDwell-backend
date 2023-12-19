package UnityDwell.com.UnityDwell.repository;

import UnityDwell.com.UnityDwell.model.Bill;
import UnityDwell.com.UnityDwell.model.Resident;
import UnityDwell.com.UnityDwell.repository.sqlProviders.BillSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Mapper
@Repository
public interface BillRepository {
    @SelectProvider(BillSqlProvider.class)
    @Results(id = "billMap", value = {
            @Result(property = "id",
                    column = "ID_RACHUNKU")})
    List<Bill> getAllBills();
//    @SelectProvider(BillSqlProvider.class)
//    List<Bill> getAllOwnersBills(UUID id);
}
//            @Result(property = "flatOwner",
//                    javaType = Resident.class,
//                    column = "id",
//                    one = @One(select = "com.sourceryacademy.apartmentbooking." +
//                            "repository.RoomRepository.findRoomsForApartment"))})
