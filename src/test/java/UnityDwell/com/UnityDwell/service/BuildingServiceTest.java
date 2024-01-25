package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.mapper.BuildingDTOMapper;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateBuildingRequest;
import UnityDwell.com.UnityDwell.dto.response.BuildingResponse;
import UnityDwell.com.UnityDwell.dto.response.FlatResponse;
import UnityDwell.com.UnityDwell.dto.listResponses.FlatsResponse;
import UnityDwell.com.UnityDwell.dto.mapper.FlatDTOMapper;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.model.Building;
import UnityDwell.com.UnityDwell.model.Flat;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.repository.AddressRepository;
import UnityDwell.com.UnityDwell.repository.BuildingsRepository;
import UnityDwell.com.UnityDwell.repository.FlatRepository;
import UnityDwell.com.UnityDwell.repository.HousingAssociationRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuildingServiceTest {
    @Mock
    FlatRepository flatRepository;
    @Mock
    BuildingsRepository buildingsRepository;
    @Mock
    FlatDTOMapper flatDTOMapper;
    @Mock
    BuildingDTOMapper buildingDTOMapper;
    @Mock
    AddressRepository addressRepository;
    @Mock
    HousingAssociationRepository housingAssociationRepository;
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

    @Test
    public void testAddNewBuilding_WhenOneNotExists() {
        // Arrange
        Building building = Building.builder().build();
        CreateOrUpdateBuildingRequest request = CreateOrUpdateBuildingRequest.builder()
                .addressId(UUID.randomUUID())
                .housingAssociationId(UUID.randomUUID())
                .build();
        HousingAssociation housingAssociation = HousingAssociation.builder().build();
        Address address = Address.builder().build();
        BuildingResponse expectedResponse = BuildingResponse.builder().build();

        when(housingAssociationRepository.findHousingAssociationById(request.getHousingAssociationId()))
                .thenReturn(Optional.of(housingAssociation));
        when(addressRepository.findAddressById(request.getAddressId()))
                .thenReturn(Optional.of(address));
        when(buildingDTOMapper.map(request, housingAssociation, address))
                .thenReturn(building);
        doNothing().when(buildingsRepository).save(building);
        when(buildingDTOMapper.mapTo(building)).thenReturn(expectedResponse);

        // Act & Assert
        BuildingResponse actualResponse = buildingService
                .addNewBuilding(request);
        assertThat(expectedResponse)
                .usingRecursiveComparison()
                .isEqualTo(actualResponse);
    }

    @Test
    public void testDeleteBuilding_WhenOneExist() {
        // Arrange
        UUID id = UUID.randomUUID();
        Building building = Building.builder().build();

        when(buildingsRepository.getBuildingById(id)).thenReturn(Optional.of(building));
        doNothing().when(buildingsRepository).delete(id);

        // Act
        buildingService.deleteBuilding(id);

        // Assert
        verify(buildingsRepository, times(1)).getBuildingById(id);
        verify(buildingsRepository, times(1)).delete(id);
    }
}
