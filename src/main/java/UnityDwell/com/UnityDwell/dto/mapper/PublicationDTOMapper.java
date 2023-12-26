package UnityDwell.com.UnityDwell.dto.mapper;

import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdatePublicationRequest;
import UnityDwell.com.UnityDwell.dto.response.PublicationResponse;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.model.Publication;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

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

    public Publication map(CreateOrUpdatePublicationRequest publicationRequest, HousingAssociation housingAssociation) {

        return Publication.builder()
                .id(UUID.randomUUID())
                .title(publicationRequest.getTitle())
                .content(publicationRequest.getContent())
                .dateOfPublishing(LocalDate.now())
                .housingAssociation(housingAssociation)
                .build();
    }
}
