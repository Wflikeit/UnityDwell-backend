package UnityDwell.com.UnityDwell.repository.sqlProvider;

import UnityDwell.com.UnityDwell.model.Publication;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class PublicationSqlProvider implements ProviderMethodResolver {
    public static String getAllPublicationsFromHousingAssociation(UUID houseAssociationId) {
        return new SQL()
                .SELECT("o.ID_OGLOSZENIA", "o.DATA_WYDANIA", "o.TRESC", "o.TYTUL", "o.ID_SPOLDZIELNI")
                .FROM("C##MACIEK.OGLOSZENIA o")
                .WHERE("o.ID_SPOLDZIELNI = #{houseAssociationId}")
                .toString();
    }

    public static String save(Publication publication) {
        return new SQL()
                .INSERT_INTO("C##MACIEK.OGLOSZENIA")
                .VALUES("ID_OGLOSZENIA", "#{id}")
                .VALUES("DATA_WYDANIA", "#{dateOfPublishing}")
                .VALUES("TRESC", "#{content}")
                .VALUES("TYTUL", "#{title}")
                .VALUES("ID_SPOLDZIELNI", "#{housingAssociation.id}")
                .toString();
    }

    public static String findPublicationById(UUID publicationId) {
        return new SQL()
                .SELECT("o.ID_OGLOSZENIA", "o.DATA_WYDANIA", "o.TRESC", "o.TYTUL", "o.ID_SPOLDZIELNI")
                .FROM("C##MACIEK.OGLOSZENIA o")
                .WHERE("o.ID_OGLOSZENIA = #{publicationId}")
                .toString();
    }

    public static String delete(UUID publicationId) {
        return new SQL()
                .DELETE_FROM("C##MACIEK.OGLOSZENIA")
                .WHERE("ID_OGLOSZENIA = #{publicationId}")
                .toString();
    }
}