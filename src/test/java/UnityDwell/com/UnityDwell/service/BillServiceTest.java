package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.listResponses.BillsResponse;
import UnityDwell.com.UnityDwell.dto.mapper.BillDTOMapper;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateBillRequest;
import UnityDwell.com.UnityDwell.dto.response.BillResponse;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Bill;
import UnityDwell.com.UnityDwell.model.BillTitle;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.model.OwnerOfFlat;
import UnityDwell.com.UnityDwell.repository.BillRepository;
import UnityDwell.com.UnityDwell.repository.BillTitleRepository;
import UnityDwell.com.UnityDwell.repository.HousingAssociationRepository;
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
public class BillServiceTest {
    @Mock
    BillRepository billRepository;
    @Mock
    OwnerOfFlatRepository ownerOfFlatRepository;
    @Mock
    HousingAssociationRepository housingAssociationRepository;
    @Mock
    BillTitleRepository billTitleRepository;
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
        when(ownerOfFlatRepository.findOwnerOfFlatById(id))
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
        when(ownerOfFlatRepository.findOwnerOfFlatById(id)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> billService
                .getAllBillsOfOwner(id));
    }

    @Test
    public void deleteBill_WhenOneExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        Bill bill = Bill.builder().build();
        when(billRepository.findBillById(id)).thenReturn(Optional.of(bill));
        doNothing().when(billRepository).delete(id);
        // Act
        billService.deleteBill(id);
        // Assert
        verify(billRepository, times(1)).findBillById(id);
        verify(billRepository, times(1)).delete(id);
    }

    @Test
    public void deleteBill_WhenOneNotExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(billRepository.findBillById(id)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(ResourceNotFoundException.class,
                () -> billService.deleteBill(id));
    }

    @Test
    public void addNewAddress_WhenOneNotExists() {
        // Arrange
        CreateOrUpdateBillRequest request = CreateOrUpdateBillRequest.builder().build();
        Bill bill = Bill.builder().build();
        BillResponse expectedResponse = BillResponse.builder().build();
        HousingAssociation housingAssociation = HousingAssociation.builder().id(UUID.randomUUID()).build();
        OwnerOfFlat owner = OwnerOfFlat.builder().id(UUID.randomUUID()).build();
        BillTitle billTitle = BillTitle.builder().id(UUID.randomUUID()).title("title").build();
        request.setBillTitleId(billTitle.getId());
        request.setFlatOwnerId(owner.getId());
        request.setHousingAssociationId(housingAssociation.getId());

        when(housingAssociationRepository.findHousingAssociationById(housingAssociation.getId())).thenReturn(Optional.of(housingAssociation));
        when(billTitleRepository.findBillTitleById(billTitle.getId())).thenReturn(Optional.of(billTitle));
        when(ownerOfFlatRepository.findOwnerOfFlatById(owner.getId())).thenReturn(Optional.of(owner));
        when(billDTOMapper.map(request, billTitle, housingAssociation, owner)).thenReturn(bill);
        doNothing().when(billRepository).save(bill);
        when(billDTOMapper.mapTo(bill)).thenReturn(expectedResponse);
        // Act
        BillResponse actualResponse = billService.addBill(request);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);

        verify(billRepository, times(1)).save(bill);
    }
}
