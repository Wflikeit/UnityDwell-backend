package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.BuildingResponse;
import UnityDwell.com.UnityDwell.dto.HousingAssociationResponse;
import UnityDwell.com.UnityDwell.dto.PublicationResponse;
import UnityDwell.com.UnityDwell.dto.listResponses.BuildingsResponse;
import UnityDwell.com.UnityDwell.dto.listResponses.PublicationsResponse;
import UnityDwell.com.UnityDwell.dto.mapper.BuildingDTOMapper;
import UnityDwell.com.UnityDwell.dto.mapper.FlatDTOMapper;
import UnityDwell.com.UnityDwell.dto.mapper.HousingAssociationDTOMapper;
import UnityDwell.com.UnityDwell.dto.mapper.PublicationDTOMapper;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Building;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.model.Publication;
import UnityDwell.com.UnityDwell.repository.BuildingsRepository;
import UnityDwell.com.UnityDwell.repository.FlatRepository;
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
public class BuildingServiceTest {
    @Mock
    FlatRepository flatRepository;
    @Mock
    FlatDTOMapper flatDTOMapper;
    @InjectMocks
    BuildingService buildingService;

    @Test
    public void testGetFlatsInBuilding_WhenOneExists() {
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
    public void testGetFlatsInBuilding_WhenDoesNotExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(housingAssociationRepository.findHousingAssociationById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> housingAssociationService
                .getHousingAssociationById(id));
    }

}
