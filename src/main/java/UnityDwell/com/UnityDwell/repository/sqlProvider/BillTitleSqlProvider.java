package UnityDwell.com.UnityDwell.repository.sqlProvider;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class BillTitleSqlProvider implements ProviderMethodResolver {
    public static String findBillTitleById(UUID billTitleId){
        return new SQL()
                .SELECT("ID_TYTULU_RACHUNKU", "TYTUL")
                .FROM("C##MACIEK.TYTULY_RACHUNKU")
                .WHERE("ID_TYTULU_RACHUNKU = #{billTitleId}")
                .toString();
    }
    public static String getAllBillTitles(UUID billTitleId){
        return new SQL()
                .SELECT("ID_TYTULU_RACHUNKU", "TYTUL")
                .FROM("C##MACIEK.TYTULY_RACHUNKU")
                .toString();
    }

}
