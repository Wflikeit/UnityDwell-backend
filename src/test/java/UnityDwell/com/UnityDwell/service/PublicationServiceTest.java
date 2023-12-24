package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.mapper.PublicationDTOMapper;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdatePublicationRequest;
import UnityDwell.com.UnityDwell.dto.response.PublicationResponse;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.model.Publication;
import UnityDwell.com.UnityDwell.repository.HousingAssociationRepository;
import UnityDwell.com.UnityDwell.repository.PublicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PublicationServiceTest {
    @Mock
    PublicationRepository publicationRepository;
    @Mock
    HousingAssociationRepository housingAssociationRepository;
    @Mock
    PublicationDTOMapper publicationDTOMapper;
    @InjectMocks
    PublicationService publicationService;

    @Test
    public void testAddNewPublication_WhenHousingAssociationExists() {

        // Arrange
        UUID id = UUID.randomUUID();
        Publication publication = Publication.builder().build();
        CreateOrUpdatePublicationRequest publicationRequest = CreateOrUpdatePublicationRequest.builder().build();
        HousingAssociation housingAssociation = HousingAssociation.builder().build();
        PublicationResponse expectedPublicationResponse = PublicationResponse.builder().build();

        when(housingAssociationRepository.findHousingAssociationById(id)).thenReturn(Optional.of(housingAssociation));
        when(publicationDTOMapper.map(publicationRequest, housingAssociation)).thenReturn(publication);
        doNothing().when(publicationRepository).save(publication);
        when(publicationDTOMapper.mapTo(publication)).thenReturn(expectedPublicationResponse);

        // Act & Assert
        PublicationResponse actualPublicationResponse = publicationService.addNewPublication(publicationRequest,
                id);
        assertThat(expectedPublicationResponse)
                .usingRecursiveComparison()
                .isEqualTo(actualPublicationResponse);
    }

    @Test
    public void testAddNewPublication_WhenHousingAssociationDoesNotExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        CreateOrUpdatePublicationRequest publicationRequest = CreateOrUpdatePublicationRequest.builder().build();
        when(housingAssociationRepository.findHousingAssociationById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class,
                () -> publicationService.addNewPublication(publicationRequest, id));
    }

    @Test
    public void testDeletePublication_WhenOneExist() {
        // Arrange
        UUID id = UUID.randomUUID();
        Publication publication = Publication.builder().build();

        when(publicationRepository.findPublicationById(id)).thenReturn(Optional.of(publication));
        doNothing().when(publicationRepository).delete(id);

        // Act
        publicationService.deletePublication(id);

        // Assert
        verify(publicationRepository, times(1)).findPublicationById(id);
        verify(publicationRepository, times(1)).delete(id);

    }

    @Test
    public void testDeletePublication_WhenOneDoesNotExist() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(publicationRepository.findPublicationById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class,
                () -> publicationService.deletePublication(id));
    }

}
