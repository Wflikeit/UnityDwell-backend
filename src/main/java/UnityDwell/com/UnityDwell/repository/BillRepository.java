package UnityDwell.com.UnityDwell.repository;

import UnityDwell.com.UnityDwell.model.Bill;
import UnityDwell.com.UnityDwell.repository.sqlProvider.BillSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
@Repository
public interface BillRepository {
    @SelectProvider(BillSqlProvider.class)
    @Results(id = "billMap", value = {
            @Result(property = "id", column = "ID_RACHUNKU"),
            @Result(property = "amount", column = "KWOTA"),
            @Result(property = "dateOfPublishing", column = "DATA_WYSTAWIENIA"),
            @Result(property = "title", column = "TYTUL"),
    })
    List<Bill> getAllBills();

    @SelectProvider(BillSqlProvider.class)
    @ResultMap("billMap")
    List<Bill> getAllOwnersBills(UUID ownerId);

    @DeleteProvider(BillSqlProvider.class)
    void delete(UUID billId);

    @SelectProvider(BillSqlProvider.class)
    @ResultMap("billMap")
    Optional<Bill> findBillById(UUID billId);
}

