package UnityDwell.com.UnityDwell.repository;

import UnityDwell.com.UnityDwell.model.BillTitle;
import UnityDwell.com.UnityDwell.repository.sqlProvider.BillTitleSqlProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface BillTitleRepository {
    @SelectProvider(BillTitleSqlProvider.class)
    @Results(id = "billTitleMap", value = {
            @Result(property = "id", column = "ID_TYTULU_RACHUNKU"),
            @Result(property = "title", column = "TYTUL")
    })
    Optional<BillTitle> findBillTitleById(UUID billTitleId);
}
