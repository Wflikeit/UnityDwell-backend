package UnityDwell.com.UnityDwell.repository;

import UnityDwell.com.UnityDwell.model.Bill;
import UnityDwell.com.UnityDwell.model.BillTitle;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.model.OwnerOfFlat;
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
    @ResultMap("billMap")
    List<Bill> getAllOwnersBills(UUID ownerId);

    @SelectProvider(BillSqlProvider.class)
    @ResultMap("billMap")
    List<Bill> getAllHousingAssociationBills(UUID housingAssociationId);

    @DeleteProvider(BillSqlProvider.class)
    void delete(UUID billId);

    @SelectProvider(BillSqlProvider.class)
    @Results(id = "billMap", value = {
            @Result(property = "id", column = "ID_RACHUNKU"),
            @Result(property = "amount", column = "KWOTA"),
            @Result(property = "dateOfPublishing", column = "DATA_WYSTAWIENIA"),
            @Result(property = "title", column = "TYTUL"),
            @Result(property = "housingAssociation",
                    javaType = HousingAssociation.class,
                    column = "ID_SPOLDZIELNI",
                    one = @One(select = "UnityDwell.com.UnityDwell.repository." +
                            "HousingAssociationRepository.findHousingAssociationById")
            ),
            @Result(property = "flatOwner",
                    javaType = OwnerOfFlat.class,
                    column = "NR_MIESZKANCA",
                    one = @One(select = "UnityDwell.com.UnityDwell.repository." +
                            "OwnerOfFlatRepository.findOwnerOfFlatById")),
            @Result(property = "billTitle",
                    javaType = BillTitle.class,
                    column = "ID_TYTULU_RACHUNKU",
                    one = @One(select = "UnityDwell.com.UnityDwell.repository." +
                            "BillTitleRepository.findBillTitleById"))
    })
    Optional<Bill> findBillById(UUID billId);

    @InsertProvider(BillSqlProvider.class)
    void save(Bill bill);

    @UpdateProvider(BillSqlProvider.class)
    void update(Bill bill);
}

