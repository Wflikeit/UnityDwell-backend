package UnityDwell.com.UnityDwell.repository;

import UnityDwell.com.UnityDwell.model.Bill;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.model.Resident;
import UnityDwell.com.UnityDwell.repository.sqlProvider.BillSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
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
            @Result(property = "housingAssociation",
                javaType = HousingAssociation.class,
                column = "ID_SPOLDZIELNI",
                one = @One(select = "UnityDwell.com.UnityDwell.repository." +
                        "HousingAssociationRepository.findHousingAssociationById")),
            @Result(property = "flatOwner",
                    javaType = Resident.class,
                    column = "NR_MIESZKANCA",
                    one = @One(select = "UnityDwell.com.UnityDwell.repository." +
                            "OwnerOfFlatRepository.findOwnerById"))})
    List<Bill> getAllBills();
    @SelectProvider(BillSqlProvider.class)
    @ResultMap("billMap")
    List<Bill> getAllOwnersBills(UUID id);
}

