package UnityDwell.com.UnityDwell.dto.mapper;

import UnityDwell.com.UnityDwell.dto.PublicationResponse;
import UnityDwell.com.UnityDwell.model.Publication;
import org.springframework.stereotype.Component;

@Component
public class PublicationDTOMapper {

    public PublicationResponse mapTo(Publication publication) {

        return PublicationResponse.builder()
                .id(publication.getId())
                .content(publication.getContent())
                .title(publication.getTitle())
                .dateOfPublishing(publication.getDateOfPublishing())
                .idOfHousingAssociation(publication.getHousingAssociation().getId())
                .build();
    }
}
