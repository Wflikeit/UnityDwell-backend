package UnityDwell.com.UnityDwell.repository.sqlProvider;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class ResidentSqlProvider implements ProviderMethodResolver {
    public static String findResidentById(UUID residentId){
        return new SQL()
                .SELECT("NR_MIESZKANCA", "IMIE", "NAZWISKO", "ID_MIESZKANIA")
                .FROM("C##MACIEK.MIESZKANCY")
                .WHERE("NR_MIESZKANCA = #{residentId}")
                .toString();
    }
}
