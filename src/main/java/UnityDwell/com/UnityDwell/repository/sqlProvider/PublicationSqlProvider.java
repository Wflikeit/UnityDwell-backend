package UnityDwell.com.UnityDwell.repository.sqlProvider;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class PublicationSqlProvider implements ProviderMethodResolver {
    public static String getAllPublicationsFromHousingAssociation(UUID id) {
        return new SQL()
                .SELECT("o.ID_OGLOSZENIA", "o.DATA_WYDANIA", "o.TRESC", "o.TYTUL", "o.ID_SPOLDZIELNI")
                .FROM("C##MACIEK.OGLOSZENIA o")
                .WHERE("o.ID_SPOLDZIELNI = '" + id + "'")
                .toString();
    }
}