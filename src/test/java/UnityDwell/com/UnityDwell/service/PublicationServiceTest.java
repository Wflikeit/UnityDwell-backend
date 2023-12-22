package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.PublicationResponse;
import UnityDwell.com.UnityDwell.dto.listResponses.PublicationsResponse;
import UnityDwell.com.UnityDwell.dto.mapper.PublicationDTOMapper;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.model.Publication;
import UnityDwell.com.UnityDwell.repository.HousingAssociationRepository;
import UnityDwell.com.UnityDwell.repository.PublicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PublicationServiceTest {
    @Mock
    PublicationRepository publicationRepository;
    @Mock
    HousingAssociationRepository housingAssociationRepository;
    @Mock
    PublicationDTOMapper publicationDTOMapper;
    @InjectMocks
    PublicationsService publicationsService;

    @Test
    public void testGetPublicationsByHousingAssociationId_WhenOneExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        Publication publication = Publication.builder().id(id).build();
        HousingAssociation housingAssociation = HousingAssociation.builder().id(id).build();
        PublicationResponse publicationResponse = PublicationResponse.builder().id(id).build();
        List<PublicationResponse> expectedPublicationResponseList = List.of(publicationResponse);
        PublicationsResponse expectedPublicationsResponse = PublicationsResponse.builder().publications(expectedPublicationResponseList).build();

        when(publicationRepository.getAllPublicationsFromHousingAssociation(id)).thenReturn(List.of(publication));
        when(housingAssociationRepository.findByIdHousingAssociation(id)).thenReturn(Optional.of(housingAssociation));
        when(publicationDTOMapper.mapTo(publication)).thenReturn(publicationResponse);

        // Act
        PublicationsResponse actualPublicationsResponse = publicationsService.getPublicationsByHousingAssociationId(id);

        // Assert
        assertThat(actualPublicationsResponse)
                .usingRecursiveComparison()
                .isEqualTo(expectedPublicationsResponse);
    }


}
