package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.BuildingResponse;
import UnityDwell.com.UnityDwell.dto.HousingAssociationResponse;
import UnityDwell.com.UnityDwell.dto.PublicationResponse;
import UnityDwell.com.UnityDwell.dto.listResponses.BuildingsResponse;
import UnityDwell.com.UnityDwell.dto.listResponses.PublicationsResponse;
import UnityDwell.com.UnityDwell.dto.mapper.BuildingDTOMapper;
import UnityDwell.com.UnityDwell.dto.mapper.HousingAssociationDTOMapper;
import UnityDwell.com.UnityDwell.dto.mapper.PublicationDTOMapper;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Building;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.model.Publication;
import UnityDwell.com.UnityDwell.repository.BuildingsRepository;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HousingAssociationServiceTest {
    @Mock
    HousingAssociationRepository housingAssociationRepository;
    @Mock
    HousingAssociationDTOMapper housingAssociationDTOMapper;
    @Mock
    PublicationRepository publicationRepository;
    @Mock
    BuildingsRepository buildingsRepository;
    @Mock
    PublicationDTOMapper publicationDTOMapper;
    @Mock
    BuildingDTOMapper buildingDTOMapper;
    @InjectMocks
    HousingAssociationService housingAssociationService;

    @Test
    public void testGetHousingAssociationById_WhenOneExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        HousingAssociation housingAssociation = HousingAssociation.builder().build();
        HousingAssociationResponse mappedAssociationResponse = HousingAssociationResponse.builder().build();
        when(housingAssociationRepository.findHousingAssociationById(id))
                .thenReturn(Optional.of(housingAssociation));
        when(housingAssociationDTOMapper.mapTo(housingAssociation)).thenReturn(mappedAssociationResponse);
        // Act & Assert
        assertEquals(mappedAssociationResponse, housingAssociationService.getHousingAssociationById(id));
    }

    @Test
    public void testGetHousingAssociationById_WhenDoesNotExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(housingAssociationRepository.findHousingAssociationById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> housingAssociationService
                .getHousingAssociationById(id));
    }

    @Test
    public void testGetPublicationsByHousingAssociationId_WhenHousingAssociationDoesNotExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(housingAssociationRepository.findHousingAssociationById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> housingAssociationService
                .getPublicationsByHousingAssociationId(id));
    }
    @Test
    public void testGetBuildings_WhenHousingAssociationDoesNotExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(housingAssociationRepository.findHousingAssociationById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> housingAssociationService
                .getBuildings(id));
    }

    @Test
    public void testGetPublicationsByHousingAssociationId_WhenOneExistsAndHousingAssociationExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        HousingAssociation housingAssociation = HousingAssociation.builder().build();
        Publication publication = Publication.builder().id(id).build();
        PublicationResponse publicationResponse = PublicationResponse.builder().id(id).build();
        List<PublicationResponse> expectedPublicationResponseList = List.of(publicationResponse);
        PublicationsResponse expectedPublicationsResponse = PublicationsResponse
                .builder().publications(expectedPublicationResponseList).build();

        when(housingAssociationRepository.findHousingAssociationById(id)).thenReturn(Optional.of(housingAssociation));
        when(publicationRepository.getAllPublicationsFromHousingAssociation(id)).thenReturn(List.of(publication));
        when(publicationDTOMapper.mapTo(publication)).thenReturn(publicationResponse);

        // Act
        PublicationsResponse actualPublicationsResponse = housingAssociationService
                .getPublicationsByHousingAssociationId(id);

        // Assert
        assertThat(actualPublicationsResponse)
                .usingRecursiveComparison()
                .isEqualTo(expectedPublicationsResponse);
    }
    @Test
    public void  testGetBuildings_WhenHousingAssociationExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        HousingAssociation housingAssociation = HousingAssociation.builder().build();
        Building building = Building.builder().id(id).build();
        BuildingResponse buildingResponse = BuildingResponse.builder().id(id).build();
        List<BuildingResponse> expectedPublicationResponseList = List.of(buildingResponse);
        BuildingsResponse expectedBuildingsResponse = BuildingsResponse
                .builder().buildings(expectedPublicationResponseList).build();

        when(housingAssociationRepository.findHousingAssociationById(id)).thenReturn(Optional.of(housingAssociation));
        when(buildingsRepository.getBuildingsInHousingAssociation(id)).thenReturn(List.of(building));
        when(buildingDTOMapper.mapToBuildingList(List.of(building))).thenReturn(expectedPublicationResponseList);

        // Act
        BuildingsResponse actualBuildingsResponse = housingAssociationService
                .getBuildings(id);

        // Assert
        assertThat(actualBuildingsResponse)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedBuildingsResponse);
    }


}
