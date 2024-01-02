package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.listResponses.OwnersOfFlatsResponse;
import UnityDwell.com.UnityDwell.dto.mapper.FlatDTOMapper;
import UnityDwell.com.UnityDwell.dto.mapper.OwnerOfFlatDTOMapper;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateFlatRequest;
import UnityDwell.com.UnityDwell.dto.response.FlatResponse;
import UnityDwell.com.UnityDwell.dto.response.OwnerOfFlatResponse;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.model.Building;
import UnityDwell.com.UnityDwell.model.Flat;
import UnityDwell.com.UnityDwell.model.OwnerOfFlat;
import UnityDwell.com.UnityDwell.repository.AddressRepository;
import UnityDwell.com.UnityDwell.repository.BuildingsRepository;
import UnityDwell.com.UnityDwell.repository.FlatRepository;
import UnityDwell.com.UnityDwell.repository.OwnerOfFlatRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FlatServiceTest {
    @Mock
    OwnerOfFlatRepository ownerOfFlatRepository;
    @Mock
    FlatRepository flatRepository;
    @Mock
    FlatDTOMapper flatDTOMapper;
    @Mock
    OwnerOfFlatDTOMapper ownerOfFlatDTOMapper;
    @Mock
    AddressRepository addressRepository;
    @Mock
    BuildingsRepository buildingsRepository;
    @InjectMocks
    FlatService flatService;

    @Test
    public void getAllOwnersOfAFlat_WhenOneExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        OwnerOfFlat owner = OwnerOfFlat.builder().build();
        Flat flat = Flat.builder().build();
        OwnerOfFlatResponse ownerOfFlatResponse = OwnerOfFlatResponse.builder().build();
        OwnersOfFlatsResponse mappedOwnersResponse = OwnersOfFlatsResponse.builder()
                .owners(List.of(ownerOfFlatResponse)).build();
        when(flatRepository.findFlatById(id)).thenReturn(Optional.of(flat));
        when(ownerOfFlatRepository.findAllOwnersOfFlat(id)).thenReturn(List.of(owner));
        when(ownerOfFlatDTOMapper.mapToOwnersOfFlatsList(List.of(owner))).thenReturn(List.of(ownerOfFlatResponse));
        // Act & Assert
        assertThat(mappedOwnersResponse).usingRecursiveComparison()
                .isEqualTo(flatService.getAllOwnersOfAFlat(id));
    }

    @Test
    public void getAllOwnersOfAFlat_WhenOneNotExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(flatRepository.findFlatById(id)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> flatService
                .getAllOwnersOfAFlat(id));
    }

    @Test
    public void addNewFlat_WhenOneNotExists() {
        // Arrange
        CreateOrUpdateFlatRequest request = CreateOrUpdateFlatRequest.builder().build();
        Flat flat = Flat.builder().build();
        FlatResponse expectedResponse = FlatResponse.builder().build();
        Address address = Address.builder().build();
        Building building = Building.builder().build();
        UUID addressId = UUID.randomUUID();
        UUID buildingId = UUID.randomUUID();

        when(buildingsRepository.getBuildingById(buildingId)).thenReturn(Optional.of(building));
        when(addressRepository.findAddressById(addressId)).thenReturn(Optional.of(address));
        when(flatDTOMapper.map(request, building, address)).thenReturn(flat);
        doNothing().when(flatRepository).save(flat);
        when(flatDTOMapper.mapTo(flat)).thenReturn(expectedResponse);

        // Act
        FlatResponse actualResponse = flatService.addNewFlat(request, buildingId, addressId);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(flatRepository, times(1)).save(flat);
    }

    @Test
    public void deleteFlat_WhenOneNotExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(flatRepository.findFlatById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class,
                () -> flatService.deleteFlat(id));
    }

    @Test
    public void deleteFlat_WhenOneExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        Flat flat = Flat.builder().build();

        when(flatRepository.findFlatById(id)).thenReturn(Optional.of(flat));
        doNothing().when(flatRepository).delete(id);
        // Act
        flatService.deleteFlat(id);

        // Assert
        verify(flatRepository, times(1)).findFlatById(id);
        verify(flatRepository, times(1)).delete(id);
    }
}
