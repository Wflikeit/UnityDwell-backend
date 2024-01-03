package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.listResponses.OwnersOfFlatsResponse;
import UnityDwell.com.UnityDwell.dto.mapper.OwnerOfFlatDTOMapper;
import UnityDwell.com.UnityDwell.dto.response.OwnerOfFlatResponse;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Flat;
import UnityDwell.com.UnityDwell.model.users.OwnerOfFlat;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlatServiceTest {
    @Mock
    OwnerOfFlatRepository ownerOfFlatRepository;
    @Mock
    FlatRepository flatRepository;
    @Mock
    OwnerOfFlatDTOMapper ownerOfFlatDTOMapper;
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
}
