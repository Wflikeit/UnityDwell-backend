package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.HousingAssociationResponse;
import UnityDwell.com.UnityDwell.dto.mapper.HousingAssociationDTOMapper;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.repository.HousingAssociationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HousingAssociationServiceTest {
    @Mock
    HousingAssociationRepository housingAssociationRepository;
    @Mock
    HousingAssociationDTOMapper housingAssociationDTOMapper;
    @InjectMocks
    HousingAssociationService housingAssociationService;
    @Test
    public void getApartmentById_WhenOneExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        HousingAssociation housingAssociation = HousingAssociation.builder().build();
        HousingAssociationResponse mappedAssociationResponse = HousingAssociationResponse.builder().build();
        when(housingAssociationRepository.findByIdHousingAssociation(id)).thenReturn(Optional.of(housingAssociation));
        when(housingAssociationDTOMapper.mapTo(housingAssociation)).thenReturn(mappedAssociationResponse);
        // Act & Assert
        assertSame(mappedAssociationResponse, housingAssociationService.getHousingAssociationById(id));
    }


}
