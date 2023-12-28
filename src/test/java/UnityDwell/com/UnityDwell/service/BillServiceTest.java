package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.listResponses.BillsResponse;
import UnityDwell.com.UnityDwell.dto.mapper.BillDTOMapper;
import UnityDwell.com.UnityDwell.dto.response.BillResponse;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Bill;
import UnityDwell.com.UnityDwell.model.OwnerOfFlat;
import UnityDwell.com.UnityDwell.repository.BillRepository;
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
public class BillServiceTest {
    @Mock
    BillRepository billRepository;
    @Mock
    OwnerOfFlatRepository ownerOfFlatRepository;
    @Mock
    BillDTOMapper billDTOMapper;
    @InjectMocks
    BillService billService;
    @Test
    public void getAllBillsOfOwner_WhenOneExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        Bill bill = Bill.builder().build();
        OwnerOfFlat owner = OwnerOfFlat.builder().build();
        BillResponse billResponse = BillResponse.builder().build();
        BillsResponse mappedBillsResponse = BillsResponse.builder().bills(List.of(billResponse)).build();
        when(ownerOfFlatRepository.findOwnerById(id))
                .thenReturn(Optional.of(owner));
        when(billRepository.getAllOwnersBills(id))
                .thenReturn(List.of(bill));
        when(billDTOMapper.mapToBillList(List.of(bill))).thenReturn(List.of(billResponse));
        // Act & Assert
        assertThat(mappedBillsResponse)
                .usingRecursiveComparison()
                .isEqualTo(billService.getAllBillsOfOwner(id));
    }
    @Test
    public void getAllBillsOfOwner_WhenOneNotExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(ownerOfFlatRepository.findOwnerById(id)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> billService
                .getAllBillsOfOwner(id));
    }
}
