package UnityDwell.com.UnityDwell.repository;

import UnityDwell.com.UnityDwell.model.Publication;
import UnityDwell.com.UnityDwell.repository.sqlProvider.PublicationSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface PublicationRepository {
    @SelectProvider(PublicationSqlProvider.class)
    @Results(id = "PublicationMapping", value = {
            @Result(property = "id", column = "ID_OGLOSZENIA"),
            @Result(property = "content", column = "TRESC"),
            @Result(property = "title", column = "TYTUL"),
            @Result(property = "dateOfPublishing", column = "DATA_WYDANIA"),
            @Result(property = "housingAssociation",
                    javaType = Publication.class,
                    column = "ID_SPOLDZIELNI",
                    one = @One(select = "UnityDwell.com.UnityDwell.repository." +
                            "HousingAssociationRepository.findHousingAssociationById")
            )
    })
    List<Publication> getAllPublicationsFromHousingAssociation(UUID houseAssociationId);

    @InsertProvider(PublicationSqlProvider.class)
    void save(Publication publication);

    @ResultMap("PublicationMapping")
    @SelectProvider(value = PublicationSqlProvider.class)
    Optional<Publication> findPublicationById(UUID publicationId);
    @DeleteProvider(value = PublicationSqlProvider.class)
    void delete(UUID publicationId);
}
