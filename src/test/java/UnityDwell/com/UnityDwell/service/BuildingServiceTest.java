package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.FlatResponse;
import UnityDwell.com.UnityDwell.dto.listResponses.FlatsResponse;
import UnityDwell.com.UnityDwell.dto.mapper.FlatDTOMapper;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Building;
import UnityDwell.com.UnityDwell.model.Flat;
import UnityDwell.com.UnityDwell.repository.BuildingsRepository;
import UnityDwell.com.UnityDwell.repository.FlatRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuildingServiceTest {
    @Mock
    FlatRepository flatRepository;
    @Mock
    BuildingsRepository buildingsRepository;
    @Mock
    FlatDTOMapper flatDTOMapper;
    @InjectMocks
    BuildingService buildingService;

    @Test
    public void testGetFlatsInBuilding_WhenOneExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        Flat flat = Flat.builder().build();
        Building building = Building.builder().build();
        FlatResponse flatResponse = FlatResponse.builder().build();
        FlatsResponse mappedflatsResponse = FlatsResponse.builder().flats(List.of(flatResponse)).build();
        when(buildingsRepository.getBuildingById(id))
                .thenReturn(Optional.of(building));
        when(flatRepository.getAllFlatsInBuilding(id))
                .thenReturn(List.of(flat));
        when(flatDTOMapper.mapToFlatsList(List.of(flat))).thenReturn(List.of(flatResponse));
        // Act & Assert
        FlatsResponse actualFlatsResponse = buildingService.getFlatsInBuilding(id);
        assertThat(mappedflatsResponse)
                .usingRecursiveComparison()
                .isEqualTo(actualFlatsResponse);
    }

    @Test
    public void testGetFlatsInBuilding_WhenDoesNotExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(buildingsRepository.getBuildingById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> buildingService
                .getFlatsInBuilding(id));
    }

}
