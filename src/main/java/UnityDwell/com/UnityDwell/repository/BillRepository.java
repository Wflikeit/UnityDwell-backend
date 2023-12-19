package UnityDwell.com.UnityDwell.repository;

import UnityDwell.com.UnityDwell.model.Bill;
import UnityDwell.com.UnityDwell.repository.sqlProviders.BillSqlProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BillRepository {
    @SelectProvider(BillSqlProvider.class)
    @Results(id = "billMap", value = {
            @Result(property = "id", column = "ID_RACHUNKU"),
            @Result(property = "amount", column = "KWOTA"),
            @Result(property = "dateOfPublishing", column = "DATA_WYSTAWIENIA"),})
//            @Result(property = "amount", column = "KWOTA"),})
    List<Bill> getAllBills();
//    @SelectProvider(BillSqlProvider.class)
//    List<Bill> getAllOwnersBills(UUID id);
}
//            @Result(property = "flatOwner",
//                    javaType = Resident.class,
//                    column = "id",
//                    one = @One(select = "com.sourceryacademy.apartmentbooking." +
//                            "repository.RoomRepository.findRoomsForApartment"))})
